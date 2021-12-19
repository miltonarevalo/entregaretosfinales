package com.example.retodos.ui.sucursales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.retodos.BuildConfig;
import com.example.retodos.ForrmActivity;
import com.example.retodos.R;

import org.jetbrains.annotations.Nullable;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


public class SucursalesFragment extends Fragment {


  private View v;
    ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();
    private MapView myOpenMapView;
    private MapController myMapController;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    v = inflater.inflate(R.layout.fragment_sucursales, container, false);

        /* ---- necesitamos establecer el valor del agente de usuario en la configuración ------- */
        //pre 5.6
        //OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        //5.6 and newer
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        /* -------------------------------------------------------------------------------------- */

        /*   punto de geolocalizacion de ejemplo */
        GeoPoint Bogota = new GeoPoint(4.6831206, -74.1187454);
        GeoPoint Bogota1 = new GeoPoint(4.6433057, -74.1241177);
        GeoPoint Bogota2 = new GeoPoint(4.652683, -74.1094328);

        //----------------------------------------------------------------------------------

        myOpenMapView = (MapView) v.findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);

        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(Bogota);
        myMapController.setZoom(15);

        myOpenMapView.setMultiTouchControls(true);
        puntos.add(new OverlayItem("Bogota", "Ciudad de Bogota", Bogota));
        puntos.add(new OverlayItem("Bogota", "Ciudad de Bogota", Bogota1));
        puntos.add(new OverlayItem("Bogota", "Salitre Plaza", Bogota2));
        refrescaPuntos();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /* ---- necesitamos establecer el valor del agente de usuario en la configuración ------- */
        //pre 5.6
        //OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        //5.6 and newer
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        /* -------------------------------------------------------------------------------------- */
    }

    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            return false;
        }
        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            return true;
        }
    };
    private void refrescaPuntos() {
        myOpenMapView.getOverlays().clear();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }
        };

        ItemizedOverlayWithFocus capa = new ItemizedOverlayWithFocus<>(getContext(), puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getContext(), ForrmActivity.class);
                intent.putExtra("name", "SUCURSALES");
                //getActivity().startActivity(intent);
                //Toast.makeText(getContext(), "Hola Sucursales", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
