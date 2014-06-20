package br.com.projeto.filazero.io.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.projeto.filazero.model.Cliente;



public class PersistenciaClienteSQL {
	
	
	private final String TB_CLIENTE = "TB_CLIENTE";	
	private final String CPF = "CPF";
	private final String LOGIN = "Login";
	private final String SENHA = "Senha";
	private final String NOME = "Nome";
	private final String EMAIL = "Email";
	private final String TELEFONE = "Telefone";
	private final String SEXO = "Sexo";
	
	private Context context;
	private SQLiteDatabase database;
	private BDHelper db;
	private String[] todasColunas = {"CPF","Login","Senha","Nome","Email","Telefone","Sexo"};
	
	public PersistenciaClienteSQL (Context context){		
		db = BDHelper.getInstance(context);
		this.context = context;	
	}
	
	public long salve(Cliente user) {	
		
		try{
			database = db.getWritableDatabase();

			ContentValues content = new ContentValues(); 
			content.put(CPF, user.getCpf());
			content.put(LOGIN, user.getLogin());
			content.put(SENHA, user.getSenha());
			content.put(NOME, user.getNome());
			content.put(EMAIL, user.getEmail());
			content.put(TELEFONE, user.getTelefone());
			content.put(SEXO, user.getSexo());
			
			long s =  database.insert(TB_CLIENTE, null,content);
			return s;
		}catch(SQLException e ){
			Log.e("BD", e.toString());	
			return -1;
		}finally{
			database.close();
			db.close();
		}		
	}

	
	public Cliente get(String userLogin) {
		try{
			database = db.getReadableDatabase();
			Cliente user = null;
			Cursor cursor = null; 
	 
			String where = "LOGIN = ?"; 
			
			String argumentos[] = new String[] { userLogin};
	 
			cursor = database.query(TB_CLIENTE, todasColunas, where, argumentos, null, null, null);
	 
			if (cursor != null  && cursor.moveToFirst()) {
				user = new Cliente();
				user.setCpf(cursor.getString(cursor.getColumnIndex(CPF)));
				user.setLogin(cursor.getString(cursor.getColumnIndex(LOGIN)));
				user.setSenha(cursor.getString(cursor.getColumnIndex(SENHA)));
				user.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
				user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
				user.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
				user.setSexo(cursor.getString(cursor.getColumnIndex(SEXO)));
	        } 
			if(cursor!=null){
				cursor.close();
			}
			
			return user;	
		}finally{			
			database.close();
			db.close();
		}
			
	}
	
	public Cliente getUsuarioLogado(){
		try{
			database = db.getReadableDatabase();
			Cliente user = null;
			Cursor cursor = null; 
	 
			cursor = database.query(TB_CLIENTE, todasColunas,null,null,null, null, null);
	 
			if (cursor != null  && cursor.moveToFirst()) {
				user = new Cliente();
				user.setCpf(cursor.getString(cursor.getColumnIndex(CPF)));
				user.setLogin(cursor.getString(cursor.getColumnIndex(LOGIN)));
				user.setSenha(cursor.getString(cursor.getColumnIndex(SENHA)));
				user.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
				user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
				user.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
				user.setSexo(cursor.getString(cursor.getColumnIndex(SEXO)));
	        } 
			if(cursor!=null){
				cursor.close();
			}
			
			return user;	
		}finally{			
			database.close();
			db.close();
		}
	}
	
	public long logoutCliente(Cliente user){
		try{
			database = db.getWritableDatabase();
			String[] valoresParaSubstituir = { String.valueOf(user.getCpf()) };
			long i = database.delete(TB_CLIENTE, CPF + " =  ?", valoresParaSubstituir);
			return i;
		}finally{
			database.close();
			db.close();
		}
		
	}
	
	public long update(Cliente user) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public long delete(Cliente user) {		
		try{
			database = db.getWritableDatabase();
			String[] valoresParaSubstituir = { String.valueOf(user.getCpf()) };
			long i = database.delete(TB_CLIENTE, CPF + " =  ?", valoresParaSubstituir);
			return i;
		}finally{
			database.close();
			db.close();
		}		
	}
}
	

