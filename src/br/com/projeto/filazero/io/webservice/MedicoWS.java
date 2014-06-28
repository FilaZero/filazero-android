package br.com.projeto.filazero.io.webservice;

import java.util.LinkedList;
import java.util.List;

import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.io.PersistenciaMedico;
import br.com.projeto.filazero.model.Medico;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MedicoWS implements PersistenciaMedico {
	private static final String URL_WS = VariaveisEstaticas.URL_WEBSERVICE+"medico/";
	
	@Override
	public List<Medico> getMedicos(String idEstab)throws Exception  {
		
		String url = URL_WS+"idEstab"+idEstab;
		String[] resposta = new WebService().get(url);
		if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        LinkedList<Medico> listaMedicos = new LinkedList<Medico>();
	        JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();        
	        for (int i = 0; i < array.size(); i++) {
	            listaMedicos.add(gson.fromJson(array.get(i), Medico.class));
	        }
	        return listaMedicos;
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}

	@Override
	public Medico getMedico(String crm)throws Exception  {
		String[] resposta = new WebService().get(URL_WS + crm);

	     if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         Medico med = gson.fromJson(resposta[1], Medico.class);
	         return med;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	}

}
