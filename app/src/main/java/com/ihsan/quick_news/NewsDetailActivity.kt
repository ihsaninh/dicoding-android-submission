package com.ihsan.quick_news

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ihsan.quick_news.data.dto.News

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var newsCover: ImageView
    private lateinit var newsTitle: TextView
    private lateinit var newsContent: TextView
    private lateinit var newsBy: TextView
    private lateinit var newsDateTime: TextView

    private lateinit var newsData: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val bundle = intent.getBundleExtra("news_bundle")
        newsData = bundle?.getParcelable("news")!!
        setActionBar()
        setNewsContent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, newsData.title + "\n" + "\n" + newsData.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            R.id.action_fovourite -> {
                Toast.makeText(this, getString(R.string.added_to_favorite), Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setNewsContent() {
        newsCover = findViewById(R.id.iv_detail_cover)
        newsTitle = findViewById(R.id.tv_detail_title)
        newsBy = findViewById(R.id.tv_detail_by)
        newsDateTime = findViewById(R.id.tv_detail_datetime)
        newsContent = findViewById(R.id.tv_detail_content)

        newsData.let {
            Glide.with(this)
                .load(it.cover)
                .into(newsCover)
            newsTitle.text = it.title
            newsBy.text = getString(R.string.news_by, it.source)
            newsDateTime.text = getString(R.string.news_datetime, it.date, it.time)
            newsContent.text = it.content
        }
    }
}