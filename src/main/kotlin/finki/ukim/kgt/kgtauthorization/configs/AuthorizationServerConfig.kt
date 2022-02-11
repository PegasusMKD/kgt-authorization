package finki.ukim.kgt.kgtauthorization.configs

import finki.ukim.kgt.kgtauthorization.enhancers.KGTTokenEnhancer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
    @Value("\${security.jwt.client-id}")
    private val clientId: String? = null

    @Value("\${security.jwt.client-secret}")
    private val clientSecret: String? = null

    @Value("\${security.jwt.grant-type}")
    private val grantType: String? = null

    @Value("\${security.jwt.scope-read}")
    private val scopeRead: String? = null

    @Value("\${security.jwt.scope-write}")
    private val scopeWrite = "write"

    @Value("\${security.jwt.resource-ids}")
    private val resourceIds: String? = null

    @Autowired
    private val tokenStore: TokenStore? = null

    @Autowired
    private val accessTokenConverter: JwtAccessTokenConverter? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    @Qualifier("SpringUserDetailsService")
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Throws(Exception::class)
    override fun configure(configurer: ClientDetailsServiceConfigurer) {
        configurer
            .inMemory()
            .withClient(clientId)
            .secret(passwordEncoder!!.encode(clientSecret))
            .authorizedGrantTypes(grantType)
            .scopes(scopeRead, scopeWrite)
            .resourceIds(resourceIds)
    }

    @Bean
    fun tokenEnhancer(): TokenEnhancer {
        return KGTTokenEnhancer()
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val enhancerChain = TokenEnhancerChain()
        enhancerChain.setTokenEnhancers(listOf(tokenEnhancer(), accessTokenConverter))
        endpoints.tokenStore(tokenStore)
            .userDetailsService(userDetailsService)
            .accessTokenConverter(accessTokenConverter)
            .tokenEnhancer(enhancerChain)
            .authenticationManager(authenticationManager)
    }
}
