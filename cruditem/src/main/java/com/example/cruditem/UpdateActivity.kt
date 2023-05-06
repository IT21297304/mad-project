package com.example.cruditem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cruditem.databinding.ActivityUpdateBinding
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
            val referencePhone = binding.referencePhone.text.toString()
            val updateName = binding.updateName.text.toString()

            updateData(referencePhone,updateName)
        }
    }

    private fun updateData(phone: String, name: String) {

        database = FirebaseDatabase.getInstance().getReference("Items")
        val user = mapOf<String,String>(
            "name" to name
        )

        database.child(phone).updateChildren(user).addOnSuccessListener {

            binding.referencePhone.text.clear()
            binding.updateName.text.clear()

            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}