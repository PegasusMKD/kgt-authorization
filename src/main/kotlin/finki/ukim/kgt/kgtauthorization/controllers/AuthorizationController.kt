package finki.ukim.kgt.kgtauthorization.controllers

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto
import finki.ukim.kgt.kgtauthorization.services.AccountService
import finki.ukim.kgt.kgtauthorization.usermodels.JwtUser
import finki.ukim.kgt.kgtauthorization.usermodels.SpringUser
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authorization")
class AuthorizationController(private val accountService: AccountService) {

    @PostMapping("/user-info")
    fun getUser(@AuthenticationPrincipal user: JwtUser?): ResponseEntity<JwtUser?> {
        return ResponseEntity.ok(user)
    }

    @PostMapping("/register")
    fun register(@RequestBody account: AccountDto): ResponseEntity<AccountDto> {
        return ResponseEntity.ok(accountService.createAccount(account))
    }

}