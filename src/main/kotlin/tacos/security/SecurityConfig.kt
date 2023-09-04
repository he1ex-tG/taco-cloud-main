package tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import tacos.data.UserRepository

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                //authorize("/design/**", hasRole("USER"))
                authorize("/design/**", hasAuthority("SCOPE_tacoMain"))
                //authorize("/orders/**", hasRole("USER"))
                authorize("/orders/**", hasAuthority("SCOPE_tacoMain"))
                authorize(HttpMethod.POST, "/api/ingredients/**", hasAuthority("SCOPE_writeIngredients"))
                authorize(HttpMethod.DELETE, "/api/ingredients/**", hasAuthority("SCOPE_deleteIngredients"))
                authorize("/", permitAll)
                authorize("/**", permitAll)
            }
            formLogin {
                loginPage = "/login"
                defaultSuccessUrl("/design", true)
            }
            logout {
                logoutSuccessUrl = "/login"
            }
            oauth2Login {
                loginPage = "/login"
            }

        }
        return http.build()
    }

    @Bean
    fun userDetailService(userRepository: UserRepository): UserDetailsService {
        return UserDetailsService {
            username ->
                val user = userRepository.findUserByUusername(username)
                user.orElseThrow {
                    UsernameNotFoundException("User \"$username\" not found.")
                }
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}