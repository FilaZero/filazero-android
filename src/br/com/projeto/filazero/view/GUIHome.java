package br.com.projeto.filazero.view;


import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.VariaveisEstaticas;
import br.com.projeto.filazero.asyncTask.CarregarViewPagina;
import br.com.projeto.filazero.fragments.ListaDeConsultas;
import br.com.projeto.filazero.fragments.SemConsultas;
import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.model.FacadeArquivo;
import br.com.projeto.filazero.model.FacadeSQL;
import br.com.projeto.filazero.model.GCMCliente;
import br.com.projeto.filazero.utils.VerificarInternet;


public class GUIHome extends ActionBarActivity{
	private Cliente usuario;
	private Facade facade;
	private FacadeSQL facadeSQL;
	private FacadeArquivo facadeArquivo;
	private GCMCliente gcm;
	private String registroGCM;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_telahomefragments);
        
        facade = new Facade();
        facadeSQL =  new FacadeSQL(this);
        facadeArquivo = new FacadeArquivo(this);
        usuario = VariaveisEstaticas.usuarioLogado;
        registroGCM = VariaveisEstaticas.registroGCM;    
        
        registrarGCM();
        manterClienteLogado();
        
        if(VerificarInternet.isOnline(this)){
        	CarregarViewPagina carregaViewPagina = new CarregarViewPagina(this);
            carregaViewPagina.execute(VariaveisEstaticas.usuarioLogado.getCpf()); 
            salvarCacheConsulta();
    	}else {
    		Toast.makeText(this, "Verifique sua conexão", Toast.LENGTH_SHORT).show();
    		List<Consulta> cacheConsultas = facadeArquivo.getCacheConsultas();
    		if(cacheConsultas!=null){
    			fragmentsListaDeConsultas(cacheConsultas);
    		}
    		
    	}
	}
	
	private void manterClienteLogado(){ //se o cliente nao salvo tiver no sqlite, salva
		Cliente clienteLogado = facadeSQL.getClienteLogado();
		if(clienteLogado==null){
			facadeSQL.manterClienteLogado(VariaveisEstaticas.usuarioLogado);
		}
	}
	
	private void salvarCacheConsulta(){
		facadeArquivo.salvarCacheConsultas(VariaveisEstaticas.consultasCache);
	}
	
	//Adiciona os menus na action bar
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.ic_marcar_consulta:
	        	getTelaLocalizarEstabelecimentos();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

//	public void configuraActionBar(){
//		getSupportActionBar().setTitle("FilaZero");
//	}

	private void registrarGCM(){
		if(!VariaveisEstaticas.verificadoGCM){
			String cpf = usuario.getCpf();
			gcm = facade.getGCMCliente(cpf);
			if(gcm==null ){ // se nao tiver nenhuam key no banco referente ao cliente, cadastre
				gcm = new GCMCliente();
				gcm.setCpfCliente(cpf);
				gcm.setKey(registroGCM);
				gcm.setAtivo('1');
				facade.salvarGCM(gcm);
			}else {
				if(!gcm.getKey().equals(registroGCM)){ //se a key que ta no banco é diferente da atual, atualize
					gcm = new GCMCliente();
					gcm.setCpfCliente(cpf);
					gcm.setKey(registroGCM);
					gcm.setAtivo('1');
					facade.atualizarGCM(gcm);
				}
				if(gcm.isAtivo()==1){ //se o usuario quer receber notificacoes, ativar o servico de notificaçao
					Log.i("Info GCM:", "Ativando Notificacoes GCM");
					startService(new Intent("SERVICO_GCM"));
				}
			}
			VariaveisEstaticas.verificadoGCM = true; // faz a verificaçao apenas 1 vez a cada login
		}
	}
	
	private void logout(){
		 facadeSQL.logoutCliente(usuario);
		 facadeArquivo.limparCacheConsultas();
    	 usuario = null; 
    	 Intent intent = new Intent(GUIHome.this, GUILogin.class);                  
         GUIHome.this.startActivity(intent);
         GUIHome.this.finish();
	}

	 public void onBackPressed() {
		 AlertDialog.Builder builder = new AlertDialog.Builder(GUIHome.this);
         builder.setMessage("Reamente deseja fazer logout?");
         builder.setCancelable(false);
         builder.setNegativeButton("Nao",
                 new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                         dialog.cancel();
                     }
                 });
         builder.setPositiveButton("Sim",
                 new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                    	 logout();
                     }
                 });
        
         AlertDialog alertDialog = builder.create();
         alertDialog.show();
	 }


	private void getTelaLocalizarEstabelecimentos(){
		Intent intent = new Intent(GUIHome.this, GUILocalizarEstabelecimento.class);                  
        GUIHome.this.startActivity(intent);
        GUIHome.this.finish();
	}
	
	public void fragmentsListaDeConsultas(List<Consulta> consultas){
		this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home, new ListaDeConsultas(consultas)).commit();
		
	}
	
	public void fragmentsListaDeConsultasVazia(){
		this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home, new SemConsultas()).commit();
	}
}