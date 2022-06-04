package com.newcore.letstryit.data.repositories.irepo

import com.newcore.letstryit.data.entites.User

interface IUserRepo {
    fun getUser(id:Int): User?
    fun getUsers() : List<User>
    fun insert(user:User) : Int
    fun update(user:User) : Int
    fun delete(id: Int):Int
}