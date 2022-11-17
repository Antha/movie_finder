package com.codeathome.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeathome.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_list.view.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        tvTitle.text = intent.getStringExtra("TITLE").toString();
        tvDesc.text = intent.getStringExtra("PLOT").toString();
        Picasso.get().load(intent.getStringExtra("POSTER").toString()).into(imgPoster);
        super.onCreate(savedInstanceState)
    }
}