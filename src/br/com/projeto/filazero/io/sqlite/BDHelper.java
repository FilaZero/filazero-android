package br.com.projeto.filazero.io.sqlite;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {
	
	private static BDHelper instance;
	private static final int DATABASE_VERSION = 2;	
    private static final String NOME_BD = "FILAZERO_BD";
   
    private BDHelper(Context ctx) {
		super(ctx,NOME_BD, null,DATABASE_VERSION);
	}
	
	public static BDHelper getInstance(Context context) {
        if(instance == null)
            instance = new BDHelper(context);
         
        return instance;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {		
		db.execSQL(TABELA_USER);
		//db.execSQL(TABELA_ESTAB);
		//db.execSQL(TABELA_MEDICO);
		//db.execSQL(TABELA_CONSULTA);
	}	
	


	public static final String TABELA_USER ="CREATE TABLE IF NOT EXISTS TB_CLIENTE ("+
			  "CPF varchar(100),"+
			  "Nome varchar(100),"+
			  "Login varchar(100),"+
			  "Senha varchar(100),"+
			  "Sexo varchar(100),"+
			  "Email varchar(100),"+
			  "Telefone varchar(15),"+
			  "PRIMARY KEY (CPF)"+
			  ") ;";
	
	
	public static final String TABELA_ESTAB ="CREATE TABLE IF NOT EXISTS ESTABELECIMENTOS (" +
			  "ID INTEGER PRIMARY KEY NOT NULL,"+
			  "NOME VARCHAR(15) NOT NULL,"+
			  "TELEFONE VARCHAR(15) NOT NULL,"+
			  "ESTADO VARCHAR(15)  NOT NULL,"+
			  "CIDADE VARCHAR(15)  NOT NULL,"+
			  "BAIRRO VARCHAR(15) NOT NULL,"+
			  "RUA VARCHAR(15) NOT NULL,"+		
			  "NUMERO VARCHAR(10) NOT NULL,"+
			  "DESCRICAO STRING "+
			   " )  ;";
	
	public static final String TABELA_MEDICO ="CREATE TABLE IF NOT EXISTS MEDICOS (" +
			  "ID_ESTAB INTEGER NOT NULL,"+
			  "CRM INTEGER PRIMARY KEY,"+
			  "NOME VARCHAR(15) NOT NULL,"+
			  "ESPECIALIDADE VARCHAR(15) NOT NULL,"+
			  "DESCRICAO STRING "+
			   " )  ;";
	
	public static final String TABELA_CONSULTA ="CREATE TABLE IF NOT EXISTS CONSULTAS (" +
			  "ID INTEGER PRIMARY KEY NOT NULL,"+
			  "ID_USER INTEGER NOT NULL,"+
			  "ID_ESTB INTEGER NOT NULL,"+
			  "CRM_MEDICO INTEGER NOT NULL,"+
			  "STATUS STRING "+
			   " )  ;";



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("UC", "Atualizando a base de dados da versão " + oldVersion + " para " + newVersion + ", que destruirá todos os dados antigos");
		String sql = TABELA_USER;
		db.beginTransaction();
		
		try{
			db.execSQL(sql);
			db.setTransactionSuccessful();
		}catch (SQLException e){
			Log.e("Erro ao atualizar as tabelas e testar os dados", e.toString());
			throw e;
		} 
		finally{
			db.endTransaction();
		}		
		onCreate(db);		
	}
	
}
