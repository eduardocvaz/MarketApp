package com.example.marketapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marketapp.R;
import com.example.marketapp.adapters.AdapterProduto;
import com.example.marketapp.model.Produto;

import java.util.ArrayList;
import java.util.List;


public class ProductsListFragment extends Fragment {

    RecyclerView rvProdutos;

    List<Produto> mProdutos = new ArrayList<Produto>();

    AdapterProduto adapterProduto;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_products_list, container, false);
        rvProdutos = layout.findViewById(R.id.rv_Lista);
        mProdutos = carregaProdutos();
        adapterProduto = new AdapterProduto(mProdutos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvProdutos.setLayoutManager(layoutManager);
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setAdapter(adapterProduto);
        return layout;
    }
    private List<Produto> carregaProdutos(){
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(Long.valueOf(1),"https://cdn.awsli.com.br/600x700/469/469651/produto/32288602/c2540b39b3.jpg","Copo","Copo Comum",10.00));
        produtos.add(new Produto(Long.valueOf(2),"https://m.media-amazon.com/images/I/516P3MaLPfL._AC_SY355_.jpg","Bacia","Bacia Comum",30.00));
        produtos.add(new Produto(Long.valueOf(3),"https://hiperideal.vteximg.com.br/arquivos/ids/167660-1000-1000/27502.jpg?v=636615816147030000","Batata","Batata Comum",0.50));

        return produtos;
    }
}