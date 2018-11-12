package com.example.bui.postjava3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://example.com";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        File imm = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/KTA");
        Bitmap bMap = BitmapFactory.decodeFile(imm+"Provaimmagine.jpg");
        //String data = "";
// ...

        try {
            /* outputStreamWriter = new OutputStreamWriter(bMap);
            outputStreamWriter.write(data);
            outputStreamWriter.close();*/
            URLConnection connection = null;

            connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);


            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            bMap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            String temp=Base64.encodeToString(b, Base64.DEFAULT);

        OutputStream output = connection.getOutputStream();
            output.write(temp.getBytes(),0 ,temp.length());


            InputStream response = connection.getInputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 


    }
//
    }

