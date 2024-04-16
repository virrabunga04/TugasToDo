package com.example.tugastodo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAktivitas;
    private List<Aktivitas> toDo = new ArrayList<>();
    private AktivitasAdapter aktivitasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvAktivitas = this.findViewById(R.id.rvAktivitas);
        this.aktivitasAdapter = new AktivitasAdapter(MainActivity.this, this.toDo);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        this.rvAktivitas.setLayoutManager(lm);
        this.rvAktivitas.setAdapter(aktivitasAdapter);

        fetchTasks();
    }

    private void fetchTasks() {
        String url = "https://mgm.ub.ac.id/todo.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String what = jsonObject.getString("what");
                            String time = jsonObject.getString("time");

                            Aktivitas aktivitas = new Aktivitas(id, what, time);
                            toDo.add(aktivitas);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    aktivitasAdapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(MainActivity.this, "Error fetching tasks", Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}