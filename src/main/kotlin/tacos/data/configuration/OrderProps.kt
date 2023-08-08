package tacos.data.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Configuration
@ConfigurationProperties(prefix = "taco.orders")
@Validated
class OrderProps {
    @field:Min(value = 5, message = "Must be between 5 and 25")
    @field:Max(value = 25, message = "Must be between 5 and 25")
    var pageSize = 10
}