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

    @Column(name = "username", length = 20, nullable = false, unique = true)
    var username: String? = null,

    @Column(name = "password", length = 255, nullable = false)
    var password: String? = null,

    @Column(name = "enabled")
    var enabled: Boolean = true,

    @Column(name = "firstName", nullable = false)
    var firstName: String? = null,

    @Column(name = "lastName", nullable = false)
    var lastName: String? = null,

    @CollectionTable
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    var roles: Set<Role>? = mutableSetOf()
)
