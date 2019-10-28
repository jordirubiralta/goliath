package com.jrubiralta.data.utils.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.security.cert.CertificateException
import java.security.interfaces.RSAPublicKey
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.NoSuchPaddingException
import javax.security.auth.x500.X500Principal

object KeyStoreUtil {

    private val KEY_ALIAS = "goliathKey"  // TODO align with alias of the keystore

    private val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

    private val PROVIDER = "AndroidOpenSSL"

    private val ANDROID_KEY_STORE = "AndroidKeyStore"

    private val CHARSET = Charsets.UTF_8

    private val ALGORITHM = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) KeyProperties.KEY_ALGORITHM_RSA else "RSA"

    fun generateKey(context: Context): Boolean {
        var generated = false
        try {
            val keyStore: KeyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                val start: Calendar = Calendar.getInstance()
                val end: Calendar = Calendar.getInstance()
                end.add(Calendar.YEAR, 100)

                val spec: AlgorithmParameterSpec
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    spec = KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_SIGN)
                            /*.setDigests(KeyProperties.DIGEST_SHA256)
                            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PSS)*/
                            .setCertificateSubject(X500Principal(String.format("CN=%s, OU=%s", KEY_ALIAS, context.packageName)))
                            .setCertificateSerialNumber(BigInteger.ONE)
                            .setKeyValidityStart(start.time)
                            .setKeyValidityEnd(end.time)
                            .build()
                } else {
                    // deprecated version >= 23
                    spec = KeyPairGeneratorSpec.Builder(context)
                            .setAlias(KEY_ALIAS)
                            .setSubject(X500Principal(String.format("CN=%s, OU=%s", KEY_ALIAS, context.packageName)))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.time)
                            .setEndDate(end.time)
                            .build()
                }
                val generator: KeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, ANDROID_KEY_STORE)
                generator.initialize(spec)
                val kp: KeyPair = generator.generateKeyPair()
            }
            generated = true
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: NoSuchProviderException) {
        } catch (e: InvalidAlgorithmParameterException) {
        } catch (e: CertificateException) {
        } catch (e: KeyStoreException) {
        } catch (e: IOException) {
        } catch (e: IllegalStateException) {
        } catch (e: NullPointerException) {
        }
        return generated
    }

    @Throws(CertificateException::class, NoSuchAlgorithmException::class, IOException::class, KeyStoreException::class, UnrecoverableEntryException::class)
    private fun getPublicKey(): RSAPublicKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        val privateKeyEntry = keyStore.getKey(KEY_ALIAS, null) as PrivateKey
        return keyStore.getCertificate(KEY_ALIAS).publicKey as RSAPublicKey
    }

    @Throws(KeyStoreException::class, CertificateException::class, NoSuchAlgorithmException::class, IOException::class, UnrecoverableEntryException::class)
    private fun getPrivateKeyEntry(): PrivateKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        return keyStore.getKey(KEY_ALIAS, null) as PrivateKey
    }

    fun encrypt(initialText: String): String? {
        var result: String? = null
        try {
            val input = Cipher.getInstance(TRANSFORMATION)
            input.init(Cipher.ENCRYPT_MODE, getPublicKey())
            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(outputStream, input)
            cipherOutputStream.write(initialText.toByteArray(CHARSET))
            cipherOutputStream.close()
            val vals = outputStream.toByteArray()
            result = Base64.encodeToString(vals, Base64.DEFAULT)
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: NoSuchPaddingException) {
        } catch (e: InvalidKeyException) {
        } catch (e: CertificateException) {
        } catch (e: IOException) {
        } catch (e: KeyStoreException) {
        } catch (e: UnrecoverableEntryException) {
        } catch (e: NullPointerException) {
        }
        return result
    }

    fun decrypt(cipherText: String): String? {
        var result: String? = null
        try {
            val output = Cipher.getInstance(TRANSFORMATION)
            output.init(Cipher.DECRYPT_MODE, getPrivateKeyEntry())

            val cipherInputStream = CipherInputStream(ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)), output)
            val values = ArrayList<Byte>()
            var nextByte: Int = cipherInputStream.read()
            while (nextByte != -1) {
                values.add(nextByte.toByte())
                nextByte = cipherInputStream.read()
            }
            val bytes = ByteArray(values.size)
            for (i in bytes.indices) {
                bytes[i] = values[i]
            }

            result = String(bytes, 0, bytes.size, CHARSET)
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: NoSuchPaddingException) {
        } catch (e: InvalidKeyException) {
        } catch (e: KeyStoreException) {
        } catch (e: CertificateException) {
        } catch (e: IOException) {
        } catch (e: UnrecoverableEntryException) {
        } catch (e: NullPointerException) {
        }
        return result
    }
}