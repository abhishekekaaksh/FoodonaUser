package com.foodona.foodona.Restarant.restaurant;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Map.EditAddress;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.Utils.CommonUtils;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends Fragment implements View.OnClickListener {
    private static final String IMAGE_DIRECTORY = "/Foodona";
    private int GALLERY = 1, CAMERA = 2;
    String filePath = "";
    View rootView;
    TextView addphoto;
    ImageView imgProfileImage,back;
    EditText edit_usernamee;
    EditText edit_phone;
    EditText edit_useremail;
    EditText edit_location;
    EditText edit_bio;
    Button email_sign_in_button;
    AVLoadingIndicatorView avi;
    String isImageEdit = "0";
    TextView title;
    String UserImage,UserName,UserPhone,userLocation,UserBio,UserId;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.edit_profile_fragment, container, false);
        // init();
        //    User_id = AppPreferences.getSavedUser(getActivity()).getId();
        UserId = AppPreferences.getSavedUser(getActivity()).getId();

        find();
        requestMultiplePermissions();


        //  st_timezoneID = TimeZone.getDefault().getID();
        //  System.out.println(st_timezoneID);
        // getLocation();
        //   getProfilesApi();
        return rootView;

    }

    public void find() {
        addphoto = rootView.findViewById(R.id.addphoto);
        back = rootView.findViewById(R.id.back);
        imgProfileImage = rootView.findViewById(R.id.profilephoto);
        edit_usernamee = rootView.findViewById(R.id.edit_usernamee);
        edit_useremail=rootView.findViewById(R.id.edit_useremail);
        edit_phone = rootView.findViewById(R.id.edit_phone);
        edit_location = rootView.findViewById(R.id.edit_location);
        edit_bio = rootView.findViewById(R.id.edit_bio);
        avi=rootView.findViewById(R.id.bar);
        email_sign_in_button = rootView.findViewById(R.id.email_sign_in_button);
        addphoto.setOnClickListener(this);
        back.setOnClickListener(this);
        edit_location.setOnClickListener(this);
        email_sign_in_button.setOnClickListener(this);
        //RestaurentList();
        //
    }

    @Override
    public void onClick(View view) {
        if (view == addphoto) {
            showPictureDialog();
        }
        if (view == email_sign_in_button) {
            checkValidations();
        }if (view==back){
            onBackPressed();
        }/*if (view==edit_location){
           *//* Fragment editProfileFragment = new EditProfileFragment();

            FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, editProfileFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*//*

            Intent intent = new Intent(getActivity(), EditUserAddress.class);
            startActivity(intent);
        }*/
    }

    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
           // super.onBackPressed();
        }
    }
    private void checkValidations() {

        if (edit_usernamee.getText().toString().isEmpty()  ||
                edit_location.getText().toString().isEmpty() || edit_bio.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all details .", Toast.LENGTH_SHORT).show();
        }
        else if (imgProfileImage.getDrawable() == null )
            Toast.makeText(getActivity(), "Please put some image for User", Toast.LENGTH_SHORT).show();

        else
            editMenuApi();

           // editMenuApi();
    }

    private void editMenuApi() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if(isImageEdit.equals("0")){
            Bitmap   bitmap = ((BitmapDrawable) imgProfileImage.getDrawable()).getBitmap();
            filePath = saveImage(bitmap);
            Log.e("filePath", ""+filePath);
        }
        MultipartBody.Part part = null;
        //if(filePath!=null && isImageEdit.equals("1")) {
        //Create a file object using file path
        File file = new File(filePath);
        Log.e("filePath", ""+filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);
        //}
        //Create request body with text description and text media type
        RequestBody user_id = RequestBody.create(MediaType.parse("user_id"), UserId);
        RequestBody user_name = RequestBody.create(MediaType.parse("user_name"), edit_usernamee.getText().toString());
        RequestBody user_email = RequestBody.create(MediaType.parse("user_email"), edit_useremail.getText().toString());
        RequestBody user_bio = RequestBody.create(MediaType.parse("user_bio"), edit_bio.getText().toString());
        RequestBody user_address = RequestBody.create(MediaType.parse("user_address"), edit_location.getText().toString());
        RequestBody image = RequestBody.create(MediaType.parse("image"), isImageEdit);


        if(CommonUtils.isNetworkAvailable(getActivity())) {
            Call<ResponseBody> call = apiInterface.EditMenu(user_id,user_name, user_email, user_bio, user_address, image,part);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        avi.setVisibility(View.GONE);
                        Fragment userProfileFragmentActivity = new UserProfileFragmentActivity();
                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, userProfileFragmentActivity);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                       // Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);

                        Toast.makeText(getActivity(), " updated successfully. ", Toast.LENGTH_SHORT).show();
                       // dismiss();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    avi.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Update Something went wrong!.", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    filePath = saveImage(bitmap);
                    imgProfileImage.setImageBitmap(bitmap);
                    isImageEdit = "1";
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgProfileImage.setImageBitmap(thumbnail);
            filePath = saveImage(thumbnail);
            isImageEdit = "1";
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            //uploadToServer(f.getAbsolutePath(),myBitmap);  // uploading umage to server

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

}
