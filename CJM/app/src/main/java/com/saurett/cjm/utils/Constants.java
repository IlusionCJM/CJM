package com.saurett.cjm.utils;

import android.support.v4.app.Fragment;

import com.saurett.cjm.R;
import com.saurett.cjm.fragments.ListadoAretesInternosFragment;
import com.saurett.cjm.fragments.ListadoCabezasFragment;
import com.saurett.cjm.fragments.RegistroCabezasFragment;
import com.saurett.cjm.fragments.RegistroaAretesInternosFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jvier on 10/08/2017.
 */

public class Constants {

    /**
     * Acciones generales
     **/
    public static final int ACCION_SIN_DEFINIR = 0;
    public static final int ACCION_REGISTRAR = 1;
    public static final int ACCION_EDITAR = 2;
    public static final int ACCION_VER = 3;

    /**
     * Key Preferences
     */
    public static final String KEY_PREF_CREDENCIALS = "key_pref_credencials";
    public static final String KEY_PREF_CREDENCIALS_USERNAME = "key_pref_credencials_username";
    public static final String KEY_PREF_CREDENCIALS_PASSWORD = "key_pref_credencials_password";
    public static final String KEY_PREF_CREDENCIALS_SESSION = "key_pref_credencials_session";

    /**
     * Key Extras
     **/
    public static final String KEY_MAIN_DECODE = "key_main_decode";
    public static final String KEY_SESSION_USER = "key_session_users";

    /**
     * Fragmentos principales de lista
     **/
    public static final String FRAGMENT_LISTADO_INICIO = "fragment_listado_inicio";
    public static final String FRAGMENT_LISTADO_ARETES_INTERNOS = "fragment_listado_aretes_internos";
    public static final String FRAGMENT_LISTADO_USUARIOS = "fragment_listado_usuarios";
    public static final String FRAGMENT_LISTADO_CABEZAS = "fragment_listado_cabezas";
    public static final String FRAGMENT_LISTADO_CHECKLIST = "fragment_listado_checklist";
    public static final String FRAGMENT_LISTADO_HERRADO = "fragment_listado_herrado";
    public static final String FRAGMENT_LISTADO_VACUNADO = "fragment_listado_vacunado";
    public static final String FRAGMENT_LISTADO_DESPARACITADO = "fragment_listado_desparacitado";

    /**
     * Fragmentos de registros
     **/
    public static final String FRAGMENT_ARETES_INTERNOS_REGISTER = "fragment_aretes_internos_register";
    public static final String FRAGMENT_USUARIOS_REGISTER = "fragment_usuarios_register";
    public static final String FRAGMENT_CABEZAS_REGISTER = "fragment_cabezas_register";

    /**
     * Formularios
     **/
    public static final String FORMULARIO_ARETES_INTERNOS = "fragment_formulario_aretes_internos";
    public static final String FORMULARIO_ARETES_INTERNOS_ASIGNACIONES = "fragment_formulario_aretes_internos_asignaciones";
    public static final String FORMULARIO_ARETES_INTERNOS_ASIGNACIONES_LISTADO = "fragment_formulario_aretes_internos_listado";
    public static final String FORMULARIO_ARETES_INTERNOS_ACCIONES = "fragment_formulario_aretes_internos_acciones";
    public static final String FORMULARIO_CABEZAS = "fragment_formulario_cabezas";
    public static final String FORMULARIO_CABEZAS_ACCIONES = "fragment_formulario_cabezas_acciones";

    /**
     * Fragmentos segundarios
     **/
    public static final String FRAGMENT_ARETES_INTERNOS = "fragment_aretes_internos";
    public static final String FRAGMENT_CABEZAS = "fragment_cabezas";

    public static final HashMap<Integer, String> ITEM_FRAGMENT;

    static {
        ITEM_FRAGMENT = new HashMap<>();
        ITEM_FRAGMENT.put(R.id.menu_item_inicio, FRAGMENT_LISTADO_INICIO);
        ITEM_FRAGMENT.put(R.id.menu_item_aretes_internos, FRAGMENT_LISTADO_ARETES_INTERNOS);
        ITEM_FRAGMENT.put(R.id.menu_item_cabezas, FRAGMENT_LISTADO_CABEZAS);
        ITEM_FRAGMENT.put(R.id.btn_registrar_arete_interno, FRAGMENT_ARETES_INTERNOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_arete_interno, FRAGMENT_ARETES_INTERNOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_cabeza, FRAGMENT_CABEZAS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_cabeza, FRAGMENT_CABEZAS_REGISTER);
    }

    /**
     * Colección de fragmentos secundarios
     **/
    public static final List<String> SECONDARY_TAG_FRAGMENTS;

    static {
        SECONDARY_TAG_FRAGMENTS = new ArrayList<>();
        SECONDARY_TAG_FRAGMENTS.add(FRAGMENT_ARETES_INTERNOS);
    }


    public static final HashMap<String, Fragment> TAG_FRAGMENT;

    static {
        TAG_FRAGMENT = new HashMap<>();
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_ARETES_INTERNOS, new ListadoAretesInternosFragment());
        TAG_FRAGMENT.put(FRAGMENT_ARETES_INTERNOS_REGISTER, new RegistroaAretesInternosFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_CABEZAS, new ListadoCabezasFragment());
        TAG_FRAGMENT.put(FRAGMENT_CABEZAS_REGISTER, new RegistroCabezasFragment());
    }

    public static final HashMap<Integer, Integer> TITLE_ACTIVITY;

    static {
        TITLE_ACTIVITY = new HashMap<>();
        TITLE_ACTIVITY.put(R.id.btn_registrar_arete_interno, R.string.default_activity_title_aretes_internos);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_arete_interno, R.string.default_activity_title_aretes_internos);
        TITLE_ACTIVITY.put(R.id.btn_registrar_cabeza, R.string.default_activity_title_cabezas);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_cabeza, R.string.default_activity_title_cabezas);
    }

    public static final HashMap<Integer, Integer> TITLE_FORM_ACTION;

    static {
        TITLE_FORM_ACTION = new HashMap<>();
        TITLE_FORM_ACTION.put(Constants.ACCION_REGISTRAR, R.string.default_form_title_new);
        TITLE_FORM_ACTION.put(Constants.ACCION_EDITAR, R.string.default_form_title_edit);
    }


    /**
     * Elementos de firebase
     **/
    public static final String FB_KEY_ITEM_ESTATUS_ACTIVO = "activo";
    public static final String FB_KEY_ITEM_ESTATUS_INACTIVO = "inactivo";
    public static final String FB_KEY_ITEM_ESTATUS_ELIMINADO = "eliminado";
    public static final String FB_KEY_MAIN_ARETES_INTERNOS = "aretesInternos";
    public static final String FB_KEY_MAIN_ITEM_ARETE_INTERNO = "areteInterno";
    public static final String FB_KEY_MAIN_ARETES_INTERNOS_HISTORIAL_CABEZAS = "historialCabezas";
    public static final String FB_KEY_MAIN_CABEZAS = "cabezas";
    public static final String FB_KEY_MAIN_ITEM_CABEZA = "cabeza";

    /**
     * Identificadoes del sistema
     */
    public static final int WS_KEY_ELIMINAR_USUARIOS = 1;
    public static final int WS_KEY_ELIMINAR_ARETES_INTERNOS = 2;
    public static final int WS_KEY_ELIMINAR_CABEZAS = 3;
}
