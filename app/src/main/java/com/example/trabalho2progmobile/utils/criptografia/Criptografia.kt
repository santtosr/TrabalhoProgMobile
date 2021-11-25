package com.example.trabalho2progmobile.utils.criptografia

import java.math.BigInteger
import java.security.MessageDigest

class Criptografia {
    companion object{
        fun encriptografarMensagem(mensagem: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(mensagem.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}