package tacos.api

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tacos.Taco
import tacos.TacoOrder
import tacos.data.OrderRepository
import java.lang.Integer.min

@RestController
@RequestMapping(path = ["/api/orders"], produces = ["application/json"])
@CrossOrigin(origins = ["http://tacocloud:8080"])
class OrderRestController(
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

    @GetMapping
    fun getOrders(): Iterable<TacoOrder> {
        return orderRepository.findAll()
    }

    @PostMapping(consumes = ["application/json"])
    fun postOrder(@RequestBody order: TacoOrder): ResponseEntity<TacoOrder> {
        val tacos = order.tacos
        return ResponseEntity(orderRepository.save(order), HttpStatus.CREATED)
    }

    @PutMapping(path = ["/{orderID}"], consumes = ["application/json"])
    fun putOrder(@PathVariable("orderID") orderID: String, @RequestBody order: TacoOrder): TacoOrder {
        order.id = orderID
        return orderRepository.save(order)
    }

    @PatchMapping(path = ["/{orderID}"], consumes = ["application/json"])
    fun patchOrder(@PathVariable("orderID") orderID: String, @RequestBody patch: TacoOrder): TacoOrder {
        val order = orderRepository.findById(orderID).get()
        if (patch.deliveryName != "") {
            order.deliveryName = patch.deliveryName
        }
        if (patch.deliveryStreet != "") {
            order.deliveryStreet = patch.deliveryStreet
        }
        if (patch.deliveryCity != "") {
            order.deliveryCity = patch.deliveryCity
        }
        if (patch.deliveryState != "") {
            order.deliveryState = patch.deliveryState
        }
        if (patch.deliveryZip != "") {
            order.deliveryZip = patch.deliveryZip
        }
        if (patch.ccNumber != "") {
            order.ccNumber = patch.ccNumber
        }
        if (patch.ccExpiration != "") {
            order.ccExpiration = patch.ccExpiration
        }
        if (patch.ccCVV != "") {
            order.ccCVV = patch.ccCVV
        }
        return orderRepository.save(order)
    }

    @DeleteMapping(path = ["/{orderID}"])
    fun deleteOrder(@PathVariable("orderID") orderID: String) {
        try {
            orderRepository.deleteById(orderID)
        } catch (_: EmptyResultDataAccessException) {}
    }
}