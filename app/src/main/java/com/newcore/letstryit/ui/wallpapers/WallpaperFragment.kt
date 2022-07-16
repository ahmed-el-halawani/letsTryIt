package com.newcore.letstryit.ui.wallpapers

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentWallpaperTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class WallpaperFragment :
    BaseFragment<FragmentWallpaperTestBinding>(FragmentWallpaperTestBinding::inflate) {

    private val vm: WallpaperFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(
            ClipData.newPlainText(
                "text",
                WallpaperUtils.imageUrlStringFromImageId(WallpaperUtils.getAllData()[0].id)
            )
        )

        //        Glide.with(requireContext())
        //            .load(WallpaperUtils.imageUrlStringFromImageId(WallpaperUtils.getAllData()[0].id))
        //            .into(binding.imageView3)

        DownloadImageFromPath(WallpaperUtils.imageUrlStringFromImageId(WallpaperUtils.getAllData()[0].id))
        //        rvList(
        //            binding.rvList,
        //            GridLayoutManager(context, 3)
        //        ) {
        //            listBuilder(
        //                ItemWallpaperBoxBinding::inflate,
        //                WallpaperUtils.getAllData()
        //            ) { item, data ->
        //                Glide.with(item.imageView.context)
        //                    .load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg")
        //                    .into(item.imageView)
        //            }
        //        }
    }


    fun DownloadImageFromPath(path: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            var `in`: InputStream? = null
            var bmp: Bitmap? = null
            val iv: ImageView = binding.imageView3 as ImageView
            var responseCode = -1
            try {
                val url = URL(path) //"http://192.xx.xx.xx/mypath/img1.jpg
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                con.doInput = true
                con.connect()
                responseCode = con.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //download
                    `in` = con.inputStream
                    Log.e(" `in` ", "" + `in`)

                    bmp = BitmapFactory.decodeStream(`in`)
                    `in`.close()
                    iv.setImageBitmap(bmp)
                } else {
                    Log.e("responseCode", "" + responseCode.toString())
                }
            } catch (ex: Exception) {
                Log.e("Exception", ex.toString())
            }
        }
    }
}

