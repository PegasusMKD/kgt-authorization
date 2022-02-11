package finki.ukim.kgt.kgtauthorization.services.impl

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto
import finki.ukim.kgt.kgtauthorization.mappers.AccountMapper
import finki.ukim.kgt.kgtauthorization.models.Account
import finki.ukim.kgt.kgtauthorization.models.Role
import finki.ukim.kgt.kgtauthorization.repositories.AccountRepository
import finki.ukim.kgt.kgtauthorization.services.AccountService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountDtoService(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper,
    private val passwordEncoder: PasswordEncoder
) : AccountService {
    override fun findByUsername(username: String): AccountDto? {
        return accountMapper.toDto(accountRepository.findByUsername(username).orElse(null))
    }

    override fun createAccount(username: String, password: String): AccountDto? {
        val account = Account()
        account.roles = mutableSetOf(Role.ADMINISTRATOR)
        account.enabled = true
        account.password = passwordEncoder.encode(password)
        account.username = username
        return accountMapper.toDto(accountRepository.save(account))
    }
}