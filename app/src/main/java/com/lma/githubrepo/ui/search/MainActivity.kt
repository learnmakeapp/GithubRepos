package com.lma.githubrepo.ui.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.lma.githubrepo.R
import com.lma.githubrepo.ui.result.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtSearch = findViewById(R.id.edt_search)
        btnSearch = findViewById(R.id.btn_search)

        btnSearch.setOnClickListener {
            val username = edtSearch.text.toString()

            if (username.isNotEmpty()) {
                DetailActivity.route(this, username)
            } else {
                // TODO
            }
        }
    }

}