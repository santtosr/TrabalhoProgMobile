package com.example.trabalho2progmobile.aplicacao.comentarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabalho2progmobile.bancoDeDados.comentario.Comentario
import com.example.trabalho2progmobile.bancoDeDados.comentario.repository.IComentarioRepository
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.IUsuarioRepository
import com.example.trabalho2progmobile.utils.comentarioComUsuario.ComentarioComUsuario
import com.example.trabalho2progmobile.utils.retorno.ResultadoComentario
import kotlinx.coroutines.runBlocking

class ComentariosViewModel(
    private val comentarioRepository: IComentarioRepository,
    private val usuarioRepository: IUsuarioRepository
): ViewModel() {

    var listaDeComentariosInseridos: MutableList<ComentarioComUsuario> = mutableListOf()

    val comentarioInserido: LiveData<ResultadoComentario> get() = _comentarioInserido
    private val _comentarioInserido by lazy { MutableLiveData<ResultadoComentario>() }

    fun verificarComentario(comentario: String): Boolean{
        return comentario.isNotEmpty()
    }

    fun inserirComentario(comentario: Comentario, usuario: Usuario){
        _comentarioInserido.value = ResultadoComentario(
            ResultadoComentario.ResultadoStatus.CARREGANDO, false, null
        )
        _comentarioInserido.value = ResultadoComentario(
            ResultadoComentario.ResultadoStatus.FINALIZADO,
            comentarioRepository.inserirComentario(comentario),
            ComentarioComUsuario(
                comentario,
                usuario
            )
        )
    }

    fun listarComentarios(topicoId: Int): List<Comentario> = runBlocking{
        return@runBlocking comentarioRepository.buscarComentarios(topicoId)
    }

    private fun buscarUsuarioDoComentario(usuarioId: Int): Usuario = runBlocking{
        return@runBlocking usuarioRepository.buscarUsuarioPeloId(usuarioId)
    }

    fun integrarUsuariosComComentarios(listaDeComentarios: List<Comentario>): List<ComentarioComUsuario>{
        val listaDeComentariosComUsuario: MutableList<ComentarioComUsuario> = mutableListOf()

        listaDeComentarios.forEach {
            listaDeComentariosComUsuario.add(
                ComentarioComUsuario(
                    it,
                    buscarUsuarioDoComentario(it.usuarioId)
                )
            )
        }

        return listaDeComentariosComUsuario
    }

}