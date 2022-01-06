package ClaseDate;

public class TipJoc {
    private String denumire_tip;
    private int id_tip;
    public TipJoc(String denumire_tip, int id_tip) {
        this.denumire_tip = denumire_tip;
        this.id_tip = id_tip;
    }
    public String getDenumire_tip() {
        return denumire_tip;
    }
    public int getId_tip() {
        return id_tip;
    }
}
