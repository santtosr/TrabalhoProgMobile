package com.example.trabalho2progmobile.utils.mensagens

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MensagemUtils {
    companion object{
        fun mostrarMensagem(context: Context, mensagem: String){
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
            }
        }
    }
}