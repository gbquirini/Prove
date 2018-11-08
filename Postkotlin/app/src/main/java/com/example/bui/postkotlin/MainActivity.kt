package com.example.bui.postkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask.execute
import android.os.Environment
import sun.net.www.http.HttpClient
import android.os.Environment.getExternalStorageDirectory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://yourserver"
        val file = File(
            Environment.getExternalStorageDirectory().getAbsolutePath(),
            "yourfile"
        )
        try {
            val httpclient = DefaultHttpClient()

            val httppost = HttpPost(url)

            val reqEntity = InputStreamEntity(
                FileInputStream(file), -1
            )
            reqEntity.setContentType("binary/octet-stream")
            reqEntity.setChunked(true) // Send in multiple parts if needed
            httppost.setEntity(reqEntity)
            val response = httpclient.execute(httppost)
            //Do something with response...

        } catch (e: Exception) {
            // show error
        }

    }
}
