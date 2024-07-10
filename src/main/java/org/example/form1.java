package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form1 {
    public JPanel mainPanel;
    public JTextField codigoU;
    public JTextField nombre;
    public JTextField edad;
    public JComboBox<String> carrera ;
    public JCheckBox becadoCheckBox;
    public JButton registrarButton;
    public JButton cancelarButton;
    private JLabel estado;

    /*Coneccion con la base de datos*/
    String url="jdbc:mysql://localhost:3306/bdestudiantes";
    String user="root";
    String password="123456";


    public form1() {
        carrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql="insert into estudiantes (codigo_unico,nombre,edad,carrera,beca) values (?,?,?,?,?)";

                try (Connection connection= DriverManager.getConnection(url,user,password)){
                    System.out.println("Connected to database");
                    System.out.println("Ingresado Datos...");
                    PreparedStatement CadenaPreparada= connection.prepareStatement(sql);

                    CadenaPreparada.setString(1, codigoU.getText());
                    CadenaPreparada.setString(2, nombre.getText());
                    CadenaPreparada.setInt(3,Integer.parseInt(edad.getText()));
                    CadenaPreparada.setString(4, "Software");
                    CadenaPreparada.setString(5, "si");

                    CadenaPreparada.executeUpdate();

                    System.out.println("Ingreso Exitoso...");
                    estado.setText("Ingreso Exitoso...");

                }
                catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    estado.setText("Algo salió mal :(");
                }


            }
        });
    }
}
