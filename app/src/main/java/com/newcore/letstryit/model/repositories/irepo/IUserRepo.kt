package com.newcore.letstryit.model.repositories.irepo

import com.newcore.letstryit.model.entites.User

interface IUserRepo {
    fun getUser(id:Int): User?
    fun getUsers() : List<User>
    fun insert(user:User) : Int
    fun update(user:User) : Int
    fun delete(id: Int):Int
}