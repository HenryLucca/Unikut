package dao;

import java.util.ArrayList;
import java.util.List;

import model.Match;
import model.Perfil;

public class MatchDAO {
    
    private static MatchDAO dao;

    private List<Match> matchs;

    private MatchDAO() {
        matchs = new ArrayList<Match>();
    }

    public static MatchDAO getInstance() {
        if (dao == null) {
            dao = new MatchDAO();
        }

        return dao;
    }
    
    public Match criar(String de, String para) {
        Match novo = new Match(de, para);
        matchs.add(novo);
        return novo;
    }

    public Match verificarSeExisteMatchDeleParaMim(String eu, String amigo) {
        if (matchs.isEmpty()) {
            return null;
        }

        for (Match match : matchs) {
            if (match.getDe().equals(amigo) && match.getPara().equals(eu)) {
                return match;
            }
        }

        return null;
    }

    public Match deuMatch(Match match) {
        match.setDeuMatch(true);

        int indice = matchs.indexOf(match);
        matchs.set(indice, match);

        return match;
    }

    public List<Match> getMatchsDoUsuario(Perfil usuario) {
        List<Match> result = new ArrayList<>();
        String login = usuario.getLogin();

        for (Match match : matchs) {
            if (match.isDeuMatch()) {
                String de = match.getDe();
                String para = match.getPara();

                if (de.equals(login) || para.equals(login)) {
                    result.add(match);
                }
            }
        }

        return result;
    }

}