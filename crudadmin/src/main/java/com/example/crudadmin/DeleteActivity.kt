package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val seller = binding.deleteSeller.text.toString()
            if (seller.isNotEmpty())
                deleteData(seller)
            else
                Toast.makeText(this, "Please enter seller name", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(seller: String){
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(seller).removeValue().addOnSuccessListener {
            binding.deleteSeller.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}