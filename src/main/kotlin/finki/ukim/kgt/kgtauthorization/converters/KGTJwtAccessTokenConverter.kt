package finki.ukim.kgt.kgtauthorization.converters

import finki.ukim.kgt.kgtauthorization.usermodels.JwtUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

class KGTJwtAccessTokenConverter : JwtAccessTokenConverter() {

    override fun extractAuthentication(map: Map<String?, *>): OAuth2Authentication {
        val authentication = super.extractAuthentication(map)
        var userAuthentication = authentication.userAuthentication
        if (userAuthentication != null) {
            val userDetails: LinkedHashMap<*, *>? = map["user_details"] as LinkedHashMap<*, *>?
            if (userDetails != null) {
                val extendedPrincipal = JwtUser(userDetails)
                val authorities = userAuthentication.authorities
                userAuthentication = UsernamePasswordAuthenticationToken(
                    extendedPrincipal,
                    userAuthentication.credentials, authorities
                )
            }
        }
        return OAuth2Authentication(authentication.oAuth2Request, userAuthentication)
    }
}