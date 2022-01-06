package ClaseDate;

public class Jucator {
    private String nume;
    private int nr_card;
    private String nume_echipa=null;
    public Jucator(String nume, int nr_card) {
        this.nume = nume;
        this.nr_card = nr_card;
    }
    public Jucator(String nume, int nr_card, String nume_echipa) {
        this.nume = nume;
        this.nr_card = nr_card;
        this.nume_echipa = nume_echipa;
    }
    public String getNume() {
        return nume;
    }
    public int getNr_card() {
        return nr_card;
    }
    public String getNume_echipa() {
        return nume_echipa;
    }
}
