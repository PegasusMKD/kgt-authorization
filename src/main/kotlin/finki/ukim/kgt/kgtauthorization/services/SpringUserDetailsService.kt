package finki.ukim.kgt.kgtauthorization.services

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto
import finki.ukim.kgt.kgtauthorization.models.Role
import finki.ukim.kgt.kgtauthorization.usermodels.SpringUser
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service("SpringUserDetailsService")
class SpringUserDetailsService @Lazy constructor(private val accountDtoService: AccountService) :
    UserDetailsService {


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): SpringUser? {
        val account: AccountDto? = accountDtoService.findByUsername(s)
        val authorities = account?.roles?.let { buildUserAuthority(it) }
        return account?.let { buildUserForAuthentication(it, authorities) }
    }

    private fun buildUserForAuthentication(account: AccountDto, authorities: List<GrantedAuthority>?): SpringUser? {
        val enabled = account.enabled
        if (!enabled)
            throw Exception("Account disabled...")

        val accountNonExpired = true
        val credentialsNonExpired = true
        val accountNonLocked = true
        return account.id?.let {
            SpringUser(
                it, account.username, account.password,
                enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities ?: mutableSetOf()
            )
        }
    }

    private fun buildUserAuthority(userRoles: Set<Role>): List<GrantedAuthority> {
        val setAuths: MutableSet<GrantedAuthority> = HashSet()
        for (userRole in userRoles) {
            setAuths.add(SimpleGrantedAuthority(userRole.name))
        }
        return ArrayList(setAuths)
    }

}
