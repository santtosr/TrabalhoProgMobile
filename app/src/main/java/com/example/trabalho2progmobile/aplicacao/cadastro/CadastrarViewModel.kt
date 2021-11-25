package com.example.trabalho2progmobile.aplicacao.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.IUsuarioRepository
import com.example.trabalho2progmobile.utils.criptografia.Criptografia.Companion.encriptografarMensagem
import com.example.trabalho2progmobile.utils.retorno.Resultado
import com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario.AbstractDadosDoUsuarioViewModel

class CadastrarViewModel(
    val usuarioRepository: IUsuarioRepository
): AbstractDadosDoUsuarioViewModel() {

    val usuarioInserido: LiveData<Resultado> get() = _usuarioInserido
    private val _usuarioInserido by lazy { MutableLiveData<Resultado>() }

    fun inserirUsuarioNoBanco(usuario: Usuario) {
        usuario.senha = encriptografarMensagem(usuario.senha)
        _usuarioInserido.value = Resultado(Resultado.ResultadoStatus.CARREGANDO, false)
        _usuarioInserido.value = Resultado(
            Resultado.ResultadoStatus.FINALIZADO,
            usuarioRepository.inserirUsuario(usuario)
        )
    }
}