package com.saurett.cjm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saurett.cjm.R;
import com.saurett.cjm.fragments.interfaces.MainRegisterInterface;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.utils.Constants;


/**
 * Created by saurett on 24/02/2017.
 */

public class AsignacionesAretesInternosFragment extends Fragment {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextView txtListado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asignacion_aretes_internos, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        txtListado = (TextView) view.findViewById(R.id.txt_asignacion_estatus_arete_interno);

        this.onPreRender();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();

        mainFragment.replace(R.id.listado_integrantes_aretes_internos_container, new AsignacionAreteInternoFragment(), Constants.FORMULARIO_ARETES_INTERNOS_ASIGNACIONES_LISTADO);

        mainFragment.commit();
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
                break;
            default:
                break;
        }
    }

    public static void showMessageAsignacion(int visible, String message) {
        txtListado.setVisibility(visible);
        txtListado.setText(message);
    }
}
