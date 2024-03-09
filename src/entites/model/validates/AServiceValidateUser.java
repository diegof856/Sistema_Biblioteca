package entites.model.validates;

import entites.model.User;
import entites.model.repository.User_Repository;

public abstract class AServiceValidateUser {
	
	public boolean validateId(int number) {
		String numberString = String.valueOf(Math.abs(number));
	    if (numberString.length() < 3) {
	        System.out.println("O ID deve possuir pelo menos 3 dígitos.");
	        return true;
	    }
		return false;
	}
	public String validateName(String name) {
		for(char c: name.toCharArray()) {
			if(!Character.isLetter(c)) {
				return "Nome inválido!!! (O NOME NÃO DEVE POSSUIR NÚMEROS OU CARACTERES ESPECIAIS)";
			}
		}
		if(name.length() > 4 && !name.isEmpty()) {
			return name;
		}
		
		
		
		return "Nome inválido!!! (O NOME DEVE POSSUIR PELO MENOS 5 CARACTERES E NÃO DEVE SER VAZIO)";
	}
	public boolean validateNumberNewNameOrId(int number) {
		if(number != 1 && number != 2 && number !=3) {
			return false;
		}
		return true;
	}
	public boolean validateOptions(int number) {
		return number >= 1 && number <= 5;
	}
	public String getUserRepositoryName(User_Repository userR){
		StringBuilder names = new StringBuilder();
		for (User user : userR.getUserList()) {
	       names.append(user.getName());
	    }
    
	 return names.toString();
}
	
}
