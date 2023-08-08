package tacos.data.fullfill

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tacos.Ingredient
import tacos.Taco
import tacos.TacoOrder
import tacos.User
import tacos.data.OrderRepository
import tacos.data.UserRepository

@Configuration
class OrderFullFill {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Bean
    fun orderRepositoryFullFill(): CommandLineRunner {
        return CommandLineRunner {
            orderRepository.apply {
                val tacoOrder = TacoOrder().apply {
                    deliveryName = "Name"

                    deliveryState = "123"
                    deliveryStreet = "123"
                    deliveryCity = "123"
                    deliveryState = "12"
                    deliveryZip = "123"

                    ccExpiration = "12/26"
                    ccCVV = "123"

                    tacos = mutableListOf(
                        Taco().apply {
                            name = "Taco1"

                            ingredients = listOf(
                                Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                                Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                                Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE)
                            )
                        }
                    )
                }
                save(tacoOrder)
            }
        }
    }
}