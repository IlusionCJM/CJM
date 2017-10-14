package com.saurett.cjm.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.saurett.cjm.R;
import com.saurett.cjm.data.model.AretesInternos;
import com.saurett.cjm.data.model.Cabezas;
import com.saurett.cjm.fragments.AretesInternosFragment;
import com.saurett.cjm.fragments.CabezasFragment;
import com.saurett.cjm.helpers.DecodeItemHelper;
import com.saurett.cjm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class CabezasAdapter extends RecyclerView.Adapter<CabezasAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Cabezas> cabezasList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNumeroDeAreteInterno;
        TextView txtNombre;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNumeroDeAreteInterno = (TextView) itemView.findViewById(R.id.item_numero_arete_interno_cabeza);
            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_del_ganado);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_cabeza);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_cabeza);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Cabezas getItemByPosition(int position) {
        return cabezasList.get(position);
    }

    public void addAll(List<Cabezas> _data) {
        this.cabezasList.addAll(_data);
    }

    public void remove(int position) {
        this.cabezasList.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabezas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cabezas item = cabezasList.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNumeroDeAreteInterno.setText(item.getNumeroDeAreteInterno());
        holder.txtNombre.setText(item.getNombreDelGanado());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                CabezasFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                CabezasFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cabezasList == null ? 0 : cabezasList.size();
    }


}
