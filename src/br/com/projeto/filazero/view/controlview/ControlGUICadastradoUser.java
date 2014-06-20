package br.com.projeto.filazero.view.controlview;


import br.com.projeto.filazero.model.Cliente;
import br.com.projeto.filazero.model.Facade;

public  class ControlGUICadastradoUser {
	private Facade facade;
	private Cliente user;
	
	public ControlGUICadastradoUser(){
		facade = new Facade();
	}
	
	//metodos para validar entradas do usuario no cadastro
	public String testarCpf(String cpf){		
				
		System.out.println(cpf);
		if(!Masks.verificarCPF(cpf)){
			return "CPF invalido";
		}
		
		try{
			user = facade.getUsuario(cpf);
			System.out.println("...");
			System.out.println(user.getCpf());
			if(user!=null){
				if(!user.getLogin().equals(""))
					return "Usuario ja cadastrado";
				else
					return "Usuario cadastrado, mas nao possui login";
			}
		}catch(Exception e){
			
		}		
		return "Ok";
	}
	
	public  String testarLogin(String l){		
		String pattern = "[a-zA-Z_0-9]*";
		
		if(l.toString().equals(""))
			return "Seu login deve conter no minimo 6";		
		
		if(l.toString().length()<6 && l.toString().length()>12)
			return "Seu login deve conter no minimo 6 e no maximo 12 caracteres";
		
		if(!(l.matches(pattern)))
			return "Seu login nao dever conter caracteres especiais";
		
		//verifica se ja tem cliente como o mesmo login
		try {
			user = facade.getUsuario(l);
			if(user!=null)
				return "Login ja cadastrado";
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
			
		return "Ok";
	}
	
	public String testarSenha(String s){
		if(s.toString().length()<6)
			return "Sua senha deve conter no minimo 6 caracteres";
		
		return "Ok";
	}
	
	public  String testarNomeUser(String n){
		String pattern = "[a-z A-Z]*";
		
		if(n.toString().length()==0)
			return "Seu nome deve conter no minimo 5 caracteres";
		
		if(!(n.matches(pattern)))
			return "Seu nome nao dever conter caracteres especiais ou numeros";	
		
		return "Ok";	
	}
	
	public boolean testarEmail(String n){
		//se nao conter arroba
		if(!(n.contains("@")))
			return false;		
		
		return true;		
	}
	
	public boolean testarTelefone(String t){
		if(t.toString().length()!=13)
			return false;	
		
		return true;
	}
	
	
	

	
	
	
}
