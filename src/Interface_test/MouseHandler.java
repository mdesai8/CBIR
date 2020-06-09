///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package Interface_test;
//
//import java.awt.event.MouseEvent;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.BoxLayout;
//import javax.swing.GroupLayout;
//import javax.swing.border.LineBorder;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.GridLayout;
//
//import java.awt.FlowLayout;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JFileChooser;
//import javax.swing.JLabel;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseListener;
//import java.awt.event.ActionEvent;
//import java.awt.LayoutManager;
//
//import java.io.File;
//
//import java.awt.image.BufferedImage;
//import java.awt.Image;
//import javax.imageio.ImageIO;
//import javax.swing.SwingConstants;
//
//import java.awt.event.MouseEvent;
//
///**
// *
// * @author pratuat
// */
//    public class MouseHandler implements MouseListener{
//
//           public void mouseClicked(MouseEvent e) {
//
//
//            if(e.getSource().getClass() == XLabel.class){
//                    LineBorder border = new LineBorder(Color.BLUE, 3);
//                    e.getSource().setBorder(border);
//                    this.setflag(true);
//                    System.out.println(getsource());
//                }
//
//            else if(this.getflag() == true) {
//                    LineBorder border = new LineBorder(Color.BLACK, 3);
//                    this.setBorder(border);
//                    this.setflag(false);
//
//                }
//
//
//        }
//
//        public void mousePressed(MouseEvent e) {
//
//        }
//
//        public void mouseReleased(MouseEvent e) {
//
//
//        }
//
//        public void mouseEntered(MouseEvent e) {
//
//
//
//
//                if(this.getflag() == false){
//
//                    LineBorder border = new LineBorder(Color.RED, 3);
//                    this.setBorder(border);
//                 }
//
//
//        }
//
//        public void mouseExited(MouseEvent e) {
//
//            if(e.getComponent() == this){
//
//                if(this.getflag() == false){
//
//                    LineBorder border = new LineBorder(Color.BLACK, 3);
//                    this.setBorder(border);
//                 }
//            }
//        }
//
//}
