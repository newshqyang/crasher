package io.ysq.crasher.view.log

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.Toast
import io.ysq.crasher.R
import io.ysq.crasher.databinding.LogActivityBinding
import io.ysq.crasher.view.base.BaseActivity
import io.ysq.crasher.view.log.viewmodel.LogViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LogActivity : BaseActivity<LogActivityBinding>() {

    private val esError by lazy {
        intent?.extras?.getString("log")
    }

    private val mViewModel: LogViewModel by viewModel()

    override fun initView() {
        mBinding.apply {
            vm = mViewModel
            presenter = this@LogActivity
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.copy_btn -> copy2clip()
        }
    }

    /* 复制到剪贴板 */
    private fun copy2clip() {
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val cd = ClipData.newPlainText("崩溃信息", esError)
        cm.setPrimaryClip(cd)
        Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId() = R.layout.log_activity

    override fun loadDate(isRefresh: Boolean) {
        mViewModel.error.set(esError)
    }

    override fun onBackPressed() {
        //退出APP
        finishAffinity()
    }
}