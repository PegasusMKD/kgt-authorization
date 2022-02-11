package finki.ukim.kgt.kgtauthorization.enhancers

import finki.ukim.kgt.kgtauthorization.mappers.JwtMapper
import finki.ukim.kgt.kgtauthorization.usermodels.SpringUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Component
class KGTTokenEnhancer : TokenEnhancer {

    @Autowired
    private lateinit var jwtMapper: JwtMapper
    @Value("\${security.custom-authentication.duration-minutes}")
    private val duration: Int? = null

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val info: MutableMap<String, Any> = mutableMapOf()
        info["user_details"] = jwtMapper.toJwtUser(authentication.principal as SpringUser) as Any
        (accessToken as DefaultOAuth2AccessToken).additionalInformation = info
        accessToken.expiration = Date.from(
            LocalDateTime.now()
                .plusMinutes(duration!!.toLong()).toInstant(ZoneOffset.ofHours(1))
        )
        return accessToken
    }
}
