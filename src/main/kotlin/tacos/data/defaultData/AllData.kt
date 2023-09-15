package tacos.data.defaultData

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tacos.data.entity.Ingredient
import tacos.data.entity.Taco
import tacos.data.entity.TacoOrder
import tacos.data.repository.OrderRepository

@Configuration
class AllData(
    private val orderRepository: OrderRepository
) {

    @Bean
    fun allDataDefaults(): CommandLineRunner {
        return CommandLineRunner {
            val mainUser = "user"

            val taco1 = Taco().apply {
                name = "Taco1"
                ingredients = listOf(
                    Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                    Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
                )
            }
            val taco2 = Taco().apply {
                name = "Taco2"
                ingredients = listOf(
                    Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                    Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
                )
            }
            val taco3 = Taco().apply {
                name = "Taco3"
                ingredients = listOf(
                    Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                    Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
                )
            }
            val taco4 = Taco().apply {
                name = "Taco4"
                ingredients = listOf(
                    Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                    Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
                )
            }
            val taco5 = Taco().apply {
                name = "Taco5"
                ingredients = listOf(
                    Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                    Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES)
                )
            }

            val order1 = TacoOrder().apply {
                deliveryName = "Order1"

                deliveryState = "111"
                deliveryStreet = "111"
                deliveryCity = "111"
                deliveryState = "11"
                deliveryZip = "111"

                ccExpiration = "12/26"
                ccCVV = "111"

                tacos = mutableListOf(taco1)

                user = mainUser
            }
            val order2 = TacoOrder().apply {
                deliveryName = "Order2"

                deliveryState = "222"
                deliveryStreet = "222"
                deliveryCity = "222"
                deliveryState = "22"
                deliveryZip = "222"

                ccExpiration = "12/26"
                ccCVV = "222"

                tacos = mutableListOf(taco2, taco3)

                user = mainUser
            }
            val order3 = TacoOrder().apply {
                deliveryName = "Order3"

                deliveryState = "333"
                deliveryStreet = "333"
                deliveryCity = "333"
                deliveryState = "33"
                deliveryZip = "333"

                ccExpiration = "12/26"
                ccCVV = "333"

                tacos = mutableListOf(taco4, taco5)

                user = mainUser
            }
            orderRepository.save(order1)
            orderRepository.save(order2)
            orderRepository.save(order3)
        }
    }
}