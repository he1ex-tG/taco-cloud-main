package tacos

import org.springframework.data.relational.core.mapping.Table

@Table
class IngredientRef(
    val ingredient: String
)