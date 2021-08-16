package com.example.cpims;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class reports extends AppCompatActivity {

       BarChart barChart,barChart1,barChart2,barChart3;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

            barChart= findViewById(R.id.bargraph);
            barChart1 = findViewById(R.id.bargraph1);
            barChart2 = findViewById(R.id.bargraph2);
            barChart3 = findViewById(R.id.bargraph3);
              db = FirebaseFirestore.getInstance();


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


                List<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f,count));


                BarDataSet set = new BarDataSet(entries,"No of Defiled Children");


                BarData data = new BarData(set);
                data.setBarWidth(0.5f); // set custom bar width
                barChart.setData(data);
                barChart.setFitBars(true); // make the x-axis fit exactly all bars
                barChart.invalidate(); // refresh
                set.setColor(Color.RED);



            } else {
                Toast.makeText(reports.this, "Error getting documents", Toast.LENGTH_SHORT).show();
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



                List<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f,count));

                BarDataSet set = new BarDataSet(entries, "No of Children in Children Homes");

                BarData data1 = new BarData(set);
                data1.setBarWidth(0.5f);
                barChart1.setData(data1);
                barChart1.setFitBars(true);
                barChart1.invalidate();
                barChart1.bringToFront();


            } else {
                Toast.makeText(reports.this, "Error getting documents", Toast.LENGTH_SHORT).show();
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



                List<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f,count));

                BarDataSet set = new BarDataSet(entries, "No of Children from Risk Environment");

                BarData data1 = new BarData(set);
                data1.setBarWidth(0.5f);
                barChart2.setData(data1);
                barChart2.setFitBars(true);
                barChart2.invalidate();
                barChart2.bringToFront();
                set.setColor(Color.BLACK);


            } else {
                Toast.makeText(reports.this, "Error getting documents", Toast.LENGTH_SHORT).show();
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



                List<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f,count));

                BarDataSet set = new BarDataSet(entries, "No of Vulnerable Children");

                BarData data1 = new BarData(set);
                data1.setBarWidth(0.5f);
                barChart3.setData(data1);
                barChart3.setFitBars(true);
                barChart3.invalidate();
                barChart3.bringToFront();
                set.setColor(Color.GREEN);


            } else {
                Toast.makeText(reports.this, "Error getting documents", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });

    }


}