package com.example.gamingroom.ClaseDateSQL;

import ClaseDate.SesiuneJoc;
import com.example.gamingroom.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SesiuneJocSQL extends SQLConnection<SesiuneJoc> {
    @Override
    public String getColumns() {
        return "denumire_joc,id_sesiune,id_tip";
    }

    @Override
    public void Insert(SesiuneJoc Obj) throws SQLException {
        String query = "INSERT INTO sesiune_gaming("+getColumns()+") VALUES('"+
                Obj.getDenumire_joc()+"',";
        if(Obj.getId_sesiune()!=0)
            query+=Obj.getId_sesiune()+","+Obj.getId_tip()+")";
        else
            query+="SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,"+Obj.getId_tip()+")";
        stm.executeUpdate(query);
    }

    @Override
    public void Update(SesiuneJoc WhereObj, SesiuneJoc Obj) throws SQLException {
        String query="UPDATE sesiune_gaming SET denumire_joc='"+Obj.getDenumire_joc()+"', id_sesiune=";
        if(Obj.getId_sesiune()!=0)
            query+=Obj.getId_sesiune()+ ", id_tip="+Obj.getId_tip()+" WHERE id_sesiune="+WhereObj.getId_sesiune();
        else
            query+="SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval, id_tip="+Obj.getId_tip()+" WHERE id_sesiune="+WhereObj.getId_sesiune();
        System.out.println(query);
        stm.executeUpdate(query);
    }

    @Override
    public void Delete(SesiuneJoc Obj) throws SQLException {
        String query="DELETE FROM sesiune_gaming WHERE id_sesiune="+Obj.getId_sesiune();
        stm.executeUpdate(query);
    }

    @Override
    public ObservableList<SesiuneJoc> Select() throws SQLException {
        ObservableList<SesiuneJoc> list = FXCollections.observableArrayList();
        ResultSet rs=stm.executeQuery("SELECT "+getColumns()+" FROM sesiune_gaming");
        while(rs.next())
        {
            list.add(new SesiuneJoc(rs.getString(1),rs.getInt(2),rs.getInt(3)));
        }
        return list;
    }
    public ObservableList<Integer> SelectSessions() throws SQLException {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        ResultSet rs=stm.executeQuery("SELECT id_sesiune FROM sesiune_gaming");
        while(rs.next())
        {
            list.add(rs.getInt(1));
        }
        return list;
    }
}
