package com.lucassalbu.crudappteste.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FireBaseHelper {

    companion object{

        fun getDataBase() = FirebaseDatabase.getInstance().reference

        private fun getAuth() = FirebaseAuth.getInstance()

        fun getIdUser()= getAuth().currentUser?.uid

        fun isAutenticated()= getAuth().currentUser != null

        fun validError(error: String): Int{
            return when{
                error.contains("")->{
                    1
                }
                else -> {
                    0
                }
            }
        }
    }
}