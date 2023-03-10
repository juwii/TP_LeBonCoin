package com.example.tp_leboncoin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class AdAddActivity extends AppCompatActivity {
    // Define the pic id
    private static final int CAMERA_PERM_CODE = 101;
    private String filePath = "";
    // Define the button and imageview type variable
    ImageButton camera_open_id;
    ImageButton gallery_open_id;
    ImageView click_image_id;


    // https://stackoverflow.com/questions/36109946/how-to-get-file-path-on-android
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Bitmap image = (Bitmap) data.getExtras().get("data");
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                File file = new File(directory, ts + ".jpg");
                if (!file.exists()) {
                    Log.d("path", file.toString());
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                        image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
                filePath = file.toString();
                click_image_id.setImageBitmap(image);
            }
        }
    });

    ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                Intent data = result.getData();
                Uri image = (Uri) result.getData().getData();
                click_image_id.setImageURI(image);
                filePath = getPath(cw, image);
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

        ImageButton b1 = (ImageButton) findViewById(R.id.send_button);
        EditText Title = (EditText) findViewById(R.id.material_title_edittext);
        EditText Address = (EditText) findViewById(R.id.material_address_edittext);
        EditText Phone = (EditText) findViewById(R.id.material_phone_edittext);
        EditText Email = (EditText) findViewById(R.id.material_email_edittext);

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        b1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdModel ad;

                if (TextUtils.isEmpty(Title.getText().toString())) {
                    Title.setError("Please Enter a title!");
                } else if (TextUtils.isEmpty(Address.getText().toString())) {
                    Address.setError("Please Enter an address!");
                } else if (TextUtils.isEmpty(Email.getText().toString())) {
                    Email.setError("Please Enter an email address!");
                } else if (TextUtils.isEmpty(Phone.getText().toString())) {
                    Phone.setError("Please Enter a phone number!");
                } else {
                    if (!filePath.equals("")) {
                        ad = new AdModel(Title.getText().toString(), Address.getText().toString(), null, filePath, Phone.getText().toString(), Email.getText().toString());
                    } else {
                        String drawableName = "wood";
                        ContextWrapper cw = new ContextWrapper(getApplicationContext());
                        File resources = cw.getDir("res", Context.MODE_PRIVATE);
                        System.out.println(resources);
                        //filePath = "/data/data/com.example.tp_leboncoin/code_cache/.overlay/base.apk/res/drawable/wood.png";
                        filePath = resources + "/drawable/wood.png";
                        ad = new AdModel(Title.getText().toString(), Address.getText().toString(), null, filePath, Phone.getText().toString(), Email.getText().toString());
                    }
                    dbManager.insert(ad);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdAddActivity.this);
                    builder.setMessage("Ad added! Thank you for your participation.");
                    builder.setTitle("Congratulations");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                        Intent sent = new Intent(AdAddActivity.this, display_list.class);
                        startActivity(sent);
                        finish();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}