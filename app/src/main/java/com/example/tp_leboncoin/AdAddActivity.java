package com.example.tp_leboncoin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AdAddActivity extends AppCompatActivity {
    // Define the button and imageview type variable
    Button camera_open_id;
    Button gallery_open_id;
    ImageView click_image_id;

    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Bitmap image = (Bitmap) data.getExtras().get("data");
                click_image_id.setImageBitmap(image);
            }
        }
    });

    ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Uri image = (Uri) result.getData().getData();
                click_image_id.setImageURI(image);
            }
        }
    });

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(AdAddActivity.this, new String[] {android.Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        setContentView(R.layout.activity_ad_add);

        camera_open_id = findViewById(R.id.picture_button);
        gallery_open_id = findViewById(R.id.gallery_button);
        click_image_id = findViewById(R.id.imageView);

        // Camera_open button is for open the camera and add the setOnClickListener in this button
        camera_open_id.setOnClickListener(v -> {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraActivityResultLauncher.launch(camera);
        });

        gallery_open_id.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryActivityResultLauncher.launch(gallery);
        });

        Button b1 = (Button) findViewById(R.id.send_button);
        EditText Title = (EditText) findViewById(R.id.material_title_edittext);
        EditText Address = (EditText) findViewById(R.id.material_address_edittext);
        EditText Phone = (EditText) findViewById(R.id.material_phone_edittext);
        b1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sent = new Intent(AdAddActivity.this, display_list.class);
                sent.putExtra("Title", Title.getText().toString());
                sent.putExtra("Address", Address.getText().toString());
                sent.putExtra("Phone", Phone.getText().toString());
                sent.putExtra("Picture", click_image_id.getImageAlpha());
                startActivity (sent);
            }
        });
    }
}