package com.example.assignment4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    EditText editCity, editName, editMVP, editID;
    ImageView editImage;
    String sport;
    Spinner editSport;
    Button btnSubmit, btnUpdate, btnDelete, btnExit, btnUpload;
    LinearLayout topLayout, bottomLayout;
    byte[] imgArray = null;
    public static String base64;
    String decryptImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editID = findViewById(R.id.teamID);
        editCity = findViewById(R.id.city);
        editName = findViewById(R.id.name);
        editSport = findViewById(R.id.sport);
        editMVP = findViewById(R.id.MVP);
        editImage = findViewById(R.id.image);
        editImage.setImageResource(0);
        topLayout = findViewById(R.id.topLayout);
        bottomLayout = findViewById(R.id.bottomLayout);

        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"},1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sports_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSport.setAdapter(adapter2);

        btnSubmit = findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                String city = editCity.getText().toString();
                String name = editName.getText().toString();
                String MVP = editMVP.getText().toString();

                Bitmap bitmap = ((BitmapDrawable) editImage.getDrawable()).getBitmap();
                Bitmap convertedImg = getResizedBitmap(bitmap, 300);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                convertedImg.compress(Bitmap.CompressFormat.PNG, 100, bos);
                imgArray = bos.toByteArray();
                String image = Base64.encodeToString(imgArray, Base64.DEFAULT);


                if( city.length() == 0 || name.length() == 0){
                    Toast.makeText(DetailsActivity.this, "City and Name are required",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dbHandler.insertItem(city,name,sport,MVP,image);
                    editCity.setText("");
                    editName.setText("");
                    editSport.setSelection(0);
                    editMVP.setText("");
                    editImage.setImageResource(R.drawable.image_not_found);
                }
            }
        });

        btnUpload = findViewById(R.id.uploadBtn);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });

        btnUpdate = findViewById(R.id.updateBtn);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                String id = editID.getText().toString();
                String city = editCity.getText().toString();
                String name = editName.getText().toString();
                String MVP = editMVP.getText().toString();

                Bitmap bitmap = ((BitmapDrawable) editImage.getDrawable()).getBitmap();
                Bitmap convertedImg = getResizedBitmap(bitmap, 300);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                convertedImg.compress(Bitmap.CompressFormat.PNG, 100, bos);
                imgArray = bos.toByteArray();
                String image = Base64.encodeToString(imgArray, Base64.DEFAULT);

                dbHandler.updateItem(id,city,name,sport, MVP, image);

                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        btnDelete = findViewById(R.id.deleteBtn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                dbHandler.deleteItem(editID.getText().toString());

                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        btnExit = findViewById(R.id.exitBtn);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        Intent intent = getIntent();

        String stringData = intent.getStringExtra("id");
        editID.setText(stringData);

        stringData = intent.getStringExtra("city");
        editCity.setText(stringData);

        stringData = intent.getStringExtra("name");
        editName.setText(stringData);

        stringData = intent.getStringExtra("sport");
        editSport.setSelection(Integer.valueOf(Integer.parseInt(stringData)).intValue());

        stringData = intent.getStringExtra("MVP");
        editMVP.setText(stringData);

        editSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StringBuilder stringbuffer = new StringBuilder();
                stringbuffer.append("");
                stringbuffer.append(position);
                sport = stringbuffer.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stringData = intent.getStringExtra("command");
        if(stringData.equals("add")){
            btnDelete.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            topLayout.setBackgroundColor(Color.parseColor("#ffc107"));
            editImage.setImageResource(R.drawable.image_not_found);
        }
        if(stringData.equals("ud")){
            btnSubmit.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            topLayout.setBackgroundColor(Color.parseColor("#ff2b2b"));
            byte[] decodedString = Base64.decode(base64,0);
            editImage.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (requestCode == 1){
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            decryptImg = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(decryptImg));
        } catch (Exception e){

        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
