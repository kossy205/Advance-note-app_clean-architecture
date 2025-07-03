package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}