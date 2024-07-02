package com.tenkovskaya.fitnes.data.database.workout

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WorkoutDataSourceImpl : WorkoutDataSource {
    private val database = FirebaseDatabase.getInstance().reference

    override suspend fun getWorkoutDescriptions(): Map<String, String> {
        return suspendCoroutine { continuation ->
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("TAGGG WorkoutDataSource", "Snapshot received: ${snapshot.value}")
                    val descriptions = snapshot.children.associate {
                        it.key!! to it.getValue(String::class.java)!!
                    }
                    Log.d("TAGGG WorkoutDataSource", "Descriptions loaded: $descriptions")
                    continuation.resume(descriptions)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAGGG WorkoutDataSource", "Error loading descriptions", error.toException())
                    continuation.resumeWithException(error.toException())
                }
            })
        }
    }
}

