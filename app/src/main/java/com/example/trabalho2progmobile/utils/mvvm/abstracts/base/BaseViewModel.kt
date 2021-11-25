package com.example.trabalho2progmobile.utils.mvvm.abstracts.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

abstract class BaseViewModel: ViewModel(), KoinComponent {
    val mensagemDeErro: LiveData<String> get() = _mensagemDeErro
    protected val _mensagemDeErro by lazy { MutableLiveData<String>() }

}