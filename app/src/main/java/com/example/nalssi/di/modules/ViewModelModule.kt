package com.example.nalssi.di.modules

import com.example.nalssi.presentation.viewmodels.DetailViewModel
import com.example.nalssi.presentation.viewmodels.FavoriteViewModel
import com.example.nalssi.presentation.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}