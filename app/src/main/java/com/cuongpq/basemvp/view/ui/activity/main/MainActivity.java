package com.cuongpq.basemvp.view.ui.activity.main;



import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment_profile.ProfileFragment;

import java.io.IOException;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private SQLiteHelper sqLiteHelper;
    final private ProfileFragment profileFragment = new ProfileFragment();
    public static final int MY_REQUEST_CODE = 10;
    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        if (intent==null){
                            return;
                        }
                        Uri uri = intent.getData();
                        profileFragment.setUri(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            profileFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
    @Override
    protected void initView() {
        replaceFragment(new HomeFragment());
        sqLiteHelper = new SQLiteHelper(this,"Database.sqlite",null,1);
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS Race(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdAccount VARCHAR(20)," +
                "IdRace INTEGER," +
                "NameRace NVARCHAR(100)," +
                "Date VARCHAR(50))");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS Car1 (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdAccount VARCHAR(20)," +
                "IdRace INTEGER," +
                "IdCar INTEGER," +
                "NameCar NVARCHAR(100)," +
                "NamePerson NVARCHAR(100)," +
                "Round INTEGER,"+
                "Start VARCHAR(20),"+
                "SS1 VARCHAR(20)," +
                "SS2 VARCHAR(20)," +
                "SS3 VARCHAR(20)," +
                "SS4 VARCHAR(20)," +
                "SS5 VARCHAR(20)," +
                "SS6 VARCHAR(20)," +
                "Stop VARCHAR(20))");
        onClickMenuItem();
    }
    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,fragment,Fragment.class.getName())
                .commit();
    }
    private void onClickMenuItem() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.createRace:
                    replaceFragment(new HomeFragment());
                    binding.bottomNavigation.getMenu().findItem(R.id.createRace).setChecked(true);
                    break;
                case R.id.listRace:
                    replaceFragment(new ListRaceFragment());
                    binding.bottomNavigation.getMenu().findItem(R.id.listRace).setChecked(true);
                    break;
                case R.id.profile:
                    replaceFragment(profileFragment);
                    binding.bottomNavigation.getMenu().findItem(R.id.profile).setChecked(true);
                    break;
            }
            return false;
        });
    }

    @Override
    public int getIdLoading() {
        return R.id.loading;
    }

    @Override
    public int getMainLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
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

}