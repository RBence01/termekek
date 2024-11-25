package com.example.restapi_people;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText ageEditText;
    private Button cancelButton;
    private Button addButton;
    private Button showAddFormButton;
    private LinearLayout formLinearLayout;
    private ListView peopleListView;
    private List<People> peopleList;
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        // API interfész létrehozása
        RetrofitApiService apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
        loadPeople(apiService);
        showAddFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formLinearLayout.setVisibility(View.VISIBLE);
                showAddFormButton.setVisibility(View.GONE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formLinearLayout.setVisibility(View.GONE);
                showAddFormButton.setVisibility(View.VISIBLE);
            }
        });
    }


    public void init() {
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        ageEditText = findViewById(R.id.ageEditText);
        formLinearLayout = findViewById(R.id.formLinearLayout);
        cancelButton = findViewById(R.id.cancelButton);
        addButton = findViewById(R.id.addButton);
        showAddFormButton = findViewById(R.id.showAddFormButton);
        peopleListView = findViewById(R.id.peopleListView);
        peopleList = new ArrayList<>();
        peopleAdapter = new PeopleAdapter(peopleList, this);
        peopleListView.setAdapter(peopleAdapter);
    }

    public void loadPeople(RetrofitApiService apiService) {

        apiService.getAllPeople().enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    peopleList.clear();
                    peopleList.addAll(response.body());
                    peopleAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Fail to load the people list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error loading the people list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}