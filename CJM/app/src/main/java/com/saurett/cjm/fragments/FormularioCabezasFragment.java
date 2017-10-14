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
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.helpers.DecodeExtraHelper;
import com.saurett.cjm.utils.Constants;
import com.saurett.cjm.utils.ValidationUtils;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioCabezasFragment extends Fragment {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static TextInputLayout tilNombreGanado, tilRaza, tilEdad, tilCosto, tilTipoGanado, tilColor;
    private static TextInputLayout tilGenero, tilPesoInicial, tilNumAreteInterno, tilNumAreteSiniga, tilPesoTerminal;
    private static TextInputLayout tilNumTotalPartos, tilNumTotalPartosInternos, tilfechaUltimoParto;
    public static Cabezas _cabezaActual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cabezas_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombreGanado = (TextInputLayout) view.findViewById(R.id.nombre_del_ganado);
        tilRaza = (TextInputLayout) view.findViewById(R.id.raza);
        tilEdad = (TextInputLayout) view.findViewById(R.id.edad);
        tilCosto = (TextInputLayout) view.findViewById(R.id.costo);
        tilTipoGanado = (TextInputLayout) view.findViewById(R.id.tipo_ganado);
        tilColor = (TextInputLayout) view.findViewById(R.id.color);
        tilGenero = (TextInputLayout) view.findViewById(R.id.genero);
        tilPesoInicial = (TextInputLayout) view.findViewById(R.id.peso_inicial);
        tilNumAreteInterno = (TextInputLayout) view.findViewById(R.id.numero_arete_interno_cabeza);
        tilNumAreteSiniga = (TextInputLayout) view.findViewById(R.id.numero_arete_siniga);
        tilPesoTerminal = (TextInputLayout) view.findViewById(R.id.peso_terminal);
        tilNumTotalPartos = (TextInputLayout) view.findViewById(R.id.numero_total_partos);
        tilNumTotalPartosInternos = (TextInputLayout) view.findViewById(R.id.numero_total_partos_interno);
        tilfechaUltimoParto = (TextInputLayout) view.findViewById(R.id.fecha_ultimo_parto);

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
                _cabezaActual = new Cabezas();
                break;
            default:
                break;
        }
    }

    private void onPrerenderEditar() {
        _cabezaActual = (Cabezas) _MAIN_DECODE.getDecodeItem().getItemModel();

        tilNombreGanado.getEditText().setText(_cabezaActual.getNombreDelGanado());
        tilRaza.getEditText().setText(_cabezaActual.getRaza());
        tilEdad.getEditText().setText(_cabezaActual.getEdadD());
        tilCosto.getEditText().setText(_cabezaActual.getCosto());
        tilTipoGanado.getEditText().setText(_cabezaActual.getTipoDeGanado());
        tilColor.getEditText().setText(_cabezaActual.getColorDeGanado());
        tilGenero.getEditText().setText(_cabezaActual.getGenero());
        tilPesoInicial.getEditText().setText(_cabezaActual.getPesoInicial());
        tilNumAreteInterno.getEditText().setText(_cabezaActual.getNumeroDeAreteInterno());
        tilNumAreteSiniga.getEditText().setText(_cabezaActual.getNumeroDeAreteSiniga());
        tilPesoTerminal.getEditText().setText(_cabezaActual.getPesoTerminal());
        tilNumTotalPartos.getEditText().setText(_cabezaActual.getNumeroTotalDePartos());
        tilNumTotalPartosInternos.getEditText().setText(_cabezaActual.getNumeroDePartosInterno());
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;
        String nombre = tilNombreGanado.getEditText().getText().toString();
        String raza = tilRaza.getEditText().getText().toString();
        String edad = tilEdad.getEditText().getText().toString();
        String costo = tilCosto.getEditText().getText().toString();
        String tipoGanado = tilTipoGanado.getEditText().getText().toString();
        String color = tilColor.getEditText().getText().toString();
        String genero = tilGenero.getEditText().getText().toString();
        String pesoInicial = tilPesoInicial.getEditText().getText().toString();
        String numAreteInterno = tilNumAreteInterno.getEditText().getText().toString();
        String numAreteSiniga = tilNumAreteSiniga.getEditText().getText().toString();
        String peroTerminal = tilPesoTerminal.getEditText().getText().toString();
        String numTotalPartos = tilNumTotalPartos.getEditText().getText().toString();
        String numPartosInternos = tilNumTotalPartosInternos.getEditText().getText().toString();
        String fechaUltimoParto = tilfechaUltimoParto.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombreGanado, nombre);

        if (a) {
            Cabezas data = new Cabezas();
            data.setNombreDelGanado(nombre);
            data.setRaza(raza);
            data.setEdadD(edad);
            data.setCosto(costo);
            data.setTipoDeGanado(tipoGanado);
            data.setColorDeGanado(color);
            data.setGenero(genero);
            data.setPesoInicial(pesoInicial);
            data.setNumeroDeAreteInterno(numAreteInterno);
            data.setNumeroDeAreteSiniga(numAreteSiniga);
            data.setPesoTerminal(peroTerminal);
            data.setNumeroTotalDePartos(numTotalPartos);
            data.setNumeroDePartosInterno(numPartosInternos);
            data.setFechaDelUltimoParto(fechaUltimoParto);

            data.setEstatus(Constants.FB_KEY_ITEM_ESTATUS_ACTIVO);
            setCabezas(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String nombre = tilNombreGanado.getEditText().getText().toString();
        String raza = tilRaza.getEditText().getText().toString();
        String edad = tilEdad.getEditText().getText().toString();
        String costo = tilCosto.getEditText().getText().toString();
        String tipoGanado = tilTipoGanado.getEditText().getText().toString();
        String color = tilColor.getEditText().getText().toString();
        String genero = tilGenero.getEditText().getText().toString();
        String pesoInicial = tilPesoInicial.getEditText().getText().toString();
        String numAreteInterno = tilNumAreteInterno.getEditText().getText().toString();
        String numAreteSiniga = tilNumAreteSiniga.getEditText().getText().toString();
        String peroTerminal = tilPesoTerminal.getEditText().getText().toString();
        String numTotalPartos = tilNumTotalPartos.getEditText().getText().toString();
        String numPartosInternos = tilNumTotalPartosInternos.getEditText().getText().toString();
        String fechaUltimoParto = tilfechaUltimoParto.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombreGanado, nombre);

        if (a) {
            Cabezas data = new Cabezas();

            data.setNombreDelGanado(nombre);
            data.setRaza(raza);
            data.setEdadD(edad);
            data.setCosto(costo);
            data.setTipoDeGanado(tipoGanado);
            data.setColorDeGanado(color);
            data.setGenero(genero);
            data.setPesoInicial(pesoInicial);
            data.setNumeroDeAreteInterno(numAreteInterno);
            data.setNumeroDeAreteSiniga(numAreteSiniga);
            data.setPesoTerminal(peroTerminal);
            data.setNumeroTotalDePartos(numTotalPartos);
            data.setNumeroDePartosInterno(numPartosInternos);
            data.setFechaDelUltimoParto(fechaUltimoParto);

            data.setEstatus(_cabezaActual.getEstatus());
            setCabezas(data);
            valido = true;
        }

        return valido;
    }

    public static Cabezas setCabezas(Cabezas data) {
        _cabezaActual.setNombreDelGanado(data.getNombreDelGanado());
        _cabezaActual.setRaza(data.getRaza());
        _cabezaActual.setEdadD(data.getEdadD());
        _cabezaActual.setCosto(data.getCosto());
        _cabezaActual.setTipoDeGanado(data.getTipoDeGanado());
        _cabezaActual.setColorDeGanado(data.getColorDeGanado());
        _cabezaActual.setGenero(data.getGenero());
        _cabezaActual.setPesoInicial(data.getPesoInicial());
        _cabezaActual.setNumeroDeAreteInterno(data.getNumeroDeAreteInterno());
        _cabezaActual.setNumeroDeAreteSiniga(data.getNumeroDeAreteSiniga());
        _cabezaActual.setPesoTerminal(data.getPesoTerminal());
        _cabezaActual.setNumeroTotalDePartos(data.getNumeroTotalDePartos());
        _cabezaActual.setNumeroDePartosInterno(data.getNumeroDePartosInterno());
        _cabezaActual.setFechaDelUltimoParto(data.getFechaDelUltimoParto());

        _cabezaActual.setEstatus(data.getEstatus());
        return _cabezaActual;
    }
}
