package com.stephen.common.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.TypedValue
import android.view.Display
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.stephen.common.log.logger
import com.stephen.common.ext.otherwise
import com.stephen.common.ext.yes
import java.util.*


object SystemUtil {
    fun getAppVersion(context: Context): String {
        context.packageManager.apply {
            val info = getPackageInfo(context.packageName, 0)
            logger.info("versionName=${info.versionName}")
            return info.versionName
        }
        return ""

    }


    @JvmStatic
    fun getDisplayWidth(context: Context): Display {
        return (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    }


    /**
     * 关闭软键盘
     *
     * @param mEditText输入框
     * @param mContext上下文
     */
    fun closeKeybord(mEditText: EditText, mContext: Context) {
        val imm = mContext
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }


    fun toDp(context: Context, value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }



    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    fun copyText(context: Context, copyStr: String): Boolean {
        try {
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("Label", copyStr)
            cm.primaryClip = mClipData
            return true
        } catch (e: Exception) {
            return false
        }
    }


    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun getSystemLocale(): Locale? {
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N).yes {
            return LocaleList.getDefault().get(0)
        }.otherwise {
            return Locale.getDefault()
        }

    }




}