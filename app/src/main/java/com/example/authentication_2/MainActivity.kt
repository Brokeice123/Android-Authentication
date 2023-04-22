package com.example.authentication_2

import android.app.backup.BackupAgentHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class MainActivity : AppCompatActivity() {

  private lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

      lateinit var mFirst:EditText
      lateinit var mSecond:EditText
      lateinit var mEmail:EditText
      lateinit var mPassword:EditText
      lateinit var mCreatebtn:Button
      lateinit var mLoginbtn:Button



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirst = findViewById(R.id.edtFirst)
        mSecond = findViewById(R.id.edtSecond)
        mEmail = findViewById(R.id.edtEmail)
        mPassword = findViewById(R.id.edtPassword)
        mCreatebtn = findViewById(R.id.btnCreate)
        mLoginbtn = findViewById(R.id.btnLogin)

        db = openOrCreateDatabase("users.db", MODE_PRIVATE, null)

        db.execSQL("CREATE TABLE IF NOT EXISTS users (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, Sname TEXT, email TEXT, password TEXT)")

        mCreatebtn.setOnClickListener {

          val name = mFirst.text.toString().trim()
          val Sname = mSecond.text.toString().trim()
          val email = mEmail.text.toString().trim()
          val password = mPassword.text.toString().trim()

          if (name.isEmpty() || Sname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
          }

          val values = ContentValues()
          values.put("name", name)
          values.put("Sname", Sname)
          values.put("email", email)
          values.put("password", password)
          db.insert("users", null, values)

          mFirst.text.clear()
          mSecond.text.clear()
          mEmail.text.clear()
          mPassword.text.clear()

          Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()

          val intent = Intent(this, LoginActivity::class.java)
          startActivity(intent)
          finish()

        }

        mLoginbtn.setOnClickListener {
          val gotologin = Intent(this, LoginActivity::class.java)
          startActivity(gotologin)
          finish()
        }







    }

  override fun onDestroy() {
    super.onDestroy()
    db.close()
  }

}