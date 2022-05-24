package finki.ukim.kgt.kgtauthorization.usermodels

class JwtUser(map: Map<*, *>?) {
    var id: String? = null
    var username: String? = null
    var firstName: String? = null
    var roles: List<*>? = null

    init {
        if(map != null) {
            id = map["id"] as String?
            username = map["username"] as String?
            firstName = map["firstName"] as String?
            roles = map["roles"] as List<*>?
        }
    }
}