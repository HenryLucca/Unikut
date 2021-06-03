package model;

public class Match {

    private String de;
    private String para;
    private boolean deuMatch;

    public Match(String de, String para) {
        this.de = de;
        this.para = para;
        this.deuMatch = false;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public boolean isDeuMatch() {
        return deuMatch;
    }

    public void setDeuMatch(boolean deuMatch) {
        this.deuMatch = deuMatch;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Match match = (Match) o;
        return this.de.equals(match.getDe()) && this.para.equals(match.getPara());
    }
}
