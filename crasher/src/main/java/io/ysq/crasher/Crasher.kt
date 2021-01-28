package io.ysq.crasher

import android.app.Application
import io.ysq.crasher.helper.util.CrashHandler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

object Crasher {

    /* 初始化 */
    fun init(app: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(appModule)
        }

        CrashHandler.init(app)
    }

}