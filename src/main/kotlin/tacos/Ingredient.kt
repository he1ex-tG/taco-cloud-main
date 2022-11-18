package tacos

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table
class Ingredient : Persistable<String> {

    @Id
    private var id: String? = null
    var name: String? = null
    var type: Type? = null

    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE,
    }

    override fun getId(): String? {
        return id
    }

    override fun isNew(): Boolean {
        return id == null
    }
}