package com.hnb.huinongbang.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.logic.model.User

//用户的SharedPerferences
object UserDAO {

    //存储用户
    fun saveUser(user: User) {
        sharedPreferences().edit {
            putString("user", Gson().toJson(user))
        }
    }
    //清除用户
    fun clearUser(user: User) {
        sharedPreferences().edit {
            putString("user", null)
        }
    }
    //获取用户
    fun getUser(): User {
        val userJson = sharedPreferences().getString("user", "")
        return Gson().fromJson(userJson, User::class.java)
    }
    //用户是否存在
    fun isUserSaved() = sharedPreferences().contains("user")
    //是否记住密码
    fun isRemembered() = sharedPreferences().getBoolean("remember_password", false)
    //记住密码
    fun remember() = sharedPreferences().edit {
        putBoolean("remember_password", true)
    }
    //不记住密码
    fun unremember() = sharedPreferences().edit {
        putBoolean("remember_password", false)
    }
    //获取SP
    private fun sharedPreferences() = HNBApplication.context.getSharedPreferences("HNB", Context.MODE_PRIVATE)
}