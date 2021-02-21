package com.hnb.huinongbang

import androidx.core.content.edit
import com.hnb.huinongbang.logic.model.UserResponse
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.logic.network.UserService
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        print(666666)
    }
    @Test
    fun dadada(){
        val testService = ServiceCreator.create<TestService>()
        print(666666)
        testService.buy(arrayOf("70", "72"), 0).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val userResponse = response.body()
                print(666666)
                print(userResponse)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                print(666666)
            }
        })
    }
}