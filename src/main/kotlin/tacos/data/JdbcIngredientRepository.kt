package tacos.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import tacos.Ingredient
import java.sql.ResultSet
import java.util.*

@Repository
class JdbcIngredientRepository : IngredientRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun findAll(): Iterable<Ingredient> {
        return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            this::mapRowToIngredient
        )
    }

    override fun findById(id: String): Optional<Ingredient> {
        val results: List<Ingredient> = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            this::mapRowToIngredient,
            id
        )
        return if (results.isEmpty()) {
            Optional.empty()
        } else {
            Optional.of(results[0])
        }
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.id,
            ingredient.name,
            ingredient.type.toString()
        )
        return ingredient
    }

    private fun mapRowToIngredient(row: ResultSet, rowNum: Int): Ingredient {
        return Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type"))
        )
    }
}