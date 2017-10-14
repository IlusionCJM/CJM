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
import com.saurett.cjm.fragments.AretesInternosFragment;
import com.saurett.cjm.helpers.DecodeItemHelper;
import com.saurett.cjm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class AretesInternosAdapter extends RecyclerView.Adapter<AretesInternosAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<AretesInternos> aretesInternoses = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtEstatus;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_numero_arete_interno);
            txtEstatus = (TextView) itemView.findViewById(R.id.item_estatus_arete_interno);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_arete_interno);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_arete_interno);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AretesInternos getItemByPosition(int position) {
        return aretesInternoses.get(position);
    }

    public void addAll(List<AretesInternos> _data) {
        this.aretesInternoses.addAll(_data);
    }

    public void remove(int position) {
        this.aretesInternoses.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aretes_internos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AretesInternos item = aretesInternoses.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getNumeroDeAreteInterno());
        holder.txtEstatus.setText(item.getEstatus());


        switch (item.getEstatus()) {
            case Constants.FB_KEY_ITEM_ESTATUS_INACTIVO:
                holder.txtEstatus.setText(item.getEstatus().toUpperCase());
                holder.txtEstatus.setTextColor(ContextCompat.getColor(holder.txtEstatus.getContext(), R.color.bootstrap_brand_danger));
                break;
            case Constants.FB_KEY_ITEM_ESTATUS_ACTIVO:
                holder.txtEstatus.setText(item.getEstatus().toUpperCase());
                holder.txtEstatus.setTextColor(ContextCompat.getColor(holder.txtEstatus.getContext(), R.color.bootstrap_brand_success));
                break;
        }

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                AretesInternosFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                AretesInternosFragment.onListenerAction(decodeItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return aretesInternoses == null ? 0 : aretesInternoses.size();
    }


}
