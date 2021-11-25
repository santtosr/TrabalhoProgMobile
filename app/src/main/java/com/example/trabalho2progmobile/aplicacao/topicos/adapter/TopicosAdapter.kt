package com.example.trabalho2progmobile.aplicacao.topicos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.topico.Topico
import com.example.trabalho2progmobile.databinding.RecyclerviewTopicosBinding

class TopicosAdapter(
    private val topicos: List<Topico>,
    private val listenerITopicos: ITopicosRecyclerViewClickListener
): RecyclerView.Adapter<TopicosAdapter.TopicosViewHolder>() {

    inner class TopicosViewHolder(
        val recyclerViewTopicos: RecyclerviewTopicosBinding
    ): RecyclerView.ViewHolder(recyclerViewTopicos.root)

    override fun getItemCount() = topicos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopicosViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_topicos,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TopicosViewHolder, position: Int) {
        holder.recyclerViewTopicos.topico = topicos[position]

        holder.recyclerViewTopicos.root.setOnClickListener {
            listenerITopicos.onTopicosRecyclerViewItemClickListener(
                holder.recyclerViewTopicos.root,
                topicos[position]
            )
        }
    }
}