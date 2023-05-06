package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val updateName = binding.updateName.text.toString()
            val updateCardNumber = binding.updateCardNumber.text.toString()

            updateData(updateName, updateCardNumber)
        }
    }

    private fun updateData(name: String, CardNumber: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "name" to name
        )

        database.child(CardNumber).updateChildren(user).addOnSuccessListener {

            binding.updateCardNumber.text.clear()
            binding.updateName.text.clear()

            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}