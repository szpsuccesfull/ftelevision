package com.example.ftelevision.conection;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseConection {

    private static  FirebaseAuth mAuth;

    //private  static StorageReference mStorageRef;
    private static FirebaseStorage mFirebaseStorage;

    private static FirebaseFirestore db;


    public static FirebaseAuth conectionAuth(){;
     return mAuth = FirebaseAuth.getInstance();
    }
    /*public static StorageReference conectionStorage(){
       return  mStorageRef = FirebaseStorage.getInstance().getReference();

    }*/
    public static FirebaseFirestore conectionFirestore(){
         return db = FirebaseFirestore.getInstance();
    }
    public static FirebaseStorage conectionStorage(){
        return mFirebaseStorage = FirebaseStorage.getInstance();
    }
}
