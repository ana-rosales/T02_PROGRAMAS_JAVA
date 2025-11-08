/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Galeria2 extends JFrame{
    JButton btnAdelante, btnAtras;
    JPanel panelbtn;
    ArrayList<ImageIcon> images;
    int ImgActual = 0;
    JLabel etimage;
   
    public Galeria2(){
        setTitle("Mi Galeria");
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());    
       
        // Cargar imágenes
        images = new ArrayList<>();
        images.add(new ImageIcon("../images/image1.jpg"));        
        images.add(new ImageIcon("../images/image2.jpg"));
        images.add(new ImageIcon("../images/image3.jpg"));
       
        etimage = new JLabel();
        showImage(ImgActual);
               
        btnAdelante = new JButton("Adelante");        
        btnAtras = new JButton("Atras");

        panelbtn = new JPanel();
       
        // ATRÁS (lambda)
        btnAtras.addActionListener(e -> showImage(ImgActual - 1));

        // ADELANTE (clase anónima)
        btnAdelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImage(ImgActual + 1);
            }
        });
       
        panelbtn.add(btnAtras); 
        panelbtn.add(btnAdelante);

        add(etimage);
        add(panelbtn);

        setVisible(true);
    }
   
    public static void main(String[] args) {
        new Galeria2();
    }
   
    public void showImage(int nuevoIndice){
        int total = images.size();

        // ----- LLAVE DEL EJERCICIO -----
        if(nuevoIndice < 0) {
            nuevoIndice = total - 1;  // si se pasa por atrás → última imagen
        }
        if(nuevoIndice >= total) {
            nuevoIndice = 0;          // si se pasa por adelante → primera imagen
        }
        // ---------------------------------

        this.ImgActual = nuevoIndice;
        etimage.setIcon(images.get(nuevoIndice));
    }
}
