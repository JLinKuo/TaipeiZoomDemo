package com.example.taipeizoomdemo.view.base

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.taipeizoomdemo.BuildConfig.ISDEBUG
import com.example.taipeizoomdemo.R
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.view.main.MainActivity
import java.io.IOException

fun Fragment.handleApiError(
    failure: Resource.Failure
) {
    val mainActivity = (activity as MainActivity)

    when (failure.errorCode) {
        400, 404, 500 -> {
            val msg = String.format(getString(R.string.dialog_error_server_busy), failure.errorCode)
            mainActivity.showOneButtonNoTitleDialog(msg, null)
        }

        else -> {
            when(failure.throwable) {
                is IOException -> {
                    mainActivity.showOneButtonNoTitleDialog(
                        getString(R.string.dialog_error_network), null)
                }
                else -> {
                    if(ISDEBUG) {
                        Log.e("JLin", "error message: ${failure.throwable.message}")
                    }
                }
            }
        }
    }
}
