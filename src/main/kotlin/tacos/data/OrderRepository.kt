package tacos.data

import tacos.TacoOrder

interface OrderRepository {

    fun save(order: TacoOrder): TacoOrder
}