package com.example.pruebaadn.view.interfaces

import com.example.domain.entity.DisponibilityEntity

interface DisponibilityViewModelDelegate {
    fun responseGetAllDisponibility(list: MutableList<DisponibilityEntity>)
}