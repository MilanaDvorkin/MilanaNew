package com.example.milananew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val email = findViewById<EditText>(R.id.editEmailRegister);
        val password = findViewById<EditText>(R.id.editPasswordRegister);

        val loginButton = findViewById<Button>(R.id.btnLoginRegister);

        loginButton.setOnClickListener {
            val auth = Firebase.auth

            val emailValid = Patterns.EMAIL_ADDRESS.matcher(email.text).matches()
            val passwordValid = password.text.length in 6..<16

            if (!emailValid || !passwordValid) {
                Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_LONG).show()
            } else {

                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
                    }

            }
        }
    }
}