package com.cuongpq.basemvp.view.ui.activity.main;
import androidx.fragment.app.Fragment;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment_profile.ProfileFragment;
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private SQLiteHelper sqLiteHelper;
    final private ProfileFragment profileFragment = new ProfileFragment();
    @Override
    protected void initView() {
//        FirebaseAuth Auth = FirebaseAuth.getInstance();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        FirebaseUser firebaseUser = Auth.getCurrentUser();
//        DocumentReference documentReference = firestore.collection("Users").document(firebaseUser.getUid());
//        documentReference.get().addOnSuccessListener(documentSnapshot -> {
//            if (documentSnapshot.getString("isMember")!=null){
//                Ultils.permision = 1;
//                startActivity(new Intent(this, MainActivity2.class));
//            }
//            if (documentSnapshot.getString("isStatist")!=null){
//                Ultils.permision = 2;
//                startActivity(new Intent(this, MainActivity2.class));
//            }if (firebaseUser.getEmail().equals("admin@gmail.com")){
//                Ultils.permision = 0;
//                replaceFragment(new HomeFragment());
//                onClickMenuItem();
//            }
//        });
        replaceFragment(new HomeFragment());
        onClickMenuItem();
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
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS User (Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "IdAccount VARCHAR(20),"+
                "Email NVARCHAR(50),"+
                "Password NVARCHAR(50),"+
                "Permission VARCHAR(20))");

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
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode ==MY_REQUEST_CODE){
//            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                openGallery();
//            }
//        }
//    }
//    public void openGallery(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        activityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
//    }

}