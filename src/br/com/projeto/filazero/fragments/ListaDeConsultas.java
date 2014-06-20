package br.com.projeto.filazero.fragments;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.asyncTask.AtualizarInterface;
import br.com.projeto.filazero.asyncTask.AtualizarViewHome;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.utils.VerificarInternet;
import br.com.projeto.filazero.view.GUIHome;
import br.com.projeto.filazero.view.GUIInfoConsulta;
import br.com.projeto.filazero.view.controlview.ArrayAdapterConsulta;

public class ListaDeConsultas extends Fragment implements AtualizarInterface {
	private ArrayAdapterConsulta adapterConsultas;
	private View listaDeConsultas;
	private List<Consulta> consultas;
	private ListView consultasList;
	
	public ListaDeConsultas() {}
	
	public ListaDeConsultas(List<Consulta> consultas) {	
		this.consultas = consultas;	
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		listaDeConsultas = inflater.inflate(R.layout.activity_gui_home, null);
		iniciarComponentes();
		setHasOptionsMenu(true);
		return listaDeConsultas;
		}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.ic_reload:
	        	if(VerificarInternet.isOnline(getActivity())){
	        		carregarConsultas(item);
	        	}else Toast.makeText(getActivity(), "Verifique sua conexão", Toast.LENGTH_SHORT).show();
	    	    return true; 
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void carregarConsultas(MenuItem item){
		AtualizarViewHome atualizarViewHome = new AtualizarViewHome(this, item);
        atualizarViewHome.execute(VariaveisEstaticas.usuarioLogado.getCpf());
	}
	public void iniciarComponentes(){
		consultasList = (ListView) listaDeConsultas.findViewById(R.id.listarconsultas_dadosLinha);
		consultasList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Consulta consulta = (Consulta) consultasList.getItemAtPosition(position); 
				getTelaInfoconsulta(consulta);
			}
		} );
		setAdapter(consultas);
	}

	public void setAdapter(List<Consulta> consultas){
    	adapterConsultas = new ArrayAdapterConsulta(getActivity(),consultas) {};
    	consultasList.setAdapter(adapterConsultas);        	
    }
	
	private void getTelaInfoconsulta(Consulta consulta){
		Intent intent = new Intent(getActivity(), GUIInfoConsulta.class);                  
		intent.putExtra("Consulta", consulta);
		getActivity().startActivity(intent);
		getActivity().finish();
	}
	
	public void fragmentsListaDeConsultasVazia(){
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home, new SemConsultas()).commit();
	}

	@Override
	public void atualizarView(List<Consulta> consultas) {
		// TODO Auto-generated method stub
		adapterConsultas.addAndNotify(consultas);
		
	}

}
