

package Interface;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;

import java.io.File;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.SwingConstants;



public class MainFrame extends JFrame{

    private JButton browsebutton;
    private JTextField browsetextfield;
    private JPanel browsepane;
    private JPanel inputimagepane;
    private JPanel outputimagepane;
    private JPanel basket;
    private XLabel querylabel;
    private XLabel[] outputlabel;
    public MainFrame(String str){

        super(str);
        //LayoutManager ly = new BorderLayout(); //AbsoluteLayout();
        LayoutManager ly = new BorderLayout();
        setLayout(ly);
     
        browsebutton = new JButton("Browse");
        browsetextfield = new JTextField("enter path to input image");

        browsepane = new JPanel();
        browsepane.setLayout(new BorderLayout());
        browsepane.add(browsebutton,BorderLayout.WEST);
        browsepane.add(browsetextfield,BorderLayout.CENTER);


        inputimagepane = new JPanel();
        inputimagepane.setBorder (new LineBorder(Color.YELLOW, 3));
        inputimagepane.setSize(256,256);

        outputimagepane = new JPanel();
        outputimagepane.setBorder(new LineBorder(Color.BLACK,3));
        
        basket = new JPanel();
        //basket.setLayout( new GridLayout(1, 2));
        basket.setLayout( new BorderLayout());

        this.add(browsepane,BorderLayout.NORTH);
        this.add(basket);

        basket.add(inputimagepane,BorderLayout.WEST); 
        basket.add(outputimagepane,BorderLayout.CENTER);

        ButtonHandler handler = new ButtonHandler();
        browsebutton.addActionListener(handler);
        browsetextfield.addActionListener(handler);
      

    }


        private class ButtonHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            if(event.getSource() == browsebutton){

            JFileChooser chooser = new JFileChooser();

            int returnvalue = chooser.showOpenDialog(null);

            if(returnvalue == JFileChooser.APPROVE_OPTION){

                String filepath;
                filepath = (chooser.getSelectedFile()).getAbsolutePath();
                browsetextfield.setText(filepath);
                inputimagepane.removeAll();
                querylabel = new XLabel();
                XLabel labelnew = new XLabel();
                inputimagepane.add(querylabel);
                outputimagepane.add(labelnew);
                querylabel.setimage(filepath);
                labelnew.setimage(filepath);

                querylabel.addMouseListener(querylabel);
                labelnew.addMouseListener(labelnew);
              

            }
          }

            else if(event.getSource() == browsetextfield)
            {
              String filepath;
              filepath = browsetextfield.getText();
             // File file = new File(filepath);
              //if(file.)
               browsetextfield.setText(filepath);
               inputimagepane.removeAll();
               XLabel label1 = new XLabel();
               inputimagepane.add(label1);
               label1.setimage(filepath);
               label1.addMouseListener(label1);


               XLabel label = new XLabel();
               outputimagepane.add(label);
               label.setimage(filepath);
               label.addMouseListener(label);


          }


      }
    }

}
