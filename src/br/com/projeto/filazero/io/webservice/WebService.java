package br.com.projeto.filazero.io.webservice;


public class WebService{

    public final String[] get(String url){   
    	String [] resposta = {"0","Ocorreu uma falha no WS"};    	
		try {
			WSGet getWS = new WSGet();	    	
	    	resposta = getWS.execute(url).get();			
		} catch (Exception e) {			
			return resposta;						
		}
		return resposta;
    }	    

    public final String[] post(String url, String json) {
    	String [] resposta = {"0","Ocorreu uma falha no WS"};    	
		try {
			WSPost post = new WSPost();	    	
	    	resposta = post.execute(url,json).get();			
		} catch (Exception e) {			
			return resposta;						
		}
		return resposta;
    }
    
    public final String[] put(String url, String json) {
    	String [] resposta = {"0","Ocorreu uma falha no WS"};    	
		try {
			WSPut put = new WSPut();	    	
	    	resposta = put.execute(url,json).get();			
		} catch (Exception e) {			
			return resposta;						
		}
		return resposta;
    }
    
    public final String[] delete(String url){   
    	String [] resposta = {"0","Ocorreu uma falha no WS"};    	
		try {
			WSDelete wsDelete = new WSDelete();	    	
	    	resposta = wsDelete.execute(url).get();			
		} catch (Exception e) {			
			return resposta;						
		}
		return resposta;
    }	  

}
