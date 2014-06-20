/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.projeto.filazero.fragments;

import br.com.projeto.filazero.R;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.view.GUIInfoConsulta;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;


public class ConteudoTabInfo extends Fragment implements OnClickListener {
	private TextView medico,turno,status,tipo,data;
	private Button btConfirmar, btCancelar;
	private View info;
	private  Consulta consulta;
	private String tipos[] = {"Consulta","Retorno","Representante"};
	private Facade facade; 
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  info = inflater.inflate(R.layout.info_consulta_view_fragments, null);
	  facade = new Facade();
	  iniciarComponentes();
	  setText();
	  return info;
	 }
	 
	public void iniciarComponentes(){
		medico = (TextView) info.findViewById(R.id.nomeMedico);
		turno = (TextView) info.findViewById(R.id.nomeTurno);
		status = (TextView) info.findViewById(R.id.nomeStatus);
		tipo = (TextView) info.findViewById(R.id.nomeTipo);
		data = (TextView) info.findViewById(R.id.nomeData);
	}
	public void setText(){
	    consulta  = ((GUIInfoConsulta) getActivity()).getConsulta();
		medico.setText(consulta.getMedico().getNome());
		turno.setText(consulta.getTurno());
		status.setText(consulta.getStatus());
		tipo.setText(tipos[Integer.parseInt(consulta.getTipo())]);
		data.setText(consulta.getData());
		btConfirmar = (Button) info.findViewById(R.id.bt_confirmar);
		btConfirmar.setOnClickListener(this);	
			
		btCancelar = (Button) info.findViewById(R.id.bt_cancelar);
		btCancelar.setOnClickListener(this);
		mudarBotao();
	 }
	public void mudarBotao(){
		String status = consulta.getStatus();
		if(status.equals("Pendente")){
			btConfirmar.setEnabled(false);
		}if(status.equals("Confirmado")){
			btConfirmar.setVisibility(View.INVISIBLE);			 	
		}if(status.equals("Aceito")){
			btConfirmar.setEnabled(true);
		}if(status.equals("Cancelado")){
			btConfirmar.setEnabled(false);
			btCancelar.setEnabled(false);
		}
	 }
	 
	 private void cancelarConsulta(){
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage("Deseja cancelar?");
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
	                   	 	String resposta = facade.cancelarConsulta(consulta.getIdConsulta());
	                   	 	if(resposta.equals("Consulta cancelada")){
		                   	 	btCancelar.setEnabled(false);
		                   	 	
	                   	 	}else
	                   	 		Toast.makeText(getActivity().getBaseContext(),resposta, Toast.LENGTH_SHORT).show();
	                    }
	                });       
	        AlertDialog alertDialog = builder.create();
	        alertDialog.show();
		}
		
		private void confirmarConsulta(){
			if(consulta.getStatus().equals("Aceito")){
				String resposta = facade.confirmarConsulta(consulta.getIdConsulta());
				if(resposta.equals("Consulta confirmada")){
					btConfirmar.setEnabled(false);
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		        builder.setMessage(resposta);
		        builder.setCancelable(false);
		        builder.setNeutralButton("Ok",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {	                    	
		                        dialog.cancel();
		                    }
		                });
		        AlertDialog alertDialog = builder.create();
		        alertDialog.show();
				
			}
		}
		
		@Override
		public void onResume() {

		super.onResume();
		this.onCreate(null);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_confirmar:
			confirmarConsulta();
			break;
		case R.id.bt_cancelar:
			cancelarConsulta();
			break;
		default:
			break;
		}
		
	}
	
}