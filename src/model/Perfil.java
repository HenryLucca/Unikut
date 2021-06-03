package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Perfil implements Comparable<Perfil> {
    String login;
    String senha;
    String user;

    List<Match> matchs = new ArrayList<Match>();
    List<Perfil> amigos = new ArrayList<Perfil>();
    List<Mensagem> mensagens = new ArrayList<Mensagem>();

    String Mural;
    String[] MatchMutuo = new String[99];
    String[] solicitações = new String[99];

    public Perfil(String login, String senha, String user) {
        this.login = login;
        this.senha = senha;
        this.user = user;
    }

    public Perfil(Perfil aux) {
        this.login = aux.getLogin();
        this.senha = aux.getSenha();
        this.user = aux.getUser();
    }

    public List<Perfil> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Perfil> amigos) {
        this.amigos = amigos;
    }

    public void addAmigo(Perfil amigo) {
        if (this.amigos == null) {
            setAmigos(new ArrayList<Perfil>());
        }

        amigos.add(amigo);
    }

    public String[] getSolicitações() {
        return solicitações;
    }

    public void setSolicitações(String[] solicitações) {
        this.solicitações = solicitações;
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;
    }

    public void addMatch(Match match) {
        if (this.matchs == null) {
            setMatchs(new ArrayList<Match>());
        }

        matchs.add(match);
    }

    public String[] getMatchMutuo() {
        return MatchMutuo;
    }

    public void setMatchMutuo(String[] MatchMutuo) {
        this.MatchMutuo = MatchMutuo;
    }

    public String getMural() {
        return Mural;
    }

    public void setMural(String Mural) {
        this.Mural = Mural;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public void addMensagem(Mensagem mensagem) {
        if (this.mensagens == null) {
            setMensagens(new ArrayList<Mensagem>());
        }

        this.mensagens.add(mensagem);
    }

    @Override
    public int compareTo(Perfil t) {
        return this.getLogin().compareTo(t.getLogin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Perfil perfil = (Perfil) o;
        return Objects.equals(this.login, perfil.getLogin());
    }

}