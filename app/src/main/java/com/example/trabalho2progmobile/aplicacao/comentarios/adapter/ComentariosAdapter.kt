package com.example.trabalho2progmobile.aplicacao.comentarios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.databinding.RecyclerviewComentarioBinding
import com.example.trabalho2progmobile.utils.comentarioComUsuario.ComentarioComUsuario

class ComentariosAdapter(
    private val comentarios: List<ComentarioComUsuario>
): RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder>() {

    inner class ComentarioViewHolder(
        val recyclerviewComentario: RecyclerviewComentarioBinding
    ): RecyclerView.ViewHolder(recyclerviewComentario.root)

    override fun getItemCount() = comentarios.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ComentarioViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_comentario,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        holder.recyclerviewComentario.comentario = comentarios[position]
    }
}