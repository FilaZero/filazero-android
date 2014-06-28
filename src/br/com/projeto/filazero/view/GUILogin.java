package br.com.projeto.filazero.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.projeto.filazero.Constants;
import br.com.projeto.filazero.GCM;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.model.FacadeSQL;
import br.com.projeto.filazero.model.GCMCliente;

public class GUILogin extends Activity implements OnClickListener{
	
	private EditText et_login, et_senha;
	private Button bt_logar;
	private TextView texto_cadastrar; 
	private AlertDialog.Builder alerta;
	private Facade facade;
	private FacadeSQL facadeSQL;
	
	private String login;	
	private String senha;
	private boolean verificacao;
	private ProgressDialog pd;
   	
	private GCMCliente gcm;	
	private Context context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gui_login);
		context = getApplicationContext();
		VariaveisEstaticas.usuarioLogado = null;
		VariaveisEstaticas.verificadoGCM = false;
		iniciarComponentes();	
		clienteLogado();	
	}
	
	public void onBackPressed() {
		
	}
	
	private void clienteLogado(){//verifica se o usuario ja esta logado no app
		Cliente clienteLogado = facadeSQL.getClienteLogado();
		if(clienteLogado!=null){
			VariaveisEstaticas.usuarioLogado = clienteLogado;
			Intent intent = new Intent(GUILogin.this, GUIHome.class);                  
            GUILogin.this.startActivity(intent);
            GUILogin.this.finish();    
		}		
	}
	
	private void dispositivoRestridado(){
		boolean ativado = GCM.isAtivo(context);
		if (ativado) { //se ja for registrado no gcm
			VariaveisEstaticas.registroGCM = GCM.getRegistrationId(context);
			Log.d("Info GCM: " , "Ja cadastrado id: "+VariaveisEstaticas.registroGCM);			
	    }else {// se nao for registra no gcm
			GCM.registrar(context, Constants.PROJECT_NUMBER);
			VariaveisEstaticas.registroGCM = GCM.getRegistrationId(context);		
			Log.d("Info GCM: " , "Cadastrado com sucesso: "+VariaveisEstaticas.registroGCM);
	    }
	}
	private void iniciarComponentes(){
		dispositivoRestridado();// verifica se o dispositivo ja esta resgistrado no gcm
		
		facade = new Facade();
		facadeSQL = new FacadeSQL(this);
		
		
		et_login = (EditText) findViewById(R.id.login_etLogin);
		et_senha = (EditText) findViewById(R.id.login_etSenha);
		
		bt_logar = (Button) findViewById(R.id.login_btEntrar);
		bt_logar.setOnClickListener(this);
			
		texto_cadastrar = (TextView) findViewById(R.id.login_Cadastrasse);
		texto_cadastrar.setOnClickListener(this);
		texto_cadastrar.setPaintFlags(texto_cadastrar.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		   case R.id.login_btEntrar:{ 
			   logar();
			   break;
		   }case R.id.login_Cadastrasse:{
			   cadastrar();
			   break;
		   }
		} 		
	}
	
	private void logar(){
		et_login.requestFocus();
		et_senha.requestFocus();
		login = et_login.getText().toString();
		senha = et_senha.getText().toString();
		if(verificarCampos(login, senha)){
			verificacao = validarUsuario(login, senha);
			if(verificacao){
				Intent intent = new Intent(GUILogin.this, GUIHome.class);                  
	            GUILogin.this.startActivity(intent);
	            GUILogin.this.finish();                
			}
		}	
	}
	
	private void cadastrar(){
		Intent intent = new Intent(GUILogin.this, GUICadastroUser.class);                  
        GUILogin.this.startActivity(intent);
        GUILogin.this.finish();
	}
	
	private boolean verificarCampos(String login,String senha){
		if(login.equalsIgnoreCase("") && senha.equalsIgnoreCase("")) {
			Toast.makeText(this, "Preencha o campo de login/senha", Toast.LENGTH_SHORT).show();
			return false;
		}else if(login.equalsIgnoreCase("")){
			Toast.makeText(this, "Preencha o campo de login", Toast.LENGTH_SHORT).show();
			return false;
		}else if(senha.equalsIgnoreCase("")) {
			Toast.makeText(this, "Preencha o campo de senha", Toast.LENGTH_SHORT).show();
			return false;
		}else return true;
	}
	
	private boolean validarUsuario(final String login, final String senha){
		pd = ProgressDialog.show(this, null, "Aguarde", false, false);
		
		try {
			Cliente user = facade.autenticacao(login,senha);
			if(user!=null){
				VariaveisEstaticas.usuarioLogado = user;
				pd.dismiss();
				return true;						
			}else{
				et_login.setError("Usuario nao cadastrado");
				pd.dismiss();
				return false;	
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
			pd.dismiss();
			return false;
		}				
	}
	
	private void alert(){
		alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Senha incorreta");
		alerta.setIcon(R.drawable.ic_alerta);
		alerta.setMessage("A senha esta incorreta. Tente novamente");
		alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int whichButton){
				et_senha.setText("");
			}
		});
		alerta.show();
	}
}
