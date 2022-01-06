package com.example.gamingroom;

//Clasa abstracta care se ocupa cu crearea unei conexiuni SQL, si comenzi specifice SQL pentru fiecare tabela

import javafx.collections.ObservableList;

import java.sql.*;

public abstract class SQLConnection<T> {
    public static Connection conn;
    public static Statement stm;

    public static void makeConnection() throws ClassNotFoundException, SQLException {
        //Incarcam driverul
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //Cream obiectul conexiune
        conn = DriverManager.getConnection("jdbc:oracle:thin:@bd-dc.cs.tuiasi.ro:1539:orcl", "bd132", "bd132");
        conn.setAutoCommit(false);//pentru butoanele de commit si rollback
        stm = conn.createStatement();

        System.out.println("Conectat!");
    }

    public static void closeConenction() throws SQLException {
        conn.close();
    }
    public static void commit() throws SQLException {
        conn.commit();
    }
    public static void rollback() throws SQLException {
        conn.rollback();
    }
    public abstract String getColumns();

    public abstract void Insert(T Obj) throws SQLException;

    public abstract void Update(T WhereObj, T Obj) throws SQLException;

    public abstract void Delete(T Obj) throws SQLException;

    public abstract ObservableList<T> Select() throws SQLException;
}
