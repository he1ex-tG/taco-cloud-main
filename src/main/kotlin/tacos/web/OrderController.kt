package tacos.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import tacos.TacoOrder
import tacos.User
import tacos.data.OrderRepository
import tacos.data.configuration.OrderProps
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController(
    val orderRepository: OrderRepository,
    val orderProps: OrderProps
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping
    fun ordersForUser(
        @AuthenticationPrincipal user: User,
        model: Model
    ): String {
        val pageable: Pageable = PageRequest.of(0, orderProps.pageSize)
        val ordersListByUser = orderRepository.findByUserOrderByPlacedAtDesc(user, pageable)
        model.addAttribute("orders", ordersListByUser)

        return "orderList"
    }

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid order: TacoOrder,
        errors: Errors,
        sessionStatus: SessionStatus,
        @AuthenticationPrincipal user: User
    ): String {

        if (errors.hasErrors()) {
            return "orderForm"
        }

        order.user = user
        orderRepository.save(order)

        sessionStatus.setComplete()
        return "redirect:/"
    }
}