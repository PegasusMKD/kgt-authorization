package finki.ukim.kgt.kgtauthorization.mappers

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto
import finki.ukim.kgt.kgtauthorization.models.Account
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AccountMapper {
    fun toDto(entity: Account?): AccountDto?
}