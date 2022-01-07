package com.example.gamingroom.ClaseDateSQL;

import ClaseDate.TipJoc;
import com.example.gamingroom.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipJocSQL extends SQLConnection<TipJoc> {
    @Override
    public String getColumns() {
        return "denumire_tip,id_tip";
    }

    @Override
    public void Insert(TipJoc Obj) throws SQLException {
        String query = "INSERT INTO tip_joc("+getColumns()+") VALUES('"+
                Obj.getDenumire_tip()+"',";
        if(0 != Obj.getId_tip())
            query+=Obj.getId_tip()+")";
        else
            query+="TIP_JOC_ID_TIP_SEQ.nextval)";
        stmt.executeUpdate(query);
    }

    @Override
    public void Update(TipJoc WhereObj, TipJoc Obj) throws SQLException {
        String query="UPDATE tip_joc SET denumire_tip='"+Obj.getDenumire_tip()+"', id_tip=";
        if(Obj.getId_tip()!=0)
            query+=Obj.getId_tip()+ " WHERE id_tip="+WhereObj.getId_tip();
        else
            query+="TIP_JOC_ID_TIP_SEQ.nextval WHERE id_tip="+WhereObj.getId_tip();
        stmt.executeUpdate(query);
    }

    @Override
    public void Delete(TipJoc Obj) throws SQLException {
        String query="DELETE FROM tip_joc WHERE id_tip="+Obj.getId_tip();
        stmt.executeUpdate(query);
    }

    @Override
    public ObservableList<TipJoc> Select() throws SQLException {
        ObservableList<TipJoc> list = FXCollections.observableArrayList();
        ResultSet rs=stmt.executeQuery("SELECT "+getColumns()+" FROM tip_joc");
        while(rs.next())
        {
            list.add(new TipJoc(rs.getString(1),rs.getInt(2)));
        }
        return list;
    }
    public ObservableList<Integer> Selecttypes() throws SQLException {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        ResultSet rs=stmt.executeQuery("SELECT id_tip FROM tip_joc");
        while(rs.next())
        {
            list.add(rs.getInt(1));
        }
        return list;
    }
}
