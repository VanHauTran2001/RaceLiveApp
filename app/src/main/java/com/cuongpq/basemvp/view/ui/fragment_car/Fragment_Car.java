package com.cuongpq.basemvp.view.ui.fragment_car;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCarBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

public class Fragment_Car extends BaseFragmentMvp<FragmentCarBinding,CarPresenter> implements ICarView {
    private int idRace;
    public static final String TAG = Fragment_Car.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_car;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new CarPresenter(this);
        presenter.onInitPresenter();
        idRace = getArguments().getInt("raceID");
    }

    @Override
    public void onClickListener() {
        binding.btnAndCar.setOnClickListener(view -> {
            String edtIDCar = binding.edtIDCar.getText().toString().trim();
            int idCar = Integer.parseInt(edtIDCar);
            String nameCar = binding.edtNameCar.getText().toString().trim();
            String namePerson = binding.edtNamePerson.getText().toString().trim();
            presenter.onAddCar(idCar,nameCar,namePerson,idRace);
            onBackStack();
        });
        binding.btnBackCar.setOnClickListener(view -> onBackStack());
    }

    @Override
    public void onBackStack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public Context onContext() {
        return getActivity();
    }

    @Override
    public void onSucessfull(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }
}
