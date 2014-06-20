package br.com.projeto.filazero.utils;

import java.io.Serializable;
import java.util.List;

import br.com.projeto.filazero.model.Consulta;

public class ListadeConsultasAsyncTask implements Serializable {
	public List<Consulta> consultas;
	public static final String KEY = "consultas";
	
	public ListadeConsultasAsyncTask(List<Consulta> consultas){
		this.consultas = consultas;
	}
	
	public List<Consulta> getConsultas() {
		return consultas;
	}

}
