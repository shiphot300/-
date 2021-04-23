package com.example.mainpage.write;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class WriteActivity2 extends AppCompatActivity {
    public static int OPEN_IMAGE_REQUEST_CODE = 49018;
    private RelativeLayout mBtnAddCamera1;
    private RecyclerView rvContentView1;
    private PhotoAdapter photoAdapter1;
    private TextView tvPhotoLabel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        mBtnAddCamera1 = findViewById(R.id.view_camera);
        rvContentView1 = findViewById(R.id.rv_content);
        tvPhotoLabel1 = findViewById(R.id.tv_photo_number);

        initRecyclerView();


        mBtnAddCamera1.setOnClickListener(view -> getGallaryIntent(WriteActivity2.this));

    }

    private void initRecyclerView() {

        photoAdapter1 = new PhotoAdapter(this) {
            @Override
            public void itemCallback(int position) {
                photoAdapter1.removeItem(position);
                updatePhotoIndexLabel();
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvContentView1.setLayoutManager(linearLayoutManager);
        rvContentView1.setAdapter(photoAdapter1);
    }

    private void getGallaryIntent(Activity activity) {
        Dexter.withActivity(activity).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, OPEN_IMAGE_REQUEST_CODE);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_IMAGE_REQUEST_CODE) {
            if (data == null) {
                return;
            }

            Uri uri = data.getData();
            photoAdapter1.addItem(String.valueOf(uri));
            updatePhotoIndexLabel();
            return;
        }
    }

    private void updatePhotoIndexLabel() {
        if (photoAdapter1.getItemList() == null) {
            tvPhotoLabel1.setText(String.format("%s/%s",0,10));
        } else {
            int registerPhotoAmount = photoAdapter1.getItemList().size();
            tvPhotoLabel1.setText(String.format("%s/%s",registerPhotoAmount,10));
        }
    }
}
