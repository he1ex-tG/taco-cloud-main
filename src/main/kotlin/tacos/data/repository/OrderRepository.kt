package tacos.data.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import tacos.data.entity.TacoOrder

interface OrderRepository : CrudRepository<TacoOrder, String> {

    fun findByUserOrderByPlacedAtDesc(user: String, pageable: Pageable): List<TacoOrder>
    fun findAll(pageable: Pageable): List<TacoOrder>
}
