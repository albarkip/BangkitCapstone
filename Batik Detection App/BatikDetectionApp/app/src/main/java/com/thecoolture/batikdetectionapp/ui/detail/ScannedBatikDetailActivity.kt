package com.thecoolture.batikdetectionapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.thecoolture.batikdetectionapp.MainActivity
import com.thecoolture.batikdetectionapp.R
import com.thecoolture.batikdetectionapp.databinding.ActivityScannedBatikDetailBinding
import com.thecoolture.batikdetectionapp.ui.store.StoreFragment

class ScannedBatikDetailActivity : AppCompatActivity() {

    private lateinit var scannedBatikDetailViewModel: ScannedBatikDetailViewModel

    companion object {
        const val EXTRA_BATIK_NAME = "batik_name"
    }

    private lateinit var binding: ActivityScannedBatikDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scannedBatikDetailViewModel = ViewModelProvider(this).get(ScannedBatikDetailViewModel::class.java)
        binding = ActivityScannedBatikDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val detailExtras = intent.extras

        var imgSample: Int? = 0

        if (detailExtras != null) {
            detailExtras.getString(EXTRA_BATIK_NAME)?.let {
                scannedBatikDetailViewModel.setBatikName(
                    it
                )
            }

            when (detailExtras.getString(EXTRA_BATIK_NAME)) {
                "Ceplok" -> imgSample = R.drawable.img_sample_ceplok
                "Kawung" -> imgSample = R.drawable.img_sample_kawung
                "Lereng" -> imgSample = R.drawable.img_sample_lereng
                "Nitik" -> imgSample = R.drawable.img_sample_nitik
                "Parang" -> imgSample = R.drawable.img_sample_parang
            }

            detailExtras.getString(EXTRA_BATIK_NAME)?.let { scannedBatikDetailViewModel.insert(it, this.application) }
        }

//        scannedBatikDetailViewModel.batikDetail.observe()

        setSupportActionBar(binding.toolbar)
        if (detailExtras != null) {
            binding.toolbarLayout.title = "Batik ${detailExtras.getString(EXTRA_BATIK_NAME)}"
        } else {
            binding.toolbarLayout.title = title
        }
        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        if (imgSample != null) {
            Glide.with(this)
                .load(imgSample)
                .into(binding.imgBatikScroll)
        }

        scannedBatikDetailViewModel.batikDetail.observe(this, {
            Log.d("TAG", "onCreate: $it")
            binding.scrollingLayout.detailLayout.tvNameDet.text = it.name.toString()
            binding.scrollingLayout.detailLayout.tvOriginDet.text = it.origin.toString()
            binding.scrollingLayout.detailLayout.tvMeaningDet.text = it.meaning.toString()
            binding.scrollingLayout.detailLayout.tvHistoryDet.text = it.history.toString()
        })
    }
}