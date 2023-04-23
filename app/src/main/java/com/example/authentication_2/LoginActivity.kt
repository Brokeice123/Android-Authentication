package com.example.authentication_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.view.WindowManager

class LoginActivity : AppCompatActivity() {

    private lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var lEmail:EditText
        lateinit var lPassword:EditText
        lateinit var lLoginbtn:Button
        lateinit var lCreatebtn:Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lEmail = findViewById(R.id.EmailforLogin)
        lPassword = findViewById(R.id.PasswordforLogin)
        lLoginbtn = findViewById(R.id.loginbtnforLogin)
        lCreatebtn = findViewById(R.id.CreateforLogin)

        db = openOrCreateDatabase("users.db", MODE_PRIVATE, null)

        lLoginbtn.setOnClickListener {

            val email = lEmail.text.toString().trim()
            val password = lPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            val cursor: Cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", arrayOf(email, password))
            if (cursor.moveToFirst()) {
                val intent = Intent(this, SplashScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
        }

        lCreatebtn.setOnClickListener {
            val gotosignup = Intent(this, MainActivity::class.java)
            startActivity(gotosignup)
            finish()
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

}