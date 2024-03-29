package finki.ukim.kgt.kgtauthorization.services

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto

interface AccountService {
    fun findByUsername(username: String): AccountDto?
    fun createAccount(accountDto: AccountDto): AccountDto?
}