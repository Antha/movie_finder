package com.codeathome.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeathome.myapplication.R
import com.codeathome.myapplication.adapter.UserAdapter
import com.codeathome.myapplication.api.RetrofitClient
import com.codeathome.myapplication.model.User
import com.codeathome.myapplication.model.UserResponse
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    var list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        showUsers()
    }

    private fun showUsers(){
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getusers().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                tvResponseCode.text = response.code().toString()
                val listResponse : ArrayList<User>? = response.body()?.data
                listResponse?.let {
                    list.addAll(it)
                }
                val adapter = UserAdapter(list)
                rvUser.adapter = adapter

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        });
    }
}