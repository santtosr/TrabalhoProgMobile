package com.example.trabalho2progmobile.utils.mvvm.abstracts.base

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.utils.dialog.LoadingDialog
import com.example.trabalho2progmobile.utils.mensagens.MensagemUtils.Companion.mostrarMensagem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseFragment: Fragment() {
    private lateinit var _dialog: LoadingDialog

    fun opcoesDialog(@StringRes operacao: Int, @StringRes mensagem: Int) {
        when (getString(operacao)) {
            getString(R.string.dialog_mostrar) -> mostrarDialog(mensagem)
            getString(R.string.dialog_atualizar) -> atualizarTextoDialog(mensagem)
            getString(R.string.dialog_remover) -> removerDialog()
        }
    }

    private fun mostrarDialog(@StringRes mensagem: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            _dialog = LoadingDialog(getString(mensagem), requireContext())
            _dialog.show()
        }
    }

    private fun atualizarTextoDialog(@StringRes mensagem: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            _dialog.setText(getString(mensagem))
        }
    }

    private fun removerDialog() {
        CoroutineScope(Dispatchers.Main).launch {
            _dialog.dismiss()
        }
    }

    protected fun exibirMensagem(mensagem: String) {
        CoroutineScope(Dispatchers.Main).launch {
            mostrarMensagem(requireContext(), mensagem)
        }
    }

    protected fun mostrarErroDoMaskEditText(editText: TextInputEditText, erro: Int){
        editText.error = getString(erro)
    }

    protected fun mostrarErroDoTextInputLayout(textInputLayout: TextInputLayout, erro: Int){
        textInputLayout.error = getString(erro)
    }
}