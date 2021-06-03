package view;

import java.util.List;
import java.util.Scanner;

import controller.Rede;
import helper.Constants;
import model.Match;
import model.Mensagem;
import model.Mural;
import model.Perfil;

public class Dashboard {

    private Rede unikut;
    private Scanner input;

    public void init(Rede unikut) {
        this.unikut = unikut;
        this.input = new Scanner(System.in);

        int tipoDeConta = unikut.tipoConta(unikut.getUsuario().getLogin());
        int opcao;

        do {

            menu(tipoDeConta);

            opcao = input.nextInt();

            switch (opcao) {
                default:
                    System.out.print("Ainda não implementado.");
                    break;

                case Constants.MENU_NORMAL_LOGOUT:
                    unikut.deslogar();
                    System.out.println("Usuário Deslogado com sucesso.");
                    break;

                case Constants.MENU_NORMAL_EDIT_PROFILE:
                    editProfile();
                    break;

                case Constants.MENU_NORMAL_ADD_FRIEND:
                    addFriend();
                    break;

                case Constants.MENU_NORMAL_SHOW_FRIENDS:
                    showFriends();
                    break;

                case Constants.MENU_NORMAL_SEND_MESSAGE:
                    sendMessage();
                    break;

                case Constants.MENU_NORMAL_SHOW_MESSAGES:
                    showMessages();
                    break;

                case Constants.MENU_NORMAL_ADD_MATCH:
                    addMatch();
                    break;

                case Constants.MENU_NORMAL_SHOW_MATCHS:
                    showMatchs();
                    break;

                case Constants.MENU_NORMAL_SEND_MURAL:
                    sendMural();
                    break;

                case Constants.MENU_NORMAL_SHOW_MURAL:
                    showMural();
                    break;
                
                case Constants.MENU_NORMAL_SHOW_PENDENT_MURAL:
                    showMuralPendentes();
                    break;

                case Constants.MENU_NORMAL_SEND_SECRET_MESSAGE:
                    sendSecretMessage();
                    break;

                case Constants.MENU_NORMAL_SHOW_SECRET_MESSAGES:
                    showSecretMessage();
                    break;

                case Constants.MENU_ADMIN_EDIT_ANOTHER_USER_PROFILE:
                    editAnotherUserProfile();
                    break;

                case Constants.MENU_ADMIN_DELETE_ANOTHER_USER_PROFILE:
                    deleteAnotherUserProfile();
                    break;
            }
        } while (opcao != 0);
        // Deslogou
    }

    public void menu(int tipoDeConta) {
        System.out.println("\n\n--------------------- MENU COMUM -------------------\n\n");
        System.out.println("Olá " + unikut.getUsuario().getUser() + ", seja bem vindo(a)!\n\n");
        System.out.println(Constants.MENU_NORMAL_EDIT_PROFILE + ")  Editar Perfil");
        System.out.println(Constants.MENU_NORMAL_ADD_FRIEND + ")  Adicionar Amigo");
        System.out.println(Constants.MENU_NORMAL_SHOW_FRIENDS + ")  Exibir Amigos");
        System.out.println(Constants.MENU_NORMAL_SEND_MESSAGE + ")  Enviar Mensagem");
        System.out.println(Constants.MENU_NORMAL_SHOW_MESSAGES + ")  Mensagens Recebidas");
        System.out.println(Constants.MENU_NORMAL_ADD_MATCH + ")  Adicionar Match");
        System.out.println(Constants.MENU_NORMAL_SHOW_MATCHS + ")  Verificar Matchs");
        System.out.println(Constants.MENU_NORMAL_SEND_MURAL + ")  Enviar mural para um usuário");
        System.out.println(Constants.MENU_NORMAL_SHOW_MURAL + ")  Exibir mensagens do Mural");
        System.out.println(Constants.MENU_NORMAL_SHOW_PENDENT_MURAL + ") Verificar Mural Pendentes");
        System.out.println(Constants.MENU_NORMAL_SEND_SECRET_MESSAGE + ") Enviar mensagem secreta para um usuário");
        System.out.println(Constants.MENU_NORMAL_SHOW_SECRET_MESSAGES + ") Exibir mensagens secretas");

        if (tipoDeConta == Constants.TIPO_CONTA_ADMIN) {
            System.out.println("\n-----");
            System.out.println(Constants.MENU_ADMIN_EDIT_ANOTHER_USER_PROFILE + ") Editar Perfil de outro usuário");
            System.out.println(Constants.MENU_ADMIN_DELETE_ANOTHER_USER_PROFILE + ") Excluir Perfil de outro usuário");
            System.out.println("-----\n");
        }

        System.out.println(Constants.MENU_NORMAL_LOGOUT + ")  Deslogar");
        System.out.print("\nDigite sua opção: ");
    }

    private void editProfile() {
        System.out.print("Digite sua nova Senha: ");
        String senha = input.nextLine();
        System.out.print("Digite seu novo Usúario: ");
        String nome = input.nextLine();

        unikut.editarConta(senha, nome);
    }

    private void addFriend() {
        input.nextLine();

        System.out.print("Digite o login de seu amigo: ");
        String login = input.nextLine();

        boolean adicionou = unikut.adicionarAmigo(login);
        if (!adicionou) {
            System.out.println("Não foi possível adicionar amizade com o login informado.");
        }
    }

    private void showFriends() {
        System.out.println("Lista de amigos: \n");
        List<Perfil> amigos = unikut.getUsuario().getAmigos();
        for (int i = 0; i < amigos.size(); i++) {
            String login = amigos.get(i).getLogin();
            System.out.println(">> " + login);
        }
    }

