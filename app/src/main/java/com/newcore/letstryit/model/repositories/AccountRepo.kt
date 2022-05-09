package com.newcore.letstryit.model.repositories

import android.content.ContentResolver
import com.newcore.letstryit.model.entites.Account
import com.newcore.letstryit.model.local.contentprovider.Routes
import com.newcore.letstryit.model.repositories.irepo.IAccountRepo
import com.newcore.letstryit.util.Constants.EMAIL
import com.newcore.letstryit.util.Constants.ID
import com.newcore.letstryit.util.Constants.PASSWORD

class AccountRepo(private val contentResolver: ContentResolver) : IAccountRepo {

    private val accountsRoute = Routes.routeFactory(Routes.Accounts)
    private val accountRoute = Routes.routeFactory(Routes.AccountsId)

    fun String.ignoreCase() = lowercase()

    override fun getAccount(email: String): Account? {
        return contentResolver.query(
            accountRoute,
            null,
            "$EMAIL = ?",
            arrayOf(email.ignoreCase()),
            null,
        )?.let { cursor ->
            if (cursor.moveToFirst())
                Account.fromCursor(cursor)
            else
                null
        }
    }

    override fun getAccountWithEmailAndPassword(email: String, password: String): Account? {
        return contentResolver.query(
            accountRoute,
            null,
            "$EMAIL = ? and $PASSWORD = ?",
            arrayOf(email.ignoreCase(), password),
            null,
        )?.let { cursor ->
            if (cursor.moveToFirst())
                Account.fromCursor(cursor)
            else
                null
        }
    }

    override fun getAccounts(): List<Account> {
        return contentResolver.query(
            accountsRoute,
            null,
            null,
            null,
            null,
        )?.let { cursor ->
            ArrayList<Account>().apply {
                while (cursor.moveToNext()) {
                    add(Account.fromCursor(cursor))
                }
            }
        } ?: emptyList()
    }

    override fun isAccountExist(email:String): Boolean {
        return getAccount(email) != null
    }

    override fun insert(account: Account): Int {
        return contentResolver.insert(
            accountRoute,
            account.apply { email.ignoreCase() }.toContentValues()
        )?.port ?: -1
    }

    override fun update(account: Account): Int {
        return contentResolver.update(
            accountRoute,
            account.toContentValues(),
            "$ID= ?",
            arrayOf("${account.id}")
        )
    }

    override fun delete(id: Int): Int {
        return contentResolver.delete(
            accountRoute,
            "$ID= ?",
            arrayOf("$id")
        )
    }


}