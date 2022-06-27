package com.cuongpq.basemvp.view.ui.fragment_profile;

import static com.cuongpq.basemvp.view.ui.activity.main.MainActivity.MY_REQUEST_CODE;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentProfileBinding;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.login.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileFragment extends BaseFragmentMvp<FragmentProfileBinding,ProfilePresenter> implements IProfileView{
    private Uri mUri;
    private MainActivity mainActivity;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new ProfilePresenter(this);
        presenter.oninitPresenter();
        mainActivity = (MainActivity) getActivity();
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
        if (mainActivity==null){
            return;
        }
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
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
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(mUri)
                .build();
        firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Glide.with(getActivity()).load(firebaseUser.getPhotoUrl()).error(R.drawable.avatar).into(binding.imgAvata);
            }
        });
    }

    @Override
    public Context ongetContext() {
        return getActivity();
    }


    public void setBitmapImageView(Bitmap bitmapImageView){
        binding.imgAvata.setImageBitmap(bitmapImageView);
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

}
