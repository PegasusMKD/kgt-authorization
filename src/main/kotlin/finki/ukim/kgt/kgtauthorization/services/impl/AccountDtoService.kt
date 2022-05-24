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

    override fun createAccount(dto: AccountDto): AccountDto? {
        if(dto.id != null)
            throw Exception("Can't create with predefined ID.")

        if(dto.roles.isEmpty())
            throw Exception("Have to contain at least one role!")

        dto.password = passwordEncoder.encode(dto.password)

        val account = Account()
        accountMapper.updateEntity(dto, account)
        return accountMapper.toDto(accountRepository.save(account))
    }
}