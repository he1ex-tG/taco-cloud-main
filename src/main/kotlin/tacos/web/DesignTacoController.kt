package tacos.web

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import tacos.Ingredient
import tacos.Taco
import tacos.TacoOrder
import java.util.stream.Collectors

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController {

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients: List<Ingredient> = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE),
        )

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

    private fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): Iterable<Ingredient> {
        return ingredients
            .stream()
            .filter { it.type == type }
            .collect(Collectors.toList())
    }

    @PostMapping
    fun processTaco(taco: Taco, @ModelAttribute tacoOrder: TacoOrder): String {
        tacoOrder.addTaco(taco)
        // log
        return "redirect:/orders/current"
    }
}