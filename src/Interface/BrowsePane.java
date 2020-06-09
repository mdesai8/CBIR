package Interface;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package Interface;
//
//import java.awt.FlowLayout;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JFileChooser;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import java.io.File;
//
//
//public class BrowsePane extends JPanel{
//
//    private JButton browsebutton;
//    private JTextField browsetextfield;
//
//    p
//
//    public BrowsePane(){
//
//        setLayout(new FlowLayout());
//
//        browsebutton = new JButton("Browse");
//        this.add(browsebutton);
//
//        browsetextfield = new JTextField("enter path to input image");
//        this.add(browsetextfield);
//
//
//
//
//    }
//
//
//    private class ButtonHandler implements ActionListener{
//
//        public void actionPerformed(ActionEvent event){
//
//            JFileChooser chooser = new JFileChooser();
//
//            int returnvalue = chooser.showOpenDialog(null);
//
//            if(returnvalue == JFileChooser.APPROVE_OPTION){
//
//                String filepath = (chooser.getSelectedFile()).getAbsolutePath();
//            }
//
//
//
//
//
//
//
//        }
//
//
//    }
//
//
//}
