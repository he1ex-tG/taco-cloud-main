package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.TacoOrder

<<<<<<< HEAD
interface OrderRepository : CrudRepository<TacoOrder, String>
=======
interface OrderRepository : CrudRepository<TacoOrder, Long>
>>>>>>> master
