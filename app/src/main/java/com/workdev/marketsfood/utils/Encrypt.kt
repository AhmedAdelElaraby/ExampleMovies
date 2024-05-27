package com.workdev.marketsfood.utils
import android.content.Context
import android.util.Base64
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher



object Encrypt {

    private const val RSA_ALGORITHM = "RSA"
   //public val  keyPublic="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuOiv0QbV8Ex6gk8QXoQRwZYI+i43KuuVvZvaRznx+VMujlgCVd0pf4g2V6C/UIEct7ooPnVoWMYVbv5wrFnyMrpA6I3MdaPMAtDweNUcFJVrx9BltqZo6r4wh3w6a0t6AFi+h5DZ6+rCcFAz/gTDcXsGCPn9KgWCL4PN6eT8grrriCCrchFL9CYPaQ/uyR+Umq0iw8O28CmipNNuipfP89XneVUV0QtOKRYuklOf4DjsV2pC9tKdBcZaRyrACKfzABwJdMIBvMMJd9B8Gi0a/f7BzcLBqtUOOv1AMfzrWVYjl49bZ0dhgNDDQOpqXVL/Mr1NXPxh5hAlEZYDowTmOwIDAQAB"

    // Function to load public key from resource file
//    fun loadPublicKey(context: Context, resourceId: Int): PublicKey {
//        val keyString = context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
//        val keyBytes = Base64.decode(keyString, Base64.DEFAULT)
//        val keySpec = X509EncodedKeySpec(keyBytes)
//        val keyFactory = KeyFactory.getInstance(RSA_ALGORITHM)
//        return keyFactory.generatePublic(keySpec)
//    }

    private const val ALGORITHM = "RSA"
    private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

    fun generateKeyPair(): KeyPair {
        val keyGen = KeyPairGenerator.getInstance(ALGORITHM)
        keyGen.initialize(2048)
        return keyGen.genKeyPair()
    }

    fun getPublicKey(key: String): PublicKey {
        val byteKey = Base64.decode(key, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(byteKey)
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        return keyFactory.generatePublic(spec)
    }

    fun getPrivateKey(key: String): PrivateKey {
        val byteKey = Base64.decode(key, Base64.DEFAULT)
        val spec = PKCS8EncodedKeySpec(byteKey)
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        return keyFactory.generatePrivate(spec)
    }

    fun encrypt(data: String, publicKey: PublicKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    fun decrypt(data: String, privateKey: PrivateKey): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes = cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
        return String(decryptedBytes)
    }










//    // Function to encrypt data using the public key
//    fun encryptData(data: String, publicKey: PublicKey): String {
//        val cipher = Cipher.getInstance("$RSA_ALGORITHM/ECB/PKCS1Padding")
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
//        val encryptedBytes = cipher.doFinal(data.toByteArray())
//        return java.util.Base64.getEncoder().encodeToString(encryptedBytes)
//    }



















//    fun encrypt(data: String, publicKey: PublicKey): String {
//        val cipher = Cipher.getInstance("RSA")
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
//        val encryptedBytes = cipher.doFinal(data.toByteArray())
//        return java.util.Base64.getEncoder().encodeToString(encryptedBytes)
//    }







































}




