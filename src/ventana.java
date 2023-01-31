import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ventana {
    private JTextField txtId_Person;
    private JTextField txtNom;
    private JTextField textCel;
    private JButton guardarButton;
    private JTextField textDir;
    private JPanel panel;
    private JTextField textEmail;

    PreparedStatement ps;





public ventana() {
    guardarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;

            try{
                con = getConection();
                ps = con.prepareStatement("INSERT INTO persona (Id_Perso, Nom_Perso, Cel_Perso, Dir_Perso, Email_Perso) VALUES(?,?,?,?,?) ");

                ps.setString(1, txtId_Person.getText());
                ps.setString(2, txtNom.getText());
                ps.setString(3, textCel.getText());
                ps.setString(4, textDir.getText());
                ps.setString(5, textEmail.getText());
                System.out.println(ps);//imprimo en consola para verificación

                int res = ps.executeUpdate();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Persona Guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                }
                //limpiartxt();
                //txtId.setText("");
                //txtNombre.setText("");
                //txtCelular.setText("");
                //txtCorreo.setText("");
                con.close();//importante!!!!

            }catch (HeadlessException | SQLException f) {
                System.err.println(f);
            }
        }
    });
}

    public static Connection getConection() {
        Connection con = null;
        String base = "datos"; //Nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre de la Base de Datos
        String user = "root"; //Usuario de Acceso a MySQL
        String password = "Wilson08"; //Password del usuario

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("insert");
        frame.setContentPane(new ventana().panel );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
    }


}
