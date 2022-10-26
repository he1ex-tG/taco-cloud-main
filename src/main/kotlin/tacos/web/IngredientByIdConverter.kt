package tacos.web

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tacos.Ingredient

@Component
class IngredientByIdConverter : Converter<String, Ingredient> {

    private val ingredientMap: MutableMap<String, Ingredient> = hashMapOf()

    init {
        ingredientMap.apply {
            put("FLTO", Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
            put("COTO", Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
            put("GRBF", Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
            put("CARN", Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
            put("TMTO", Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
            put("LETC", Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
            put("CHED", Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
            put("JACK", Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
            put("SLSA", Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
            put("SRCR", Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
        }
    }

    override fun convert(source: String): Ingredient? {
        return ingredientMap[source]
    }
}