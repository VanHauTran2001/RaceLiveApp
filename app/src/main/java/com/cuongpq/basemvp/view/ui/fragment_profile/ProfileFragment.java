package com.cuongpq.basemvp.view.ui.fragment_profile;

import static android.app.Activity.RESULT_OK;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentProfileBinding;
import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.login.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;

public class ProfileFragment extends BaseFragmentMvp<FragmentProfileBinding,ProfilePresenter> implements IProfileView{
    private Uri mUri;
    public static final int MY_REQUEST_CODE = 10;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new ProfilePresenter(this);
        presenter.oninitPresenter();
    }
    @Override
    public void onClickListenner() {
        binding.txtLogout.setOnClickListener(view -> {
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Confirm Dialog")
                    .setMessage("Are you sure you want to exit ?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {

                    })
                    .create();
            dialog.show();
        });
        binding.imgAvata.setOnClickListener(view -> onClickRequestPermission());
        binding.imgSave.setOnClickListener(view -> onUpdateUser());
    }
    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , result -> {
                if (result.getResultCode() == RESULT_OK){
                    Intent intent = result.getData();
                    if (intent==null){
                        return;
                    }
                    Uri uri = intent.getData();
                    setUri(uri);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                        setBitmapImageView(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==MY_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }
    public void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    @Override
    public void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }else {
            Uri photoUrl = user.getPhotoUrl();
            binding.txtEmailAccount.setText(user.getEmail());
            Glide.with(getActivity()).load(photoUrl).error(R.drawable.avatar).into(binding.imgAvata);
        }
    }
    @Override
    public void onClickRequestPermission() {

        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else {
            String [] permison = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permison,MY_REQUEST_CODE);
        }
    }

    @Override
    public void onUpdateUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser==null){
            return;
        }
        viewLoading();
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(mUri)
                .build();
        firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                disableLoading();
                Toast.makeText(getContext(), "Upload avata onsuccessfull", Toast.LENGTH_SHORT).show();
                Glide.with(getActivity()).load(firebaseUser.getPhotoUrl()).error(R.drawable.avatar).into(binding.imgAvata);
            }
        });
    }

    @Override
    public Context ongetContext() {
        return getActivity();
    }


    public void setBitmapImageView(Bitmap bitmapImageView){
        Glide.with(binding.imgAvata).load(bitmapImageView).into(binding.imgAvata);
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }


}
