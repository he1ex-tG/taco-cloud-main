package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.User
import java.util.Optional

interface UserRepository : CrudRepository<User, String> {

    fun findUserByUusername(uusername: String): Optional<User>
}