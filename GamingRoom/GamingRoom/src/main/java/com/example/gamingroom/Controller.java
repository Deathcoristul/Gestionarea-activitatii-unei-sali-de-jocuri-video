package com.example.gamingroom;

import ClaseDate.*;
import com.example.gamingroom.ClaseDateSQL.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class Controller implements Initializable {//Pentru a ne fi mai usor cu atributele pentru tabel si popup curent
                                                    //care nu sunt in fxml,vom implementa Initializable
    @FXML
    public TabPane tabPane;
    //Tabelele din interfata cu utilizatorul
    @FXML
    public TableView tabelJucator;
    @FXML
    public TableView tabelDetails;
    @FXML
    public TableView tabelTipuri;
    @FXML
    public TableView tabelSesiuni;
    @FXML
    public TableView tabelAchitari;
    //Tab-urile din fereastra
    @FXML
    public Tab tabJucator;
    @FXML
    public Tab tabDetails;
    @FXML
    public Tab tabTipuri;
    @FXML
    public Tab tabSesiuni;
    @FXML
    public Tab tabAchitari;
    //pop-up-urile pentru fiecare tabela
    @FXML
    public AnchorPane popUpJucator;
    @FXML
    public AnchorPane popUpDetails;
    @FXML
    public AnchorPane popUpTipuri;
    @FXML
    public AnchorPane popUpSesiuni;
    @FXML
    public AnchorPane popUpAchitari;
    //coloanele din tabele
    public TableColumn<Jucator,String> Jucator_nume;
    public TableColumn<Jucator,Integer> Jucator_numar_card;
    public TableColumn<Jucator,String> Jucator_nume_echipa;
    public TableColumn<DetaliiJucator,Integer> Detalii_numar_card;
    public TableColumn<DetaliiJucator,String> Detalii_email;
    public TableColumn<DetaliiJucator,Date> Detalii_birth;
    public TableColumn<DetaliiJucator,String> Detalii_gen;
    public TableColumn<TipJoc,String> Tip_denumire;
    public TableColumn<TipJoc,Integer> Tip_id;
    public TableColumn<SesiuneJoc,String> Sesiune_numejoc;
    public TableColumn<SesiuneJoc,Integer> Sesiune_id;
    public TableColumn<SesiuneJoc,Integer> Sesiune_id_tip;
    public TableColumn<Achitare,Integer> Achitare_nr_card;
    public TableColumn<Achitare,Integer> Achitare_sesiune;
    public TableColumn<Achitare, Timestamp> Achitare_data_achitarii;
    public TableColumn<Achitare,Integer> Achitare_nr_bon;
    public TableColumn<Achitare,Integer> Achitare_pret;
    public TableColumn<Achitare,Timestamp> Achitare_fin;

    //casetele de text din pop-up-uri
    public TextField FJucator_nume;
    public TextField FJucator_nr_card;
    public TextField FJucator_nume_echipa;
    public ComboBox FDetails_nr_card;
    public TextField FDetails_email;
    public TextField FDetails_birth;
    public TextField FDetails_gen;
    public TextField FTip_denumire;
    public TextField FTip_id;
    public TextField FSesiune_nume_joc;
    public TextField FSesiune_id;
    public ComboBox FSesiune_id_tip;
    public ComboBox FAchitare_nr_card;
    public ComboBox FAchitare_id_sesiune;
    public TextField FAchitare_dataAq;
    public TextField FAchitare_nrbon;
    public ComboBox FAchitare_pret;
    public TextField FAchitare_dataFin;

    public Button clearButton;

    //Lista pentru timp jucat
    public ListView PlayedTimeList;
    public AnchorPane TimePlayedAnchor;


    private boolean adding=true;//variabila booleana pentru add(adevarat) si edit(fals)
    //conexiunile aferente tabelelor
    public JucatorSQL jucatorSQL;
    public DetaliiJucatorSQL detaliiJucatorSQL;
    public TipJocSQL tipJocSQL;
    public SesiuneJocSQL sesiuneJocSQL;
    public AchitareSQL achitareSQL;
    //variabile pentru situatia curenta
    public Tab currTab;
    public AnchorPane currpopUp;
    public TableView currTable;
    public SQLConnection currSQL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currTab=tabJucator;
        currpopUp=popUpJucator;
        currTable=tabelJucator;
        //Setam ValueFactory pentru toate coloanele pentru a afisa pe tabela,altfel nu merge
        Jucator_nume.setCellValueFactory(new PropertyValueFactory<Jucator,String>("nume"));
        Jucator_numar_card.setCellValueFactory(new PropertyValueFactory<Jucator,Integer>("nr_card"));
        Jucator_nume_echipa.setCellValueFactory(new PropertyValueFactory<Jucator,String>("nume_echipa"));
        Detalii_numar_card.setCellValueFactory(new PropertyValueFactory<DetaliiJucator,Integer>("nr_card"));
        Detalii_email.setCellValueFactory(new PropertyValueFactory<DetaliiJucator,String>("email"));
        Detalii_birth.setCellValueFactory(new PropertyValueFactory<DetaliiJucator, Date>("data_nasterii"));
        Detalii_gen.setCellValueFactory(new PropertyValueFactory<DetaliiJucator,String>("gen"));
        Tip_denumire.setCellValueFactory(new PropertyValueFactory<TipJoc,String>("denumire_tip"));
        Tip_id.setCellValueFactory(new PropertyValueFactory<TipJoc,Integer>("id_tip"));
        Sesiune_numejoc.setCellValueFactory(new PropertyValueFactory<SesiuneJoc,String>("denumire_joc"));
        Sesiune_id.setCellValueFactory(new PropertyValueFactory<SesiuneJoc,Integer>("id_sesiune"));
        Sesiune_id_tip.setCellValueFactory(new PropertyValueFactory<SesiuneJoc,Integer>("id_tip"));
        Achitare_nr_card.setCellValueFactory(new PropertyValueFactory<Achitare,Integer>("nr_card"));
        Achitare_sesiune.setCellValueFactory(new PropertyValueFactory<Achitare,Integer>("id_sesiune"));
        Achitare_data_achitarii.setCellValueFactory(new PropertyValueFactory<Achitare,Timestamp>("data_achitarii"));
        Achitare_nr_bon.setCellValueFactory(new PropertyValueFactory<Achitare,Integer>("nr_bon"));
        Achitare_pret.setCellValueFactory(new PropertyValueFactory<Achitare,Integer>("pret"));
        Achitare_fin.setCellValueFactory(new PropertyValueFactory<Achitare,Timestamp>("data_finalizare"));
        FAchitare_pret.setItems(FXCollections.observableArrayList(5,10));
        jucatorSQL=new JucatorSQL();
        detaliiJucatorSQL=new DetaliiJucatorSQL();
        tipJocSQL=new TipJocSQL();
        sesiuneJocSQL=new SesiuneJocSQL();
        achitareSQL=new AchitareSQL();
        currSQL=jucatorSQL;

        try {
            tabelJucator.setItems(jucatorSQL.Select());
            FDetails_nr_card.setItems(jucatorSQL.SelectCards());//Inseram in comboboxul popup-ului valorile din coloana nr_card din Jucator
            FAchitare_nr_card.setItems(jucatorSQL.SelectCards());
            tabelDetails.setItems(detaliiJucatorSQL.Select());
            tabelTipuri.setItems(tipJocSQL.Select());
            FSesiune_id_tip.setItems(tipJocSQL.Selecttypes());
            tabelSesiuni.setItems(sesiuneJocSQL.Select());
            FAchitare_id_sesiune.setItems(sesiuneJocSQL.SelectSessions());
            tabelAchitari.setItems(achitareSQL.Select());
            PlayedTimeList.setItems(achitareSQL.HoursPlayed());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            showMessageDialog(null, "A apărut o eroare la baza de date! Uitați-vă pe SQL Developer și verificați datele!");
        }
    }
    public void updateCombobox() throws SQLException {
        FDetails_nr_card.setItems(jucatorSQL.SelectCards());//Inseram in comboboxul popup-ului valorile din coloana nr_card din Jucator
        FAchitare_nr_card.setItems(jucatorSQL.SelectCards());
        FSesiune_id_tip.setItems(tipJocSQL.Selecttypes());
        FAchitare_id_sesiune.setItems(sesiuneJocSQL.SelectSessions());
    }
    public void OnAdd(ActionEvent actionEvent) {
        adding=true;
        PopUp(currpopUp,true);
    }

    public void OnEdit(ActionEvent actionEvent) {
        adding=false;
        PopUp(currpopUp,true);
    }

    public void OnDelete(ActionEvent actionEvent) {
        if(currTable.getSelectionModel().getSelectedItem() == null){
            showMessageDialog(null, "Nu a fost selectat niciun rand");
            return;
        }
        try {
            currSQL.Delete(currTable.getSelectionModel().getSelectedItem());//apelam sql si stergem elementul selectat
            updateCombobox();
            currTable.setItems(currSQL.Select());//dupa stergere, afisam
            if(currTable.equals(tabelAchitari))
                PlayedTimeList.setItems(achitareSQL.HoursPlayed());
        } catch (SQLException e) {
            showMessageDialog(null, "Atenție la FOREIGN KEY!!");
            e.printStackTrace();
            return;
        }
    }
    public void JucatorTabSelected(Event event) {
        currTab=tabJucator;
        currpopUp=popUpJucator;
        currTable=tabelJucator;
        currSQL=jucatorSQL;
        if(clearButton!=null) {//odata ce incepe aplicatia, daca nu inserez acest if, se va arunca o exceptie de pointer null
            clearButton.setDisable(true);
            clearButton.setVisible(false);
        }
    }
    public void DetailsTabSelected(Event event) {
        currTab=tabDetails;
        currpopUp=popUpDetails;
        currTable=tabelDetails;
        currSQL=detaliiJucatorSQL;
        clearButton.setDisable(true);
        clearButton.setVisible(false);
    }
    public void TipuriTabSelected(Event event) {
        currTab=tabTipuri;
        currpopUp=popUpTipuri;
        currTable=tabelTipuri;
        currSQL=tipJocSQL;
        clearButton.setDisable(true);
        clearButton.setVisible(false);
    }
    public void SesiuniTabSelected(Event event) {
        currTab=tabSesiuni;
        currpopUp=popUpSesiuni;
        currTable=tabelSesiuni;
        currSQL=sesiuneJocSQL;
        clearButton.setDisable(true);
        clearButton.setVisible(false);
    }
    public void AchitariTabSelected(Event event) {
        currTab=tabAchitari;
        currpopUp=popUpAchitari;
        currTable=tabelAchitari;
        currSQL=achitareSQL;
        clearButton.setDisable(false);
        clearButton.setVisible(true);
    }
    public void PopUp(AnchorPane a,boolean b){
        if(b && !adding && currTable.getSelectionModel().getSelectedItem() == null) {
            showMessageDialog(null, "Nu a fost selectat niciun rand");
            return;
        }
        if(!adding && currTable.getSelectionModel().getSelectedItem() != null)
        {//aici vom seta in field-uri valorile randului selectat
            if (currTable.equals(tabelJucator)) {
                Jucator juc= (Jucator) currTable.getSelectionModel().getSelectedItem();
                FJucator_nume.setText(juc.getNume());
                FJucator_nr_card.setText(String.valueOf(juc.getNr_card()));
                FJucator_nume_echipa.setText(juc.getNume_echipa());
            }
            else if (currTable.equals(tabelDetails)) {
                DetaliiJucator djuc= (DetaliiJucator) currTable.getSelectionModel().getSelectedItem();
                FDetails_email.setText(djuc.getEmail());
                FDetails_nr_card.setValue(String.valueOf(djuc.getNr_card()));
                FDetails_gen.setText(djuc.getGen());
                FDetails_birth.setText(djuc.getData_nasterii());
            }
            else if (currTable.equals(tabelTipuri)) {
                TipJoc tj=(TipJoc) currTable.getSelectionModel().getSelectedItem();
                FTip_denumire.setText(tj.getDenumire_tip());
                FTip_id.setText(String.valueOf(tj.getId_tip()));
            }
            else if (currTable.equals(tabelSesiuni)) {
                SesiuneJoc sj=(SesiuneJoc) currTable.getSelectionModel().getSelectedItem();
                FSesiune_nume_joc.setText(sj.getDenumire_joc());
                FSesiune_id.setText(String.valueOf(sj.getId_sesiune()));
                FSesiune_id_tip.setValue(String.valueOf(sj.getId_tip()));
            }
            else if (currTable.equals(tabelAchitari)) {
                Achitare aq=(Achitare) currTable.getSelectionModel().getSelectedItem();
                FAchitare_nr_card.setValue(String.valueOf(aq.getNr_card()));
                FAchitare_id_sesiune.setValue(String.valueOf(aq.getId_sesiune()));
                FAchitare_dataAq.setText(aq.getData_achitarii().substring(0,aq.getData_achitarii().length()-2));
                FAchitare_nrbon.setText(String.valueOf(aq.getNr_bon()));
                FAchitare_pret.setValue(String.valueOf(aq.getPret()));
                FAchitare_dataFin.setText(aq.getData_finalizare().substring(0,aq.getData_finalizare().length()-2));
            }
        }
        tabPane.setDisable(b);//sa nu mai umblam la fereastra principala cat timp inseram/editam
        a.setVisible(b);
        a.setDisable(!b);
    }

    public void OnOK(ActionEvent actionEvent) {
        if(adding)//add
        {
            try {

                if (currTable.equals(tabelJucator)) {
                    Jucator juc=new Jucator(FJucator_nume.getText(),
                            Integer.parseInt(FJucator_nr_card.getText()),
                            FJucator_nume_echipa.getText());
                    jucatorSQL.Insert(juc);//apelam conexiunea si inseram
                }
                else if (currTable.equals(tabelDetails)) {
                    DetaliiJucator dej= new DetaliiJucator(
                            Integer.parseInt(String.valueOf(FDetails_nr_card.getValue())),
                            FDetails_email.getText(),
                            FDetails_birth.getText(),
                            FDetails_gen.getText()
                    );
                    detaliiJucatorSQL.Insert(dej);
                }
                else if (currTable.equals(tabelTipuri)) {
                    TipJoc tip;
                    if(FTip_id.getText()=="")
                        tip = new TipJoc(
                            FTip_denumire.getText(),
                            0);
                    else
                        tip=new TipJoc(
                                FTip_denumire.getText(),
                                Integer.parseInt(FTip_id.getText()));
                    tipJocSQL.Insert(tip);
                }
                else if (currTable.equals(tabelSesiuni)) {
                    SesiuneJoc sj;
                    if(FSesiune_id.getText()=="")
                    {
                        sj=new SesiuneJoc(
                                FSesiune_nume_joc.getText(),
                                0,
                                Integer.parseInt(String.valueOf(FSesiune_id_tip.getValue())));
                    }
                    else
                    {
                        sj=new SesiuneJoc(
                            FSesiune_nume_joc.getText(),
                            Integer.parseInt(FSesiune_id.getText()),
                            Integer.parseInt(String.valueOf(FSesiune_id_tip.getValue())));
                    }
                    sesiuneJocSQL.Insert(sj);
                }
                else if (currTable.equals(tabelAchitari)) {
                    Achitare aq;
                    int x;//in caz de dorim autoincrement pentru a nu scrie cod lung
                    if(FAchitare_nrbon.getText()=="")
                        x=0;
                    else
                        x=Integer.parseInt(FAchitare_nrbon.getText());
                    if(FAchitare_dataAq.getText()!="" && FAchitare_dataFin.getText()!="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                FAchitare_dataAq.getText(),
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                FAchitare_dataFin.getText()
                        );
                    else if(FAchitare_dataAq.getText()=="" && FAchitare_dataFin.getText()!="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                "0001-01-01 00:00:00.0",
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                FAchitare_dataFin.getText()
                        );
                    else if(FAchitare_dataAq.getText()!="" && FAchitare_dataFin.getText()=="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                FAchitare_dataAq.getText(),
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                "0001-01-01 00:00:00.0"
                        );
                    else
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                "0001-01-01 00:00:00.0",
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                "0001-01-01 00:00:00.0"
                        );
                    achitareSQL.Insert(aq);
                }
                updateCombobox();
                clearFields();//curatam casetele text
                currTable.setItems(currSQL.Select());//dupa inserare,afisam
            }
            catch (SQLException e)
            {
                showMessageDialog(null, "A apărut o eroare la baza de date! Uitați-vă pe SQL Developer și verificați datele!\nAtenție mare la constrângeri!!!");
                return;
            }
            catch(NumberFormatException e)
            {
                showMessageDialog(null, "S-a introdus o valoare nulă sau incorectă pe o casetă de text unde trebuie număr.");
                return;
            }
            catch(IllegalArgumentException e)
            {
                showMessageDialog(null, "Data este introdusă incorect! Formatul corect este:YYYY-MM-DD [HH24:MI:SS pentru Achitare] !");
                return;
            }
        }
        else//edit
        {
            try {
                if (currTable.equals(tabelJucator)) {
                    Jucator juc=new Jucator(FJucator_nume.getText(),
                            Integer.parseInt(FJucator_nr_card.getText()),
                            FJucator_nume_echipa.getText());
                    jucatorSQL.Update((Jucator) currTable.getSelectionModel().getSelectedItem(),juc);//apelam conexiunea si editam
                }
                else if (currTable.equals(tabelDetails)) {
                    DetaliiJucator dej= new DetaliiJucator(
                            Integer.parseInt(String.valueOf(FDetails_nr_card.getValue())),
                            FDetails_email.getText(),
                            FDetails_birth.getText(),
                            FDetails_gen.getText()
                    );
                    detaliiJucatorSQL.Update((DetaliiJucator) currTable.getSelectionModel().getSelectedItem(),dej);//apelam conexiunea si editam
                }
                else if (currTable.equals(tabelTipuri)) {
                    TipJoc tip;
                    if(FTip_id.getText()=="")
                        tip = new TipJoc(
                                FTip_denumire.getText(),
                                0);
                    else
                        tip=new TipJoc(
                                FTip_denumire.getText(),
                                Integer.parseInt(FTip_id.getText()));
                    tipJocSQL.Update((TipJoc) currTable.getSelectionModel().getSelectedItem(),tip);//apelam conexiunea si inseram
                }
                else if (currTable.equals(tabelSesiuni)) {
                    SesiuneJoc sj;
                    if(FSesiune_id.getText()=="")
                    {
                        sj=new SesiuneJoc(
                                FSesiune_nume_joc.getText(),
                                0,
                                Integer.parseInt(String.valueOf(FSesiune_id_tip.getValue())));
                    }
                    else
                    {
                        sj=new SesiuneJoc(
                                FSesiune_nume_joc.getText(),
                                Integer.parseInt(FSesiune_id.getText()),
                                Integer.parseInt(String.valueOf(FSesiune_id_tip.getValue())));
                    }
                    sesiuneJocSQL.Update((SesiuneJoc) currTable.getSelectionModel().getSelectedItem(),sj);//apelam conexiunea si inseram
                }
                else if (currTable.equals(tabelAchitari)) {
                    Achitare aq;
                    int x;//in caz de dorim autoincrement
                    if(FAchitare_nrbon.getText()=="")
                        x=0;
                    else
                        x=Integer.parseInt(FAchitare_nrbon.getText());
                    if(FAchitare_dataAq.getText()!="" && FAchitare_dataFin.getText()!="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                FAchitare_dataAq.getText(),
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                FAchitare_dataFin.getText()
                        );
                    else if(FAchitare_dataAq.getText()=="" && FAchitare_dataFin.getText()!="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                "0001-01-01 00:00:00.0",
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                FAchitare_dataFin.getText()
                        );
                    else if(FAchitare_dataAq.getText()!="" && FAchitare_dataFin.getText()=="")
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                FAchitare_dataAq.getText(),
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                "0001-01-01 00:00:00.0"
                        );
                    else
                        aq=new Achitare(
                                Integer.parseInt(String.valueOf(FAchitare_nr_card.getValue())),
                                Integer.parseInt(String.valueOf(FAchitare_id_sesiune.getValue())),
                                "0001-01-01 00:00:00.0",
                                x,
                                Integer.parseInt(String.valueOf(FAchitare_pret.getValue())),
                                "0001-01-01 00:00:00.0"
                        );
                    achitareSQL.Update((Achitare) currTable.getSelectionModel().getSelectedItem(),aq);//apelam conexiunea si inseram
                }
                updateCombobox();
                clearFields();//curatam casetele text
                currTable.setItems(currSQL.Select());//dupa inserare,afisam

            }
            catch (SQLException e)
            {
                showMessageDialog(null, "A apărut o eroare la baza de date! Uitați-vă pe SQL Developer și verificați datele!\nAtenție mare la constrângeri!!!");
                return;
            }
            catch(NumberFormatException e)
            {
                showMessageDialog(null, "S-a introdus o valoare nulă sau incorectă pe o casetă de text unde trebuie număr.");
                return;
            }
            catch(IllegalArgumentException e)
            {
                showMessageDialog(null, "Data este introdusă incorect! Formatul corect este:YYYY-MM-DD [HH24:MI:SS pentru Achitare] !");
                return;
            }
        }
        PopUp(currpopUp,false);
    }
    public void OnCancel(ActionEvent actionEvent) {
        clearFields();
        PopUp(currpopUp,false);
    }
    public void clearFields()
    {
        //curatam casetele text
        FJucator_nume.clear();
        FJucator_nr_card.clear();
        FJucator_nume_echipa.clear();

        FDetails_email.clear();
        FDetails_birth.clear();
        FDetails_gen.clear();
        FTip_denumire.clear();
        FTip_id.clear();
        FSesiune_nume_joc.clear();
        FSesiune_id.clear();
        FAchitare_dataAq.clear();
        FAchitare_nrbon.clear();
        FAchitare_dataFin.clear();

    }

    public void OnCommit(ActionEvent actionEvent) throws SQLException {
        SQLConnection.commit();
    }

    public void OnRollback(ActionEvent actionEvent) throws SQLException {
        SQLConnection.rollback();
        tabelJucator.setItems(jucatorSQL.Select());
        FDetails_nr_card.setItems(jucatorSQL.SelectCards());//Inseram in comboboxul popup-ului valorile din coloana nr_card din Jucator
        FAchitare_nr_card.setItems(jucatorSQL.SelectCards());
        tabelDetails.setItems(detaliiJucatorSQL.Select());
        tabelTipuri.setItems(tipJocSQL.Select());
        FSesiune_id_tip.setItems(tipJocSQL.Selecttypes());
        tabelSesiuni.setItems(sesiuneJocSQL.Select());
        FAchitare_id_sesiune.setItems(sesiuneJocSQL.SelectSessions());
        tabelAchitari.setItems(achitareSQL.Select());
    }

    public void OnTimePlayed(ActionEvent actionEvent) {//pt a deschide fereastra de informatii
        try {
            PlayedTimeList.setItems(achitareSQL.HoursPlayed());
            TimePlayedAnchor.setDisable(false);
            TimePlayedAnchor.setVisible(true);
            tabPane.setDisable(true);
        } catch (SQLException e) {
            showMessageDialog(null, "A apărut o eroare la baza de date! Uitați-vă pe SQL Developer și verificați datele!\nProbabil nu există înregistrări!!!");
            e.printStackTrace();
        }
    }

    public void OnClear(ActionEvent actionEvent){
        try {
            achitareSQL.TimeExpiredDelete();
            tabelAchitari.setItems(achitareSQL.Select());
        } catch (SQLException e) {
            showMessageDialog(null, "A apărut o eroare la baza de date! Uitați-vă pe SQL Developer și verificați datele!\nProbabil nu există înregistrări!!!");
            e.printStackTrace();
        }
    }

    public void OnClose(ActionEvent actionEvent) {//pt a inchide fereastra de informatii
        TimePlayedAnchor.setDisable(true);
        TimePlayedAnchor.setVisible(false);
        tabPane.setDisable(false);
    }
}