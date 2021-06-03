/*
Falta Implementar :
- Edição que um ADM pode fazer (Editar qualquer perfil)
- Match e Solicitação de Match
- Envio de Recados e Mensagem Secreta
- Usar Threats no código
*/
package view;
import java.util.Scanner;
import controller.Rede;

public class Aplicacao {
    
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int resp,opcao, tipoDeConta;
        // Resp = Opção no menun principal, Opcao = Opcao após logado
        String login,senha,user;
        Rede unikut = new Rede();
        
        
        System.out.print("Rede Social Unikut");
        
        do {
            menu();
            resp = input.nextInt();
            switch (resp) {
                default : //Opção invalida
                    System.out.print("Ainda não implementado.");
                    break;
                case 0: //Encerrar Programa
                    System.out.print("Encerrando...");
                    break;
                case 1: // Cadastrar Perfil
                    input.nextLine();
                    System.out.println("Cadastro de Perfil.");
                    System.out.print("Digite o Login do Usuário\n =>");
                     login = input.nextLine();
                    if ( unikut.contaExistente(login) ) { //Conta Já Existente
                        System.out.print("Login Já utilizado, cadastro cancelado");
                    } else {
                        // Login disponível para cadastro
                        System.out.print("Digite a Senha do Usuário\n =>");
                         senha = input.nextLine();
                        System.out.print("Digite o Nome de Usuário\n => ");
                         user = input.nextLine();
                        
                         // Se vai ser conta ADM ou conta Comum
                         System.out.println("Digite o tipo de conta");
                          System.out.println("1 - Para Conta Comum");
                           System.out.print("2 - Para Conta ADM\n =>");
                           int tipo = input.nextInt();
                        
                        //Informando que criou a conta e seu índice
                        if (tipo == 1) {
                            unikut.criarConta(login,senha,user);
                            System.out.print("Conta Comum criada!");
                        } else if (tipo == 2) {
                            unikut.criarContaAdm(login, senha, user);
                            System.out.print("Conta Admin criada!");
                        } else {
                            System.out.print("Opção inválida.");
                        }
                    }
                    break;
                case 2: // Logar
                    input.nextLine();
                    System.out.print("Digite o Login\n => ");
                     login = input.nextLine();
                    System.out.print("Digite a Senha\n =>");
                     senha = input.nextLine();
                     if (unikut.contaExistente(login)) { //Verificar se a conta existe
                         // Existe, agora verificar se o Login confere com a Senha
                         if (unikut.logar(login, senha)) { 
                             // Confere
                             System.out.println("Login Efetuado");
                             tipoDeConta = unikut.tipoConta(login);
                             
                                 
                                 //Logado
                                 do {
                                     menuComum();
                                     if (tipoDeConta== 2) {
                                         System.out.print("10 - Editar Perfil de outro usuário");
                                     }
                                     System.out.print("0 - Deslogar \n=>");
                                     opcao = input.nextInt();
                                     if (opcao == 10 && tipoDeConta == 1) {
                                         while (opcao == 10) {
                                         System.out.print("Ainda não implementado.");
                                         System.out.print("Digite sua opção \n => ");
                                         opcao = input.nextInt();
                                         }
                                     }
                                     switch (opcao) {
                                         default :
                                             System.out.print("Ainda não implementado.");
                                             break;
                                         case 0:
                                             unikut.deslogar();
                                             System.out.print("Usuário Deslogado com sucesso.");
                                         case 1:
                                             input.nextLine();
                                             System.out.print("Digite sua nova Senha\n => ");
                                             String s = input.nextLine();
                                             System.out.print("Digite seu novo Usúario\n => ");
                                             String u = input.nextLine();
                                             unikut.editarConta(s, u);
                                             System.out.print("Dados Alterados!");
                                             break;
                                         case 2:
                                             input.nextLine();
                                             System.out.print("Digite o Usuario que deseja");
                                             
                                     }
                                 }while(opcao != 0);
                                 //Deslogou
                             
                                                        
                                                        
                         } else {
                             // Não confere
                             System.out.print(" Senha inválida. Login NÃO efetuado");
                         }
                     } else { // Conta não existe
                         System.out.print("Conta Inexistente! Login não efetuado");
                     }
            }
            
        } while(resp != 0);
        //Fim do programa
        System.out.print("Adeus");
        input.close(); // Encerrar Scanner
    }
    public static void menu() {
        System.out.print(  " > Menu <                          \n"      +
                           " 1 – Cadastrar um novo Perfil      \n"      +
                           " 2 – Fazer Login                   \n"      +
                           " 0 - Encerrar programa             \n"      +
                           " Digite sua opção \n => "                    );
    }
    public static void menuComum() {
        System.out.print("> Menu Comum < \n"        +
                         " 1 - Editar Perfil                \n"      + //Falta implementar
                         " 2 - Adicionar Amigo              \n"      + //Falta implementar, Colo
                         " 3 - Exibir Amigos                \n"      + // Falta implementar, Exibição Simples
                         " 4 - Enviar Mensagem              \n"      + //Falta Implementar, Pedir qual Usuário e enviar mensagem
                         " 5 - Mensagens Recebidas          \n"      + //Falta Implementar, Porém apenas Exibir as Msg no vetor em Perfil
                         " 6 - Adicionar Match              \n"      + //Falta Implementar
                         " 7 - Verificar Match's            \n"      + // Falta Implementar, Porém apenas Exibir os Matchs no vetor em Perfil
                         " 8 - Exibir Mural de um usuário   \n"      + // Falta Implementar    
                         " 9 - Enviar Mensagem Secreta      \n");
    }
    public static void menuAdm (){
        
    }
}

