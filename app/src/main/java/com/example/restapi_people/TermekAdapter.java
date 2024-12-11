package com.example.restapi_people;

import android.app.AlertDialog;
import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermekAdapter extends BaseAdapter {
    private List<Termek> termekList;
    private MainActivity context;
    private RetrofitApiService apiService;

    TermekAdapter(List<Termek> termekList, MainActivity context) {
        this.termekList = termekList;
        this.context = context;
        this.apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
    }
    @Override
    public int getCount() {
        return termekList.size();
    }

    @Override
    public Object getItem(int i) {
        return termekList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.people_list_items, viewGroup, false);

        TextView nev = view.findViewById(R.id.nev);
        TextView mennyiseg = view.findViewById(R.id.mennyiseg);
        TextView darab_ar = view.findViewById(R.id.darab_ar);
        TextView kategoria = view.findViewById(R.id.kategoria);

        Termek termek = termekList.get(i);

        nev.setText(termek.getNev());
        mennyiseg.setText(MessageFormat.format("{0}db", termek.getMennyiseg()));
        darab_ar.setText(MessageFormat.format("{0}Ft", termek.getDarab_ar()));
        kategoria.setText(termek.getKategoria());

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Törlés");
                builder.setMessage("Biztosan törli a terméket?");
                builder.setPositiveButton("Igen", (dialog, which) -> {
                    // Handle delete confirmation
                    apiService.deleteTermek(termekList.get(i).getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) Toast.makeText(context, "Sikeres törlés", Toast.LENGTH_SHORT).show();
                            else Toast.makeText(context, "Sikertelen törlés", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, "Sikertelen törlés", Toast.LENGTH_SHORT).show();
                        }
                    });
                    termekList.remove(i);
                    notifyDataSetChanged();
                    dialog.dismiss();
                });
                builder.setNegativeButton("Nem", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });


        return view;
    }
}
