package tacos.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {

    @Autowired
    lateinit var orderRepository: OrderRepository

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping
    fun ordersForUser(@AuthenticationPrincipal user: User, model: Model): String {
        val ddd = orderRepository.findAll()
        //val ddd = orderRepository.findByUserOrderByPlacedAtDesc(user)
        model.addAttribute(
            "orders",
            ddd
        )

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