package com.newcore.letstryit.data.repositories

import com.newcore.letstryit.data.entites.Account
import com.newcore.letstryit.data.repositories.irepo.IAccountRepo
import com.newcore.letstryit.data.repositories.irepo.IAuthenticationFacade
import com.newcore.letstryit.data.repositories.irepo.ISettingsSharedPreferenceRepo
import com.newcore.letstryit.core.util.Either
import com.newcore.letstryit.core.util.exceptions.*

class AuthenticationFacade(
    private val accountRepo: IAccountRepo,
    private val settingsSharedPreference: ISettingsSharedPreferenceRepo,
) : IAuthenticationFacade {
    override fun login(email: String, password: String): Either<ILoginFailure, Account> {
        return accountRepo.getAccountWithEmailAndPassword(email, password)?.let { account ->
            settingsSharedPreference.update {
                it.apply { this.account = account }
            }
            Either.Success(account)
        } ?: Either.Failure(InValidEmailOrPassword)
    }

    override fun register(account: Account): Either<IRegisterFailure, Account> {
        if (!accountRepo.isAccountExist(account.email))
            return Either.Failure(UserAlreadyExist)

        accountRepo.insert(account)
        return accountRepo.getAccount(account.email)?.let {
            settingsSharedPreference.update { settings ->
                settings.apply { this.account = account }
            }
            Either.Success(it)
        } ?: Either.Failure(SomethingWrongHappened)
    }

    override fun signOut() = settingsSharedPreference.update { settings ->
        settings.apply { account = null }
    }


}