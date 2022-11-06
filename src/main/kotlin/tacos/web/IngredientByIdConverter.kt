package tacos.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tacos.Ingredient
import tacos.data.IngredientRepository

@Component
class IngredientByIdConverter : Converter<String, Ingredient> {

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    override fun convert(source: String): Ingredient? {
        return ingredientRepository.findById(source).orElse(null)
    }
}