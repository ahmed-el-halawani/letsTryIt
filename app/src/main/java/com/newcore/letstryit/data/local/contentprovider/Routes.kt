package com.newcore.letstryit.data.local.contentprovider

import android.content.UriMatcher
import android.net.Uri

enum class Routes(val route: String, val code: Int) {
    Users("users", 1),
    UserId("users/#", 2),
    Accounts("accounts", 3),
    AccountsId("accounts/#", 4);

    companion object {
        fun fromCode(code: Int): Routes {
            return values().first { it.code == code }
        }

        private const val autherity = "com.newcore.letstryit.provider.mysql"

        fun routeFactory(route: Routes): Uri =
            Uri.parse("content://$autherity/${route.route}")

        val matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            matcher.addURI(autherity, Users.route, Users.code)
            matcher.addURI(autherity, UserId.route, UserId.code)
            matcher.addURI(autherity, Accounts.route, Accounts.code)
            matcher.addURI(autherity, AccountsId.route, AccountsId.code)
        }
    }
}