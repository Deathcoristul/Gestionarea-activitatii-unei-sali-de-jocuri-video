package ClaseDate;

import java.sql.Timestamp;
public class Achitare {
    private int nr_card;
    private int id_sesiune;
    private Timestamp data_achitarii;
    private int nr_bon;
    private int pret;
    private Timestamp data_finalizare;
    public Achitare(int nr_card, int id_sesiune, int nr_bon, int pret) {
        this.nr_card = nr_card;
        this.id_sesiune = id_sesiune;
        this.nr_bon = nr_bon;
        this.pret = pret;
    }
    public Achitare(int nr_card, int id_sesiune, Timestamp data_achitarii, int nr_bon, int pret, Timestamp data_finalizare) {
        this(nr_card,id_sesiune,nr_bon,pret);
        this.data_achitarii = data_achitarii;
        this.data_finalizare = data_finalizare;
    }
    public Achitare(int nr_card, int id_sesiune, String data_achitarii, int nr_bon, int pret, Timestamp data_finalizare) {
        this(nr_card,id_sesiune,nr_bon,pret);
        this.data_achitarii = Timestamp.valueOf(data_achitarii);
        this.data_finalizare = data_finalizare;
    }
    public Achitare(int nr_card, int id_sesiune, Timestamp data_achitarii, int nr_bon, int pret, String data_finalizare) {
        this(nr_card,id_sesiune,nr_bon,pret);
        this.data_achitarii = data_achitarii;
        this.data_finalizare = Timestamp.valueOf(data_finalizare);
    }
    public Achitare(int nr_card, int id_sesiune, String data_achitarii, int nr_bon, int pret, String data_finalizare) {
        this(nr_card,id_sesiune,nr_bon,pret);
        this.data_achitarii = Timestamp.valueOf(data_achitarii);
        this.data_finalizare = Timestamp.valueOf(data_finalizare);
    }
    public int getNr_card() {
        return nr_card;
    }
    public int getId_sesiune() {
        return id_sesiune;
    }
    public String getData_achitarii() {
        return data_achitarii.toString();
    }
    public int getNr_bon() {
        return nr_bon;
    }
    public int getPret() {
        return pret;
    }
    public String getData_finalizare() {
        return data_finalizare.toString();
    }
}
