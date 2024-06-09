package com.jes.wikiworld

object Singleton {
    private var userName: String? = null

    fun setUserName(name: String) {
        userName = name
    }

    fun getUserName(): String? {
        return userName
    }
}