package br.com.projeto.filazero.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Consulta;

public class TabInfoConsulta extends Fragment implements OnClickListener {
	private static int iconeSelecionado = 1;
	private View infoView; 
	private ImageView tabInfo, tabFila,tabRota,tabChamada;
	private String telefoneLigar;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		infoView = inflater.inflate(R.layout.tab_info_fragment, null);
		iniciarComponentes();
		iconeSelecionado();
		return infoView;
		
	}
	
	public void setConsulta(Consulta consulta){
		telefoneLigar = consulta.getEstabelecimento().getTelefone().replace("-", "").substring(4,consulta.getEstabelecimento().getTelefone().length()-1);
	}
	
	private void iniciarComponentes(){
		tabInfo = (ImageView) infoView.findViewById(R.id.tab_info);
		tabInfo.setOnClickListener(this);
		tabFila = (ImageView) infoView.findViewById(R.id.tab_fila);
		tabFila.setOnClickListener(this);
		tabRota = (ImageView) infoView.findViewById(R.id.tab_rota);
		tabRota.setOnClickListener(this);
		tabChamada = (ImageView) infoView.findViewById(R.id.tab_chamada);
		tabChamada.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		   case R.id.tab_info:{ 
			   iconeSelecionado = 1;
			   iconeSelecionado();
			   FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConteudoTabInfo());
			   transaction.commit();
			   break;
		   }case R.id.tab_fila:{
			   iconeSelecionado = 2;
			   iconeSelecionado();
			   FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConteudoTabFilaFragment());
			   transaction.commit();
			   break;
		   }
		   case R.id.tab_rota:{
			   iconeSelecionado = 3;
			   iconeSelecionado();
			   break;
		   }
		   case R.id.tab_chamada:{
			   chamada();
			   break;
		   }
		}   
		
	}
	public void chamada() {
		try {
			Intent dial = new Intent();
			dial.setAction("android.intent.action.DIAL");
			dial.setData(Uri.parse("tel:"+telefoneLigar));
			startActivity(dial);
	    } catch (ActivityNotFoundException activityException) {
	    	Toast.makeText(getActivity(), "Call failed", Toast.LENGTH_SHORT).show();
	         
	    }
	}
	
	public void iconeSelecionado(){
		switch(iconeSelecionado){
			case 1:{
				tabInfo.setBackgroundResource(R.drawable.ic_tab_info_selecionado);
				tabFila.setBackgroundResource(R.drawable.ic_tab_fila);
				tabRota.setBackgroundResource(R.drawable.ic_tab_rota);
				tabChamada.setBackgroundResource(R.drawable.ic_tab_chamada);
				break;
			}case 2:{
				tabInfo.setBackgroundResource(R.drawable.ic_tab_info);
				tabFila.setBackgroundResource(R.drawable.ic_tab_fila_selecionado);
				tabRota.setBackgroundResource(R.drawable.ic_tab_rota);
				tabChamada.setBackgroundResource(R.drawable.ic_tab_chamada);
				break;
			}case 3:{
				tabInfo.setBackgroundResource(R.drawable.ic_tab_info);
				tabFila.setBackgroundResource(R.drawable.ic_tab_fila);
				tabRota.setBackgroundResource(R.drawable.ic_tab_rota_selecionado);
				tabChamada.setBackgroundResource(R.drawable.ic_tab_chamada);
				break;
			}
		
		}
	}
}
