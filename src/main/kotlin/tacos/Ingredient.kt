package tacos

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Ingredient(
    @Id
    val id: String,
    val name: String,
    val type: Type,
) {

    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE,
    }
}