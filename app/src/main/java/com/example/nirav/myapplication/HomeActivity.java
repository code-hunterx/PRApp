package com.example.nirav.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class HomeActivity extends AppCompatActivity {

    ImageButton captureBtn, attachBtn;
    ImageView imageView1;
    TextView textView,textView2;
    Button proceedBtn;
    static final int CAPTURE_REQUEST_CODE = 1;
    static final int ATTACH_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        captureBtn = (ImageButton) findViewById(R.id.captureBtn);
        attachBtn = (ImageButton) findViewById(R.id.attachBtn);
        imageView1=(ImageView) findViewById(R.id.imageView1);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        proceedBtn=(Button)findViewById(R.id.proceedBtn);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1, CAPTURE_REQUEST_CODE);
            }
        });

        attachBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent2, ATTACH_REQUEST_CODE);
            }
        });

    }


    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i("sucessfull","abcd");
            Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1,  150, 150,true);
            imageView1.setImageBitmap(bitmap2);

        }
        else if (requestCode==ATTACH_REQUEST_CODE && resultCode==RESULT_OK)
        {
            ImageView imageView1=(ImageView)findViewById(R.id.imageView1);
            Uri uri1=data.getData();
            InputStream is= null;
            try {
                is = getContentResolver().openInputStream(uri1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap3= BitmapFactory.decodeStream(is);
            Bitmap bitmap4 = Bitmap.createScaledBitmap(bitmap3,  150, 150,true);
            imageView1.setImageBitmap(bitmap4);

        }
    }
}
