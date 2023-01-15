package tacos.data

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import tacos.TacoOrder

interface OrderRepository : CrudRepository<TacoOrder, Long> {

    // Example
    @Query("select max(id) from TacoOrder")
    fun findMaxTacoOrderId(): Long?
}