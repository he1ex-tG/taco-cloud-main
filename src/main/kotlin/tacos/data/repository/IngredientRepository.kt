package tacos.data.repository

import org.springframework.data.repository.CrudRepository
import tacos.data.entity.Ingredient

interface IngredientRepository : CrudRepository<Ingredient, String>