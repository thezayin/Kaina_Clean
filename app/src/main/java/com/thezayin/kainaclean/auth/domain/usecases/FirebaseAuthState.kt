package com.thezayin.kainaclean.auth.domain.usecases

import com.thezayin.kainaclean.auth.domain.repository.AuthRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.getFirebaseAuthState()
}