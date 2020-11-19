package com.example.ftelevision.models;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.ftelevision.BaseActivity;
import com.example.ftelevision.R;
import com.example.ftelevision.adapters.televisionAdapter;
import com.example.ftelevision.models.TelevisionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static androidx.appcompat.widget.AppCompatDrawableManager.get;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_televisions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });
        lv_list_televisions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                model = modelArrayList.get(position);
                goToDetail(model);
            }
        });

    }
    protected void init(){
        fab_list_create = findViewById(R.id.fab_list_create);
       lv_list_televisions = findViewById(R.id.lv_list_televisions);
    }
    protected void  getTelevisions(){
        if(collectionReference != null){
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult() != null){
                                    modelArrayList = new ArrayList<>();
                                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                                        model = snapshot.toObject(TelevisionModel.class);
                                        modelArrayList.add(model);
                                    }
                                    if(modelArrayList.size() > 0){
                                        paintTelevisions(modelArrayList);
                                    }else{
                                        makeSimpleAlertDialog("Alert", "televisions doesn't found");
                                    }
                                }else{
                                    makeSimpleAlertDialog("Warning", "televisions doesn't found");
                                }
                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });
        }else{
            makeSimpleToast("Database error",1);
        }
    }

    private void  paintTelevisions(ArrayList<TelevisionModel> modelArrayList) {
        adapters = new televisionAdapter( this, modelArrayList);
        lv_list_televisions.setAdapter(adapters);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTelevisions();
    }
}