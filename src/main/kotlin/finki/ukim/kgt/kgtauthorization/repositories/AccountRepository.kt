package finki.ukim.kgt.kgtauthorization.repositories

import finki.ukim.kgt.kgtauthorization.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository: JpaRepository<Account, String> {
    fun findByUsername(username: String): Optional<Account>
}