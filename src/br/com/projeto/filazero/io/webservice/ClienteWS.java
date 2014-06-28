package br.com.projeto.filazero.io.webservice;

	import java.util.ArrayList;
import java.util.List;

import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.io.PersistenciaCliente;
import br.com.projeto.filazero.model.Cliente;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

	public class ClienteWS implements PersistenciaCliente  {
		
		private static final String URL_WS = VariaveisEstaticas.URL_WEBSERVICE+"cliente/";

		public ClienteWS(){

		}
		
		public String save(Cliente cliente) throws Exception {
		     Gson gson = new Gson();
		     String clienteJSON = gson.toJson(cliente);
		     String[] resposta = new WebService().post(URL_WS, clienteJSON);
		     if (resposta[0].equals("200")) {
		         return resposta[1];
		     } else {
		         throw new Exception(resposta[1]);
		     }
	    }
	    

	    public Cliente get(String login) throws Exception {
	    	String[] resposta = new WebService().get(URL_WS + login);
	    	if (resposta[0].equals("200")) {
	    		Gson gson = new Gson();
		        Cliente cliente = gson.fromJson(resposta[1], Cliente.class);
		        return cliente;
		    } else {
		        throw new Exception(resposta[1]);
		    }
	    }
	    
	    public Cliente autenticacao(String login,String senha) throws Exception {
	    	String url = URL_WS +"login="+login+"/senha="+senha;
	    	System.out.println(url);
	    	String[] resposta = new WebService().get(url);
	    	
	    	if (resposta[0].equals("200")) {
	    		Gson gson = new Gson();
		        Cliente cliente = gson.fromJson(resposta[1], Cliente.class);
		        return cliente;
		    } else {
		        throw new Exception(resposta[1]);
		    }
	    }
	    	  	    	      
	    public String delete(int id) {   
	     	String[] resposta = new WebService().get(URL_WS + "delete/" + id);
	     	return resposta[1];
	    }
			    
		@Override
		public String update(Cliente cliente) throws Exception {
			Gson gson = new Gson();
		     String clienteJSON = gson.toJson(cliente);
		     String[] resposta = new WebService().post(URL_WS + "atualizar", clienteJSON);
		     if (resposta[0].equals("200")) {
		         return resposta[1];
		     } else {
		         throw new Exception(resposta[1]);
		     }
		}

		
}

