package com.daylightapp.domain.mapper

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

interface Mapper<I, O> {
    fun map(input: I?): O
}