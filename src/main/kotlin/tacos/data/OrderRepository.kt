package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.TacoOrder

interface OrderRepository : CrudRepository<TacoOrder, Long>