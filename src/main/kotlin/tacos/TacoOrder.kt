package tacos

class TacoOrder {
    lateinit var deliveryName: String
    lateinit var deliveryStreet: String
    lateinit var deliveryCity: String
    lateinit var deliveryState: String
    lateinit var deliveryZip: String

    lateinit var ccNumber: String
    lateinit var ccExpiration: String
    lateinit var ccCVV: String

    private val tacos: MutableList<Taco> = mutableListOf()

    fun addTaco(taco: Taco) {
        tacos.add(taco)
    }
}