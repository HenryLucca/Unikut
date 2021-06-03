package view;

import java.util.Scanner;

import controller.Rede;
import helper.Constants;
import model.Perfil;

public class Home {

    private Rede unikut;
    private Scanner input;

    public void init(Rede unikut) {
        this.unikut = unikut;
        this.input = new Scanner(System.in);

        int option;

        do {

            menu();

            option = input.nextInt();
            switch (option) {

                default:
                    System.out.println("\n\nAinda não implementado.");
                    break;

                case Constants.MENU_EXIT:
                    System.out.print("\n\nEncerrando... ");
                    break;

                case Constants.MENU_REGISTER:
                    register();
                    break;

                case Constants.MENU_LOGIN:
                    login();

            }

        } while (option != 0);

    }

    public void menu() {
        System.out.println("\n\n--------------------- MENU -------------------\n\n");
        System.out.println(Constants.MENU_REGISTER + ") Cadastrar um novo Perfil");
        System.out.println(Constants.MENU_LOGIN + ") Fazer Login");
        System.out.println(Constants.MENU_EXIT + ") Encerrar programa");
        System.out.print("\nDigite sua opção: ");
    }

    public void register() {
        System.out.println("\n\n--------------------- CADASTRO -------------------\n\n");
        input.nextLine();
        System.out.print("Digite o Login do Usuário: ");
        String login = input.nextLine();

        if (unikut.contaExistente(login)) { // Conta Já Existente
            System.out.print("Login Já utilizado, cadastro cancelado");
            return;
        }

        // Login disponível para cadastro
        System.out.print("Digite a Senha do Usuário: ");
        String senha = input.nextLine();
        System.out.print("Digite o Nome de Usuário: ");
        String user = input.nextLine();
        if (user.isEmpty()) {
            user = "Convidado";
        }

        // Se vai ser conta ADM ou conta Comum
        System.out.println("Digite o tipo de conta: ");
        System.out.println(Constants.TIPO_CONTA_NORMAL + ") Para Conta Comum");
        System.out.println(Constants.TIPO_CONTA_ADMIN + ") Para Conta ADM");
        System.out.print("\n=> ");
        int tipo = input.nextInt();

        switch (tipo) {
            case Constants.TIPO_CONTA_NORMAL:
                unikut.criarConta(login, senha, user);
                System.out.print("Conta Comum criada!");
                break;

            case Constants.TIPO_CONTA_ADMIN:
                unikut.criarContaAdm(login, senha, user);
                System.out.print("Conta Admin criada!");
                break;
            default:
                System.out.print("Opção inválida.");
                break;
        }
    }

    public void login() {
        System.out.println("\n\n--------------------- LOGIN -------------------\n\n");
        input.nextLine();
        System.out.print("Digite o Login: ");
        String login = input.nextLine();
        System.out.print("Digite a Senha: ");
        String senha = input.nextLine();

        // Verificar se a conta NÃO existe
        if (!unikut.contaExistente(login)) {
            System.out.print("Conta Inexistente! Login não efetuado");
            return;
        }

        Perfil usuario = unikut.logar(login, senha);
        if (usuario == null) {
            System.out.print("Login ou Senha inválida. Login NÃO efetuado");
            return;
        }

        System.out.println("Login Efetuado");

        Dashboard dashboard = new Dashboard();
        dashboard.init(unikut);
    }

}
