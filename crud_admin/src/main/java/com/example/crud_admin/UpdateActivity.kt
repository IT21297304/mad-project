package com.example.crud_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referencePhone = binding.referencePhone.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateAddress = binding.updateAddress.text.toString()
            val updatePostalCode = binding.updatePostalCode.text.toString()
            updateData(referencePhone,updateName,updateAddress,updatePostalCode)
        }
    }
    private fun updateData(phone: String, name: String, location: String, postalCode: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>("name" to name, "location" to location, "postalCode" to postalCode)

        databaseReference.child(phone).updateChildren(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateAddress.text.clear()
            binding.updatePostalCode.text.clear()
            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}
