package com.example.ftelevision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.ftelevision.models.TelevisionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_create_save,fab_create_clear,fab_create_back;
    ImageView iv_create_image;
    TextView tv_create_click_image;
    EditText edt_create_serial,edt_create_brand,edt_create_description;
    Button btnsubir;

    private Button mUploaBtb;
    private StorageReference mStorage;
    private static  final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStorage = FirebaseStorage.getInstance().getReference();
        mUploaBtb =(Button) findViewById(R.id.btnsubir);

        mUploaBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

        super.init();
        init();
        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });
        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clear();
            }
        });
        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serial,description,brand;
                boolean active;

                serial = edt_create_serial.getText().toString();
                description = edt_create_description.getText().toString();
                brand = edt_create_brand.getText().toString();

                if(serial.isEmpty()|| description.isEmpty()||brand.isEmpty()){
                    makeSimpleAlertDialog("Info", "please fill all fields");
                }else{
                    model = new TelevisionModel();
                    model.setActive(true);
                    model.setSerial(serial);
                    model.setDescription(description);
                    model.setBrand(brand);

                    save(model);
                }

            }
        });
    }

    private void save(TelevisionModel model) {
        if(collectionReference !=null){
            collectionReference.add(model)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            if(task.getResult() != null){
                                makeSimpleAlertDialog("Success", "televisions saved");
                                clear();
                            }else{
                                makeSimpleAlertDialog("Warning","televisions not saved");
                            }
                        }else{
                            makeSimpleAlertDialog("Error", task.getException().getMessage());
                        }
                    }

                });
        }else{
            makeSimpleAlertDialog("Error", "Not Connection");
        }
    }

    protected void init(){
        fab_create_back = findViewById(R.id.fab_create_back);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        fab_create_save = findViewById(R.id.fab_create_save);
        iv_create_image = findViewById(R.id.iv_create_image);
        edt_create_serial = findViewById(R.id.edt_create_serial  );
        edt_create_brand = findViewById(R.id.edt_create_brand);
        edt_create_description = findViewById(R.id.edt_create_description);
        tv_create_click_image = findViewById(R.id.tv_create_click_image);

    }
    private void clear(){
        edt_create_description.setText("");
        edt_create_brand.setText("");
        edt_create_serial.setText("");

        edt_create_serial.requestFocus();
        iv_create_image.setImageResource(R.drawable.ic_tv_black_18dp);
    }

    @Override
    protected void onActivityResult(int requestserial, int resultserial, @Nullable Intent data) {
        super.onActivityResult(requestserial, resultserial, data);
        if(requestserial == GALLERY_INTENT && requestserial== RESULT_OK){

            Uri uri =data.getData();
            StorageReference filepath =mStorage.child("fotos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(com.example.ftelevision.CreateActivity.this, "se subio corretamente ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}