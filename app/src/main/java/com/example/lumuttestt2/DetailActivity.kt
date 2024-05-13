package com.example.lumuttestt2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lumuttestt2.PlaceHolder
import com.example.lumuttestt2.R
import com.example.lumuttestt2.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var retrofit: Retrofit
    private lateinit var service: PlaceHolder
    private lateinit var imageView: ImageView
    private lateinit var nameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.imageView)
        nameTextView = findViewById(R.id.nameTextView)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PlaceHolder::class.java)

        val id = intent.getIntExtra("todoId", 1)


        val call = service.getTodoById(id)
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    val todo = response.body()
                    todo?.let {
                        // Update views with data
                        nameTextView.text = todo.title
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
            }
        })
    }
}
