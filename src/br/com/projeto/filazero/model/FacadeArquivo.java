package br.com.projeto.filazero.model;

import java.util.List;

import android.content.Context;
import br.com.projeto.filazero.io.arquivo.PersistenciaConsultasArquivo;

public class FacadeArquivo {
	private PersistenciaConsultasArquivo persistenciaConsulta;
	
	public FacadeArquivo(Context c){
		persistenciaConsulta = new PersistenciaConsultasArquivo(c);
	}
	
	public void salvarCacheConsultas(List<Consulta> consultas){
		persistenciaConsulta.salvarCache(consultas);
	}
	
	public List<Consulta> getCacheConsultas(){
		return persistenciaConsulta.getCache();
	}
	
	public void limparCacheConsultas(){
		persistenciaConsulta.limparCache();
	}
	
}
