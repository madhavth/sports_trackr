package com.madhav.sportstrackr.features.favorite.data.data_sources.remote

import androidx.compose.runtime.rememberCoroutineScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FavoritesRemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {

    fun getFavoriteTeams(): Flow<List<FavoriteTeam>> {
        return callbackFlow {
            firebaseFirestore.collection("users")
                .document(firebaseAuth.currentUser?.uid.toString())
                .collection("favorite_teams")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Timber.e("Firebase RemoteDataSource Error getting documents: $error")
                        return@addSnapshotListener
                    }
                    val favoriteTeams = mutableListOf<FavoriteTeam>()
                    for (doc in value!!) {
                        val favoriteTeam = doc.toObject(FavoriteTeam::class.java)
                        favoriteTeams.add(favoriteTeam)
                    }

                    this.channel.trySend(favoriteTeams)
                }
        }
    }

    fun addFavoriteTeam(favoriteTeam: FavoriteTeam) {
        firebaseFirestore.collection("users")
            .document(firebaseAuth.currentUser?.uid.toString())
            .collection("favorite_teams")
            .document(favoriteTeam.id)
            .set(favoriteTeam)
    }

    fun removeFavoriteTeam(teamId: String) {
        firebaseFirestore.collection("users")
            .document(firebaseAuth.currentUser?.uid.toString())
            .collection("favorite_teams")
            .document(teamId)
            .delete()
    }

    fun isFavoriteTeam(teamId: String): Flow<Boolean> {
        return callbackFlow {
            firebaseFirestore.collection("users")
                .document(firebaseAuth.currentUser?.uid.toString())
                .collection("favorite_teams")
                .document(teamId)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Timber.e("FirebaseRemoteDataSource Error getting documents: $error")
                        return@addSnapshotListener
                    }
                    channel.trySend(value?.exists() ?: false)
                }
        }
    }
}