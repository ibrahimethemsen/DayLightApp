package com.daylightapp.data.mapper

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

interface Mapper<I, O> {
    fun map(input: I?): O
}