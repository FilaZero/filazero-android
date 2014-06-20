package br.com.projeto.filazero.view.controlview;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;



public abstract class Masks {
	 
    public static String unmask(String s) {
    	return s.replaceAll("[.]", "").replaceAll("[-]", "")
			.replaceAll("[/]", "").replaceAll("[(]", "")
			.replaceAll("[)]", "");
    }

    public static String dataUsToBR(String data){
		String dataS = data.replace("-","/");
		try {						
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			Date date = (Date)formatter.parse(dataS);
			Locale BRAZIL = new Locale("pt","BR"); 
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, BRAZIL);  
		    dataS = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 		
		return dataS;
	}
    
	public static TextWatcher insert(final String mask, final EditText ediTxt) {
			return new TextWatcher() {
			boolean isUpdating;
			String old = "";
	
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = Masks.unmask(s.toString());
				String mascara = "";
				if (isUpdating) {
					old = str;
					isUpdating = false;
					return;
				}
				int i = 0;
				for (char m : mask.toCharArray()) {
					if (m != '#' && str.length() > old.length()) {
						mascara += m;
						continue;
					}
					try {
						mascara += str.charAt(i);
					} catch (Exception e) {
						break;
					}
					i++;
				}
				isUpdating = true;
				ediTxt.setText(mascara);
				ediTxt.setSelection(mascara.length());
			}
	
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
			}
	
			public void afterTextChanged(Editable s) {
			}
		};
	}
	
	public static boolean  verificarCPF(String cpf) {
	       String strCpf = cpf;
	        if (strCpf.equals("")) {
	            return false;
	        } 
	        
	        int d1, d2;
	        int digito1, digito2, resto;
	        int digitoCPF;
	        String nDigResult;

	        d1 = d2 = 0;
	        digito1 = digito2 = resto = 0;

	        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
	            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

	            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.  
	            d1 = d1 + (11 - nCount) * digitoCPF;

	            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.  
	            d2 = d2 + (12 - nCount) * digitoCPF;
	        }

	        //Primeiro resto da divisão por 11.  
	        resto = (d1 % 11);

	        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
	        if (resto < 2) {
	            digito1 = 0;
	        } else {
	            digito1 = 11 - resto;
	        }

	        d2 += 2 * digito1;

	        //Segundo resto da divisão por 11.  
	        resto = (d2 % 11);

	        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
	        if (resto < 2) {
	            digito2 = 0;
	        } else {
	            digito2 = 11 - resto;
	        }

	        //Digito verificador do CPF que está sendo validado.  
	        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

	        //Concatenando o primeiro resto com o segundo.  
	        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

	        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.  
	        return nDigVerific.equals(nDigResult);
	    }

}
