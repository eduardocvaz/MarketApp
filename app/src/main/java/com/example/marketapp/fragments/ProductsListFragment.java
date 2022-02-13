package com.example.marketapp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marketapp.R;
import com.example.marketapp.adapters.AdapterProduto;
import com.example.marketapp.model.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ProductsListFragment extends Fragment {

    public static final String PRODUTOS = "produtos";

    RecyclerView rvProdutos;

    List<Produto> mProdutos = new ArrayList<Produto>();

    AdapterProduto adapterProduto;

    public static ProductsListFragment newInstance(ArrayList<Produto> produtos) {
        ProductsListFragment productsListFragment = new ProductsListFragment();
        Bundle args =new Bundle();
        args.putInt("TamanhoLista",produtos.size());

        for(int i=0; i< produtos.size(); i++){
            args.putSerializable(String.valueOf(i),produtos.get(i));
        }
        productsListFragment.setArguments(args);
        return productsListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            int tam = getArguments().getInt("TamanhoLista");
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            for(int i=0; i<tam;i++) {
                Produto produto = (Produto) getArguments().getSerializable(String.valueOf(i));
                produtos.add(produto);
            }
            mProdutos = produtos;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_products_list, container, false);
        rvProdutos = layout.findViewById(R.id.rv_Lista);
        adapterProduto = new AdapterProduto(mProdutos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvProdutos.setLayoutManager(layoutManager);
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setAdapter(adapterProduto);

        adapterProduto.implementaAoClicarNoBotao(new AdapterProduto.AoClicarNoBotao() {
            @Override
            public void cliclouNoElemento(int position) {
                Activity activity = getActivity();

                if(activity instanceof AoClicarNoProduto){
                    AoClicarNoProduto listener = (AoClicarNoProduto) activity;
                    listener.clicouNoProduto(mProdutos.get(position));
                }
            }
        });

        return layout;
    }
    public interface AoClicarNoProduto{
        public void clicouNoProduto(Produto produto);
    }

}