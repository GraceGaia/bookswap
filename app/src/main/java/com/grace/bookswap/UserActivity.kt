package com.grace.bookswap

import android.app.Activity
import android.content.Intent
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    var picha: ImageView? = null
    var userName: EditText? = null
    var eMail: EditText? = null
    var buttonSave: Button? = null
    var buttonSignOut: TextView? = null

    private lateinit var auth: FirebaseAuth
    private var fileUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        //picha = findViewById(R.id.iv_profileImage)
        userName = findViewById(R.id.et_profileUsername)
        eMail = findViewById(R.id.et_profileEmail)
        buttonSave = findViewById(R.id.btn_profileSaveInfo)
        buttonSignOut = findViewById(R.id.tv_profile_signOut)

//        buttonSave!!.setOnClickListener {
//            var usersIntent = Intent(this,StatusActivity::class.java)
//            startActivity(usersIntent)
//        }


        auth = FirebaseAuth.getInstance()
        setUserInfo()

//        btnClicks()
    }

    private fun btnClicks(){
        buttonSignOut!!.setOnClickListener{
            signOutUser()
        }

        buttonSave!!.setOnClickListener(){
            saveUserInfo()
        }

//        picha!!.setOnClickListener{
//            selectImage()
//        }
    }

    private fun setUserInfo(){
        eMail!!.setText(auth.currentUser?.email)
        userName!!.setText(auth.currentUser?.displayName)
        //picha!!.setImageURI(auth.currentUser?.photoUrl)

        fileUri = auth.currentUser?.photoUrl
    }

    private fun saveUserInfo(){
        auth.currentUser?.let {
            val username:String = userName!!.text.toString()
            //val userProfilePicture:Uri =fileUri!!
            val userEmail = eMail!!.text.toString()

//            val update:UserProfileChangeRequest = UserProfileChangeRequest.Builder()
//                .setDisplayName(username)
//                .setPhotoUri(userProfilePicture)
//                .build()
            GlobalScope.launch(Dispatchers.IO){
                try {
                    //it.updateProfile(update).await()
                    it.updateEmail(userEmail)

                    withContext(Dispatchers.Main){
                        setUserInfo()

                        Toast.makeText(this@UserActivity,"Profile successfully completed",Toast.LENGTH_SHORT).show()
                    }

                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UserActivity,e.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun signOutUser(){
        auth.signOut()
        val i = Intent(this, SignInActivity::class.java)
        startActivity(i)

        Toast.makeText(this,"Successfully signed out", Toast.LENGTH_SHORT).show()
    }

    fun saveButtonListener(view: View) {
        startActivity(Intent(this,StatusActivity::class.java))
    }
//    private fun selectImage(){
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, 1001)
//    }


//            ImagePicker.RESULT_ERROR ->{
//               Toast.makeText(this,ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//            }
//            else -> {
//                Toast.makeText(this,"Task cancelled", Toast.LENGTH_SHORT).show()
//            }
        }

