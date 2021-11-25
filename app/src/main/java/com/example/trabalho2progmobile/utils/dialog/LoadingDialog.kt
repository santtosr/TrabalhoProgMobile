package com.example.trabalho2progmobile.utils.dialog

import android.content.Context
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog

class LoadingDialog(
    var mensagem: String,
    val context: Context
){
    private val loadingDialog: SweetAlertDialog = SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)

    fun setText(novaMensagem: String){
        loadingDialog.titleText = novaMensagem
    }

    fun show(cancelavel: Boolean = false){
        loadingDialog.progressHelper.barColor = Color.parseColor("#569783")
        loadingDialog.titleText = this.mensagem
        loadingDialog.setCancelable(cancelavel)
        loadingDialog.show()
    }

    fun dismiss(){
        loadingDialog.cancel()
    }
}