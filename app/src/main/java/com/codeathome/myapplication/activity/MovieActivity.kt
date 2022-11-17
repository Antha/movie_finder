package com.codeathome.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeathome.myapplication.adapter.MovieAdapter
import com.codeathome.myapplication.api.RetrofitClient
import com.codeathome.myapplication.model.Movie
import com.codeathome.myapplication.model.MovieDetail
import com.codeathome.myapplication.model.MovieResponse
import com.codeathome.myapplication.R
import kotlinx.android.synthetic.main.activity_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity()  {
    var list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            showMovies(edtMovie.text.toString());
        }
    }

    private fun showMovies(txt_movie: String){
        RetrofitClient.instance.getMovies("5cafb10f",txt_movie).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                tvResponseCode.text = response.code().toString()
                val listResponse : ArrayList<Movie>? = response.body()?.Search
                listResponse?.let {
                    list = it
                }
                val adapter = MovieAdapter(list)
                rvMovie.adapter = adapter

                adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Movie){
                        //Toast.makeText(this@MovieActivity,data.Poster, Toast.LENGTH_SHORT).show()
                        // intentObject.putExtra(IntentObjectActivity.EXTRA_MOBIL, mobil)
                        //val intentObject = Intent(this@MovieActivity, MovieDetailActivity::class.java)
                        //startActivity(intentObject)
                        RetrofitClient.instance.getMovieDetail("5cafb10f",data.imdbID).enqueue(object : Callback<MovieDetail> {
                            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                                val listResponse : MovieDetail? = response.body()
                                if (listResponse != null) {
                                    //Toast.makeText(this@MovieActivity,listResponse.Plot.toString(), Toast.LENGTH_SHORT).show()
                                    val intentObject = Intent(this@MovieActivity, MovieDetailActivity::class.java)
                                    intentObject.putExtra("TITLE", data.Title)
                                    intentObject.putExtra("POSTER", data.Poster)
                                    intentObject.putExtra("PLOT", listResponse.Plot)
                                    //val intentObject = Intent(this@MovieActivity, MovieDetailActivity::class.java)
                                    startActivity(intentObject)
                                }
                            }

                            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                                //tvResponseCode.text = t.message
                                Toast.makeText(this@MovieActivity,t.message, Toast.LENGTH_SHORT).show()
                            }
                        });
                    }
                })
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                tvResponseCode.text = t.message
                Toast.makeText(this@MovieActivity,t.message, Toast.LENGTH_SHORT).show()
            }

        });
    }
}
