
package com.example.charging_station_app

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.SmsManager

import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import java.util.ArrayList
import com.google.firebase.FirebaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.DatabaseReference
import javax.mail.Transport.send

class Stationdetails : AppCompatActivity() {

    private lateinit var etFreeSlots: EditText
    private lateinit var databaseReference: DatabaseReference

    // Other variables and methods...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stationdetails)

        // Initialize Views
        val image = findViewById<ImageView>(R.id.image1)
        val txtName = findViewById<TextView>(R.id.txtname)
        val txtMaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtNumber = findViewById<TextView>(R.id.number)
        val txtManufacture = findViewById<TextView>(R.id.txtmanifacture)
        val txtOrigin = findViewById<TextView>(R.id.txtorigin)
        etFreeSlots = findViewById(R.id.etFreeSlots)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference.child("station")

        // Retrieve data from intent
        val bundle = intent.extras
        val name = bundle?.getString("name")
        val material = bundle?.getString("address")
        val number = bundle?.getString("number")
        val manufacture = bundle?.getString("tslot")
        val origin = bundle?.getString("fslot")

        // Set values to Views
        txtName.text = name
        txtMaterial.text = material
        txtNumber.text = number
        txtManufacture.text = manufacture
        txtOrigin.text = origin
        etFreeSlots.setText(origin)

        // Update button click listener
        val btnUpdateSlot = findViewById<AppCompatButton>(R.id.btnorder)
        btnUpdateSlot.setOnClickListener {
            updateFreeSlots(name, etFreeSlots.text.toString())
        }
    }

    private fun updateFreeSlots(name: String?, newFreeSlots: String) {
        databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val key = snapshot.key
                        key?.let {
                            databaseReference.child(it).child("fslot").setValue(newFreeSlots)
                                .addOnSuccessListener {
                                    Toast.makeText(applicationContext, "Free slots updated successfully", Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener {
                                    Toast.makeText(applicationContext, "Failed to update free slots", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                } else {
                    Toast.makeText(applicationContext, "Station not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}











//
//package com.example.charging_station_app
//
//import android.annotation.SuppressLint
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.preference.PreferenceManager
//import android.telephony.SmsManager
//
//import android.widget.*
//import androidx.core.app.NotificationCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.google.firebase.database.*
//import java.util.ArrayList
//import com.google.firebase.FirebaseError
//
//import com.google.firebase.database.DataSnapshot
//
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database.DatabaseError
//
//import com.google.firebase.database.FirebaseDatabase
//
//import com.google.firebase.database.DatabaseReference
//import javax.mail.Transport.send
//
//
//class Stationdetails : AppCompatActivity() {
//
//    val MY_PREFS_NAME = "MyPrefsFile"
//    var name:String?=null
//    var material:String?=null
//    var manifacute:String?=null
//    var origin:String?=null
//    var weight:String?=null
//    var rating:String?=null
//    var demi:String?=null
//    var price:String?=null
//
//    var ref: DatabaseReference? = null
//    var username:String?=null
//    var usermobile:String?=null
//    var useremail:String?=null
//    var useraddress:String?=null
//    var url:String?=null
//    var number:String?=null
//    var email:String?=null
//    var no:Int?=null
//    var unumber:String?=null
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_stationdetails)
//
//
//        val prefs = getSharedPreferences("MY", MODE_PRIVATE)
//
//        unumber = prefs.getString("number", "default")
//
//        Toast.makeText(applicationContext,unumber.toString(),Toast.LENGTH_LONG).show()
//
//        val txtproname = findViewById<TextView>(R.id.txtname)
//        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
//        val txtnumber = findViewById<TextView>(R.id.number)
//        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)
//
//        val txtarea = findViewById<TextView>(R.id.txtorigin)
//
//
//        val image = findViewById<ImageView>(R.id.image1)
//
//        val bundle = intent.extras
//
//        name = bundle?.getString("name")
//        material = bundle?.getString("address")
//        number = bundle?.getString("number")
//        manifacute=bundle?.getString("tslot")
//        origin = bundle?.getString("fslot")
//        url = bundle?.getString("url")
//
//
//        Glide.with(this@Stationdetails).load(url).into(image)
//
//
//        txtproname.setText("Station Name:" +name)
//        txtmaterial.setText("Address: "+material)
//        txtnumber.setText("Number :"+number)
//        txtaddress.setText("Total solt: "+manifacute)
//        txtarea.setText("Free Solt: "+origin)
//
//        val btnuser = findViewById<TextView>(R.id.btnorder)
//
//        btnuser.setOnClickListener {
//            Toast.makeText(applicationContext,"Slot Booked", Toast.LENGTH_LONG).show()
//
//            val name = txtproname.text.toString().trim()
//            val address = txtmaterial.text.toString().trim()
//            val number = txtnumber.text.toString().trim()
//            val tslot = txtaddress.text.toString().toString()
//            val fslot = txtarea.text.toString().toString()
//
//            val msg = name+address+number
//
//
//            val message = "Your slot has been booked at $name. Address: $address. Contact Number: $number"
//
//
//            updatedata()
//            sendnotify(message)
//        }
//
//
//
//
//    }
//
//    private fun sendnotify(msg: String) {
//
//        val smsManager = SmsManager.getDefault() as SmsManager
//        smsManager.sendTextMessage("+91$number",null,"YOur  Slot Book at",null,null)
//
//        val smsManager1 = SmsManager.getDefault() as SmsManager
//        smsManager1.sendTextMessage("+91$unumber",null, msg,null,null)
//
//
//    }
//
//
//    private fun updatedata() {
//        no = origin?.toInt()
//        if (no!! > 0) {
//            no = no?.minus(1)
//            println(no)
//            val value = no.toString()
//            Toast.makeText(applicationContext, value.toString(), Toast.LENGTH_LONG).show()
//            Toast.makeText(applicationContext, name.toString(), Toast.LENGTH_LONG).show()
//
//
//            val mDatabaseRef =
//                FirebaseDatabase.getInstance().getReference("station").orderByChild("name")
//                    .equalTo(name)
//            mDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
////                println(snapshot)
//                    for (obj in snapshot.children) {
////                   println(obj)
//                        println(obj.key)
//
//                        val key = obj.key
//
//                        val databaseReference =
//                            FirebaseDatabase.getInstance().getReference("station").child(key!!)
//                        databaseReference.child("name").setValue(name)
//                        databaseReference.child("address").setValue(material)
//                        databaseReference.child("number").setValue(number)
//                        databaseReference.child("tslot").setValue(manifacute)
//                        databaseReference.child("fslot").setValue(value)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//        }else{
//            Toast.makeText(applicationContext, "Slots are not available", Toast.LENGTH_LONG).show()
//
//
//        }
//    }
//}
//
