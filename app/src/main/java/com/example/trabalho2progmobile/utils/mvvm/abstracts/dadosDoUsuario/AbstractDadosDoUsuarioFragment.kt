package com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.utils.converters.Converters
import com.example.trabalho2progmobile.utils.mvvm.abstracts.base.BaseFragment
import kotlinx.android.synthetic.main.bloco_dados_usuario.*

abstract class AbstractDadosDoUsuarioFragment: BaseFragment() {
    private val REQUEST_IMAGE_CAPTURE = 1
    var foto: Bitmap? = null

    fun capturePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            foto = imageBitmap
            imgViewFoto.setImageBitmap(Converters().getRoundedCornerBitmap(imageBitmap, 360))
        }
    }

    fun verificarSeEFotoOuDrawable(): Bitmap{
        return if(foto != null){
            foto as Bitmap
        } else{
            Converters().drawableToBitmap(
                requireContext().getDrawable(R.drawable.ic_foto)!!
            )
        }
    }
}