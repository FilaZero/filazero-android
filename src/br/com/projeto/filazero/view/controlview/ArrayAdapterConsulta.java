package br.com.projeto.filazero.view.controlview;

import java.util.List;

import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Consulta;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ArrayAdapterConsulta extends ArrayAdapter<Consulta> {
	private List<Consulta> consultas;
	private Context context;
	
	
	public ArrayAdapterConsulta(Context context, List<Consulta> consultas) {
	    super(context,R.layout.list_consulta_item ,consultas);
	    this.consultas=consultas;
	    this.context = context;
//	    cpfCliente = VariavesEstaticas.usuarioLogado.getCpf();
	  }
		
	@Override
	public int getCount() {
		return consultas.size();
	}
	
	@Override 
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
		  View view = convertView;
		  
	      if(view == null) {
	            LayoutInflater li = LayoutInflater.from(getContext());
	            view = li.inflate(R.layout.list_consulta_item, null);           
	      }
	      Consulta consulta = consultas.get(position);
	      if(consulta!=null){
		  	  ImageView icone = (ImageView) view.findViewById(R.id.linhasconsultas_icon);
	    	  TextView nomeEstabelecimento = (TextView)view.findViewById(R.id.linhasconsultas_titulo);
	    	  nomeEstabelecimento.setText(consulta.getEstabelecimento().getNome());
	    	  TextView nomeMedico = (TextView)view.findViewById(R.id.linhasconsultas_medico);
	  
	    	  nomeMedico.setText(consulta.getMedico().getNome());
	    	  TextView data = (TextView)view.findViewById(R.id.linhasconsultas_data);
	    	 
	    	  data.setText(consulta.getData());
	    	  ImageView iconeStatus = (ImageView) view.findViewById(R.id.linhasconsultas_icon_status);
	    	  if(consulta!=null){
	    		 mudarIcone(consulta.getStatus(), iconeStatus);
	    	  }
	     	 
	      }
	     
	      return view;  
	  }
	
	private void mudarIcone(String status,ImageView icone){	  	    		
		if(status.equals("Confirmado"))
    		icone.setBackgroundResource(R.drawable.ic_consulta_conf);	
		else if(status.equals("Pendente"))
    		icone.setBackgroundResource(R.drawable.ic_consulta_pendente);    		
		else
    		icone.setBackgroundResource(R.drawable.ic_consulta_aceito);	    	
    }
	
	public void addAndNotify(List<Consulta> consultas){    
        if(this.consultas!=null){    
            this.consultas = consultas;
            this.notifyDataSetChanged();    
        }    
    }  
}
