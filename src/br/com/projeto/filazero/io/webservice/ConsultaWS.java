package br.com.projeto.filazero.io.webservice;



import java.util.LinkedList;
import java.util.List;

import android.provider.SyncStateContract.Constants;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.io.PersistenciaConsulta;
import br.com.projeto.filazero.model.Consulta;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ConsultaWS implements PersistenciaConsulta{
	
	private static final String URL_WS = VariaveisEstaticas.URL_WEBSERVICE+"consulta/";
	
	public ConsultaWS(){
		
	}

	@Override
	public String save(Consulta c)throws Exception {
		Gson gson = new Gson();
	    String consultaJSON = gson.toJson(c);
	    String[] resposta = new WebService().post(URL_WS + "inserir", consultaJSON);
	    if (resposta[0].equals("200")) {
	        return resposta[1];
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}

	@Override
	public List<Consulta> getConsultas(String cpf) throws Exception {
		String[] resposta = new WebService().get(URL_WS + cpf);
				
        if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        LinkedList<Consulta> listaConsultas = new LinkedList<Consulta>();
	        JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();        
	        for (int i = 0; i < array.size(); i++) {
	            listaConsultas.add(gson.fromJson(array.get(i), Consulta.class));
	        }
	        return listaConsultas;
	    } else {
	        throw new Exception(resposta[1]);
	    }
	
	}
	
	public Consulta getConsultaId(String idConsulta)throws Exception{
		
		String[] resposta = new WebService().get(URL_WS + "buscarConsultaId/" + idConsulta);

	    if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        Consulta cons = gson.fromJson(resposta[1], Consulta.class);
	        return cons;
	    } else {
	    	System.out.println("erro" +resposta[1]);
	        throw new Exception(resposta[1]);
	    }
	}
	
	public List<Consulta> getConsultasFila(String idEstab, String crmMed, String data) throws Exception{
		String argumentos = idEstab+"&"+crmMed+"&"+data;
		String[] resposta = new WebService().get(URL_WS +"buscarConsultasFila/" + argumentos );
		
        if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        LinkedList<Consulta> listaConsultas = new LinkedList<Consulta>();
	        JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();        
	        for (int i = 0; i < array.size(); i++) {
	            listaConsultas.add(gson.fromJson(array.get(i), Consulta.class));
	        }
	        return listaConsultas;
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}
	
	public String confimarConsulta(String idConsulta) throws Exception{
		String[] resposta = new WebService().get(URL_WS + "confirmar/"+idConsulta);
	    if (resposta[0].equals("200")) {
	        return resposta[1];
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}
	
	public String cancelarConsulta(String idConsulta) throws Exception{
		String[] resposta = new WebService().get(URL_WS + "cancelar/"+idConsulta);
	    if (resposta[0].equals("200")) {
	        return resposta[1];
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}
	
	public List<Consulta> getConsultasEmDia(String cpf) throws Exception {
		String[] resposta = new WebService().get(URL_WS +"consultasEmDia/"+ cpf);
				
        if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        LinkedList<Consulta> listaConsultas = new LinkedList<Consulta>();
	        JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();        
	        for (int i = 0; i < array.size(); i++) {
	            listaConsultas.add(gson.fromJson(array.get(i), Consulta.class));
	        }
	        return listaConsultas;
	    } else {
	        throw new Exception(resposta[1]);
	    }
	
	}
	

}
