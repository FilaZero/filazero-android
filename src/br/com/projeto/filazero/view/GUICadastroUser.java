package br.com.projeto.filazero.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.view.controlview.ControlGUICadastradoUser;
import br.com.projeto.filazero.view.controlview.Masks;


public class GUICadastroUser extends Activity implements OnFocusChangeListener{
	
	
	private EditText etCpf, etLogin, etSenha, etNome, etEmail, etTelefone;
	private Button btCadastrar;
	private Spinner spSexos;
	private ArrayAdapter adapter;
	
	private Context context;
	private Facade facade;
	private boolean camposValidos; 
	private boolean atualizar;
	private ControlGUICadastradoUser controle;
	private Cliente cliente;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gui_cadastro_user);
		iniciarComponentes();
		onOffCampos(false);
	}
	
	//quando clicar no botao voltar , volta para a tela anterior
	public void onBackPressed() {
	    	Intent intent = new Intent(GUICadastroUser.this, GUILogin.class);                  
	        GUICadastroUser.this.startActivity(intent);
	        GUICadastroUser.this.finish();
	}
	
	private void iniciarComponentes(){
		camposValidos = true;
		context = GUICadastroUser.this;
		controle = new ControlGUICadastradoUser();
		facade = new Facade();
		
		etCpf = (EditText) findViewById(R.id.cadastroUser_etCPF);
		etCpf.addTextChangedListener(Masks.insert("###.###.###-##", etCpf));
		etCpf.setOnFocusChangeListener(this);		
		
		etLogin = (EditText) findViewById(R.id.cadastroUser_etNickname);
		etLogin.setOnFocusChangeListener(this);	
		
		etSenha = (EditText) findViewById(R.id.cadastroUser_etSenha);
		etSenha.setOnFocusChangeListener(this);
		
		etNome = (EditText) findViewById(R.id.cadastroUser_etNome);
		etNome.setOnFocusChangeListener(this);		
	
		etEmail = (EditText) findViewById(R.id.cadastroUser_etEmail);
		etEmail.setOnFocusChangeListener(this);
		
		etTelefone = (EditText) findViewById(R.id.cadastroUser_etTelefone);
		etTelefone.addTextChangedListener(Masks.insert("(##)####-####", etTelefone));
		etTelefone.setOnFocusChangeListener(this);
		
		spSexos = (Spinner) findViewById(R.id.cadastroUser_spSexo);
		adapter = ArrayAdapter.createFromResource(this,R.array.sexos_array,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spSexos.setAdapter(adapter);		
		
		btCadastrar = (Button) findViewById(R.id.cadastroUser_btCadastrar);
		btCadastrar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				 cadastrar();
			}});				
	}
	
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch(v.getId()){
		   case R.id.cadastroUser_etCPF:{ 
			   testarCpf(hasFocus);
			   break;
		   }case R.id.cadastroUser_etNickname:{
			   testarLogin(hasFocus);
			   break;
		   }case R.id.cadastroUser_etSenha:{
			   testarSenha(hasFocus);
			   break;
		   }case R.id.cadastroUser_etNome:{
			   testarNome(hasFocus);
			   break;
		   }case R.id.cadastroUser_etEmail:{
			   testarEmail(hasFocus);
			   break;
		   }case R.id.cadastroUser_etTelefone:{
			   testarTelefone(hasFocus);
			   break;
		   }		
		}
	}
	
	private void cadastrar(){
		if(camposValidos & camposNotNull()){
			Cliente cliente = getCliente();
			if(atualizar){	//se ja existir no sistema, mas nao tiver login e senha, atualiza
				String atualizado = facade.atualizarUsuario(cliente);						
				exibirToast(atualizado); 
			
				if(atualizado.equals("Atualizado com sucesso")){
					Intent intent = new Intent(GUICadastroUser.this, GUILogin.class);                  
				    GUICadastroUser.this.startActivity(intent);
				    GUICadastroUser.this.finish();
				}													
			}else{//cadastra um novo cliente;
				String cadastrar = facade.salvarUsuario(cliente);						
				exibirToast(cadastrar);
				
				if(cadastrar.equals("Cadastrado com sucesso")){
					Intent intent = new Intent(GUICadastroUser.this, GUILogin.class);                  
				    GUICadastroUser.this.startActivity(intent);
				    GUICadastroUser.this.finish();
				}
			}	
		}else
			exibirToast("Porfavor verifique os campos invalidos");	
	}
	private void exibirToast(String msg){
		Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
	}
	
	//pega os valores do campos
	private Cliente getCliente(){
		Cliente user = new Cliente();
		user.setCpf(etCpf.getText().toString().replace(".","").replace("-", ""));
		user.setLogin(etLogin.getText().toString());
		user.setSenha(etSenha.getText().toString());
		user.setNome(etNome.getText().toString());
		user.setEmail(etEmail.getText().toString());
		user.setTelefone(etTelefone.getText().toString());
		user.setSexo(spSexos.getSelectedItem().toString());
		
		return user;		
	}
	
	//testa se os campos estao em branco
	private boolean camposNotNull(){
		etLogin.requestFocus();
		etSenha.requestFocus();
		etNome.requestFocus();
		etEmail.requestFocus();
		etTelefone.requestFocus();
		if(etLogin.getText().toString().equals("") || etSenha.getText().toString().equals(""))
			return false;
		if(etNome.getText().toString().equals("") || etLogin.getText().toString().equals(""))
			return false;		
		if(etLogin.getText().toString().equals(""))
			return false;		
		
		return true;
	}
	
	private void limpaCampos(){
		etNome.setText("");
		etNome.setTextColor(Color.parseColor("#000000"));
		etNome.setError(null,null);
		etLogin.setText("");
		etLogin.setTextColor(Color.parseColor("#000000"));
		etLogin.setError(null,null);
		etSenha.setText("");
		etSenha.setTextColor(Color.parseColor("#000000"));
		etSenha.setError(null,null);
		etEmail.setText("");
		etEmail.setTextColor(Color.parseColor("#000000"));
		etEmail.setError(null,null);
		etTelefone.setText("");
		etTelefone.setTextColor(Color.parseColor("#000000"));
		etTelefone.setError(null,null);
		spSexos.setSelection(0);
	}
	
	private void preencheCampos(){
		String cpf = etCpf.getText().toString().replace(".","");
		cpf = cpf.replace("-","");
		try{
			cliente = facade.getUsuario(cpf);
			etNome.setText(cliente.getNome());
			etEmail.setText(cliente.getEmail());
			etTelefone.setText(cliente.getTelefone());
			if(cliente.getSexo().equals("Masculino"))
				spSexos.setSelection(0);
			else
				spSexos.setSelection(1);
		}catch(Exception e){
		}		
	}
	
	private void onOffCampos(boolean onoff){
		etNome.setEnabled(onoff);
		etLogin.setEnabled(onoff);
		etSenha.setEnabled(onoff);
		etEmail.setEnabled(onoff);
		etTelefone.setEnabled(onoff);
		spSexos.setEnabled(onoff);
	}
	
	private void testarCpf(boolean hasFocus){
		if(hasFocus){
			etCpf.setTextColor(Color.parseColor("#000000"));
			limpaCampos();
		}else{
			String respostaTeste = controle.testarCpf(etCpf.getText().toString().replace(".","").replace("-", ""));
			if(respostaTeste.equals("Ok")){
				camposValidos = true;
				onOffCampos(true);
				atualizar = false;
			}else if (respostaTeste.equals("Usuario cadastrado, mas nao possui login")){
				onOffCampos(true);
				preencheCampos();
				atualizar = true;
			}else{					
				etCpf.setTextColor(Color.parseColor("#FF0000"));
				etCpf.setError(respostaTeste);
				onOffCampos(false);
				camposValidos = false;
			}				
		}
	}
	
	private void testarLogin(boolean hasFocus){
		if(hasFocus){
			etLogin.setTextColor(Color.parseColor("#000000"));
		}else{
			String respostaTeste = controle.testarLogin(etLogin.getText().toString());
			if(respostaTeste.equals("Ok"))
				camposValidos = true;
			else{					
				etLogin.setTextColor(Color.parseColor("#FF0000"));
				etLogin.setError(respostaTeste);
				camposValidos = false;
			}				
		}	
	}
	
	private void testarSenha(boolean hasFocus){
		if(hasFocus){
			etSenha.setTextColor(Color.parseColor("#000000"));
		}else{
			String respostaTeste = controle.testarSenha(etSenha.getText().toString());
			if(respostaTeste.equals("Ok"))
				camposValidos = true;
			else{						
				etSenha.setTextColor(Color.parseColor("#FF0000"));
				etSenha.setError(respostaTeste);
				camposValidos = false;
			}						
		}
	}
	
	private void testarNome(boolean hasFocus){
		if(hasFocus){
			etNome.setTextColor(Color.parseColor("#000000"));
		}else{
			String respostaTeste = controle.testarNomeUser(etNome.getText().toString());
			if(respostaTeste.equals("Ok"))				
				camposValidos = true;
			else{
				etNome.setTextColor(Color.parseColor("#FF0000"));
				etNome.setError(respostaTeste);
				camposValidos = false;
			}						
		}
	}
	
	private void testarEmail(boolean hasFocus){
		if(hasFocus){
			etEmail.setTextColor(Color.parseColor("#000000"));
		}else{
			if(controle.testarEmail(etEmail.getText().toString()))					
				camposValidos = true;
			else{
				etEmail.setTextColor(Color.parseColor("#FF0000"));
				etEmail.setError("Email invalido, seu email deve conter @");
				camposValidos = false;
			}
			
		}
	}
	
	private void testarTelefone(boolean hasFocus){
		if(hasFocus){
			etTelefone.setTextColor(Color.parseColor("#000000"));
		}else{
			if(controle.testarTelefone(etTelefone.getText().toString()))					
				camposValidos = true;
			else{						 
				etTelefone.setTextColor(Color.parseColor("#FF0000"));
				etTelefone.setError("Telefone invalido");						
				camposValidos = false;
			}				
		}	
	}

}
