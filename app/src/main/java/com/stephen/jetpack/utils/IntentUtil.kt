package com.ddzh.downloader.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import android.widget.Toast
import com.stephen.jetpack.R
import java.io.File
import javax.inject.Inject

class IntentUtil @Inject constructor() {
    fun openFolder(context: Context?, path: String) {
        context?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(path), "resource/*")

            if (intent.resolveActivityInfo(it.packageManager, 0) != null) {
                it.startActivity(intent)
            } else {
                Toast.makeText(it, it.getString(R.string.settings_message_open_folder), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}