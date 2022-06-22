package com.cuongpq.basemvp.view.ui.activity.main;


import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.content,new HomeFragment(),HomeFragment.class.getName()).addToBackStack(null).commit();
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

}