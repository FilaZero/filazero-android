package br.com.projeto.filazero;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;


public class GCM {
	private static final String TAG = "GCM";

	public static String getRegistrationId(Context context) {
		return GCMRegistrar.getRegistrationId(context);
	}
	public static void registrar(Context context, String projectId) {
		GCMRegistrar.checkDevice(context);
		GCMRegistrar.checkManifest(context);
		final String regId = getRegistrationId(context);
		if (regId.equals("")) {
			GCMRegistrar.register(context, projectId);
			Log.d(TAG, "GCM registrado com sucesso.");
			Log.d("Registro do device: " , getRegistrationId(context));
		} else {
			Log.d(TAG, "GCM já está registrado, ID: " + regId);
		}
	}
	public static void cancelar(Context context) {
		GCMRegistrar.unregister(context);
		Log.d(TAG, "GCM cancelado com sucesso.");
	}
	public static boolean isAtivo(Context context) {
		return GCMRegistrar.isRegistered(context);
	}
	public static void onDestroy(Context context) {
		GCMRegistrar.onDestroy(context);
	}
}