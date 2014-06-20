package br.com.projeto.filazero.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.projeto.filazero.R;
import br.com.projeto.filazero.fragments.ConteudoTabInfo;
import br.com.projeto.filazero.fragments.TabInfoConsulta;
import br.com.projeto.filazero.model.Consulta;

public class GUIInfoConsulta extends ActionBarActivity{
	private Consulta consulta;
	private TextView nomeEstabelecimento, enderecoEstabelecimento;
	private ImageView iconeEstabelecimento;

	
	protected void onCreate(Bundle savedInstanceState )  { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.activity_gui_info_consulta);
	    this.consulta = (Consulta) this.getIntent().getSerializableExtra("Consulta");
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
	    }    
	    ConteudoTabInfo primeiroFragmento = new ConteudoTabInfo();
	    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, primeiroFragmento).commit();
	    iniciarComponentes();
	    
	}
	    
	public void onBackPressed() {
    	Intent intent = new Intent(GUIInfoConsulta.this, GUIHome.class);                  
    	GUIInfoConsulta.this.startActivity(intent);
    	GUIInfoConsulta.this.finish();
	}
	
	//Adiciona os menus na action bar
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_info_consulta, menu);
        return super.onCreateOptionsMenu(menu);
    }

	private void iniciarComponentes(){
		nomeEstabelecimento = (TextView) findViewById(R.id.nomeEstabelecimento);
		nomeEstabelecimento.setText(consulta.getEstabelecimento().getNome());
		enderecoEstabelecimento = (TextView) findViewById(R.id.enderecoEstabelecimento);
		String endereco = consulta.getEstabelecimento().getEndereco().getRua() + "," + 
		consulta.getEstabelecimento().getEndereco().getNumero() + "," + 
		consulta.getEstabelecimento().getEndereco().getBairro() + " "+ consulta.getEstabelecimento().getTelefone();
		enderecoEstabelecimento.setText(endereco);
		iconeEstabelecimento = (ImageView) findViewById(R.id.iconeEstabelecimento);
		setarConsultaTabela();
	}
	
	public Consulta getConsulta(){	
		return consulta;
	}
	
	public void setarConsultaTabela(){
		FragmentManager fg = getSupportFragmentManager();
		TabInfoConsulta tabinfos = (TabInfoConsulta) fg.findFragmentById(R.id.tabConsulta);
		tabinfos.setConsulta(consulta);
	}
}

