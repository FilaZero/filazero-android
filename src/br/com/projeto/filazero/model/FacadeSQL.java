package br.com.projeto.filazero.model;

import android.content.Context;
import android.util.Log;
import br.com.projeto.filazero.io.sqlite.PersistenciaClienteSQL;

public class FacadeSQL {

	private Context context;
	private PersistenciaClienteSQL persistenciaCliente;
	
	
	public FacadeSQL(Context c){
		context = c;
		persistenciaCliente = new PersistenciaClienteSQL(c);
	}
	
	public void manterClienteLogado(Cliente c){
		long sucesso = persistenciaCliente.salve(c);
		if(sucesso>0){
			Log.i("SQL:", "Cliente mantido");
		}
	}
	
	public Cliente getClienteLogado(){
		return persistenciaCliente.getUsuarioLogado();
	}
	
	public void logoutCliente(Cliente c){
		long sucesso = persistenciaCliente.logoutCliente(c);
		if(sucesso>0){
			Log.i("SQL:", "Logout com sucesso");
		}
	}
	
}
