package com.example.trabalho2progmobile.utils.mvvm.di

import androidx.room.Room
import com.example.trabalho2progmobile.aplicacao.cadastrarTopico.CadastrarTopicoViewModel
import com.example.trabalho2progmobile.aplicacao.cadastro.CadastrarViewModel
import com.example.trabalho2progmobile.aplicacao.comentarios.ComentariosViewModel
import com.example.trabalho2progmobile.aplicacao.editarUsuario.EditarUsuarioViewModel
import com.example.trabalho2progmobile.aplicacao.inicial.InicialViewModel
import com.example.trabalho2progmobile.aplicacao.login.LoginViewModel
import com.example.trabalho2progmobile.aplicacao.topicos.TopicosViewModel
import com.example.trabalho2progmobile.bancoDeDados.BancoDeDados
import com.example.trabalho2progmobile.bancoDeDados.comentario.ComentarioDao
import com.example.trabalho2progmobile.bancoDeDados.comentario.repository.ComentarioRepository
import com.example.trabalho2progmobile.bancoDeDados.comentario.repository.IComentarioRepository
import com.example.trabalho2progmobile.bancoDeDados.topico.TopicoDao
import com.example.trabalho2progmobile.bancoDeDados.topico.repository.ITopicoRepository
import com.example.trabalho2progmobile.bancoDeDados.topico.repository.TopicoRepository
import com.example.trabalho2progmobile.bancoDeDados.usuario.UsuarioDao
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.IUsuarioRepository
import com.example.trabalho2progmobile.bancoDeDados.usuario.repository.UsuarioRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single<BancoDeDados>{
        Room.databaseBuilder(androidApplication(), BancoDeDados::class.java, "bandoDeDados")
            .build()
    }

    single<UsuarioDao>{
        get<BancoDeDados>().usuarioDao()
    }

    single<IUsuarioRepository>{
        UsuarioRepository(
            usuarioDao = get()
        )
    }

    single<TopicoDao>{
        get<BancoDeDados>().topicoDao()
    }

    single<ITopicoRepository>{
        TopicoRepository(
            topicoDao = get()
        )
    }

    single<ComentarioDao>{
        get<BancoDeDados>().comentarioDao()
    }

    single<IComentarioRepository>{
        ComentarioRepository(
            comentarioDao = get()
        )
    }

    viewModel {
        InicialViewModel()
    }

    viewModel {
        LoginViewModel(
            usuarioRepository = get()
        )
    }

    viewModel {
        TopicosViewModel(
            topicoRepository = get()
        )
    }

    viewModel {
        CadastrarViewModel(
            usuarioRepository = get()
        )
    }

    viewModel {
        CadastrarTopicoViewModel(
            topicoRepository = get()
        )
    }

    viewModel {
        EditarUsuarioViewModel(
            usuarioRepository = get()
        )
    }

    viewModel {
        ComentariosViewModel(
            comentarioRepository = get(),
            usuarioRepository = get()
        )
    }

}
