package io.ysq.crasher

import io.ysq.crasher.view.log.viewmodel.LogViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        LogViewModel()
    }

}