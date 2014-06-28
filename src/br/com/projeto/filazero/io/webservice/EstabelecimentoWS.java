package br.com.projeto.filazero.io.webservice;

import java.util.LinkedList;
import java.util.List;

import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.io.PersistenciaEstabelecimento;
import br.com.projeto.filazero.model.Estabelecimento;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class EstabelecimentoWS implements PersistenciaEstabelecimento{
	
	private static final String URL_WS = VariaveisEstaticas.URL_WEBSERVICE+"estabelecimento/";
	
	@Override
	public Estabelecimento getEstabelecimento(String cnes)throws Exception {
		String[] resposta = new WebService().get(URL_WS + cnes);
		if (resposta[0].equals("200")) {
	         Gson gson = new Gson();
	         Estabelecimento estb = gson.fromJson(resposta[1], Estabelecimento.class);
	         return estb;
	     } else {
	         throw new Exception(resposta[1]);
	     }
	}

	@Override
	public List<Estabelecimento> getEstabelecimentosCidade(String cidade) throws Exception {
		cidade = cidade.replace(" ","_");
		String url = URL_WS+"cidade="+cidade;
		String[] resposta = new WebService().get(url);
		if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        LinkedList<Estabelecimento> listaEstabs = new LinkedList<Estabelecimento>();
	        JsonParser parser = new JsonParser();
	        JsonArray array = parser.parse(resposta[1]).getAsJsonArray();        
	        for (int i = 0; i < array.size(); i++) {
	            listaEstabs.add(gson.fromJson(array.get(i), Estabelecimento.class));
	        }
	        return listaEstabs;
	    } else {
	        throw new Exception(resposta[1]);
	    }
	    
	    
	}

}
