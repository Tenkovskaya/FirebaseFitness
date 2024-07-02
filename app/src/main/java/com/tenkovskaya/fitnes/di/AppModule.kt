package com.tenkovskaya.fitnes.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.tenkovskaya.fitnes.data.api.FirebaseService
import com.tenkovskaya.fitnes.data.database.WorkoutDataSource
import com.tenkovskaya.fitnes.data.database.WorkoutDataSourceImpl
import com.tenkovskaya.fitnes.data.repository.UserInfoRepository
import com.tenkovskaya.fitnes.data.repository.UserRegistersRepositoryImpl
import com.tenkovskaya.fitnes.data.repository.WorkoutRepositoryImpl
import com.tenkovskaya.fitnes.domain.repository.UsersRegistersRepository
import com.tenkovskaya.fitnes.domain.repository.WorkoutRepository
import com.tenkovskaya.fitnes.domain.usecase.LoginUseCase
import com.tenkovskaya.fitnes.domain.usecase.RegisterUseCase
import com.tenkovskaya.fitnes.presentation.viewmodel.DashboardViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.LoginViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.RegisterViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.UserViewModel
import com.tenkovskaya.fitnes.presentation.viewmodel.WorkoutCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModuleSingle = module {
    single { FirebaseService() }
    single { UserInfoRepository(get()) }
    single<UsersRegistersRepository> { UserRegistersRepositoryImpl(get()) }
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

    viewModel { UserViewModel(get()) }
}


val appModuleDatabase = module {
    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().userInfoDao() }
}