package com.example.domain.value_object

class PricesValueObj {
    var typeId: Int? = null
    var typePrice: Int? = null
    var value: Double? = null
    var extra: Double? = null

    fun PricesValueObj(typeId: Int?, typePrice: Int?, value: Double?, extra: Double?){
        this.typeId = typeId
        this.typePrice = typePrice
        this.value = value
        this.extra = extra
    }
}