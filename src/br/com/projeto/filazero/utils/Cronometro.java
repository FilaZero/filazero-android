package br.com.projeto.filazero.utils;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Timer;  
import java.util.TimerTask;  

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Cronometro {  
	private TextView cronometroTexto;
    private Timer cronometro;  
    private DateFormat formato = new SimpleDateFormat("HH:mm:ss");  
    private Calendar calendario = Calendar.getInstance();  
    private final byte contagem;  
    public static final byte PROGRESSIVA = 1;  
    public static final byte REGRESSIVA = -1;  
    public Cronometro( int ano, int mes, int dia, int horas, int minutos, int segundos, byte tipoContagem, final TextView text) {  
        this.cronometro = new Timer();  
        calendario.set(ano, mes, dia, horas, minutos, segundos);  
        this.contagem = tipoContagem;
        this.cronometroTexto = text;
    }  
  
    public void cronometro(final Activity activity) {  
        TimerTask tarefa = new TimerTask() {  
            @Override  
            public void run() {  
            	activity.runOnUiThread(new Runnable() {
        			@Override
        			public void run() {
        				cronometroTexto.setText(getTime());		
        			}
        		});
            }  
            
        };
        
        cronometro.scheduleAtFixedRate(tarefa, 0, 1000);  
        this.cronometro = null;  
    }  
  
    public String getTime() {  
        calendario.add(Calendar.SECOND, contagem);  
        return formato.format(calendario.getTime());  
    }  
}  