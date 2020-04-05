package com.example.roughage.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roughage.R
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.formats.UnifiedNativeAdView

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val builder = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd { unifiedNativeAd ->
                // Assumes that your ad layout is in a file call ad_unified.xml
                // in the res/layout folder
                val adView = layoutInflater
                    .inflate(R.layout.fragment_gallery, null) as UnifiedNativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
//                populateUnifiedNativeAdView(unifiedNativeAd, adView)
//
//                // Assumes you have a placeholder FrameLayout in your View layout
//                // (with id ad_frame) where the ad is to be placed.
//                ad_frame.removeAllViews()
//                ad_frame.addView(adView)
            }
        return root
    }
}
