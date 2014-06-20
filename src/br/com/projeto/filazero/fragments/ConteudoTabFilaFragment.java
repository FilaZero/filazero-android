package br.com.projeto.filazero.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.utils.Cronometro;

public class ConteudoTabFilaFragment extends Fragment {
	private  View filaview;
	private TextView relogio;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    filaview = inflater.inflate(R.layout.info_fila_view_fragment, null);
	    iniciarChronometer();
	    return filaview;
	}
	
	public void iniciarChronometer(){
		relogio = (TextView) filaview.findViewById(R.id.cronometroTexto);
		Cronometro contagem = new Cronometro(0000, 0, 0, 2, 10, 10, Cronometro.REGRESSIVA,relogio);  
		contagem.cronometro(getActivity());  
	}
	
	public void setarPocicao(){
	}
	
}
