package io.ysq.crasher.view.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.ysq.crasher.BR
import io.ysq.crasher.helper.presenter.Presenter

abstract class BaseActivity<VB: ViewDataBinding>: AppCompatActivity(), Presenter {

    protected val mContext by lazy {
        this
    }
    protected val mBinding by lazy {
        DataBindingUtil.setContentView<VB>(this, getLayoutId())
    }

    abstract fun getLayoutId(): Int

    abstract fun loadDate(isRefresh: Boolean)

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding.setVariable(BR.presenter, this)
        mBinding.executePendingBindings()

        initView()
        initEvent()
        loadDate(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun setShowBackNav() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    open fun doBack() {
        onBackPressed()
    }

    open fun initEvent() {}
}