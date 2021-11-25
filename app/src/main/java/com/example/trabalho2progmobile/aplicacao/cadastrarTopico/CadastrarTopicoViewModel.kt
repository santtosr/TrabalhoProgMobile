package com.example.trabalho2progmobile.aplicacao.cadastrarTopico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.topico.Topico
import com.example.trabalho2progmobile.bancoDeDados.topico.repository.ITopicoRepository
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.utils.criptografia.Criptografia
import com.example.trabalho2progmobile.utils.mvvm.abstracts.base.BaseViewModel
import com.example.trabalho2progmobile.utils.retorno.Resultado

class CadastrarTopicoViewModel(
    private val topicoRepository: ITopicoRepository
    ): BaseViewModel() {

    val erroNome: LiveData<Int> get() = _erroNome
    private val _erroNome by lazy { MutableLiveData<Int>() }

    val erroDescricao: LiveData<Int> get() = _erroDescricao
    private val _erroDescricao by lazy { MutableLiveData<Int>() }

    val topicoInserido: LiveData<Resultado> get() = _topicoInserido
    private val _topicoInserido by lazy { MutableLiveData<Resultado>() }

    val dadosCorretos: LiveData<Boolean> get() = _dadosCorretos
    protected val _dadosCorretos by lazy { MutableLiveData<Boolean>() }

        fun inseriTopico(topico: Topico){
            topicoRepository.inserirTopico(topico)
        }

    fun verificarCampos(
        nome: String,
        descricao: String,
    ){
        val verificarNome = verificarNome(nome)
        val verificarDescricao = verificarDescricao(descricao)

        _dadosCorretos.value = verificarNome && verificarDescricao
    }

    fun inserirTopicoNoBanco(topico: Topico) {
        _topicoInserido.value = Resultado(Resultado.ResultadoStatus.CARREGANDO, false)
        _topicoInserido.value = Resultado(
            Resultado.ResultadoStatus.FINALIZADO,
            topicoRepository.inserirTopico(topico)
        )
    }

    private fun verificarNome(nome: String): Boolean{
        return if(nome.isNotEmpty()) true
        else {
            _erroNome.value = R.string.erro_nome_vazio
            false
        }
    }

    private fun verificarDescricao(descricao: String): Boolean{
        return if(descricao.isNotEmpty()) true
        else {
            _erroDescricao.value = R.string.erro_descricao
            false
        }
    }
}