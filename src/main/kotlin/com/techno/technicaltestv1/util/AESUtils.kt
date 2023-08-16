package com.techno.technicaltestv1.util

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class AESUtils {

    companion object{
        lateinit var secret : SecretKey
        var key : ByteArray = ByteArray(16)
        val instance : AESUtils = AESUtils()

        @JvmStatic
        val CIPHER : String = "AES/ECB/PKCS5PADDING"

        private fun setKey(myKey:String){
            val sha : MessageDigest?
            try {
                key = myKey.toByteArray(Charsets.UTF_8)
                sha = MessageDigest.getInstance("SHA-512")
                key = sha.digest(key)
                key = key.copyOf(16)
                secret = SecretKeySpec(key,"AES")
            }catch (e : NoSuchAlgorithmException){
                e.printStackTrace()
            }catch (e : UnsupportedEncodingException){
                e.printStackTrace()
            }
        }

        fun encrypt(strToEncrypt : String, secretKey : String): String {
            try {
                setKey(secretKey)
                val cipher : Cipher = Cipher.getInstance(CIPHER)
                cipher.init(Cipher.ENCRYPT_MODE,secret)
                return Base64.getEncoder().encodeToString(
                    cipher.doFinal(strToEncrypt.toByteArray(Charsets.UTF_8)
                    )
                )
            }catch (e: Exception){
                e.printStackTrace()
            }
            return ""
        }

        fun decrypt(strToEncrypt : String, secretKey : String): String {
            setKey(secretKey)
            val cipher : Cipher = Cipher.getInstance(CIPHER)
            cipher.init(Cipher.DECRYPT_MODE,secret)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToEncrypt)))
        }

    }
}