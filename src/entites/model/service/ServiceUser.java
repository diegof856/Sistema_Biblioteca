package entites.model.service;

import java.util.Scanner;

import entites.model.User;
import entites.model.Interface.I_ClassValidateChar;
import entites.model.Interface.I_User;
import entites.model.repository.User_Repository;
import entites.model.validates.AServiceValidateUser;

public class ServiceUser extends AServiceValidateUser implements I_User, I_ClassValidateChar {

    @Override
    public void register(Scanner sc, User_Repository userRepository) {
        System.out.println("Cadastre-se para ter acesso aos nossos livros");
        String name;
        int id;

        char response = 0;

        do {
            System.out.print("Digite o seu nome: ");
            name = sc.nextLine().toLowerCase();
            if (!validateName(name).equals(name)) {
                System.out.println(validateName(name));
            }
        } while (!validateName(name).equals(name));

        do {
            System.out.print("Digite agora o seu ID: ");
            while (!sc.hasNextInt()) {
                System.out.print("Por favor, digite um número inteiro para o ID: ");
                sc.next();
            }

            id = sc.nextInt();
            sc.nextLine();

        } while (validateId(id));

        do {
            System.out.print("Deseja alterar algo no cadastro? (s/n): ");
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Digite s ou n");
                continue;
            }
            response = Character.toLowerCase(input.charAt(0));

        } while (validateChar(response));

        if (response == 's') {
            toAlter(name, id, sc, userRepository);
        } else {
        	
            toSendUser(name, id, userRepository);
        }
    }

    // Alterar o nome ou id ou ambos
    public void toAlter(String name, int id, Scanner sc, User_Repository userRepository) {

        int answerChange;

        do {
            System.out.println("O que você deseja alterar:");
            System.out.println("1 - Seu nome");
            System.out.println("2 - Seu ID");
            System.out.println("3 - Ambos");
            System.out.print("Digite sua opção: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, digite um número inteiro para a opção desejada: ");
                sc.next();
            }
            answerChange = sc.nextInt();
            sc.nextLine();
            if (!validateNumberNewNameOrId(answerChange)) {
                System.out.println("Digite um valor entre 1 e 3");
            }
        } while (!validateNumberNewNameOrId(answerChange));

        int x = 0;
        while (x == 0) {
            switch (answerChange) {
                case 1:
                    do {
                        System.out.print("Digite o novo nome: ");
                        name = sc.nextLine().toLowerCase();
                        if (!validateName(name).equals(name)) {
                            System.out.println(validateName(name));
                        }
                    } while (!validateName(name).equals(name));
                    break;
                case 2:
                    do {
                        System.out.print("Digite o novo ID: ");
                        while (!sc.hasNextInt()) {
                            System.out.print("Por favor, digite um número inteiro para o ID: ");
                            sc.next();
                        }
                        id = sc.nextInt();
                        sc.nextLine();
                    } while (validateId(id));
                    break;
                case 3:
                    do {
                        System.out.print("Digite o seu novo nome: ");
                        name = sc.nextLine().toLowerCase();
                        if (!validateName(name).equals(name)) {
                            System.out.println(validateName(name));
                        }
                    } while (!validateName(name).equals(name));

                    do {
                        System.out.print("Digite o seu novo ID: ");
                        while (!sc.hasNextInt()) {
                            System.out.print("Por favor, digite um número inteiro para o ID: ");
                            sc.next();
                        }
                        id = sc.nextInt();
                        sc.nextLine();
                    } while (validateId(id));
                    break;

            }

            System.out.print("Você confirma esse nome " + name + " e ID " + id + " (s/n): ");
            char responseConfirmNameAndId = Character.toLowerCase(sc.nextLine().charAt(0));

            if (validateChar(responseConfirmNameAndId)) {
                System.out.println("Digite s ou n!!!");
            } else if (responseConfirmNameAndId == 's') {
                toSendUser(name, id, userRepository);
                x++;
            }
        }
    }

    // Envia para o repositório
    public void toSendUser(String name, int id, User_Repository user_Repository) {
        User user = new User(name, id);
        user_Repository.addList(user);
        
    }

    @Override
    public int options(Scanner sc, User_Repository userR) {

        int response;

        do {
            System.out.println("Olá " +getUserRepositoryName(userR) + ", qual opção você deseja:");
            System.out.println("1 - Ver a nossa estante de livros e pegar algum(s) emprestados");
            System.out.println("2 - Remover algum(s) livro(s) da estante");
            System.out.println("3 - Adicionar algum(s) livro(s) à estante");
            System.out.println("4 - Ver lista de livros atualmente emprestados");
            System.out.println("5 - Sair");
            System.out.print("Digite sua opção: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, digite um número inteiro para a opção desejada: ");
                sc.next();
            }

            response = sc.nextInt();
            sc.nextLine();
            if (!validateOptions(response)) {
                System.out.println("Digite um valor entre 1 e 5");
            }
        } while (!validateOptions(response));

        return response;
    }

    @Override
    public boolean validateChar(char item) {
        return item != 's' && item != 'n';
    }
}
