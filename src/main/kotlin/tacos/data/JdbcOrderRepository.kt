package tacos.data

import org.springframework.asm.Type
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import tacos.Ingredient
import tacos.IngredientRef
import tacos.Taco
import tacos.TacoOrder
import java.sql.Types
import java.time.LocalDateTime
import java.util.*

@Repository
class JdbcOrderRepository : OrderRepository {

    @Autowired
    lateinit var jdbcOperations: JdbcOperations

    @Transactional
    override fun save(order: TacoOrder): TacoOrder {
        val pscf: PreparedStatementCreatorFactory = PreparedStatementCreatorFactory(
            "insert into Taco_Order "
            + "(delivery_name, delivery_street, delivery_city, "
            + "delivery_state, delivery_zip, cc_number, "
            + "cc_expiration, cc_cvv, placed_at) "
            + "values (?,?,?,?,?,?,?,?,?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        )
        pscf.setReturnGeneratedKeys(true)

        order.placedAt = LocalDateTime.now()
        val psc: PreparedStatementCreator = pscf.newPreparedStatementCreator(
            listOf(
                order.deliveryName,
                order.deliveryStreet,
                order.deliveryCity,
                order.deliveryState,
                order.deliveryZip,
                order.ccNumber,
                order.ccExpiration,
                order.ccCVV,
                order.placedAt
            )
        )

        val keyHolder: GeneratedKeyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)

        val orderId: Long? = keyHolder.key?.toLong()
        order.id = orderId

        val tacos: List<Taco> = order.tacos
        var i = 0
        for (taco in tacos) {
            saveTaco(orderId, i++, taco)
        }

        return order
    }

    private fun saveTaco(orderId: Long?, orderKey: Int, taco: Taco): Long? {
        val pscf = PreparedStatementCreatorFactory(
            "insert into Taco "
            + "(name, created_at, taco_order, taco_order_key) "
            + "values (?, ?, ?, ?)",
            Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        )
        pscf.setReturnGeneratedKeys(true)

        taco.createdAt = LocalDateTime.now()
        val psc: PreparedStatementCreator = pscf.newPreparedStatementCreator(
            listOf(
                taco.name,
                taco.createdAt,
                orderId,
                orderKey
            )
        )

        val keyHolder: GeneratedKeyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)

        val tacoId: Long? = keyHolder.key?.toLong()
        taco.id = tacoId

        val ingredientRefs: List<IngredientRef>? = taco.ingredients?.map { IngredientRef(it.id) }
        saveIngredientRefs(tacoId, ingredientRefs)

        return tacoId
    }

    private fun saveIngredientRefs(tacoId: Long?, ingredientRefs: List<IngredientRef>?) {
        if (ingredientRefs.isNullOrEmpty()) {
            return
        }
        var key = 0
        for (ingredientRef in ingredientRefs) {
            jdbcOperations.update(
                "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                + "values (?, ?, ?)",
                ingredientRef.ingredient, tacoId, key++
            )
        }
    }
}