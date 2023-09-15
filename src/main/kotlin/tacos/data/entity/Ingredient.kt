package tacos.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Ingredient(
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