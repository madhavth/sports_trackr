package com.madhav.sportstrackr.core.domain.entity

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class MyUserInfo(
    val id: String,
    val fullName: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val email: String
) {
    fun getInitials(): String {
        return firstName[0].toString() + lastName[0].toString()
    }
}

fun GoogleSignInAccount.toMyUserInfo(): MyUserInfo? {
    if (
        id == null
        && displayName == null
        && email == null
    ) {
        return null
    }

    return MyUserInfo(
        id = id!!,
        fullName = displayName!!,
        firstName = givenName ?: "",
        lastName = familyName ?: "",
        image = if (photoUrl != null) photoUrl.toString() else "",
        email = email!!
    )
}