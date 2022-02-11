package finki.ukim.kgt.kgtauthorization.mappers

import finki.ukim.kgt.kgtauthorization.usermodels.JwtUser
import finki.ukim.kgt.kgtauthorization.usermodels.SpringUser
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface JwtMapper {
    fun toJwtUser(springUser: SpringUser?): JwtUser?
}