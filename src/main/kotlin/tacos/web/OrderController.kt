package tacos.web

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import tacos.TacoOrder

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
interface OrderController {

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(order: TacoOrder, sessionStatus: SessionStatus): String {
        // log
        sessionStatus.setComplete()
        return "redirect:/"
    }
}