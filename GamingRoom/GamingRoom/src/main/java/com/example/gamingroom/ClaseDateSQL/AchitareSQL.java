package com.example.gamingroom.ClaseDateSQL;

import ClaseDate.Achitare;
import com.example.gamingroom.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AchitareSQL extends SQLConnection<Achitare> {
    @Override
    public String getColumns() {
        return "nr_card,id_sesiune,data_achitarii,nr_bon,pret,data_finalizare";
    }
    @Override
    public void Insert(Achitare Obj) throws SQLException {
        String query = "INSERT INTO achitare("+getColumns()+") VALUES("+
                Obj.getNr_card()+","+Obj.getId_sesiune();
        if(!Obj.getData_achitarii().equals("0001-01-01 00:00:00.0"))
                query+=",TO_DATE('"+Obj.getData_achitarii().substring(0,Obj.getData_achitarii().length()-2)+"','YYYY-MM-DD HH24:MI:SS')," ;
        else
            query+=",SYSDATE," ;
        if(Obj.getNr_bon()!=0)
            query+= Obj.getNr_bon()+","+Obj.getPret();
        else
            query+= "achitare_nr_bon_seq.nextval,"+Obj.getPret();
        if(!Obj.getData_finalizare().equals("0001-01-01 00:00:00.0"))
            query+=",TO_DATE('"+Obj.getData_finalizare().substring(0,Obj.getData_finalizare().length()-2)+"','YYYY-MM-DD HH24:MI:SS'))";
        else {//folosim substring ca sa nu ne mai complicam cu nanosecundele
            if(!Obj.getData_achitarii().equals("0001-01-01 00:00:00.0"))
                query += ",TO_DATE('"+Obj.getData_achitarii().substring(0,Obj.getData_achitarii().length()-2)+"','YYYY-MM-DD HH24:MI:SS')+"+Obj.getPret()+"/120)";
            else
                query += ",SYSDATE+" + Obj.getPret() + "/120)";
        }
        if(Obj.getData_achitarii().equals("0001-01-01 00:00:00.0") && !Obj.getData_finalizare().equals("0001-01-01 00:00:00.0"))
        {
            showMessageDialog(null, "Dacă introduceți data de finalizare, introduceți și data de achitare!!!");
            return;
        }
        stmt.executeUpdate(query);
    }
    @Override
    public void Update(Achitare WhereObj, Achitare Obj) throws SQLException {
        String query="UPDATE achitare SET nr_card="+Obj.getNr_card()+", id_sesiune="+Obj.getId_sesiune()+
                ", data_achitarii=";
        if(!Obj.getData_achitarii().equals("0001-01-01 00:00:00.0"))
            query+="TO_DATE('"+Obj.getData_achitarii().substring(0,Obj.getData_achitarii().length()-2)+"','YYYY-MM-DD HH24:MI:SS'), nr_bon=";
        else
            query+="SYSDATE, nr_bon=";
        if(Obj.getNr_bon()!=0)
            query+=Obj.getNr_bon()+ ", pret="+Obj.getPret()+", data_finalizare=";
        else
            query+="achitare_nr_bon_seq.nextval, pret="+Obj.getPret()+", data_finalizare=";
        if(!Obj.getData_finalizare().equals("0001-01-01 00:00:00.0"))
            query+="TO_DATE('"+Obj.getData_finalizare().substring(0,Obj.getData_finalizare().length()-2)+ "','YYYY-MM-DD HH24:MI:SS') WHERE nr_bon="+WhereObj.getNr_bon();
        else
        {
            if(!Obj.getData_achitarii().equals("0001-01-01 00:00:00.0"))
                query += "TO_DATE('"+Obj.getData_achitarii().substring(0,Obj.getData_achitarii().length()-2)+"','YYYY-MM-DD HH24:MI:SS')+"+Obj.getPret()+"/120 WHERE nr_bon="+WhereObj.getNr_bon();
            else
                query += "SYSDATE+"+Obj.getPret()+"/120 WHERE nr_bon="+WhereObj.getNr_bon();
        }
        if(Obj.getData_achitarii().equals("0001-01-01 00:00:00.0") && !Obj.getData_finalizare().equals("0001-01-01 00:00:00.0"))
        {
            showMessageDialog(null, "Dacă introduceți data de finalizare, introduceți și data de achitare!!!");
            return;
        }
        stmt.executeUpdate(query);
    }
    @Override
    public void Delete(Achitare Obj) throws SQLException {
        String query="DELETE FROM achitare WHERE nr_bon="+Obj.getNr_bon();
        stmt.executeUpdate(query);
    }
    @Override
    public ObservableList<Achitare> Select() throws SQLException {
        ObservableList<Achitare> list = FXCollections.observableArrayList();
        ResultSet rs=stmt.executeQuery("SELECT "+getColumns()+" FROM achitare");
        while(rs.next())
        {
            list.add(new Achitare(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6)));
        }
        return list;
    }
    public void TimeExpiredDelete() throws SQLException {
        stmt.executeUpdate("DELETE FROM achitare WHERE SYSDATE> data_finalizare");
    }
    public ObservableList<String> HoursPlayed() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        String query= "SELECT  nume||'  '||denumire_joc||'  '||TRUNC(minutes_ / 60) || ':' || TRUNC(MOD(minutes_,  60)) as Played " +
                "FROM (SELECT  (sysdate - data_achitarii) * 24 * 60 AS minutes_,nume,denumire_joc " +
                "FROM  achitare,jucator,sesiune_gaming " +
                "WHERE jucator.nr_card=achitare.nr_card and sesiune_gaming.id_sesiune=achitare.id_sesiune)";
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next())
        {
            list.add(rs.getString(1));
        }
        return list;
    }
}
