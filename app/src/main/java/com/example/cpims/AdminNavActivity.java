package com.example.cpims;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cpims.databinding.ActivityAdminNavBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class AdminNavActivity<ActivityAdminNavBinding> extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityAdminNavBinding binding;
    FirebaseFirestore db;
    TextView textDisplay,lowincomeshow,drugaddictsshow,vulnerableshow,riskenvshow,vaneshow,childhomeshow,childmarriageshow,
            childlabourshow,childsocialshow,
    childdisputesshow,traumashow,childoffendersshow,handicappedshow,
            separatedshow,streetshow,droppedshow,mydashboard;
    CardView carddefiled,cardchildhome,cardchildoffenders,cardseparated,cardchildlabour,carddidputes,cardvulnerable,cardhandicapped,cardchildmarriage,
            carddropouts,cardtrauma,cardriskenv,cardsocial,cardvane;
    private FirebaseAuth mout;
    FloatingActionButton tosms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_nav);


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);


        db = FirebaseFirestore.getInstance();
        textDisplay = findViewById(R.id.defiled);
        drugaddictsshow = findViewById(R.id.drugaddictsshow);
        separatedshow = findViewById(R.id.separatedshow);
        carddefiled = findViewById(R.id.carddefiled);
        droppedshow = findViewById(R.id.droppedshow);
        tosms = findViewById(R.id.floatingbutton);

        vulnerableshow = findViewById(R.id.vulnerableshow);
        riskenvshow = findViewById(R.id.riskenvshow);
        vaneshow = findViewById(R.id.vaneshow);
        childhomeshow = findViewById(R.id.childhomeshow);
        childmarriageshow = findViewById(R.id.childmarriageshow);
        childlabourshow = findViewById(R.id.childlabourshow);
        childsocialshow = findViewById(R.id.childsocialshow);
        childdisputesshow = findViewById(R.id.childdisputesshow);
        traumashow = findViewById(R.id.traumashow);
        childoffendersshow = findViewById(R.id.childoffenders);
        handicappedshow = findViewById(R.id.handicappedshow);

        cardchildhome=findViewById(R.id.cardchildhome);
        cardchildoffenders=findViewById(R.id.cardchildoffenders);
        cardseparated =findViewById(R.id.cardseparated);
        cardchildlabour=findViewById(R.id.cardchildlabour);
        carddidputes=findViewById(R.id.carddisputes);
        cardvulnerable=findViewById(R.id.cardvulnerable);
        cardhandicapped=findViewById(R.id.cardhandicapped);
        cardchildmarriage=findViewById(R.id.cardchildmarriage);
        carddropouts=findViewById(R.id.carddropouts);
        cardtrauma=findViewById(R.id.cardtrauma);
        cardriskenv=findViewById(R.id.cardriskenv);
        cardsocial=findViewById(R.id.cardsocial);
        cardvane=findViewById(R.id.cardvane);



        mout = FirebaseAuth.getInstance();

//
//        binding = ActivityAdminNavBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());


        db.collection("defiled").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                textDisplay.setText(fields.toString());


            } else {
                Toast.makeText(AdminNavActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("vulnerable").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                vulnerableshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("riskenv").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                riskenvshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });




        db.collection("separated").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                separatedshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("Vane").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                vaneshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });



        db.collection("childrenhomes").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childhomeshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });

        db.collection("childoffenders").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childoffendersshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("handicapped").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                handicappedshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("childmarriage").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childmarriageshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });


        db.collection("childlabour").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childlabourshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });




        db.collection("sass").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childsocialshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });



        db.collection("schooldropped").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                droppedshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });



        db.collection("childdispute").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                childdisputesshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });



        db.collection("Trauma").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                traumashow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });




        db.collection("daddicts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int count = 0;
                for (DocumentSnapshot document : task.getResult()) {
                    count++;
                }
                Log.d("TAG", count + "");

                QuerySnapshot doc = task.getResult();
                StringBuilder fields = new StringBuilder();
                fields.append(count).append("");
                drugaddictsshow.setText(fields.toString());



            } else {
                Toast.makeText(AdminNavActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });








        setSupportActionBar(toolbar);

        tosms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
// implements your things
                //TODO: change number
                String textnum = "+254788137120";
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.setData(Uri.parse("sms:" + textnum));
                startActivity(smsIntent);
            }
        });


        carddefiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,defiled.class);
                startActivity(intent);
            }
        });
        cardchildhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNavActivity.this,childhome.class);
                startActivity(intent);
            }
        });
        cardchildoffenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,childoffenders.class);
                startActivity(intent); 
            }
        });
        cardseparated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,separatedcases.class);
                startActivity(intent);
            }
        });
        cardchildlabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,childlabourcases.class);
                startActivity(intent);
            }
        });
        carddidputes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,childdispute.class);
                startActivity(intent);
            }
        });
        cardvulnerable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,vulnerable.class);
                startActivity(intent);
            }
        });
        cardhandicapped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,handicaped.class);
                startActivity(intent);
            }
        });
        cardchildmarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,childmarriage.class);
                startActivity(intent);
            }
        });
        carddropouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,schooldropped.class);
                startActivity(intent);
            }
        });

        cardtrauma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,trauma.class);
                startActivity(intent);
            }
        });
        cardriskenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,riskenv.class);
                startActivity(intent);
            }
        });
        cardsocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,sass.class);
                startActivity(intent);
            }
        });
        cardvane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminNavActivity.this,vane.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.






//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.admin_nav, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//
//
    }

    private void RunAnimation()


    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale);
        a.reset();
        TextView mydashboard = findViewById(R.id.myDashboard);
        mydashboard.clearAnimation();
        mydashboard.startAnimation(a);
    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();

        if (id==R.id.nav_home){
//            write your code here
        }else if (id== R.id.stateslink){
            Intent intent= new Intent(AdminNavActivity.this,registerstates.class);
            startActivity(intent);
        }else if (id== R.id.policedesklink) {
            Intent intent = new Intent(AdminNavActivity.this, policedesk.class);
            startActivity(intent);
        }else if (id== R.id.reportslink) {
            Intent intent = new Intent(AdminNavActivity.this, reports.class);
            startActivity(intent);
        }else if (id== R.id.reguserslink) {
            Intent intent = new Intent(AdminNavActivity.this, registertypes.class);
            startActivity(intent);
        }else if (id== R.id.childrencaseslink) {
            Intent intent = new Intent(AdminNavActivity.this, casetypes.class);
            startActivity(intent);
        }else if (id== R.id.institutionslink) {
            Intent intent = new Intent(AdminNavActivity.this, ManageInstitutions.class);
            startActivity(intent);
        }else if (id== R.id.muserslink) {
            Intent intent = new Intent(AdminNavActivity.this, Manageusers.class);
            startActivity(intent);
        }else if (id== R.id.filedcaseslink) {
            Intent intent = new Intent(AdminNavActivity.this, filedcases.class);
            startActivity(intent);
        }else  if (id == R.id.logoutlink) {
            FirebaseAuth.getInstance().signOut();

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mout.signOut();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}