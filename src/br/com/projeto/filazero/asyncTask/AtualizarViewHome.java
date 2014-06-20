package br.com.projeto.filazero.asyncTask;
import java.util.List;

import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;

public class AtualizarViewHome extends AsyncTask<String, String, List<Consulta>> {
	private AtualizarInterface at;
	private MenuItem menuItemTab;
	private Facade facade;
	
	public AtualizarViewHome(AtualizarInterface at,MenuItem menuItemTab){
		this.menuItemTab = menuItemTab;
		this.at = at;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		MenuItemCompat.setActionView(menuItemTab, R.layout.progress_bar_menu);
	}
	@Override
	protected List<Consulta> doInBackground(String... params) {
		facade = new Facade();
		List<Consulta> consultas = facade.getConsultasEmDia(params[0]);
		return consultas;
	}
	
	@Override
	protected void onPostExecute(List<Consulta> params) {
		MenuItemCompat.setActionView(menuItemTab, null);
		if(params.size()==0){
			at.fragmentsListaDeConsultasVazia();
		}else{
			at.atualizarView(params);
		}
	}
	
	
}
