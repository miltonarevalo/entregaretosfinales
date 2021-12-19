package com.example.retodos.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.retodos.R;
import com.example.retodos.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    Button botonGET;
    TextView mostrarJSON;

View v;
ImageView imagen1;

Drawable drawable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);

       /* drawable = getResources().getDrawable(R.drawable.camisalogo);
        imagen1 = (ImageView) v.findViewById(R.id.id_imagen_inicial);
        imagen1.setImageDrawable(drawable);*/

        botonGET = (Button) v.findViewById(R.id.botonGET);
        mostrarJSON = (TextView) v.findViewById(R.id.mostrarJSON);

        botonGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamadoGET();
            }
        });



        return v;
    }

    private void llamadoGET() {
        String url = "https://gf86292c611d686-db202112171841.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/producto/producto";


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        mostrarJSON.setText(jsonObject.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        }
}