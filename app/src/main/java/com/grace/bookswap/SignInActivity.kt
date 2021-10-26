package com.grace.bookswap

import android.app.Activity
import android.content.Intent
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignInActivity : AppCompatActivity() {

    var picha: ImageView? =null
    var useremail: EditText? = null
    var userPassword: EditText? = null
    var buttonSignUp: Button? = null
    var buttonLogIn: Button? = null

    private lateinit var auth: FirebaseAuth
    private var firstTimeUser = true
    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        picha = findViewById(R.id.iv_profileImage)
        useremail = findViewById(R.id.et_emailLogin)
        userPassword = findViewById(R.id.et_passwordLogin)
        buttonSignUp = findViewById(R.id.btn_register)
        buttonLogIn = findViewById(R.id.btn_login)

        auth= FirebaseAuth.getInstance()

        buttonSignUp!!.setOnClickListener {
            var usersIntent = Intent(this,StatusActivity::class.java)
            startActivity(usersIntent)
        }

//        buttonClicks()
        buttonLogIn!!.setOnClickListener{
            startActivity(Intent(this,UserActivity::class.java))
        }
        buttonSignUp!!.setOnClickListener{
            val email = useremail!!.text.toString()
            val password = userPassword!!.text.toString()
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,StatusActivity::class.java))
                }else{
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun buttonClicks(){
        buttonLogIn!!.setOnClickListener{
            firstTimeUser = false
            createOrLoginUser()
        }
        buttonSignUp!!.setOnClickListener{
            firstTimeUser = true
            createOrLoginUser()
        }
//        picha!!.setOnClickListener{
//            selectImage()
//        }
    }

    private fun createOrLoginUser(){
        val email = useremail!!.text.toString()
        val password = userPassword!!.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            GlobalScope.launch(Dispatchers.Main){
                try {
                    if (firstTimeUser){
                        auth.createUserWithEmailAndPassword(email, password).await()
                        auth.currentUser.let{
                            val update = UserProfileChangeRequest.Builder()
                                .setPhotoUri(fileUri)
                                .build()

                            it?.updateProfile(update)
                        }?.await()
                    }else{
                        auth.signInWithEmailAndPassword(email, password).await()
                    }

                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignInActivity, "You are now logged in!",Toast.LENGTH_SHORT).show()

                        val i = Intent(this@SignInActivity, UserActivity::class.java)
                        startActivity(i)
                        finish()
                    }

                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignInActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkIfUserIsLoggedIn(){
        if (auth.currentUser != null){
            val i = Intent(this@SignInActivity, UserActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        checkIfUserIsLoggedIn()
    }



//    private fun selectImage(){
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, 1001)
    }
//            }
//            ImagePicker.RESULT_ERROR ->{
//                Toast.makeText(this,ImagePicker.getError(data),Toast.LENGTH_SHORT).show()
//            }
//            else -> {
//                Toast.makeText(this,"Task cancelled", //Toast.LENGTH_SHORT).show()
//            }



