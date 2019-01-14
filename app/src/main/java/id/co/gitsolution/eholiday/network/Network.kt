/*
 * Developed By Arief TB on 1/14/19 7:37 AM.
 * Github : github.com/arieftb .
 * Web : arieftb.com .
 * Copyright (c) 2019.
 */

package id.co.gitsolution.eholiday.network

import android.content.Context
import id.co.gitsolution.eholiday.R
import id.co.gitsolution.eholiday.utils.Constants
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

object Network {
    fun <T> request(
        context: Context,
        call: Deferred<T>,
        success: ((response: T)-> Unit)?,
        error: ((throwable: Throwable)-> Unit)?= null,
        doOnSubscribe: (()-> Unit)?= null,
        doOnTerminate: (()-> Unit)?= null) {
        GlobalScope.launch(Dispatchers.Main) {
            doOnSubscribe?.invoke()
            try {
                success?.invoke(call.await())
            } catch (t: Throwable) {
                    error?.invoke(t)
            } finally {
                doOnTerminate?.invoke()
            }
        }
    }

    private fun code(t: Throwable): Int {
        return when (t) {
            is HttpException -> t.code()
            is UnknownHostException -> Constants.CODE_HOST_ERROR
            is TimeoutException -> Constants.CODE_TIME_OUT
            is SocketTimeoutException -> Constants.CODE_TIME_OUT
            else -> 0
        }
    }

    private fun message(context: Context, t:Throwable, code: Int): String {
        return when (code) {
            Constants.CODE_HOST_ERROR -> context.getString(R.string.err_mes_no_connection)
            Constants.CODE_TIME_OUT -> context.getString(R.string.err_mes_timeout_connection)
            else -> context.getString(R.string.err_mes_unknown, t.message, code)
        }
    }
}