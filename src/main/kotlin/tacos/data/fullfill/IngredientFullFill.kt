package tacos.data.fullfill

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tacos.Ingredient
import tacos.data.IngredientRepository

@Configuration
class IngredientFullFill {

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    @Bean
    fun ingredientRepositoryFullFill(): CommandLineRunner {
        return CommandLineRunner {
            ingredientRepository.apply {
                save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
                save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
                save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
                save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
                save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
                save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
                save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
                save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
                save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
                save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
            }
        }
    }
}