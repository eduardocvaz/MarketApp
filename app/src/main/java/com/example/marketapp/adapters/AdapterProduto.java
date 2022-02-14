package com.example.marketapp.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.marketapp.R;
import com.example.marketapp.model.Produto;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MinhaViewHolder> {

    private List<Produto> listaProdutos;

    public AdapterProduto(List<Produto> lista){
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elementoLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista,parent,false);

        return new MinhaViewHolder(elementoLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {
         Produto produto = listaProdutos.get(position);

         holder.tvNome.setText(produto.getNome());
         holder.tvValor.setText(String.valueOf(produto.getValor()));

         Picasso.get().load(produto.getFotoURL()).into(holder.ivImagem);
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImagem;
        TextView tvNome;
        TextView tvValor;
        CardView cvItem;
        public MinhaViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeRV);
            ivImagem = itemView.findViewById(R.id.ivImagemRV);
            tvValor = itemView.findViewById(R.id.tvValorRV);
            cvItem = itemView.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.cliclouNoElemento(getLayoutPosition());
        }
    }
    public interface AoClicarNoBotao{
        void cliclouNoElemento(int position);
    }
    private AoClicarNoBotao listener;
    public void implementaAoClicarNoBotao(AoClicarNoBotao listener){
        this.listener = listener;
    }
}
