package com.example.crudclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {

            val searchSeller : String = binding.searchSeller.text.toString()
            if  (searchSeller.isNotEmpty()){
                readData(searchSeller)
            }else{
                Toast.makeText(this,"PLease enter seller name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(seller: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(seller).get().addOnSuccessListener {

            if (it.exists()){

                val name = it.child("name").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchSeller.text.clear()
                binding.name.text = name.toString()

            }else{
                Toast.makeText(this,"Seller does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}