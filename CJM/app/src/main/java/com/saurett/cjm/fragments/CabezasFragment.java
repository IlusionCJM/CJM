package com.saurett.cjm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurett.cjm.MainRegisterActivity;
import com.saurett.cjm.R;
import com.saurett.cjm.adapters.CabezasAdapter;
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.fragments.interfaces.NavigationDrawerInterface;
import com.saurett.cjm.helpers.DecodeItemHelper;
import com.saurett.cjm.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by jvier on 05/09/2017.
 */

public class CabezasFragment extends Fragment implements View.OnClickListener {

    private static List<Cabezas> cabezasList;
    private RecyclerView recyclerView;
    private CabezasAdapter cabezasAdapter;
    private static NavigationDrawerInterface navigationDrawerInterface;


    /**
     * Declaraciones para Firebase
     **/
    private FirebaseDatabase database;
    private DatabaseReference drCabezas;
    private ValueEventListener listenerCabeza;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cabezas, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_cabezas);
        cabezasAdapter = new CabezasAdapter();
        cabezasAdapter.setOnClickListener(this);

        /**Obtiene la instancia compartida del objeto FirebaseAuth**/
        database = FirebaseDatabase.getInstance();
        drCabezas = database.getReference(Constants.FB_KEY_MAIN_CABEZAS);

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
        this.listadoAretesInternos();
    }

    private void listadoAretesInternos() {

        listenerCabeza = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cabezasAdapter = new CabezasAdapter();
                cabezasList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Cabezas cabeza = postSnapshot.child(Constants.FB_KEY_MAIN_ITEM_CABEZA).getValue(Cabezas.class);
                    if (!cabeza.getEstatus().equals(Constants.FB_KEY_ITEM_ESTATUS_ELIMINADO)) {
                        cabezasList.add(cabeza);
                    }
                }

                onPreRender();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        drCabezas.addValueEventListener(listenerCabeza);
    }

    @Override
    public void onStop() {
        super.onStop();
        drCabezas.removeEventListener(listenerCabeza);
    }

    private void onPreRender() {
        Collections.sort(cabezasList, new Comparator<Cabezas>() {
            @Override
            public int compare(Cabezas o1, Cabezas o2) {
                return (o1.getNumeroDeAreteInterno().compareTo(o2.getNumeroDeAreteInterno()));
            }
        });

        cabezasAdapter.addAll(cabezasList);
        recyclerView.setAdapter(cabezasAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationDrawerInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {

    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        navigationDrawerInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_editar_cabeza:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_cabeza:
                navigationDrawerInterface.showQuestion();
                break;
        }
    }

}
