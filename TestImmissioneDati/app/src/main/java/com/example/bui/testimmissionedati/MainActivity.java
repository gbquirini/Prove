package com.example.bui.testimmissionedati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Carta;
    private TextView Res;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Carta = findViewById(R.id.Cartaid);
        Res = findViewById(R.id.Risultati);
        b1 = findViewById(R.id.Button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Res.setText(Carta.getText());
                Res.setVisibility(View.VISIBLE);
                Carta.setText("");
            }
        });

        Toast.makeText(MainActivity.this, "Compila!", Toast.LENGTH_LONG).show();



    }


}
