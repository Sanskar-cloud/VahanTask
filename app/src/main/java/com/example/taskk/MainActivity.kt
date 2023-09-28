package com.example.taskk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.vaaahantaskapp.RetrofitAPI
import com.example.vaaahantaskapp.Universities

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var UniversityArrayList = ArrayList<Universities>()
    private lateinit var NewsRV: RecyclerView

    private lateinit var universityAdapter: UniversityAdapter
    private lateinit var loadingPB: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        universityAdapter = UniversityAdapter(UniversityArrayList, this)
        NewsRV = findViewById(R.id.idRVNews)
        NewsRV.layoutManager = LinearLayoutManager(this)
        NewsRV.adapter = universityAdapter
        universityAdapter.notifyDataSetChanged()
        loadingPB = findViewById(R.id.loadingPB)








        val retrofit = Retrofit.Builder()
            .baseUrl("http://universities.hipolabs.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            .create(RetrofitAPI::class.java)




        val retrofitdata = retrofit.getUniversities()


        if(retrofitdata!=null){

            retrofitdata.enqueue(object : Callback<List<Universities>> {
                override fun onResponse(call: Call<List<Universities>>, response: Response<List<Universities>>) {
                    if (response.isSuccessful) {
                        loadingPB.visibility = View.GONE
                        val uniModal = response.body()
                        uniModal?.let {
                            UniversityArrayList.addAll(it)
                            universityAdapter.notifyDataSetChanged()
                        }

                    }

                }




                override fun onFailure(call: Call<List<Universities>>, t: Throwable) {
                    Log.e("Retrofit", "Network request failed: ${t.message}")
                    Toast.makeText(this@MainActivity, "Welcome to SignIn", Toast.LENGTH_SHORT).show()

                }
            })

        }




    }
}


