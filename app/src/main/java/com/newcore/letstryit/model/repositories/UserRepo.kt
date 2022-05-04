package com.newcore.letstryit.model.repositories

import android.content.ContentResolver
import com.newcore.letstryit.model.entites.User
import com.newcore.letstryit.model.local.contentprovider.Routes
import com.newcore.letstryit.model.local.sqlite.MySqlite
import com.newcore.letstryit.model.repositories.irepo.IUserRepo

class UserRepo(private val contentResolver: ContentResolver) : IUserRepo {

    private val usersRoute = Routes.routeFactory(Routes.Users)
    private val userRoute = Routes.routeFactory(Routes.UserId)

    override fun getUser(id: Int): User? {
        return contentResolver.query(
            userRoute,
            null,
            "${MySqlite.ID} = ?",
            arrayOf("$id"),
            null,
        )?.let { cursor ->
            User.fromCursor(cursor)
        }
    }

    override fun getUsers(): List<User> {
        return contentResolver.query(
            usersRoute,
            null,
            null,
            null,
            null,
        )?.let { cursor ->
            ArrayList<User>().apply {
                while (cursor.moveToNext()) {
                    add(User.fromCursor(cursor))
                }
            }
        } ?: emptyList()
    }

    override fun insert(user: User): Int {
        return contentResolver.insert(
            userRoute,
            user.toContentValues()
        )?.port ?: -1
    }

    override fun update(user: User): Int {
        return contentResolver.update(
            userRoute,
            user.toContentValues(),
            MySqlite.ID + "= ?",
            arrayOf("${user.id}")
        )
    }

    override fun delete(id: Int): Int {
        return contentResolver.delete(
            userRoute,
            MySqlite.ID + "= ?",
            arrayOf("$id")
        )
    }


}