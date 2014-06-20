package br.com.projeto.filazero.asyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.fragments.CarregarPagina;
import br.com.projeto.filazero.fragments.SemConsultas;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.view.GUIHome;

public class CarregarViewPagina extends AsyncTask<String, String, List<Consulta>> {
	private GUIHome ci;
	private Facade facade;
	
	public CarregarViewPagina(GUIHome ci){
		this.ci= ci;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		ci.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_home,new CarregarPagina()).commit();
	}
	@Override
	protected List<Consulta> doInBackground(String... params) {
		facade = new Facade();
		List<Consulta> consultas = facade.getConsultasEmDia(params[0]);
		return consultas;
	}
	
	@Override
	protected void onPostExecute(List<Consulta> params) {
		if(params.size()==0){
			ci.fragmentsListaDeConsultasVazia();
		}else{
			ci.fragmentsListaDeConsultas(params);
			VariaveisEstaticas.consultasCache = params;
		}
	}
	
}
