package io.ysq.crasher.view.log

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.ysq.crasher.R


class LogActivity : AppCompatActivity() {

    private val esError by lazy {
        intent?.extras?.getString("log")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_activity)

        findViewById<TextView>(R.id.error_tv).text = esError

        findViewById<Button>(R.id.copy_btn).setOnClickListener {
            copy2clip()
        }
    }

    /* 复制到剪贴板 */
    private fun copy2clip() {
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val cd = ClipData.newPlainText("崩溃信息", esError)
        cm.setPrimaryClip(cd)
        Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        //退出APP
        finishAffinity()
    }
}