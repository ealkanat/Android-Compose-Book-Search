package com.inomob.booksearch.di

import com.inomob.booksearch.fbook.common.Constants
import com.inomob.booksearch.fbook.data.remote.OpenLibraryApi
import com.inomob.booksearch.fbook.data.repository.BookRepositoryImpl
import com.inomob.booksearch.fbook.domain.repository.BookRepository
import com.inomob.booksearch.fbook.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// This is the app module we can use create room database or etc...

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenLibraryApi(): OpenLibraryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBooksRepository(api: OpenLibraryApi) : BookRepository {
        return BookRepositoryImpl(api)
    }


}