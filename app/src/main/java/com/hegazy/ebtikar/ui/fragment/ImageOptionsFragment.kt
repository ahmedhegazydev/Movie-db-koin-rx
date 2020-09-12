package com.hegazy.ebtikar.ui.fragment

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.constants.Constants
import com.hegazy.ebtikar.databinding.FragmentImageOptionsBinding
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls
import com.hegazy.ebtikar.utils.doToast
import kotlinx.android.synthetic.main.fragment_image_options.*
import java.io.File

class ImageOptionsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentImageOptionsBinding
    private var fullPath: String? = ""
    var msg: String? = ""
    var lastMsg = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentImageOptionsBinding.inflate(inflater, container, false)
        viewDataBinding.imageOptionsFragment = this
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgImagePath()

    }

    private fun getArgImagePath() {

        (arguments?.containsKey(Constants.ARG_KEY_IMAGE_TO_DOWNLOAD)!!).apply {
            if (this) {
                val passedImagePath =
                    requireArguments().getString(Constants.ARG_KEY_IMAGE_TO_DOWNLOAD)
                fullPath = ApiUrls.BASE_IMAGE_PATH + "/dG7gLzlZCjZkjKMyoGIL8h5wjRj.jpg"
//               fullPath = ApiUrls.BASE_IMAGE_PATH.plus(passedImagePath)

                loadImage()

            }
        }

    }

    private fun loadImage() {
        Glide.with(requireActivity())
            .load(fullPath)
            //            .placeholder(R.drawable.will_smith)
            .into(image_to_download)
    }

    fun handleOnDownloadClicked() {
        downloadImage(fullPath.toString())
    }


    private fun downloadImage(url: String) {

        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdirs()
        }
        val downloadManager =
            requireActivity().getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(url)
        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or
                        DownloadManager.Request.NETWORK_MOBILE
            )
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }

        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread(Runnable {
            var downloading = true
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                    requireActivity().runOnUiThread {
                        doToast(requireActivity(), msg.toString())
                    }
                    lastMsg = msg ?: ""
                }
                cursor.close()
            }
        }).start()
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There's nothing to download"
        }
        return msg
    }

}