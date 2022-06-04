package com.newcore.letstryit.data.repositories.irepo

import com.newcore.letstryit.data.entites.Account
import com.newcore.letstryit.core.util.Either
import com.newcore.letstryit.core.util.exceptions.ILoginFailure
import com.newcore.letstryit.core.util.exceptions.IRegisterFailure

interface IAuthenticationFacade {
    fun login(email: String, password: String): Either<ILoginFailure, Account>
    fun register(account: Account): Either<IRegisterFailure, Account>
    fun signOut()
}