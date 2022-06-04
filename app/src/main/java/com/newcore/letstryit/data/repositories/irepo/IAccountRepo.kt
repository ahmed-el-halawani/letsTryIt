package com.newcore.letstryit.data.repositories.irepo

import com.newcore.letstryit.data.entites.Account

interface IAccountRepo {
    fun getAccount(email: String): Account?
    fun getAccountWithEmailAndPassword(email: String,password:String): Account?
    fun getAccounts(): List<Account>
    fun isAccountExist(email:String): Boolean
    fun insert(account: Account): Int
    fun update(account: Account): Int
    fun delete(id: Int): Int
}