package com.example.roughage.ui.slideshow

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roughage.R
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.UnifiedNativeAd
import java.util.*


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    lateinit var minterstitialAD: InterstitialAd
    lateinit var button: Button


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        val adBannerView = root.findViewById<AdView>(R.id.ad_banner)

        RequestConfiguration.Builder().setTestDeviceIds(
            Arrays.asList("BE5741B6FF1851A2D750A81916CFB478"))
        minterstitialAD = InterstitialAd(context)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        button = root.findViewById(R.id.button)

        adBannerView.loadAd(AdRequest.Builder().build())
        adBannerView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                Toast.makeText(context, "add loaded banner", Toast.LENGTH_SHORT).show()
            }
        }

        minterstitialAD.adUnitId = getString(R.string.interstiatial_id_test)
        minterstitialAD.loadAd(AdRequest.Builder().build())
        minterstitialAD.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                Toast.makeText(context, "add loaded interstitial", Toast.LENGTH_SHORT).show()
            }
        }



        val adLoader: AdLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd(object : UnifiedNativeAd.OnUnifiedNativeAdLoadedListener {
                override fun onUnifiedNativeAdLoaded(unifiedNativeAd: UnifiedNativeAd?) {
                    val styles =
                        NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(Color.parseColor("#abcdef"))).build()
                    val template: TemplateView = root.findViewById(R.id.my_template)
                    template.setStyles(styles)
                    template.setNativeAd(unifiedNativeAd)
                }
            })
            .withAdListener(object: AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    Toast.makeText(context, "native", Toast.LENGTH_SHORT).show()
                }
            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //chouhan

        button.setOnClickListener {
            if(minterstitialAD.isLoaded){
                minterstitialAD.show()
            }
        }
    }
}
