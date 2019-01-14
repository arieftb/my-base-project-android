/*
 * Developed By Arief TB on 1/14/19 7:37 AM.
 * Github : github.com/arieftb .
 * Web : arieftb.com .
 * Copyright (c) 2019.
 */

package id.co.gitsolution.eholiday.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogHelper {
    fun showWarnDialog(context: Context, message: String, positiveMsg: String, isCancelable: Boolean, answer: Positive) {

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)

        alertDialogBuilder.setMessage(message)
                .setCancelable(isCancelable)
                .setPositiveButton(positiveMsg, answer::positiveButton)

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(isCancelable)
        alertDialog.show()
    }

    fun showConfirmDialog(context: Context, message: String, positiveMsg: String, negativeMsg: String, isCancelable: Boolean, answer: Answer) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)

        alertDialogBuilder.setMessage(message)
            .setCancelable(isCancelable)
            .setPositiveButton(positiveMsg, answer::positiveButton)
            .setNegativeButton(negativeMsg, answer::negativeButton)

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(isCancelable)
        alertDialog.show()
    }

    interface Answer {
        fun positiveButton(dialog: DialogInterface, id: Int)
        fun negativeButton(dialog: DialogInterface, id: Int)
    }

    interface Positive {
        fun positiveButton(dialog: DialogInterface, id: Int)
    }
}