package tacos.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tacos.data.entity.Ingredient
import tacos.data.repository.IngredientRepository

@RestController
@RequestMapping(path = ["/api/ingredients"], produces = ["application/json"])
@CrossOrigin(origins = ["http://tacocloud:8080"])
class IngredientsRestController(
    private val ingredientRepository: IngredientRepository
) {

    @GetMapping
    fun listIngredients(): Iterable<Ingredient> {
        return ingredientRepository.findAll()
    }

    @PostMapping
    fun addIngredient(@RequestBody ingredient: Ingredient): ResponseEntity<Ingredient> {
        return ResponseEntity(ingredientRepository.save(ingredient), HttpStatus.CREATED)
    }

    @DeleteMapping(path = ["/{id}"])
    fun removeIngredient(@PathVariable("id") id: String): ResponseEntity<Unit> {
        return ResponseEntity(ingredientRepository.deleteById(id), HttpStatus.NO_CONTENT)
    }
}