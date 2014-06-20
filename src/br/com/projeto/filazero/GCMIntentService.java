package br.com.projeto.filazero;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.utils.ActivityStackUtils;


import com.google.android.gcm.GCMBaseIntentService;

/**
 * IntentService responsável por tratar as mensagens do GCM (Google Cloud
 * Messaging).
 * 
 * O método onRegistered é chamado quando o registro no GCM é feito com sucesso.
 * Neste momento temos o registrationId único do device.
 * 
 * O método onMessage é chamado quando uma mensagem por push é recebida.
 * 
 * @author Ricardo Lecheta
 * 
 */
public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	
	public GCMIntentService() {
		super(Constants.PROJECT_NUMBER);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		enviarMensagemParaApp("Device registrado: registrationId = " + registrationId);		
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		enviarMensagemParaApp("Device removido do registro.");
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		String msg = intent.getStringExtra("msg");
		enviarMensagemParaApp(msg);
	}

	@Override
	public void onError(Context context, String errorId) {
		enviarMensagemParaApp("Erro: " + errorId);
	}

	private void enviarMensagemParaApp(String msg) {
		Log.i(TAG, msg);
		Intent intent = new Intent("RECEIVER_MSG");
		intent.putExtra("msg", msg);
		sendBroadcast(intent);
	}
	
}
