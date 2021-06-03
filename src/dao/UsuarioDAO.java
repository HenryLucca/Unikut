package dao;

import java.util.ArrayList;
import java.util.List;

import model.Perfil;
import model.PerfilAdm;

public class UsuarioDAO {
    
    private static UsuarioDAO dao;

    private List<Perfil> perfis;
    private Perfil perfilLogado; // Qual o Perfil Logadoo

    private UsuarioDAO() {
        perfis = new ArrayList<Perfil>(); // Estipular Limite apenas para simplificar lógica
    }

    public static UsuarioDAO getInstance() {
        if (dao == null) {
            dao = new UsuarioDAO();
        }

        return dao;
    }

    public Perfil getUsuarioLogado() {
        return perfilLogado;
    }
    
    // Lógica de Login
    public Perfil logar(String login, String senha) { // Obrigatoriamente conta existente
        Perfil usuario = encontrarUsuarioPeloLogin(login);
        if (usuario == null) {
            return null;
        }

        if (usuario.getSenha().equals(senha)) {
            perfilLogado = usuario;
            return perfilLogado;
        }

        return null;
    }

    public void deslogar() {
        perfilLogado = null;
    }

    public Perfil criarConta(boolean isAdmin, String login, String senha, String user) {
        Perfil novo = new Perfil(login, senha, user);
        if (isAdmin) {
            novo = new PerfilAdm(login, senha, user);
        }

        perfis.add(novo); // Adicionado no Indice Disponivel
        return novo;
    }

    // Verificar se a Conta Existe a partir do Login
    public boolean contaExistente(String login) {
        if (perfis.isEmpty()) { // Vazio
            return false; // Conta NÃO existente
        }

        Perfil usuario = encontrarUsuarioPeloLogin(login);
        return usuario != null;
    }

    public Perfil encontrarUsuarioPeloLogin(String login) {
        for (Perfil perfil : perfis) {
            if (perfil.getLogin().equals(login)) { // Encontrou a posição da conta
                return perfil;
            }
        }

        return null;
    }

    public Perfil encontrarUsuario(Perfil usuario) {
        for (Perfil perfil : perfis) {
            if (perfil.getLogin().equals(usuario.getLogin())) { // Encontrou a posição da conta
                return perfil;
            }
        }

        return null;
    }

    public void atualizarUsuario(Perfil usuario) {
        int indice = perfis.indexOf(usuario);
        perfis.set(indice, usuario);
    }

    public void removerUsuario(Perfil usuario) {
        int indice = perfis.indexOf(usuario);
        perfis.remove(indice);
    }

}