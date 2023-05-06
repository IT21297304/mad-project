package com.example.crud_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_client.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchPhone : String = binding.searchPhone.text.toString()
            if  (searchPhone.isNotEmpty()){
                readData(searchPhone)
            }else{
                Toast.makeText(this,"PLease enter the phone number",Toast.LENGTH_SHORT).show()
            }
        }
    }




    private fun readData(phone:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("shipping")
        databaseReference.child(phone).get().addOnSuccessListener {
            if(it.exists()){
                val name = it.child("name").value
                val address = it.child("address").value
                val postalCode = it.child("postalCode").value
                Toast.makeText(this,"Result Found",Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = name.toString()
                binding.readAddress.text = address.toString()
                binding.readPostalCode.text = postalCode.toString()


            }else{
                Toast.makeText(this,"Phone Number Does Not Exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
        }
    }
}
