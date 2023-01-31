package tacos

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.validation.constraints.Size

@Entity
class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var createdAt: LocalDateTime = LocalDateTime.now()
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null
    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    var ingredients: MutableList<Ingredient>? = null
}