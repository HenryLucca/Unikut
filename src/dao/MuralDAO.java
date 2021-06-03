package dao;

import java.util.ArrayList;
import java.util.List;

import model.Mural;

public class MuralDAO {
    
    private static MuralDAO dao;

    private List<Mural> murals;

    private MuralDAO() {
        murals = new ArrayList<Mural>();
    }

    public static MuralDAO getInstance() {
        if (dao == null) {
            dao = new MuralDAO();
        }

        return dao;
    }

    public Mural criar(String login, String message) {
        Mural novo = new Mural(login, message);
        murals.add(novo);
        return novo;
    }

    public List<Mural> muraisPendentesDoUsuarioAtual(String login) {
        List<Mural> result = new ArrayList<>();
        for (Mural mural : murals) {
            if (mural.getLogin().equals(login) && !mural.isAceito()) {
                result.add(mural);
            }
        }

        return result;
    }

    public boolean aceitarMural(Mural mural) {
        mural.setAceito(true);

        int indice = murals.indexOf(mural);
        murals.set(indice, mural);

        return true;
    }

    public List<Mural> muraisAceitos() {
        List<Mural> result = new ArrayList<>();
        for (Mural mural : murals) {
            if (mural.isAceito()) {
                result.add(mural);
            }
        }

        return result;
    }
}
