package ClaseDate;

import java.sql.Date;

public class DetaliiJucator {
    private int nr_card;
    private String email=null;
    private Date data_nasterii;
    private String gen=null;
    public DetaliiJucator(int nr_card, String email, String data_nasterii, String gen) {
        this.nr_card = nr_card;
        this.email = email;
        this.data_nasterii = Date.valueOf(data_nasterii);
        this.gen = gen;
    }
    public DetaliiJucator(int nr_card, String email, Date data_nasterii, String gen) {
        this.nr_card = nr_card;
        this.email = email;
        this.data_nasterii = data_nasterii;
        this.gen = gen;
    }
    public int getNr_card() {
        return nr_card;
    }
    public String getEmail() {
        return email;
    }
    public String getData_nasterii() {
        return data_nasterii.toString();
    }
    public String getGen() {
        return gen;
    }
}
