package finki.ukim.kgt.kgtauthorization.usermodels

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors

class SpringUser(
    val id: String,
    username: String?,
    password: String?,
    enabled: Boolean,
    accountNonExpired: Boolean,
    credentialsNonExpired: Boolean,
    accountNonLocked: Boolean,
    authorities: Collection<GrantedAuthority>,
) : User(
        username, password, enabled, accountNonExpired,
        credentialsNonExpired, accountNonLocked, authorities
    )
{
    val roles: List<String>

    init {
        roles = authorities.stream().map { obj: GrantedAuthority -> obj.authority }.collect(Collectors.toList())
    }
}