package com.example.skannaus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HistoriaActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Model> models = new ArrayList<Model>();
    RecyclerView rvTechSolPoint;
    RvAdapter rvAdapter;
    TextView tvAdd;
    EditText etEnterName;
    int position;
    private int tuoteId;
    private String tuoteNimi;
    private String tuoteEan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        rvTechSolPoint = findViewById(R.id.rv_list_item);
        tvAdd = findViewById(R.id.tv_add);
        etEnterName = findViewById(R.id.et_enter_name);
        rvTechSolPoint.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvTechSolPoint.setLayoutManager(layoutManager);
        rvAdapter = new RvAdapter(getApplicationContext(), models,
                new RvAdapter.Onclick() {
                    @Override
                    public void onEvent(Model model, int pos) {
                        position = pos;
                        etEnterName.setText(model.getName());
                    }
                });
        rvTechSolPoint.setAdapter(rvAdapter);
        try {
            lueCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add: {
                insertMethod(String.valueOf(etEnterName.getText()));
            }
            break;
        }
    }


    private void insertMethod(String tuoteNimi) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", tuoteNimi);
            Model model = gson.fromJson(String.valueOf(jsonObject), Model.class);
            models.add(model);
            rvAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void lueCSV() throws IOException {
        String[] row = null;
        String csvFilename = "data.txt";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
        List content = csvReader.readAll();

        for (Object object : content) {
            row = (String[]) object;
            String nimi = Arrays.toString(row);
            insertMethod(nimi);
        }
        csvReader.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String csv = "data.txt";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
            List<String[]> data = new ArrayList<>();
            for (int i = 0; i < models.size(); i++) {
                data.add(new String[] {models.get(i).toString()});
            }
            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
