package tacos

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Size

class Taco {
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<Ingredient>? = null
}