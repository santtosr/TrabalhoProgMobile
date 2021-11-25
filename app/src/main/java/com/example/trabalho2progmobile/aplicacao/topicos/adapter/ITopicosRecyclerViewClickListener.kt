package com.example.trabalho2progmobile.aplicacao.topicos.adapter
import android.view.View
import com.example.trabalho2progmobile.bancoDeDados.topico.Topico

interface ITopicosRecyclerViewClickListener {
    fun onTopicosRecyclerViewItemClickListener(view: View, produto: Topico)
}
