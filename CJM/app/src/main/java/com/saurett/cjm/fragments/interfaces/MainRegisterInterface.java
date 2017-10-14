package com.saurett.cjm.fragments.interfaces;

import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.data.model.Cabezas;

/**
 * Created by jvier on 07/10/2017.
 */

public interface MainRegisterInterface {
    /**Permite registrar aretes internos*/
    void registrarAretesInternos(AretesInternos aretesInternos);
    /**Permite editar aretes internos*/
    void editarAretesInternos(AretesInternos aretesInternos);
    /**Permite registrar cabezas*/
    void registrarCabezas(Cabezas cabezas);
    /**Permite editar cabezas*/
    void editarCabezas(Cabezas aretesInternos);
}
