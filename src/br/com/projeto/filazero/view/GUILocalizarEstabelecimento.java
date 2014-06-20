package br.com.projeto.filazero.view;

import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Facade;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class GUILocalizarEstabelecimento extends ActionBarActivity {
	private Facade facade;
	
	protected void onCreate(Bundle savedInstanceState )  { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.activity_gui_buscar_estabelecimento); 

	    ActionBar actionBar = getSupportActionBar(); 
	    actionBar.setDisplayHomeAsUpEnabled(true);  
	}
	

	//Adiciona os menus na action bar
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_localizar_estabelecimento, menu);
        return super.onCreateOptionsMenu(menu);
    }
		
	private void iniciarComponentes(){
		
	}
}
