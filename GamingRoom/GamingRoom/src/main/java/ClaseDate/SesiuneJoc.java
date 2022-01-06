package ClaseDate;

public class SesiuneJoc {
    private String denumire_joc;
    private int id_sesiune;
    private int id_tip;
    public SesiuneJoc(String denumire_joc, int id_sesiune, int id_tip) {
        this.denumire_joc = denumire_joc;
        this.id_sesiune = id_sesiune;
        this.id_tip = id_tip;
    }
    public String getDenumire_joc() {
        return denumire_joc;
    }
    public int getId_sesiune() {
        return id_sesiune;
    }
    public int getId_tip() {
        return id_tip;
    }
}
