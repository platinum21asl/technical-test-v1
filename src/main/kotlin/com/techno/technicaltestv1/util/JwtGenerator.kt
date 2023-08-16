package com.techno.technicaltestv1.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class JwtGenerator {

    companion object{
        private const val SECRET_KEY = "TECHNOSECRET"
        private val instance: JwtGenerator = JwtGenerator()
    }

    fun createJwt(subject: String, ttlMills: Long): String?{
        val signatureAlgorithm : SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signgingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
        val builder : JwtBuilder = Jwts.builder()
//            .setId(AESUtils.encrypt(id, SECRET_KEY))
            .setSubject(AESUtils.encrypt(subject, SECRET_KEY))
            .setIssuedAt(now)
            .signWith(signatureAlgorithm, signgingKey)


        if(ttlMills >=0){
            val expMills = nowMills + ttlMills
            val exp = Date(expMills)
            builder.setExpiration(exp)
        }

        return builder.compact()
    }
    fun decodeJWT(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(token).body
    }
}