package com.example.firebaseintagram

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow

class FirebaseRepo {

    val postList = MutableStateFlow<MutableList<Post>>(arrayListOf())

    fun getPostData(database:DatabaseReference){
        val dataRef = database.child("users").child("post")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<Post>()
                snapshot.children.forEach {
                    val postI = it.getValue<Post>()
                    println("Success: $postI")
                    postI?.let { element ->
                        list.add(element)
                    }
                    postList.value = list
                }
            }

            override fun onCancelled(error: DatabaseError) {

                println("error : $error")
            }

        })
    }
}