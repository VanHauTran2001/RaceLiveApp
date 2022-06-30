package com.cuongpq.basemvp.view.ui.login.activity;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityLoginBinding;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment_signin.FragmentSignin;
import java.util.ArrayList;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    public int getMainLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FragmentSignin(),"Login");
        binding.viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> fragments;
        private final ArrayList<String> titles;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment , String title){
            fragments.add(fragment);
            titles.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}