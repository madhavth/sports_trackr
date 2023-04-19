package com.madhav.sportstrackr.features.favorite.data.data_sources.remote

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


const val FAVORITE_TEAM  = "favorite_teams"
const val USERS = "users"

class FavoritesRemoteDataSource @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getFavoriteTeams(signedInAccount: String): Flow<List<FavoriteTeam>> {
        return callbackFlow {
            val listener =
                EventListener<QuerySnapshot> { value, error ->
                    if (error != null) {
                        Timber.e("Firebase RemoteDataSource Error getting documents: $error")
                        return@EventListener
                    }
                    val favoriteTeams = mutableListOf<FavoriteTeam>()
                    for (doc in value!!) {
                        val favoriteTeam = doc.toObject(FavoriteTeam::class.java)
                        favoriteTeams.add(favoriteTeam)
                    }

                    this.channel.trySend(favoriteTeams)
                }

            val favoriteTeamCollection = firebaseFirestore.collection(USERS)
                .document(signedInAccount)
                .collection(FAVORITE_TEAM)

            favoriteTeamCollection.addSnapshotListener(listener)

            awaitClose {
            }
        }
    }

    fun addFavoriteTeam(favoriteTeam: FavoriteTeam) {
        val userId = GoogleSignIn.getLastSignedInAccount(applicationContext)?.id ?: return

        firebaseFirestore.collection(USERS)
            .document(userId)
            .collection(FAVORITE_TEAM)
            .document(favoriteTeam.id)
            .set(favoriteTeam)
    }

    fun removeFavoriteTeam(teamId: String) {
        val userId = GoogleSignIn.getLastSignedInAccount(applicationContext)?.id ?: return

        firebaseFirestore.collection(USERS)
            .document(userId)
            .collection(FAVORITE_TEAM)
            .document(teamId)
            .delete()
    }

    fun isFavoriteTeam(teamId: String): Flow<Boolean> {
        return callbackFlow {
            val userId =
                GoogleSignIn.getLastSignedInAccount(applicationContext)?.id ?: return@callbackFlow

            firebaseFirestore.collection(USERS)
                .document(userId)
                .collection(FAVORITE_TEAM)
                .document(teamId)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Timber.e("FirebaseRemoteDataSource Error getting documents: $error")
                        return@addSnapshotListener
                    }
                    channel.trySend(value?.exists() ?: false)
                }

            awaitClose {

            }
        }
    }
}