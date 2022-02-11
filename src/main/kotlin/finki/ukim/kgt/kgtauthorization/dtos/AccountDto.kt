package finki.ukim.kgt.kgtauthorization.dtos

import finki.ukim.kgt.kgtauthorization.models.Role

data class AccountDto(
    var id: String? = null,
    var username: String? = null,
    var password: String? = null,
    var enabled: Boolean = true,
    var roles: Set<Role>? = mutableSetOf()
)
