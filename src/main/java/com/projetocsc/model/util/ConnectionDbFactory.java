package com.projetocsc.model.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDbFactory {

    private Connection con;
	
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetocsc?useTimezone=true&serverTimezone=UTC", "root", "root");
            return con;
        } catch (Exception e) {
            System.out.println("Erro na conex‹o: " + e.getMessage());
        }
        return con;
    }

    public void setClose() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
}
