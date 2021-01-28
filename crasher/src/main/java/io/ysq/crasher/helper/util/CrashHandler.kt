package io.ysq.crasher.helper.util

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import io.ysq.crasher.view.log.LogActivity
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

object CrashHandler : Thread.UncaughtExceptionHandler {

    private val TAG = "io.ysq.crasher.helper.util/CrashHandler"
    private var mContext: Context? = null
    fun init(context: Context) {
        mContext = context
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.d(TAG, "uncaughtException: 捕获到异常")
        val intent = Intent(mContext, LogActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("log", getThrowableString(e))
        mContext?.startActivity(intent)
        Process.killProcess(Process.myPid())
        exitProcess(0)
    }

    /* 获取报错日志 */
    private fun getThrowableString(e: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        pw.flush()
        pw.close()
        return sw.toString()
    }
}