package br.com.projeto.filazero.io.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.projeto.filazero.model.Consulta;

public class PersistenciaConsultasArquivo {
	private Context context;
	
	private final String FILECONSULTAS = "consultas.dat";
	
	public PersistenciaConsultasArquivo(Context c){
		context = c;
	}
	
		
	public List<Consulta> getCache(){
		List<Consulta> consultas = getListaConsultas();
		if(consultas.isEmpty() || consultas==null)
			return null;
		
		return consultas;
			
	}
	
	private List<Consulta> getListaConsultas(){		
		try {	
			File file = context.getFileStreamPath(FILECONSULTAS);					
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));			 
			List<Consulta> resultado = (List<Consulta>) ois.readObject();
			ois.close();
			
			Log.i("Arquivo:", "Cache das consultas recuperado");
			return resultado;			 
		} catch (Exception e) {	
			Log.i("Arquivo:", "Erro ao recuperar o cache das consultas");
			return null;			 
		}
		 
	}
			
	
	public void salvarCache(List<Consulta> consultas){
		if(consultas!=null && !consultas.isEmpty()){
			try {				
				File file = context.getFileStreamPath(FILECONSULTAS);				 
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(consultas);
				oos.close();
				fos.close();						

				Log.i("Arquivo:", "Cache das consultas salvo");
			} catch (IOException e) {
				Log.i("Arquivo:", "Erro ao salvar o cache das consultas");
				e.printStackTrace();
			}	
		}		
	}
	
	public void limparCache(){
		File file = context.getFileStreamPath(FILECONSULTAS);
		file.delete();
		Log.i("Arquivo:", "Cache limpo");
	}
}
