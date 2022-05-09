package com.newcore.letstryit.model.repositories.irepo

import com.newcore.letstryit.model.entites.Account
import com.newcore.letstryit.util.Either
import com.newcore.letstryit.util.exceptions.ILoginFailure
import com.newcore.letstryit.util.exceptions.IRegisterFailure

interface IAuthenticationFacade {
    fun login(email: String, password: String): Either<ILoginFailure, Account>
    fun register(account: Account): Either<IRegisterFailure, Account>
    fun signOut()
}