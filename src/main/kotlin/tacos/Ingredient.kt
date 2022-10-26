package tacos

class Ingredient() {
    lateinit var id: String
    lateinit var name: String
    lateinit var type: Type

    constructor(id: String, name: String, type: Type) : this() {
        this.id = id
        this.name = name
        this.type = type
    }

    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE,
    }
}