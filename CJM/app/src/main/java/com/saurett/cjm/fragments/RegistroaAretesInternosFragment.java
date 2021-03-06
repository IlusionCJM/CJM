package com.saurett.cjm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.saurett.cjm.R;
import com.saurett.cjm.fragments.interfaces.MainRegisterInterface;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.utils.Constants;


/**
 * Created by saurett on 24/02/2017.
 */

public class RegistroaAretesInternosFragment extends Fragment {

    private MainRegisterInterface activityInterface;
    private FrameLayout frameLayoutAsignaciones;

    private static DecodeExtraHelper _MAIN_DECODE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_aretes_internos, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        frameLayoutAsignaciones = (FrameLayout) view.findViewById(R.id.fragment_asignaciones_arete_interno_container);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();

        mainFragment.replace(R.id.fragment_registro_arete_interno_container, new FormularioAretesInternosFragment(), Constants.FORMULARIO_ARETES_INTERNOS);
        mainFragment.replace(R.id.fragment_asignaciones_arete_interno_container, new AsignacionesAretesInternosFragment(), Constants.FORMULARIO_ARETES_INTERNOS_ASIGNACIONES);
        mainFragment.replace(R.id.fragment_acciones_arete_interno_container, new AccionesAretesInternosFragment(), Constants.FORMULARIO_ARETES_INTERNOS_ACCIONES);

        mainFragment.commit();

        this.onPreRender();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (MainRegisterInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    public void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                break;
            case Constants.ACCION_REGISTRAR:
                frameLayoutAsignaciones.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
