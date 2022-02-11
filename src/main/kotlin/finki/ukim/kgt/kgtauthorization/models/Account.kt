package finki.ukim.kgt.kgtauthorization.models

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Account(
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "strategy-uuid")
    @GenericGenerator(name = "strategy-uuid", strategy = "uuid")
    @Id
    var id: String? = null,

    @Column(name = "username", length = 20)
    var username: String? = null,

    @Column(name = "password", length = 255)
    var password: String? = null,

    @Column(name = "enabled")
    var enabled: Boolean = true,

    @CollectionTable
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    var roles: Set<Role>? = mutableSetOf()
)
