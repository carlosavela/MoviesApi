package com.ing.cavl.moviesapi.ui.core

import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

object InternetCheck {

    suspend fun isNetworkAvailable()  =  coroutineScope {
        return@coroutineScope try {
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8",53)
            socket.connect(socketAddress, 2000)
            socket.close()
            true
        }catch (e: Exception){
            false
        }
    }
}