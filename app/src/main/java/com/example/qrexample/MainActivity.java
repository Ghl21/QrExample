package com.example.qrexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btnScaner;
    TextView txTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScaner = findViewById(R.id.scanner);
        txTexto = findViewById(R.id.text);

        btnScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator ii = new IntentIntegrator(MainActivity.this);
                ii.setOrientationLocked(true);
                ii.setPrompt("Scan el QR");
                ii.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                ii.initiateScan();;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult ir = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(ir != null)
        {
            String contents = ir.getContents();
            if(contents != null)
            {
                txTexto.setText(ir.getContents());
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}