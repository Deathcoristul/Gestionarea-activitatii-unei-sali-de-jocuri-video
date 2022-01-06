package com.example.gamingroom.ClaseDateSQL;

import ClaseDate.Jucator;
import com.example.gamingroom.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JucatorSQL extends SQLConnection<Jucator> {
    @Override
    public String getColumns() {
        return "nume,nr_card,nume_echipa";
    }
    @Override
    public void Insert(Jucator Obj) throws SQLException {
        String query = "INSERT INTO Jucator("+getColumns()+") VALUES('"+
                Obj.getNume()+"',"+Obj.getNr_card()+",";
        if(Obj.getNume_echipa()==null)
            query+="NULL)";
        else
            query+="'"+Obj.getNume_echipa()+"')";
        stm.executeUpdate(query);
    }
    @Override
    public void Update(Jucator WhereObj, Jucator Obj) throws SQLException {
        String query="UPDATE JUCATOR SET nume='"+Obj.getNume()+"', nr_card="+Obj.getNr_card()+", nume_echipa=";
        if(Obj.getNume_echipa()==null)
            query+="NULL WHERE nr_card="+WhereObj.getNr_card();
        else
            query+="'"+Obj.getNume_echipa()+"' WHERE nr_card="+WhereObj.getNr_card();
        stm.executeUpdate(query);
    }
    @Override
    public void Delete(Jucator Obj) throws SQLException {
        String query="DELETE FROM JUCATOR WHERE nr_card="+Obj.getNr_card();
        stm.executeUpdate(query);
    }
    @Override
    public ObservableList<Jucator> Select() throws SQLException {
        ObservableList<Jucator> list = FXCollections.observableArrayList();
        //Nu merge cu new ObservableList()
        ResultSet rs=stm.executeQuery("SELECT "+getColumns()+" FROM JUCATOR");
        while(rs.next())
        {
            list.add(new Jucator(rs.getString(1),rs.getInt(2),rs.getString(3)));
        }
        return list;
    }
    public ObservableList<Integer> SelectCards() throws SQLException {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        //Nu merge cu new ObservableList()
        ResultSet rs=stm.executeQuery("SELECT nr_card FROM JUCATOR");
        while(rs.next())
        {
            list.add(rs.getInt(1));
        }
        return list;
    }
}
