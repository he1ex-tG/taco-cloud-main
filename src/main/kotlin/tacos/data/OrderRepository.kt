package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.TacoOrder
import tacos.User

interface OrderRepository : CrudRepository<TacoOrder, String> {

    //fun findByUserOrderByPlacedAtDesc(user: User): List<TacoOrder>
}
