package tacos.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import tacos.TacoOrder
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid order: TacoOrder,
        errors: Errors,
        sessionStatus: SessionStatus
    ): String {

        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info("Order submitted: $order")
        sessionStatus.setComplete()
        return "redirect:/"
    }
}