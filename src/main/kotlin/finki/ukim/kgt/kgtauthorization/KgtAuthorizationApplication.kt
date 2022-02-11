package finki.ukim.kgt.kgtauthorization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class KgtAuthorizationApplication

fun main(args: Array<String>) {
    runApplication<KgtAuthorizationApplication>(*args)
}
