package com.example.charging_station_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.ArrayList

class ShowStations : AppCompatActivity() {
    var ref: DatabaseReference? = null
    var list: ArrayList<data>? = null
    private var listener: ShowAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_stations)

        ref = FirebaseDatabase.getInstance().reference.child("station")
        recyclerView = findViewById(R.id.recyclerview)

    }

    override fun onStart() {
        super.onStart()
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {

                            list!!.add(ds.getValue(data::class.java)!!)
                        }

                        setOnClickListner()


                        val adapter = ShowAdapter(list,listener)

                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ShowStations, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }



    }

    private fun setOnClickListner() {
        listener = ShowAdapter.RecyclerViewClickListener { v, position ->

            val intent = Intent(applicationContext, Stationdetails::class.java)
            intent.putExtra("name", list!![position].name)
            intent.putExtra("address", list!![position].address)
            intent.putExtra("number", list!![position].number)

            intent.putExtra("tslot", list!![position].tslot)
            intent.putExtra("fslot", list!![position].fslot)
            intent.putExtra("url",list!![position].imageurl)




            startActivity(intent)
        }
    }


}


