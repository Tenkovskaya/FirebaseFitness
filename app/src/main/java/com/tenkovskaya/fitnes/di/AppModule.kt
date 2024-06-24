package com.tenkovskaya.fitnes.di

import com.tenkovskaya.fitnes.data.api.FirebaseService
import com.tenkovskaya.fitnes.data.database.WorkoutDataSource
import com.tenkovskaya.fitnes.data.database.WorkoutDataSourceImpl
import com.tenkovskaya.fitnes.data.repository.UserRepositoryImpl
import com.tenkovskaya.fitnes.data.repository.WorkoutRepositoryImpl
import com.tenkovskaya.fitnes.domain.repository.UserRepository
import com.tenkovskaya.fitnes.domain.repository.WorkoutRepository
import com.tenkovskaya.fitnes.domain.usecase.LoginUseCase
import com.tenkovskaya.fitnes.domain.usecase.RegisterUseCase
import com.tenkovskaya.fitnes.presentation.viewmodel.DashboardViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.LoginViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.RegisterViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.WorkoutCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModuleSingle = module {
    single { FirebaseService() }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<WorkoutDataSource> { WorkoutDataSourceImpl() }
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
}

val appModuleFactory = module {
    factory { RegisterUseCase(get()) }
    factory { LoginUseCase(get()) }
}

val appModuleViewModel = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { DashboardViewModel() }
    viewModel { WorkoutCardViewModel(get()) }
}