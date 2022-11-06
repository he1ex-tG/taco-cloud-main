package tacos.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import tacos.Ingredient
import tacos.Taco
import tacos.TacoOrder
import tacos.data.IngredientRepository
import java.util.stream.Collectors
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController {

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients: Iterable<Ingredient> = ingredientRepository.findAll()
        val types = Ingredient.Type.values()
        for (type in types) {
            model.addAttribute(type.toString().lowercase(), filterByType(ingredients, type))
        }
    }

    @ModelAttribute(name = "taco")
    fun taco(): Taco {
        return Taco()
    }

    @ModelAttribute(name = "tacoOrder")
    fun tacoOrder(): TacoOrder {
        return TacoOrder()
    }

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    private fun filterByType(ingredients: Iterable<Ingredient>, type: Ingredient.Type): Iterable<Ingredient> {
        return ingredients.toList()
            .stream()
            .filter { it.type == type }
            .collect(Collectors.toList())
    }

    @PostMapping
    fun processTaco(
        @Valid taco: Taco,
        errors: Errors,
        @ModelAttribute tacoOrder: TacoOrder
    ): String {

        if (errors.hasErrors()) {
            return "design"
        }

        tacoOrder.addTaco(taco)
        log.info("Processing taco: $taco")
        return "redirect:/orders/current"
    }
}