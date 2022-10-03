package com.rentguruz.app.b2b.alsalaam.backgroundtasks

interface AcuantTokenServiceListener {
    fun onSuccess(token: String)
    fun onFail(responseCode: Int)
}