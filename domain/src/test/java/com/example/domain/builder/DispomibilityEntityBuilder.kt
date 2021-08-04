package com.example.domain.builder

import com.example.domain.model.DisponibilityEntity


class DispomibilityEntityBuilder {
    private var id = 1
    private var typeId = 1
    private var count = 20

    fun withId(id: Int): DispomibilityEntityBuilder {
        this.id = id
        return this
    }

    fun withTypeId(typeId: Int): DispomibilityEntityBuilder {
        this.typeId = typeId
        return this
    }

    fun withCount(count: Int): DispomibilityEntityBuilder {
        this.count = count
        return this
    }

    fun buildDisponibility(): DisponibilityEntity {
        val disponibility = DisponibilityEntity()
        disponibility.DisponibilityEntity(id, typeId, count)
        return disponibility
    }

}