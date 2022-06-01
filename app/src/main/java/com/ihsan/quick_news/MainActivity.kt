package com.ihsan.quick_news

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.quick_news.adapter.ListNewsAdapter
import com.ihsan.quick_news.data.NewsData
import com.ihsan.quick_news.data.dto.News

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvNews: RecyclerView
    private var list: ArrayList<News> = arrayListOf()
    private lateinit var btnAll: Button
    private lateinit var btnTekno: Button
    private lateinit var btnSport: Button
    private lateinit var btnCrypto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAll = findViewById(R.id.btn_all)
        btnAll.setOnClickListener(this)

        btnTekno = findViewById(R.id.btn_tekno)
        btnTekno.setOnClickListener(this)

        btnSport = findViewById(R.id.btn_sport)
        btnSport.setOnClickListener(this)

        btnCrypto = findViewById(R.id.btn_crypto)
        btnCrypto.setOnClickListener(this)

        rvNews = findViewById(R.id.rv_news)
        rvNews.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
        rvNews.setHasFixedSize(true)

        list.addAll(NewsData.listData)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_user) {
            startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(list)
        rvNews.adapter = listNewsAdapter

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                showSelectedNews(data)
            }
        })
    }

    private fun showSelectedNews(news: News) {
        val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
        val bundle = Bundle()
        val parcel = News(
            news.cover,
            news.title,
            news.content,
            news.author,
            news.source,
            news.tag,
            news.time,
            news.date
        )
        bundle.putParcelable("news", parcel)
        intent.putExtra("news_bundle", bundle)
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_all -> {
                list.clear()
                list.addAll(NewsData.listData)
            }
            R.id.btn_tekno -> {
                list.clear()
                list.addAll(NewsData.listData.filter { it.tag == "Tekno" })
            }
            R.id.btn_sport -> {
                list.clear()
                list.addAll(NewsData.listData.filter { it.tag == "Sport" })
            }
            R.id.btn_crypto -> {
                list.clear()
                list.addAll(NewsData.listData.filter { it.tag == "Crypto" })
            }
        }
        setActiveButton(v)
        rvNews.adapter?.notifyDataSetChanged()
    }

    private fun setActiveButton(view: View?) {
        // reset state button to default
        btnAll.let {
            it.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            it.setTextColor(ContextCompat.getColor(this, R.color.button_text))
        }
        btnTekno.let {
            it.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            it.setTextColor(ContextCompat.getColor(this, R.color.button_text))
        }
        btnSport.let {
            it.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            it.setTextColor(ContextCompat.getColor(this, R.color.button_text))
        }
        btnCrypto.let {
            it.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            it.setTextColor(ContextCompat.getColor(this, R.color.button_text))
        }


        // set active button
        view?.let {
            it.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.button_bg))
            (it as Button).setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}