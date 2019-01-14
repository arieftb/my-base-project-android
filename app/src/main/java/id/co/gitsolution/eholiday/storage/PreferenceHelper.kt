/*
 * Developed By Arief TB on 1/14/19 7:37 AM.
 * Github : github.com/arieftb .
 * Web : arieftb.com .
 * Copyright (c) 2019.
 */

package id.co.gitsolution.eholiday.storage

import android.content.Context
import android.preference.PreferenceManager
import id.co.gitsolution.eholiday.BuildConfig

class PreferenceHelper(private val context: Context) {
    companion object {
        private const val USERDATA = BuildConfig.APPLICATION_ID + ".USERDATA"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

//    SETGET DATA USER
    var dataUser = preferences.getString(USERDATA, "")
    set(value) = preferences.edit().putString(USERDATA, value).apply()

}