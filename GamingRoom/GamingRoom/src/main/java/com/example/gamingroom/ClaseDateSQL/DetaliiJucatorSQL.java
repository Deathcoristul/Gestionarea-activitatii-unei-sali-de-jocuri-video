package com.example.gamingroom.ClaseDateSQL;

import ClaseDate.DetaliiJucator;
import com.example.gamingroom.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetaliiJucatorSQL extends SQLConnection<DetaliiJucator> {
    @Override
    public String getColumns() {
        return "nr_card,email,data_nasterii,gen";
    }

    @Override
    public void Insert(DetaliiJucator Obj) throws SQLException {
        String query = "INSERT INTO Detalii_Jucator("+getColumns()+") VALUES("+
                Obj.getNr_card()+",";
        if(Obj.getEmail()==null)
            query+="NULL,";
        else
            query+="'"+Obj.getEmail()+"',";
        query+="TO_DATE('"+Obj.getData_nasterii()+"','yyyy-mm-dd'),";
        if(Obj.getGen()==null)
            query+="NULL)";
        else
            query+="'"+Obj.getGen()+"')";
        stm.executeUpdate(query);
    }

    @Override
    public void Update(DetaliiJucator WhereObj, DetaliiJucator Obj) throws SQLException {
        String query="UPDATE DETALII_JUCATOR SET nr_card="+Obj.getNr_card()+", email=";
        if(Obj.getEmail()==null)
            query+="NULL, ";
        else
            query+="'"+Obj.getEmail()+"', ";
        query+="data_nasterii=TO_DATE('"+Obj.getData_nasterii()+"','yyyy-mm-dd'), gen=";
        if(Obj.getGen()==null)
            query+="NULL WHERE nr_card="+WhereObj.getNr_card();
        else
            query+="'"+Obj.getGen()+"' WHERE nr_card="+WhereObj.getNr_card();
        stm.executeUpdate(query);
    }

    @Override
    public void Delete(DetaliiJucator Obj) throws SQLException {
        String query="DELETE FROM DETALII_JUCATOR WHERE nr_card="+Obj.getNr_card();
        stm.executeUpdate(query);
    }

    @Override
    public ObservableList<DetaliiJucator> Select() throws SQLException {
        ObservableList<DetaliiJucator> list = FXCollections.observableArrayList();
        ResultSet rs=stm.executeQuery("SELECT "+getColumns()+" FROM detalii_jucator");
        while(rs.next())
        {
            list.add(new DetaliiJucator(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4)));
        }
        return list;
    }
}
