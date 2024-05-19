package com.tenkovskaya.fitnes.di

import com.tenkovskaya.fitnes.data.api.FirebaseService
import com.tenkovskaya.fitnes.data.repository.UserRepositoryImpl
import com.tenkovskaya.fitnes.domain.repository.UserRepository
import com.tenkovskaya.fitnes.domain.usecase.RegisterUseCase
import com.tenkovskaya.fitnes.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseService() }
    single<UserRepository> { UserRepositoryImpl(get()) }
    factory { RegisterUseCase(get()) }
    viewModel { RegisterViewModel(get()) }
}