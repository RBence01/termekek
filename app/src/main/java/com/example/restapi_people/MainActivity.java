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

    private EditText nevEditText;
    private EditText mennyisegEditText;
    private EditText darab_arEditText;
    private EditText kategoriaEditText;
    private Button cancelButton;
    private Button addButton;
    private Button showAddFormButton;
    private LinearLayout formLinearLayout;
    private ListView termekListView;
    private List<Termek> termekList;
    private TermekAdapter termekAdapter;

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Termek termek = new Termek(
                        nevEditText.getText().toString(),
                        Integer.parseInt(mennyisegEditText.getText().toString()),
                        Integer.parseInt(darab_arEditText.getText().toString()),
                        kategoriaEditText.getText().toString()
                );
                termekList.add(termek);
                nevEditText.setText("");
                mennyisegEditText.setText("");
                darab_arEditText.setText("");
                kategoriaEditText.setText("");
                termekAdapter.notifyDataSetChanged();
                formLinearLayout.setVisibility(View.GONE);
                showAddFormButton.setVisibility(View.VISIBLE);
                apiService.createTermek(termek).enqueue(new Callback<Termek>() {
                    @Override
                    public void onResponse(Call<Termek> call, Response<Termek> response) {
                        if (response.isSuccessful()) Toast.makeText(MainActivity.this, "Sikeres hozzáadás", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(MainActivity.this, "Sikertelen hozzáadás", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Termek> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Sikertelen hozzáadás", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    public void init() {
        nevEditText = findViewById(R.id.nev);
        mennyisegEditText = findViewById(R.id.mennyiseg);
        darab_arEditText = findViewById(R.id.darab_ar);
        kategoriaEditText = findViewById(R.id.kategoria);
        formLinearLayout = findViewById(R.id.formLinearLayout);
        cancelButton = findViewById(R.id.cancelButton);
        addButton = findViewById(R.id.addButton);
        showAddFormButton = findViewById(R.id.showAddFormButton);
        termekListView = findViewById(R.id.peopleListView);
        termekList = new ArrayList<>();
        termekAdapter = new TermekAdapter(termekList, this);
        termekListView.setAdapter(termekAdapter);
    }

    public void loadPeople(RetrofitApiService apiService) {

        apiService.getAllTermek().enqueue(new Callback<List<Termek>>() {
            @Override
            public void onResponse(Call<List<Termek>> call, Response<List<Termek>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    termekList.clear();
                    termekList.addAll(response.body());
                    termekAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Fail to load the people list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Termek>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error loading the people list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}