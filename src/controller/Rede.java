package controller;

import java.util.List;

import dao.MatchDAO;
import dao.MuralDAO;
import dao.UsuarioDAO;
import helper.Constants;
import model.*;

public class Rede {
    
    private UsuarioDAO dao;
    private MatchDAO matchDAO;
    private MuralDAO muralDAO;

    public Rede() {
        dao = UsuarioDAO.getInstance();
        matchDAO = MatchDAO.getInstance();
        muralDAO = MuralDAO.getInstance();
    }

    public Perfil getUsuario() {
        return dao.getUsuarioLogado();
    }

    public Perfil logar(String login, String senha) {
        return dao.logar(login, senha);
    }

    public void deslogar() {
        dao.deslogar();
    }

    public Perfil criarConta(String login, String senha, String user) {
        return dao.criarConta(false, login, senha, user);
    }

    public Perfil criarContaAdm(String login, String senha, String user) {
        return dao.criarConta(true, login, senha, user);
    }

    public boolean contaExistente(String login) {
        return dao.contaExistente(login);
    }
    
    // Informar qual tipo de Conta 1 - Comum, 2-ADM
    public int tipoConta(String login) {
        Perfil usuario = dao.encontrarUsuarioPeloLogin(login);
        
        if (usuario instanceof PerfilAdm) {
            return Constants.TIPO_CONTA_ADMIN;
        }
        
        return Constants.TIPO_CONTA_NORMAL;
    }

    public boolean editarConta(String senha, String nomeDeUsuario) {
        Perfil usuario = dao.getUsuarioLogado();
        usuario.setSenha(senha);
        usuario.setUser(nomeDeUsuario);

        dao.atualizarUsuario(usuario);
        return true;
    }

    public boolean adicionarAmigo(String login) {
        boolean isOk = validarLogin(login);
        if (!isOk) {
            return false;
        }
        
        Perfil usuario = getUsuario();
        Perfil amigo = dao.encontrarUsuarioPeloLogin(login);
        usuario.addAmigo(amigo);
        dao.atualizarUsuario(usuario);
        return true;
    }

    public boolean enviarMensagem(String login, String message) {
        return enviarMensagem(login, message, null);
    }

    public boolean enviarMensagem(String login, String message, String senha) {
        boolean isOk = validarLogin(login);
        if (!isOk) {
            return false;
        }

        Mensagem mensagem = new Mensagem();
        mensagem.setUsername(login);
        mensagem.setContent(message);
        if (senha != null) {
            mensagem.setSenha(senha);
        }

        Perfil amigo = dao.encontrarUsuarioPeloLogin(login);
        amigo.addMensagem(mensagem);
        dao.atualizarUsuario(amigo);
        return true;
    }

    public Match darMatch(String login) {
        boolean isOk = validarLogin(login);
        if (!isOk) {
            return null;
        }

        Perfil eu = getUsuario();
        Match match = matchDAO.verificarSeExisteMatchDeleParaMim(eu.getLogin(), login);

        if (match == null) {
            // não existe salvo nenhum match com esses 2 usuarios, então cria agora
            return matchDAO.criar(eu.getLogin(), login);
        } else {
            // existe, então atualizo a flag deuMatch dentro desse Modelo
            return matchDAO.deuMatch(match);
        }
    }

    public List<Match> getMatchsUsuarioAtual() {
        Perfil usuario = getUsuario();
        return matchDAO.getMatchsDoUsuario(usuario);
    }

    public Mensagem encontrarMensagemSecreta(String senha) {
        Perfil usuario = getUsuario();
        List<Mensagem> mensagens = usuario.getMensagens();
        
        for (Mensagem mensagem : mensagens) {
            if ( senha.equals(mensagem.getSenha()) ) {
                return mensagem;
            }
        }

        return null;
    }

    public boolean enviarMensagemMural(String login, String message) {
        boolean isOk = validarLogin(login);
        if (!isOk) {
            return false;
        }

        muralDAO.criar(login, message);
        return true;
    }

    public List<Mural> muraisPendentesDoUsuarioAtual() {
        Perfil usuario = getUsuario();
        return muralDAO.muraisPendentesDoUsuarioAtual(usuario.getLogin());
    }

    public List<Mural> muraisAceitos() {
        return muralDAO.muraisAceitos();
    }

    public boolean aceitarMural(Mural mural) {
        return muralDAO.aceitarMural(mural);
    }

    public boolean validarLogin(String login) {
        Perfil amigo = dao.encontrarUsuarioPeloLogin(login);
        if (amigo == null) {
            return false;
        }
        
        Perfil usuario = getUsuario();
        if (amigo.equals(usuario)) {
            return false;
        }

        return true;
    }

    public void editarUsuario(String login, String novoLogin) {
        Perfil usuario = dao.encontrarUsuarioPeloLogin(login);
        usuario.setLogin(novoLogin);
        dao.atualizarUsuario(usuario);
    }

    public void removerUsuario(String login) {
        Perfil usuario = dao.encontrarUsuarioPeloLogin(login);
        dao.removerUsuario(usuario);
    }
}
