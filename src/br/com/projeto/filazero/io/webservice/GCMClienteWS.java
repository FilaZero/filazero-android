package br.com.projeto.filazero.io.webservice;

import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.GCMCliente;

import com.google.gson.Gson;

public class GCMClienteWS {

	private static final String URL_WS = VariaveisEstaticas.URL_WEBSERVICE+"GCMCliente/";
	
	public String salvarGCMCliente(GCMCliente gcm)throws Exception {
		Gson gson = new Gson();
	    String gcmJSON = gson.toJson(gcm);
	    String[] resposta = new WebService().post(URL_WS, gcmJSON);
	    if (resposta[0].equals("200")) 
	        return resposta[1];
	    else
	        throw new Exception(resposta[1]);
	}
	
	public String atulizarGCMCliente(GCMCliente gcm)throws Exception {
		Gson gson = new Gson();
	    String gcmJSON = gson.toJson(gcm);
	    String[] resposta = new WebService().put(URL_WS, gcmJSON);
	    if (resposta[0].equals("200")) {
	        return resposta[1];
	    } else {
	        throw new Exception(resposta[1]);
	    }
	}
	
	public GCMCliente getGCMCliente(String cpf)throws Exception{
		String[] resposta = new WebService().get(URL_WS +"cpf="+ cpf);

	    if (resposta[0].equals("200")) {
	        Gson gson = new Gson();
	        GCMCliente gcm = gson.fromJson(resposta[1], GCMCliente.class);
	        return gcm;
	    } else {
	    	System.out.println("erro" +resposta[1]);
	        throw new Exception(resposta[1]);
	    }
	}	
}
