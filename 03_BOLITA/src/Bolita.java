/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bolita extends JFrame implements ActionListener{
    JPanel pt, pd, pb;
    int posX = 0, posY = 0;

    JButton btnAdelante, btnAtras, btnArriba, btnAbajo;  // <-- NUEVOS

    public Bolita(){
        setTitle("Bolita 01");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
       
        pt = new PanelTexto();
        pd = new PanelDibujo();
        pb = new JPanel();
        pb.setLayout(new GridLayout(1,4));   // <-- ahora hay 4 botones
       
        btnAdelante = new JButton(">>");
        btnAtras = new JButton("<<");
        btnArriba = new JButton("↑");       // <-- NUEVO
        btnAbajo = new JButton("↓");        // <-- NUEVO
       
        btnAdelante.addActionListener(this);
        btnAtras.addActionListener(this);
        btnArriba.addActionListener(this);
        btnAbajo.addActionListener(this);
       
        pb.add(btnAtras);
        pb.add(btnArriba);
        pb.add(btnAbajo);
        pb.add(btnAdelante);
       
        add(pt);
        add(pd);
        add(pb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int maxX = pd.getWidth() - 20;  // límite horizontal del panel
        int maxY = pd.getHeight() - 20; // límite vertical del panel

        if(e.getSource().equals(btnAdelante)){
            posX += 10;
            if(posX > maxX) posX = 0;
        }

        if(e.getSource().equals(btnAtras)){
            posX -= 10;
            if(posX < 0) posX = maxX;
        }

        if(e.getSource().equals(btnArriba)){
            posY -= 10;
            if(posY < 0) posY = maxY;
        }

        if(e.getSource().equals(btnAbajo)){
            posY += 10;
            if(posY > maxY) posY = 0;
        }

        pd.repaint();
    }

   
    private class PanelTexto extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            Font font = new Font("Arial", 20, 20);
            g.setFont(font);
            g.drawString("Juego Bolita", 10, 20);            
        }
    }
   
    private class PanelDibujo extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.ORANGE);
            g.fillOval(posX, posY, 20, 20);
        }
    }
   
    public static void main(String[] args) {
        Bolita b = new Bolita();
        b.setVisible(true);
    }
}
