package com.example.restapi_people;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PeopleAdapter extends BaseAdapter {

    private List<People> peopleList;
    private Context context;

    public PeopleAdapter(List<People> peopleList, Context context) {
        this.peopleList = peopleList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.people_list_items, viewGroup, false);

        TextView firstNameTextView = view.findViewById(R.id.firstNameTextView);
        TextView lastNameTextView = view.findViewById(R.id.lastNameTextView);
        TextView ageNameTextView = view.findViewById(R.id.ageNameTextView);
        TextView emailTextView = view.findViewById(R.id.emailTextView);
        Button deleteButton = view.findViewById(R.id.deleteButton);

        People people = peopleList.get(i);
        firstNameTextView.setText(people.getFirstName());
        lastNameTextView.setText(people.getLastName());
        ageNameTextView.setText("(" + people.getAge() + ")");
        emailTextView.setText(people.getEmail());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete button click with alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Törlés");
                builder.setMessage("Biztosan törli a felhasználót?");
                builder.setPositiveButton("Igen", (dialog, which) -> {
                    // Handle delete confirmation
                    peopleList.remove(i);
                    notifyDataSetChanged();
                    dialog.dismiss();
                });
                builder.setNegativeButton("Nem", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        return view;
    }
}
