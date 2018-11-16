package com.example.bui.japo6;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button Upbn;
    private Button Chbn;
    private EditText NAME;
    private ImageView imgView;
    private final int img_req = 1;
    private Bitmap bitmap;
    private String UpUrl = "http://192.168.1.21/KofaxRTTI/api/ZUtenti";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Upbn = findViewById(R.id.uploadbn);
        Chbn = findViewById(R.id.choosebn);
        NAME = findViewById(R.id.name);
        imgView = findViewById(R.id.imageView);
        Upbn.setOnClickListener(this);
        Chbn.setOnClickListener(this);
        Toast.makeText(MainActivity.this, "testo", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choosebn:
                selectImage();
                break;
            case R.id.uploadbn:
                uploadImage();
                break;
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,img_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == img_req && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imgView.setImageBitmap(bitmap);
                imgView.setVisibility(View.VISIBLE);
                NAME.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,UpUrl,
                new Response.Listener<String>(){

            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(MainActivity.this,Response,Toast.LENGTH_LONG).show();
                    imgView.setImageResource(0);
                    imgView.setVisibility(View.GONE);
                    NAME.setText("");
                    NAME.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Errore nell'url", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();

                params.put("processIA--mage",imageToString(bitmap));
                params.put("imageResult","true");
                params.put("xTipoDocumento","CI_C");
                params.put("xCognome","Luna");
                params.put("xNome","Federico");
                params.put("xDataNascita","27/04/1997");

                return params;
            }
        };
        MySingleton.getmInstance(MainActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}
