package com.inomob.booksearch.fbook.domain.use_case

import javax.inject.Inject

// Created that class for the wrapping the all use cases in single
// class for easy to read and understand what use cases we have
// It is not necessary for the small scale applications
data class BookUseCases @Inject constructor (
    val searchBook: SearchBook,
)