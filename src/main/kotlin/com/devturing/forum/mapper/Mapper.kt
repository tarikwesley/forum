package com.devturing.forum.mapper

fun interface Mapper<T, U> {
    fun map(t: T): U
}
