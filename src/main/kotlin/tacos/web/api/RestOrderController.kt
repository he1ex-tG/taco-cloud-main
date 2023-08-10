package tacos.web.api

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tacos.Taco
import tacos.data.OrderRepository
import java.lang.Integer.min

@RestController
@RequestMapping(path = ["/api/orders"], produces = ["application/json"])
@CrossOrigin(origins = ["http://tacocloud:8080"])
class RestOrderController(
    val orderRepository: OrderRepository
) {

    @GetMapping(params = ["recentTacos"])
    fun recentTacos(@RequestParam("recentTacos") count: Int): Iterable<Taco> {
        val pageable: Pageable = PageRequest.of(0, count, Sort.by("placedAt").descending())
        val orders = orderRepository.findAll(pageable)
        val tacos = orders.flatMap { it.tacos ?: listOf() }
            .sortedByDescending { it.createdAt }.apply {
                subList(0, min(count, this.lastIndex))
            }
        return tacos
    }
}