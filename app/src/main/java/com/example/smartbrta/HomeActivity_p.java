package com.example.smartbrta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity_p extends AppCompatActivity {

    private static final String TAG = "HomeActivity_Tag";

    private RecyclerView recyclerView;
    private ArrayList<DataSetFire> arrayList;
    private FirebaseRecyclerOptions<DataSetFire> options;
    private FirebaseRecyclerAdapter<DataSetFire,FirebaseViewHolder> adapter;
    private DatabaseReference databaseReference;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<DataSetFire> dataSetFireArrayList = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
//        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
        databaseReference.keepSynced(true);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSetFire dataSetFire = dataSnapshot.getValue(DataSetFire.class);

                    dataSetFireArrayList.add(dataSetFire);
                    setDataInRecyclerView();




                /*if (!dataSetFire.getAmount().equals("0")){
                    dataSetFireArrayList.add(dataSetFire);
                    recyclerViewAdapter.setDataList(dataSetFireArrayList);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }*/


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        

//        options = new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(databaseReference,DataSetFire.class).build();
//
//        adapter = new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final DataSetFire model) {
//
//
//              holder.name.setText(model.getName());
//              holder.reg.setText(model.getReg());
//              holder.gender.setText(model.getGender());
//
//              holder.itemView.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View view) {
//                      Intent intent = new Intent(HomeActivity.this, Profile_admin.class);
//                      intent.putExtra("name", model.getName());
//                      intent.putExtra("reg", model.getReg());
//                      intent.putExtra("gender", model.getGender());
//                      startActivity(intent);
//                  }
//              });
//
//
//
//            }
//
//            @NonNull
//            @Override
//            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                return new FirebaseViewHolder(LayoutInflater.from(HomeActivity.this).inflate(R.layout.row,viewGroup,false));
//            }
//        };



//        recyclerView.setAdapter(adapter);


    }
    private void setDataInRecyclerView() {
        recyclerViewAdapter.setDataList(dataSetFireArrayList,this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
