package com.saurett.cjm.fragments.interfaces;

import com.saurett.cjm.helpers.DecodeItemHelper;

/**
 * Created by jvier on 07/10/2017.
 */

public interface NavigationDrawerInterface {
    /**
     * Permite mostrar el dialogo de preguntas
     **/
    void showQuestion();
    /**Permite transferir los valores seleccionados en DecodeItem*/
    void setDecodeItem(DecodeItemHelper decodeItem);
    /**Permte abrir una actividad externa enviando parametros en el DecodeItem**/
    void openExternalActivity(int action, Class<?> externalActivity);

}
