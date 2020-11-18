package com.example.ftelevision.models;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.ftelevision.BaseActivity;
import com.example.ftelevision.R;
import com.example.ftelevision.models.TelevisionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class DetailActivity extends BaseActivity {

    private FloatingActionButton fab_detail_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (TelevisionModel) getIntent().getSerializableExtra( "model");
            if(model != null){
                makeSimpleAlertDialog("Success", "serial: " + model.getSerial());
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", (Serializable) model);
                DataDetailFragment.receiveData(bundle);
            }else{
                makeSimpleAlertDialog("Error", "Empty serial");
            }
        fab_detail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });
    }
    protected void  init(){
        fab_detail_list = findViewById(R.id.fab_detail_list);
    }
}