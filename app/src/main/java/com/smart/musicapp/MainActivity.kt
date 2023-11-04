package com.smart.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // https://rapidapi.com/deezerdevs/api/deezer-1

    lateinit var musicList: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        musicList = findViewById(R.id.musicList)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyDataPosoClass?> {
            override fun onResponse(
                call: Call<MyDataPosoClass?>,
                response: Response<MyDataPosoClass?>
            ) {
                val dataList = response.body()?.data!!

                myAdapter = MyAdapter(this@MainActivity, dataList)
                musicList.adapter = myAdapter
                musicList.layoutManager = LinearLayoutManager(this@MainActivity,)

                Log.d("sanju", "onResponse: "+ response.body())
            }

            override fun onFailure(call: Call<MyDataPosoClass?>, t: Throwable) {
                Log.d("sanju", "onFailure: "+ t.message)
            }
        })
    }
}