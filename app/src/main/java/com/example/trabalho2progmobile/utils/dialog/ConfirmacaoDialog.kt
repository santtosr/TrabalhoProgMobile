package com.example.trabalho2progmobile.utils.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trabalho2progmobile.R
import kotlinx.android.synthetic.main.dialog_confirmacao.*

data class ConfirmacaoDialog(
        var mensagem: String,
        val cancelavel: Boolean
): DialogFragment(){
    val retornoDialog: LiveData<Boolean> get() = _retornoDialog
    private val _retornoDialog by lazy { MutableLiveData<Boolean>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)

        isCancelable = cancelavel

        return inflater.inflate(R.layout.dialog_confirmacao, container, false)
    }

    override fun onStart() {
        super.onStart()

        edtMensagemDialog.setText(mensagem)

        btnOkDialogConfirmacao.setOnClickListener{
            _retornoDialog.value = true
            dismiss()
        }

        btnCancelarDialogConfirmacao.setOnClickListener{
            _retornoDialog.value = false
            dismiss()
        }
    }
}