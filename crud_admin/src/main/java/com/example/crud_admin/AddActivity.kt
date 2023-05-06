package com.example.crud_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_admin.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val phone = binding.uploadPhone.text.toString()
            val address = binding.uploadAddress.text.toString()
            val postalCode = binding.uploadPostalCode.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("shipping")
            val users = UserData(name,phone,address,postalCode)
            databaseReference.child(phone).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadPhone.text.clear()
                binding.uploadAddress.text.clear()
                binding.uploadPostalCode.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@AddActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }


    }


}
