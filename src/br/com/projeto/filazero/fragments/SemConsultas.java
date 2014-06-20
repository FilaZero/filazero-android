package br.com.projeto.filazero.fragments;

import java.util.List;

import br.com.projeto.filazero.R;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.asyncTask.AtualizarInterface;
import br.com.projeto.filazero.asyncTask.AtualizarViewHome;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.utils.VerificarInternet;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SemConsultas extends Fragment implements AtualizarInterface {
	private View semConsultas;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			semConsultas = inflater.inflate(R.layout.activity_gui_sem_consultas, null);
			setHasOptionsMenu(true);
			return semConsultas;
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

	@Override
	public void atualizarView(List<Consulta> consultas) {
		FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home, new ListaDeConsultas(consultas));
		transaction.commit();
	}

	@Override
	public void fragmentsListaDeConsultasVazia() {
		// TODO Auto-generated method stub
		
	}
}
