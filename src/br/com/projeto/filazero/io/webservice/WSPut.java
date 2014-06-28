package br.com.projeto.filazero.io.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import android.os.AsyncTask;
import android.util.Log;

public class WSPut extends AsyncTask<String,Integer,String[]> {

	@Override
	protected String[] doInBackground(String... params) {
		String[] result = new String[2];
	     try {
	    	 HttpPut httpPut = new HttpPut(new URI(params[0]));
	         httpPut.setHeader("Content-type", "application/json");
	         StringEntity sEntity = new StringEntity(params[1], "UTF-8");
	         httpPut.setEntity(sEntity);

	         HttpResponse response;
	         response = HttpClientSingleton.getHttpClientInstace().execute(httpPut);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) {
	             result[0] = String.valueOf(response.getStatusLine().getStatusCode());
	             InputStream instream = entity.getContent();
	             result[1] = toString(instream);
	             instream.close();
	             Log.d("post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
	         }

	     } catch (Exception e) {
	         Log.e("NGVL", "Falha ao acessar Web service", e);
	         result[0] = "0";
	         result[1] = "Falha na rede, verifique sua conexao";
	     }
	     return result;
	}
	
	 private String toString(InputStream is) throws IOException {
	     byte[] bytes = new byte[1024];
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     int lidos;
	    
	     while ((lidos = is.read(bytes)) > 0) {
	         baos.write(bytes, 0, lidos);
	     }
	     return new String(baos.toByteArray());
	 }
}
