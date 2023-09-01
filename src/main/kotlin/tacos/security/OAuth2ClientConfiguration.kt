package tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.security.oauth2.core.oidc.OidcScopes
import java.util.UUID

@Configuration
class OAuth2ClientConfiguration(
    val encoder: PasswordEncoder
) {

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(tacoClientRegistration())
    }

    @Bean
    fun authorizedClientService(clientRegistrationRepository: ClientRegistrationRepository): OAuth2AuthorizedClientService {
        return InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository)
    }

    @Bean
    fun authorizedClientRepository(authorizedClientService: OAuth2AuthorizedClientService): OAuth2AuthorizedClientRepository {
        return AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService)
    }

    private fun tacoClientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId("taco-main")
            .clientId("taco-main")
            .clientSecret(encoder.encode("secret"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("http://localhost:8080/login/oauth2/code/taco-main")
            .scope("tacoMain")
            .scope(OidcScopes.OPENID)
            .authorizationUri("http://authserver:9000/oauth2/authorize")
            .tokenUri("http://authserver:9000/oauth2/token")
            .userInfoUri("http://authserver:9000/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .jwkSetUri("http://authserver:9000/oauth2/jwks")
            .clientName("taco-main")
            .build()
    }
}