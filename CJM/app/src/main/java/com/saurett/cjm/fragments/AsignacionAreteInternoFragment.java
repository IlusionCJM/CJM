package com.saurett.cjm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurett.cjm.MainRegisterActivity;
import com.saurett.cjm.R;
import com.saurett.cjm.adapters.AsignacionesAretesAdapter;
import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.fragments.interfaces.MainRegisterInterface;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.helpers.DecodeItemHelper;
import com.saurett.cjm.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by saurett on 24/02/2017.
 */

public class AsignacionAreteInternoFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static List<Cabezas> cabezasList;
    private static RecyclerView recyclerView;
    public static AsignacionesAretesAdapter asignacionesAdapter;
    private static MainRegisterInterface activityInterface;

    /**
     * Declaraciones para Firebase
     **/
    private ValueEventListener listenerCabeza;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_asignacion_arete_interno, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_asignaciones_aretes_internos);
        asignacionesAdapter = new AsignacionesAretesAdapter();
        asignacionesAdapter.setOnClickListener(this);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.onPreRender();
    }

    private void onPreRender() {

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.listadoCabezas();
                break;
            default:
                break;
        }
    }

    private void listadoCabezas() {

        final AretesInternos areteInterno = (AretesInternos) _MAIN_DECODE.getDecodeItem().getItemModel();

        DatabaseReference dbCabeza = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FB_KEY_MAIN_ARETES_INTERNOS)
                .child(areteInterno.getFirebaseId())
                .child(Constants.FB_KEY_MAIN_ARETES_INTERNOS_HISTORIAL_CABEZAS);

        listenerCabeza = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                asignacionesAdapter = new AsignacionesAretesAdapter();
                cabezasList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Cabezas cabeza = postSnapshot.child(Constants.FB_KEY_MAIN_ITEM_CABEZA).getValue(Cabezas.class);
                    cabezasList.add(cabeza);
                    
                }

                onPreRenderListadoIntegrantes();

                if (cabezasList.size() > 0) {
                    AsignacionesAretesInternosFragment.showMessageAsignacion(View.GONE, "");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        dbCabeza.addValueEventListener(listenerCabeza);
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    public static void onPreRenderListadoIntegrantes() {

        Collections.sort(cabezasList, new Comparator<Cabezas>() {
            @Override
            public int compare(Cabezas o1, Cabezas o2) {
                return (o1.getNombreDelGanado().compareTo(o2.getNombreDelGanado()));
            }
        });

        asignacionesAdapter.addAll(cabezasList);
        recyclerView.setAdapter(asignacionesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (MainRegisterActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        //activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_editar_asignacion_arete:
                //activityInterface.showQuestion("Autorizar", "¿Esta seguro que desea hacer responsable?");
                break;
            case R.id.item_btn_eliminar_asignacion_arete:
                //activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea elminar?");
                break;
        }
    }
}
