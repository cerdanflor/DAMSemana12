package com.dam.prueba.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.prueba.NuevaNotaDialogViewModel;
import com.dam.prueba.db.entity.NotaEntity;
import com.dam.prueba.R;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context ctx;
    private NuevaNotaDialogViewModel viewModel;


    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitulo());
        holder.tvContenido.setText(holder.mItem.getContenido());
        if(holder.mItem.isFavorita()){
            holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_24);
        }

       holder.ivFavorita.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(holder.mItem.isFavorita()){
                   holder.mItem.setFavorita(false);
                   holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_border_24);
               }else{
                   holder.mItem.setFavorita(true);
                   holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_24);
               }
               viewModel.updateNota(holder.mItem);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    // Creamos un método, para actualizar el adapter
    public void setNuevasNotas(List<NotaEntity> nuevasNotas){
        this.mValues = nuevasNotas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView ivFavorita;
        public NotaEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitulo = view.findViewById(R.id.textViewTitulo);
            tvContenido = view.findViewById(R.id.textViewContenido);
            ivFavorita = view.findViewById(R.id.imageViewFavorita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}