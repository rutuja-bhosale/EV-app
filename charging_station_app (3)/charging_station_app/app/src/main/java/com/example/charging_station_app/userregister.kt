package com.example.charging_station_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class userregister : AppCompatActivity() {

    val MY_PREFS_NAME = "MyPrefsFile"


    lateinit var auth: FirebaseAuth

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userregister)

        val name = findViewById<EditText>(R.id.student_name)

        val number = findViewById<EditText>(R.id.student_monumber)
        val email = findViewById<EditText>(R.id.student_email)
        val password = findViewById<EditText>(R.id.student_password)


        val btn = findViewById<Button>(R.id.btnregister)
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")

        btn.setOnClickListener {
            if (name.text.isEmpty()) {
                name.setError("Enter name")
                return@setOnClickListener
            } else if (password.text.isEmpty()) {
                password.setError("Enter Password ")
                return@setOnClickListener
            } else if (number.text.isEmpty()) {
                number.setError("Enter Contact Number")
                return@setOnClickListener
            } else if (email.text.isEmpty()) {
                email.setError("Enter Email id")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentuser = auth.currentUser
                        val currentUserdb = databaseReference?.child((currentuser?.uid!!))
                        currentUserdb?.child("name")?.setValue(name.text.toString())

                        currentUserdb?.child("number")?.setValue(number.text.toString())

                        Toast.makeText(applicationContext, "success", Toast.LENGTH_LONG).show()
//                        Toast.makeText(applicationContext,adhar.text.toString(), Toast.LENGTH_LONG).show()

                        sharedata(number.text.toString())
                    } else {
                        Toast.makeText(applicationContext, "failed", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }

    private fun sharedata(number: String) {
        val editor = getSharedPreferences("MY", MODE_PRIVATE).edit()
        editor.putString("number", number)
        editor.apply()

        editor.commit()

    }
}