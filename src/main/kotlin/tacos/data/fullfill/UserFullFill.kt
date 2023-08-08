package tacos.data.fullfill

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tacos.Ingredient
import tacos.User
import tacos.data.UserRepository

@Configuration
class UserFullFill {

    @Autowired
    lateinit var userRepository: UserRepository

    @Bean
    fun userRepositoryFullFill(): CommandLineRunner {
        return CommandLineRunner {
            userRepository.apply {
                save(User("htG", "\$2a\$10\$As4Hq3nL2eo/XKQyPeI3euTgnfSliQnyMjchnWAX7Rpf.gVJ1Rw6S"))
            }
        }
    }
}