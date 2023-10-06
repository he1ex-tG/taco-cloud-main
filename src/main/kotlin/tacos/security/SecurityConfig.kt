package tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf {
                disable()
            }
            authorizeRequests {
                authorize("/design/**", hasAuthority("SCOPE_taco:user"))
                authorize("/orders/**", hasAuthority("SCOPE_taco:user"))
                authorize(HttpMethod.POST, "/api/ingredients/**", hasAuthority("SCOPE_write:ingredients"))
                authorize(HttpMethod.DELETE, "/api/ingredients/**", hasAuthority("SCOPE_delete:ingredients"))
                authorize("/", permitAll)
                authorize("/**", permitAll)
            }
            oauth2Login {
                loginPage = "/oauth2/authorization/taco-user-client"
                defaultSuccessUrl("/design", true)
            }
            oauth2ResourceServer {
                jwt { }
            }
        }
        return http.build()
    }

}