package finki.ukim.kgt.kgtauthorization.mappers

import finki.ukim.kgt.kgtauthorization.dtos.AccountDto
import finki.ukim.kgt.kgtauthorization.models.Account
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface AccountMapper {
    fun toDto(entity: Account?): AccountDto?
    fun updateEntity(dto: AccountDto, @MappingTarget entity: Account)
}