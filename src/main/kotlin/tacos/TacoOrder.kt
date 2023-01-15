package tacos

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
class TacoOrder : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var placedAt: LocalDateTime = LocalDateTime.now()

    @NotBlank(message = "Delivery name is required")
    var deliveryName: String = ""
    @NotBlank(message = "Street is required")
    var deliveryStreet: String = ""
    @NotBlank(message = "City is required")
    var deliveryCity: String = ""
    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State length must be 2")
    var deliveryState: String = ""
    @NotBlank(message = "Zip code is required")
    var deliveryZip: String = ""

    //@CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String = ""
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
        message = "Must be formatted MM/YY")
    var ccExpiration: String = ""
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String = ""
    @OneToMany(cascade = [CascadeType.ALL])
    val tacos: MutableList<Taco> = mutableListOf()

    fun addTaco(taco: Taco) {
        tacos.add(taco)
    }
}