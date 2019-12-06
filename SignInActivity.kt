package com.apps.guida.recipes

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.apps.guida.recipes.ui.main.PageViewModel
import com.apps.guida.recipes.ui.main.PlaceholderFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity: AppCompatActivity() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mUser: FirebaseUser? = null
    private var mUserName: EditText? = null
    private var mNewUserName: EditText? = null
    private var mNewPassword: EditText? = null
    private var mSignInButton: Button? = null
    private var mPassword: EditText? = null
    private var mCreateAccountButton: Button? = null
    private var mCreateAccountAndSignInButton: Button? = null


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        Log.d("sign_in", "Checking user authentication...")
        mUser  = mAuth!!.currentUser
        if(mUser != null) {
            Log.d("sign_in", "Status: User is already signed in as ${mUser?.uid}...")
            Toast.makeText(this, "Already signed in", Toast.LENGTH_SHORT).show()
            //update ui
        } else {
            Log.d("sign_in", "Status: User is not already signed in...")
            Toast.makeText(this, "Not already signed in", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_main)

        //get firebase instance
        mAuth = FirebaseAuth.getInstance()

        //get user name
        mUserName = findViewById(R.id.editText2)

        //get password
        mPassword = findViewById(R.id.editText)

        //get buttons
        mSignInButton = findViewById(R.id.button)
        mCreateAccountButton = findViewById(R.id.button2)

        //click lambda for sign in button
        mSignInButton?.setOnClickListener {
            mAuth?.signInWithEmailAndPassword(mUserName!!.text.toString(), mPassword!!.text.toString())!!
                .addOnCompleteListener(this) { task ->
                    try {
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("sign_in", "Failure -- due to ${task.result}")
                        }
                    } catch (e: Exception) {
                        //If sign in causes an exception make sure you catch it
                        Log.d("sign_in", "Failed to create a new account because ${e.cause}")
                        Toast.makeText(this, "Cant login: ${e.cause}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        //click lambda for create account button
        mCreateAccountButton?.setOnClickListener {
            setContentView(R.layout.signin_create)
            Log.d("sign_in", "Creating new account...")

            //Get created password
            mNewPassword = findViewById(R.id.CreatePassword)

            //Get created username
            mNewUserName = findViewById(R.id.CreateEmail)

            //Get create account button
            mCreateAccountAndSignInButton = findViewById(R.id.CreateAccount)

            mCreateAccountAndSignInButton?.setOnClickListener {
                mAuth?.createUserWithEmailAndPassword(mNewUserName?.text.toString(), mNewPassword?.text.toString())!!
                    .addOnCompleteListener(this) { task ->
                        try {
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d(Constants.LOG_SIGNIN, "Failed to create a new account because ${task.result}")
                            }
                        } catch (e: Exception) {
                            Log.d(Constants.LOG_SIGNIN, "Failed to create a new account because ${e.cause}")
                        }

                    }
            }
        }
    }
}