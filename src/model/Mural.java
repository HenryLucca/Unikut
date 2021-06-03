package model;

public class Mural {

    private String login;
    private String message;
    private boolean isAceito;

    public Mural(String login, String message) { 
        this.login = login;
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAceito() {
        return isAceito;
    }

    public void setAceito(boolean isAceito) {
        this.isAceito = isAceito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Mural mural = (Mural) o;
        return this.getLogin().equals(mural.getLogin()) && this.getMessage().equals(mural.getMessage());
    }
}
