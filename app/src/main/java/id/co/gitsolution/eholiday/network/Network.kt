package id.co.gitsolution.eholiday.network

import id.co.gitsolution.eholiday.utils.Constants
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

object Network {
    fun <T> request(
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

    fun code(t: Throwable): Int {
        val code: Int

        if (t is HttpException) {
            code = t.code()
        } else if (t is UnknownHostException) {
            code = Constants.CODE_HOST_ERROR
        } else if (t is TimeoutException) {
            code = Constants.CODE_TIME_OUT
        } else {
            code = 0
        }

        return  code
    }
}