package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.Prefs
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var prefs: Prefs

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== RESULT_OK){
            val task= GoogleSignIn
                .getSignedInAccountFromIntent(it.data)
            try {
                val cuenta= task.getResult(ApiException::class.java)
                if (cuenta!=null){
                    val credenciales= GoogleAuthProvider.getCredential(cuenta.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales).addOnCompleteListener {
                        if (it.isSuccessful){
                            goApp(cuenta.email?:"")
                        }else{
                            fallo()
                        }
                    }
                }
            }catch (e : ApiException){
                Log.d("ERROR Google --->>>",e.message.toString())
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs= Prefs(this)
        setListener()
        sesion()
    }

    private fun sesion() {
        val e= prefs.leerEmail()
        if (!e.isNullOrEmpty()){
            goApp(e)
        }
    }

    private fun goApp(email: String?) {
        val i =Intent(this, InicioActivity::class.java).apply {
            putExtra("EMAIL", email)
        }
        startActivity(i)
    }

    private fun setListener() {
        binding.btnGoogle.setOnClickListener {
            iniciarSesion()
        }
    }

    private fun iniciarSesion() {
        val gso= GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("129422731235-k0la6cjsa1a8v2krjkp8offvmep9p13m.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleClient= GoogleSignIn.getClient(this,gso)
        googleClient.signOut()

        responseLauncher.launch(googleClient.signInIntent)
    }

    private fun fallo() {
        val alert = AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(getString(R.string.errorMenssage))
            .setPositiveButton(getString(R.string.errorButton), null)
            .create()
            .show()
    }

}