package com.cuongpq.basemvp.view.ui.activity.main;
import androidx.fragment.app.Fragment;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMain2Binding;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment_home_member.HomeMemberFragment;
import com.cuongpq.basemvp.view.ui.fragment_profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment_statist.FragmentStatist;
public class MainActivity2 extends BaseActivity<ActivityMain2Binding> {

    @Override
    protected void initView() {
        replaceFragmentUser(new FragmentStatist());
        onClickMenuItemUser();
    }

    @Override
    public int getMainLayout() {
        return R.layout.activity_main2;
    }
    private void replaceFragmentUser(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,fragment,Fragment.class.getName())
                .commit();
    }
    private void onClickMenuItemUser(){
        binding.bottomNavigationUser.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.listRaceUser:
                    replaceFragmentUser(new FragmentStatist());
                    binding.bottomNavigationUser.getMenu().findItem(R.id.listRaceUser).setChecked(true);
                    break;
                case R.id.profileUser:
                    replaceFragmentUser(new ProfileFragment());
                    binding.bottomNavigationUser.getMenu().findItem(R.id.profileUser).setChecked(true);
                    break;
            }
            return false;
        });
    }
}