package br.com.projeto.filazero;

import java.util.List;

import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Consulta;

public class VariaveisEstaticas {
	
	public static String registroGCM;
	public static Cliente usuarioLogado;
	public static List<Consulta> consultasCache;
	public static boolean verificadoGCM;
	public static final String URL_WEBSERVICE = "http://10.0.2.2:8080/WebServiceREST/android/";
	
	public VariaveisEstaticas(){
		
	}

}
