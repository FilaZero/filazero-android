package br.com.projeto.filazero;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Consulta;
import br.com.projeto.filazero.model.Facade;
import br.com.projeto.filazero.utils.ActivityStackUtils;
import br.com.projeto.filazero.utils.NotificationUtil;
import br.com.projeto.filazero.view.GUIInfoConsulta;
import br.com.projeto.filazero.view.GUILogin;

public class Servico extends Service {
		
	public static String registroGCM;
	private Cliente cliente;
	private Context context;
	private Facade facade;
		
	@Override
	public void onStart(Intent intent, int startId) {
		this.context = getApplicationContext();
		facade = new Facade();
		registerReceiver(mensagemReceiver, new IntentFilter("RECEIVER_MSG"));
		Log.d("Info GCM:", "Notificaçoes GCM Ativadas");
	}
	
		
	private final BroadcastReceiver mensagemReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getExtras().getString("msg");
			Log.d("Info GCM:", "Nova Mensagem:" +msg);
			
			String [] argsMensagem = msg.split(":");
			String tipo = argsMensagem[0];
			String mensagem = argsMensagem[1];
			String arg = argsMensagem[2];
			Consulta consulta = facade.getConsultaId(arg);
			
			if(msg != null) {
				if(tipo.equals("Confirmar Consulta")){
					if(isLogado()){
						Intent intent2 = new Intent(context, GUIInfoConsulta.class);
						intent2.putExtra("Consulta", consulta);
						transfereActivity(mensagem,intent2);
					}else{
						Intent intent2 = new Intent(context, GUILogin.class);
						transfereActivity(mensagem,intent2);
					}
				}	
				if(tipo.equals("Notificacao Fila")){
					if(isLogado()){
						Intent intent2 = new Intent(context, GUIInfoConsulta.class);
						intent2.putExtra("Consulta", consulta);
						transfereActivity(mensagem,intent2);
					}else{
						Intent intent2 = new Intent(context, GUILogin.class);
						transfereActivity(mensagem,intent2);
					}
				}
			}
		}
	};
	
	
	private void transfereActivity(String msg, Intent intent){
		if (ActivityStackUtils.isMyApplicationTaskOnTop(context)) {		
			NotificationUtil.createNotification(context, msg,intent);		
		}else{		
			NotificationUtil.createNotification(context, msg,intent);			
		}
	}
	
	private boolean isLogado(){
		cliente = VariaveisEstaticas.usuarioLogado;
		if(cliente==null)
			return false;
		return true;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public void onDestroy() {		
		unregisterReceiver(mensagemReceiver);
		super.onDestroy();
	}
	
}
