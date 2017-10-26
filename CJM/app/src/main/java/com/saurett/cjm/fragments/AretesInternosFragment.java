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
import com.saurett.cjm.adapters.AretesInternosAdapter;
import com.saurett.cjm.data.model.AretesInternos;
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

public class AretesInternosFragment extends Fragment implements View.OnClickListener {

    private static List<AretesInternos> aretesInternosList;
    private RecyclerView recyclerView;
    private AretesInternosAdapter aretesInternosAdapter;
    private static NavigationDrawerInterface navigationDrawerInterface;


    /**
     * Declaraciones para Firebase
     **/
    private FirebaseDatabase database;
    private DatabaseReference drAreteInterno;
    private ValueEventListener listenerAreteInterno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aretes_internos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_aretes_internos);
        aretesInternosAdapter = new AretesInternosAdapter();
        aretesInternosAdapter.setOnClickListener(this);

        /**Obtiene la instancia compartida del objeto FirebaseAuth**/
        database = FirebaseDatabase.getInstance();
        drAreteInterno = database.getReference(Constants.FB_KEY_MAIN_ARETES_INTERNOS);

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

        listenerAreteInterno = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                aretesInternosAdapter = new AretesInternosAdapter();
                aretesInternosList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AretesInternos areteInterno = postSnapshot.child(Constants.FB_KEY_MAIN_ITEM_ARETE_INTERNO).getValue(AretesInternos.class);
                    if (!areteInterno.getEstatus().equals(Constants.FB_KEY_ITEM_ESTATUS_ELIMINADO)) {
                        aretesInternosList.add(areteInterno);
                    }
                }

                onPreRender();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        drAreteInterno.addValueEventListener(listenerAreteInterno);
    }

    @Override
    public void onStop() {
        super.onStop();
        drAreteInterno.removeEventListener(listenerAreteInterno);
    }

    private void onPreRender() {
        Collections.sort(aretesInternosList, new Comparator<AretesInternos>() {
            @Override
            public int compare(AretesInternos o1, AretesInternos o2) {
                return (o1.getNumeroDeAreteInterno().compareTo(o2.getNumeroDeAreteInterno()));
            }
        });

        aretesInternosAdapter.addAll(aretesInternosList);
        recyclerView.setAdapter(aretesInternosAdapter);

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
            case R.id.item_btn_editar_arete_interno:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_arete_interno:
                navigationDrawerInterface.showQuestion();
                break;
        }
    }

}
