package com.example.lumuttestt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var retrofit: Retrofit
    private lateinit var service: PlaceHolder
    private lateinit var adapter: TodoAdapter
    private lateinit var recyclerView: RecyclerView // Declare RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PlaceHolder::class.java)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = TodoAdapter { itemId ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("todoId", itemId) // Pass the clicked item's ID to the DetailActivity
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val call = service.getTodos()
        call.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful) {
                    val todos = response.body()
                    todos?.let {
                        adapter.setData(it)
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {

            }
        })
    }
}
