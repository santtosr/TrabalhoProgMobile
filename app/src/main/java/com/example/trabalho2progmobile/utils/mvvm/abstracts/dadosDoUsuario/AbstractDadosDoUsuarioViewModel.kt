package com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.utils.mvvm.abstracts.base.BaseViewModel

abstract class AbstractDadosDoUsuarioViewModel: BaseViewModel() {
    val erroNome: LiveData<Int> get() = _erroNome
    protected val _erroNome by lazy { MutableLiveData<Int>() }

    val erroEmail: LiveData<Int> get() = _erroEmail
    protected val _erroEmail by lazy { MutableLiveData<Int>() }

    val erroSenha: LiveData<Int> get() = _erroSenha
    protected val _erroSenha by lazy { MutableLiveData<Int>() }

    val erroConfirmarSenha: LiveData<Int> get() = _erroConfirmarSenha
    protected val _erroConfirmarSenha by lazy { MutableLiveData<Int>() }

    val dadosCorretos: LiveData<Boolean> get() = _dadosCorretos
    protected val _dadosCorretos by lazy { MutableLiveData<Boolean>() }

    open fun verificarCampos(
        nome: String,
        email: String,
        senha: String,
        confirmacaoDeSenha: String
    ){
        val verificarNome = verificarNome(nome)
        val verificarEmail = verificarEmail(email)
        val verificarSenhas = verificarSenhas(senha, confirmacaoDeSenha)

        _dadosCorretos.value = verificarNome && verificarEmail && verificarSenhas
    }

    protected fun verificarNome(nome: String): Boolean{
        return if(nome.isNotEmpty()) true
        else {
            _erroNome.value = R.string.erro_nome_vazio
            false
        }
    }

    fun verificarEmail(email: String): Boolean{
        return if(email.isNotEmpty()) true
        else {
            _erroEmail.value = R.string.erro_email_vazio
            false
        }
    }

    protected fun verificarSenhas(senha: String, confirmacaoDeSenha: String): Boolean{
        return if(senha.isNotEmpty() && confirmacaoDeSenha.isNotEmpty()) {
            if(senha == confirmacaoDeSenha){
                true
            }
            else{
                _erroConfirmarSenha.value = R.string.erro_confirmar_senha
                false
            }
        }
        else {
            if(senha.isEmpty())_erroSenha.value = R.string.erro_senha_vazia
            else _erroConfirmarSenha.value = R.string.erro_confirmar_senha
            false
        }
    }
}