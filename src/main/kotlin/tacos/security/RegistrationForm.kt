package tacos.security

import org.springframework.security.crypto.password.PasswordEncoder
import tacos.User

data class RegistrationForm(
    val username: String,
    val password: String,
    val fullname: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val phoneNumber: String
) {

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username, passwordEncoder.encode(password), fullname, street, city, state, zip, phoneNumber
        )
    }
}
