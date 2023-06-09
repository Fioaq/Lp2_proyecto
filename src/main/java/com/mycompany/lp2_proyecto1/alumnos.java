/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lp2_proyecto1;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Acer
 */
public class alumnos {
    int id;
    String nombre;
    String apellido;
    String edad;
    String direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void insertarAlumno(JTextField paramNombre, JTextField paramApellido, JTextField paramEdad, JTextField paramDireccion){
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setEdad(paramEdad.getText());
        setDireccion(paramDireccion.getText());
        conexion objetoConexion= new conexion();
        String Consulta=" INSERT INTO alumnos(nombre, apellido, edad, direccion) VALUES(?,?,?,?);";
        try{
            CallableStatement cs= objetoConexion.establecerConexcion().prepareCall(Consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getEdad());
            cs.setString(4, getDireccion());
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se creo correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Hubo un error: "+e.toString());
        }
    }
    
    public void MostrarTabla(JTable paramTabla){
        conexion ObjetoConexion= new conexion();
        DefaultTableModel model= new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(model);
        paramTabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");
        model.addColumn("Direccion");
        sql="SELECT * FROM `alumnos`";
        String [] datos= new String[5];
        Statement st;
        try{
            st= ObjetoConexion.establecerConexcion().createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);
                datos[4]= rs.getString(5);
                model.addRow(datos);
            }
            paramTabla.setModel(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar los registros: "+e.toString());
        }
    }
}
