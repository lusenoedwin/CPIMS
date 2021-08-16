package com.example.cpims;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.media.Image;
        import android.net.Uri;

        import android.provider.MediaStore;
//        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;


        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.storage.FirebaseStorage;

        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;


//        import com.theartofdev.edmodo.cropper.CropImage;
//        import com.theartofdev.edmodo.cropper.CropImageView;


public class captureimageupload extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitBtn;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    private Uri mImageUri = null;

    private static final  int GALLERY_REQUEST =1;

    private static final int CAMERA_REQUEST_CODE=1;

    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captureimageupload);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        mSelectImage = findViewById(R.id.imageSelect);
//        mPostTitle = (EditText)  findViewById(R.id.titleField);
//        mPostDesc = (EditText) findViewById(R.id.descField);
        mSubmitBtn = findViewById(R.id.submitBtn);

        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
                //startActivityForResult(intent,CAMERA_REQUEST_CODE);


/*
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

                */

            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }



    private void startPosting(){

        mProgress.setMessage("Posting to blog...");


        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(desc_val) && mImageUri != null){

            mProgress.show();
            StorageReference filepath = mStorage.child("Blog_Images").child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Uri downloadUrl =taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("title").setValue(title_val);
                    newPost.child("desc").setValue(desc_val);
//                    newPost.child("image").setValue(downloadUrl.toString());


                    mProgress.dismiss();
                    startActivity(new Intent(captureimageupload.this,MainActivity.class));
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){




            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);

//            CropImage.activity(mImageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1,1)
//                    .start(this);




        /* Bitmap mImageUri1 = (Bitmap) data.getExtras().get("data");
         mSelectImage.setImageBitmap(mImageUri1);

          Toast.makeText(this, "Image saved to:\n" +
                  data.getExtras().get("data"), Toast.LENGTH_LONG).show();


*/



        }

//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//
//                mSelectImage.setImageURI(resultUri);
//                mImageUri = resultUri;
//
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
//        }


    }




}