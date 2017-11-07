package com.android.messaging

/**
 * Created by kalman.bencze on 06/11/2017.
 */

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}

fun uiThread(f: () -> Unit) {
    UI_EXECUTOR.execute(f)
}

val UI_EXECUTOR = UiExecutor()

class UiExecutor : Executor {
    private val mHandler: Handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable?) {
        mHandler.post(command)
    }
}