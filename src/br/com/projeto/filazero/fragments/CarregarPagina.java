package br.com.projeto.filazero.fragments;

import br.com.projeto.filazero.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CarregarPagina extends Fragment {
	private View carregando;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			carregando = inflater.inflate(R.layout.activity_carregar_pagina, null);
		    return carregando;
		}
}