    private void sendMessage() {
        input.nextLine();

        System.out.print("Digite o login de quem vai receber a mensagem: ");
        String login = input.nextLine();

        System.out.print("Digite a mensagem que " + login + " irá receber:\n=> ");
        String message = input.nextLine();

        boolean enviou = unikut.enviarMensagem(login, message);
        if (!enviou) {
            System.out.println("Não foi possível enviar essa mensagem para o login informado!");
            return;
        }

        System.out.println("Mensagem enviada com sucesso!\n\n");
    }

    private void showMessages() {
        System.out.println("Mensagens recebidas: \n");
        List<Mensagem> mensagens = unikut.getUsuario().getMensagens();
        for (int i = 0; i < mensagens.size(); i++) {
            Mensagem mensagem = mensagens.get(i);
            if (mensagem.getSenha() == null || mensagem.getSenha().isEmpty()) {
                System.out.println(">> " + mensagem.getUsername() + ": " + mensagem.getContent());
            }
        }
    }

    private void addMatch() {
        input.nextLine();

        System.out.print("Digite o login de quem você quer dar match: ");
        String login = input.nextLine();

        Match match = unikut.darMatch(login);
        if (match == null) {
            System.out.println("Não foi possível dar match para o login informado!");
            return;
        }

        if (match.isDeuMatch()) {
            System.out.println("DEU MATCH!!!\n\n");
        } else {
            System.out.println("Match enviado com sucesso!\n\n");
        }
    }

    private void showMatchs() {
        System.out.println("Matchs: \n");
        List<Match> matchs = unikut.getMatchsUsuarioAtual();
        for (int i = 0; i < matchs.size(); i++) {
            Match match = matchs.get(i);
            System.out.println(">> " + match.getDe() + " S2 " + match.getPara());
        }
    }

    private void sendMural() {
        input.nextLine();

        System.out.print("Digite o login de quem vai receber a mensagem: ");
        String login = input.nextLine();

        System.out.print("Digite a mensagem que " + login + " irá receber:\n=> ");
        String message = input.nextLine();

        boolean enviou = unikut.enviarMensagemMural(login, message);
        if (!enviou) {
            System.out.println("Não foi possível enviar essa mensagem para o login informado!");
            return;
        }

        System.out.println("Mensagem enviada com sucesso!\n\n");
    }

    private void showMuralPendentes() {
        input.nextLine();
        System.out.println("Murais Pendentes: \n");
        List<Mural> murals = unikut.muraisPendentesDoUsuarioAtual();

        for (int i = 0; i < murals.size(); i++) {
            Mural mural = murals.get(i);
            System.out.println("#" + (i+1) + " " + mural.getLogin());
            System.out.println(">> " + mural.getMessage() + "\n");

            int option = 0;
            do {
                System.out.print("Aceitar mensagem? (0) Não (1) Sim \n>> ");
                option = input.nextInt();
            } while (option != 0 && option != 1);
            
            if (option == 1) {
                unikut.aceitarMural(mural);
            }
        }
    }

    private void showMural() {
        System.out.println("Mural: \n");
        List<Mural> murals = unikut.muraisAceitos();
        for (int i = 0; i < murals.size(); i++) {
            Mural mural = murals.get(i);
            System.out.println(">> " + mural.getLogin() + ": " + mural.getMessage());
        }
    }

    private void sendSecretMessage() {
        input.nextLine();

        System.out.print("Digite o login de quem vai receber a mensagem: ");
        String login = input.nextLine();

        System.out.print("Digite a mensagem secreta que " + login + " irá receber:\n=> ");
        String message = input.nextLine();

        System.out.print("Digite a senha para a mensagem: ");
        String senha = input.nextLine();

        boolean enviou = unikut.enviarMensagem(login, message, senha);
        if (!enviou) {
            System.out.println("Não foi possível enviar essa mensagem secreta para o login informado!");
            return;
        }

        System.out.println("Mensagem secreta enviada com sucesso!\n\n");
    }

    private void showSecretMessage() {
        input.nextLine();

        System.out.print("Digite a senha para ler a mensagem secreta: ");
        String senha = input.nextLine();

        Mensagem mensagem = unikut.encontrarMensagemSecreta(senha);
        if (mensagem != null) {
            System.out.println("Mensagem:");
            System.out.println(">> " + mensagem.getUsername() + ": " + mensagem.getContent());
            return;
        }

        System.out.println("Nenhuma mensagem secreta encontrada com a senha informada!\n\n");
    }

    private void editAnotherUserProfile() {
        input.nextLine();

        System.out.print("Digite o login de quem você deseja editar: ");
        String login = input.nextLine();

        boolean loginValidado = unikut.validarLogin(login);
        if (!loginValidado) {
            System.out.println("Não foi possível editar o usuário do login informado!");
            return;
        }

        System.out.print("Digite o novo login desse usuário: ");
        String novoLogin = input.nextLine();

        unikut.editarUsuario(login, novoLogin);
        System.out.println("Usuário alterado com sucesso!\n\n");
    }

    private void deleteAnotherUserProfile() {
        input.nextLine();

        System.out.print("Digite o login de quem você deseja excluir: ");
        String login = input.nextLine();

        boolean loginValidado = unikut.validarLogin(login);
        if (!loginValidado) {
            System.out.println("Não foi possível excluir o usuário do login informado!");
            return;
        }

        int option = 0;
        do {
            System.out.print("Tem certeza que deseja continuar? (0) Não (1) Sim \n=> ");
            option = input.nextInt();
        } while (option != 0 && option != 1);

        if (option == 1) {
            unikut.removerUsuario(login);
            System.out.println("Usuário excluído com sucesso!\n\n");
        }
    }

}
