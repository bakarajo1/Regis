package com.example.regis

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.regis.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var age : String
    lateinit var name : String
    lateinit var email : String
    lateinit var  lastname : String
    lateinit var desc: String



    var editor: Editor? = null

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        var myyList: List<String>? = null


        //init()
    }
    fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun valid():Boolean {

        when {
            binding.editTextAge.text.isNullOrEmpty() ||
                    binding.editTextTextEmail.text.isNullOrEmpty() ||
                    binding.editTextTextPersonName.text.isNullOrEmpty() ||
                    binding.editTextTextPersonName2.text.isNullOrEmpty()
            -> {toast(
                    CHECK
            )
                return false}

        }

        return true

    }
    fun update(view: View) {}








    fun exists() : Boolean {

        val keys: Map<String, *> = sharedPreferences.getAll()
        for ((key, value) in keys) {

            if (value == desc){


                toast(EXISTS)

                return true
            }



        }
        return false
    }
    fun add(view: View) {



        age = binding.editTextAge.getText().toString()
        name = binding.editTextTextPersonName.getText().toString()
        email= binding.editTextTextEmail.getText().toString()
        lastname = binding.editTextTextPersonName2.getText().toString()

        if (valid()) {

            desc = "$name  $lastname , $age wlis ,  Email: $email"






            if (!exists()) {
                editor!!.putString("person $count", desc)



                editor!!.commit()

                binding.ActiveTextView.setText("active users: $count")

                toast(desc)

                count++

            }
        }
    }
    fun remove(view: View) {

        val keys: Map<String, *> = sharedPreferences.getAll()

        age = binding.editTextAge.getText().toString()
        name = binding.editTextTextPersonName.getText().toString()
        email= binding.editTextTextEmail.getText().toString()
        lastname = binding.editTextTextPersonName2.getText().toString()

        desc= "$name $lastname , $age wlis ,  Email: $email"

        for ((key, value) in keys) {
            Log.d("map values", key + ": " +
                    value.toString())

            if (value == desc){

                editor?.remove(key)

                editor?.commit()

                count--
                binding.ActiveTextView.setText("active users: $count")
                toast(REMOVED)
            }

            else
                toast(FIND)
        }



    }

    companion object {

        const val CHECK = "Sheavset velebi"
        const val FIND = "Not Found"
        const val REMOVED = "User Removed"
        var count: Int=0
        const val EXISTS = "User Exists"

    }
}