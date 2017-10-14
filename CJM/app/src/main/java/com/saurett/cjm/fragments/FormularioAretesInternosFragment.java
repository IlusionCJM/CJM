package com.saurett.cjm.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurett.cjm.R;
import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.utils.Constants;
import com.saurett.cjm.utils.ValidationUtils;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioAretesInternosFragment extends Fragment {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static TextInputLayout tilNumeroAreteInterno;
    public static AretesInternos _aretesInternosActual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aretes_internos_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNumeroAreteInterno = (TextInputLayout) view.findViewById(R.id.numero_arete_interno);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.onPreRender();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.onPrerenderEditar();
                break;
            case Constants.ACCION_REGISTRAR:
                _aretesInternosActual = new AretesInternos();
                break;
            default:
                break;
        }
    }

    private void onPrerenderEditar() {
        _aretesInternosActual = (AretesInternos) _MAIN_DECODE.getDecodeItem().getItemModel();
        tilNumeroAreteInterno.getEditText().setText(_aretesInternosActual.getNumeroDeAreteInterno());
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;
        String numero = tilNumeroAreteInterno.getEditText().getText().toString();

        boolean a = ValidationUtils.esNumeroValido(tilNumeroAreteInterno, numero);

        if (a) {
            AretesInternos data = new AretesInternos();
            data.setNumeroDeAreteInterno(numero);
            data.setEstatus(Constants.FB_KEY_ITEM_ESTATUS_INACTIVO);
            setAretesInternos(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;
        String numero = tilNumeroAreteInterno.getEditText().getText().toString();

        boolean a = ValidationUtils.esNumeroValido(tilNumeroAreteInterno, numero);

        if (a) {
            AretesInternos data = new AretesInternos();
            data.setNumeroDeAreteInterno(numero);
            data.setEstatus(_aretesInternosActual.getEstatus());
            setAretesInternos(data);
            valido = true;
        }

        return valido;
    }

    public static AretesInternos setAretesInternos(AretesInternos data) {
        _aretesInternosActual.setNumeroDeAreteInterno(data.getNumeroDeAreteInterno());
        _aretesInternosActual.setEstatus(data.getEstatus());
        return _aretesInternosActual;
    }
}
