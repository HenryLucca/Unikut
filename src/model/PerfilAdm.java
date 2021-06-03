package model;

public class PerfilAdm extends Perfil {

    public PerfilAdm(String login, String senha, String user) {
        super(login, senha, user);
    }

    public PerfilAdm(Perfil aux) {
        super(aux);
    }
     
    //Incompleto
    public boolean editaConta (Perfil conta) {
        if (conta == null) {
            return false;
        } else {
            return true;
        }
    }
}
