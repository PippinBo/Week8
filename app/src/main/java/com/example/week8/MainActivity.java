package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week8.adapter.RecyclerViewAdapter;
import com.example.week8.databinding.ActivityMainBinding;
import com.example.week8.model.CourseResult;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager layoutManager;
    private List<CourseResult> units;
    private RecyclerViewAdapter adapter;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        units=new ArrayList<CourseResult>();
        units = CourseResult.createContactsList();
        adapter = new RecyclerViewAdapter(units);
        //this just creates a line divider between rows
        binding.recyclerView.addItemDecoration(new
                DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unit = binding.etUnit.getText().toString().trim();
                String smark= binding.etMark.getText().toString().trim();
                if (!unit.isEmpty() || !smark.isEmpty()) {
                    int mark=new Integer(smark).intValue();
                    saveData(unit, mark);
                }
            }
        });
    }
    private void saveData(String unit, int mark) {
        CourseResult courseResult = new CourseResult(unit, mark);
        units.add(courseResult);
        adapter.addUnits(units);
    }
}
