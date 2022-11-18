package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.Ingredient
import java.util.Optional

interface IngredientRepository : CrudRepository<Ingredient, String>