package com.example.marketapp.adapters;

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
         holder.ivImagem.setImageURI(Uri.parse(produto.getFotoURL()));
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImagem;
        TextView tvNome;
        TextView tvValor;
        Button btnAdd;
        CardView cvItem;
        public MinhaViewHolder(View itemView){
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeRV);
            ivImagem = itemView.findViewById(R.id.ivImagemRV);
            tvValor = itemView.findViewById(R.id.tvValorRV);
            btnAdd = itemView.findViewById(R.id.btnAddRV);
            cvItem = itemView.findViewById(R.id.cvItem);
        }
    }
}
