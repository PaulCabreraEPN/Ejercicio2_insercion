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

    /*Se crea el estudiante*/
    Estudiante estudiante=new Estudiante();


    public form1() {
        carrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*Se setea los valores en el objeto*/
                estudiante.setCodigo_unico(codigoU.getText());
                estudiante.setNombre(nombre.getText());
                estudiante.setEdad(Integer.parseInt(edad.getText()));
                estudiante.setCarrera("Software");
                estudiante.setBeca("Si");

                /*Preparamos cadena de inserción*/

                String sql="insert into estudiantes (codigo_unico,nombre,edad,carrera,beca) values (?,?,?,?,?)";


                try (Connection connection= DriverManager.getConnection(url,user,password)){
                    System.out.println("Connected to database");
                    System.out.println(">Ingresado Datos<");
                    PreparedStatement CadenaPreparada= connection.prepareStatement(sql);

                    CadenaPreparada.setString(1,estudiante.getCodigo_unico() );
                    CadenaPreparada.setString(2, estudiante.getNombre());
                    CadenaPreparada.setInt(3, estudiante.getEdad());
                    CadenaPreparada.setString(4, estudiante.getCarrera());
                    CadenaPreparada.setString(5, estudiante.getBeca());

                    CadenaPreparada.executeUpdate();

                    System.out.println("Ingreso Exitoso...");
                    estudiante.mostrar_estudiante();
                    estado.setText("Ingreso Exitoso...");


                }
                catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    estado.setText("Algo salió mal :(");
                }


            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoU.setText(null);
                nombre.setText(null);
                edad.setText(null);
            }
        });
    }
}
