

package Interface_test;

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
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.FlowLayout;
import java.awt.Graphics;
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
    private UserPane usrpane;

    private int color_value;
    private int texture_value;
    private int shape_value;

    private static final int QUERYLABEL_SIZE = 256;
   // private static final int QUERYLABEL_HEIGHT = 256;
    private static final int OUTPUTLABEL_SIZE = 156;
   // private static final int OUTPUTLABEL_HEIGHT = 156;

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
        inputimagepane.setBorder (new LineBorder(Color.BLACK, 3));
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

        querylabel = new XLabel(false);
        querylabel.addMouseListener(querylabel);
        querylabel.setimage("/home/pratuat/intro.jpg",QUERYLABEL_SIZE);

        inputimagepane.setLayout(new GridLayout(2, 1, 5, 5));


        usrpane = new UserPane();

        inputimagepane.add(querylabel,0);
        inputimagepane.add(usrpane,1);
        inputimagepane.repaint();

      

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
             inputimagepane.remove(0);

             querylabel.setimage(filepath,QUERYLABEL_SIZE);

             inputimagepane.add(querylabel,0);
             querylabel.setIconTextGap(8);
             querylabel.setHorizontalTextPosition(SwingConstants.CENTER);
             querylabel.setVerticalTextPosition(SwingConstants.BOTTOM);
             querylabel.setText(filepath);               
                
             inputimagepane.repaint();
               
             XLabel labelnew = new XLabel();
             outputimagepane.add(labelnew);                
             labelnew.setimage(filepath);
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
            inputimagepane.remove(0);

            querylabel.setimage(filepath,QUERYLABEL_SIZE);
            querylabel.setText(filepath);
            querylabel.setHorizontalTextPosition(SwingConstants.CENTER);
            querylabel.setVerticalTextPosition(SwingConstants.BOTTOM);
               
            inputimagepane.add(querylabel,0);
            inputimagepane.repaint();

            XLabel label = new XLabel();
            outputimagepane.add(label);
            label.setimage(filepath);
            label.addMouseListener(label);
        }
     }
   }


public class XLabel extends JLabel implements MouseListener{

    boolean color_flag;
    boolean select_flag;
    private String image_src = null;

    
    public XLabel (){
        color_flag = true;
        select_flag = false;
        image_src = null;
    }

    public XLabel (boolean flag){
        color_flag = flag;
        select_flag = false;
        image_src = null;
    }

    public boolean setimage (String filepath){

       return setimage(filepath, OUTPUTLABEL_SIZE);
        
    }

//        @Override
//    public void paintComponent(Graphics g){
//
//           setBackground(Color.BLACK);
//
//           paint(g);
//        }


    public boolean setimage ( String filepath, int size){

        //LineBorder border = new LineBorder(Color.BLACK, );
        //this.setBorder(border);
        if(color_flag){
        this.setBorder(new LineBorder(Color.BLACK, 5));
        this.setBackground(Color.BLACK);
        }
        setPreferredSize(new Dimension(size, size));
        image_src = filepath;
        Image img;
        double h=0;
        double w=0;
        double width=0;
        double height=0;

        try {

            img = ImageIO.read(new File(filepath));
            w = img.getWidth(null);
            h = img.getHeight(null);

            double aspectratio = w/h;

            if(aspectratio >= 1){
                width = size;
                height = (double)size/aspectratio;
            }

            else {
                height = size;
                width = size * aspectratio;
            }

//            System.out.printf(" width: %d \t height: %d \n", (int)width, (int)height);
//            Image temp;


            Icon icon = new ImageIcon(img.getScaledInstance((int)width, (int)height,Image.SCALE_DEFAULT));
            this.setIcon(icon);
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.CENTER);


            return true;
        }

        catch (IOException ex) {
            Logger.getLogger(XLabel.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;

    }

    public void setflag(boolean flag){
        select_flag = flag;
    }

    public boolean getflag(){ return select_flag; }


   public void mouseClicked(MouseEvent e) {

                    System.out.println(image_src);

                    inputimagepane.remove(0);
                    querylabel.setimage(image_src,QUERYLABEL_SIZE);
                    querylabel.setText(image_src);
                    browsetextfield.setText(image_src);
                    querylabel.setHorizontalTextPosition(SwingConstants.CENTER);
                    querylabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                    inputimagepane.add(querylabel,0);
                    inputimagepane.repaint();

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {


        }

        public void mouseEntered(MouseEvent e) {
                if(color_flag){
                    LineBorder border = new LineBorder(Color.RED,5);
                    this.setBorder(border);
            }

        }

        public void mouseExited(MouseEvent e) {
                if(color_flag){
                    LineBorder border = new LineBorder(Color.BLACK, 5);
                    this.setBorder(border);                
            }
        }


}

public class UserPane extends javax.swing.JPanel {


    public UserPane() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        Radio_Color = new javax.swing.JRadioButton();
        Radio_Texture = new javax.swing.JRadioButton();
        Radio_Shape = new javax.swing.JRadioButton();
        colorslider = new javax.swing.JSlider();
        textureslider = new javax.swing.JSlider();
        shapeslider = new javax.swing.JSlider();
        Button_Search = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.lightGray));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Radio_Color.setText("Color");
        add(Radio_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        Radio_Texture.setText("Texture");
        add(Radio_Texture, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        Radio_Shape.setText("Shape");
        add(Radio_Shape, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        add(colorslider, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 50));
        add(textureslider, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 210, -1));
        add(shapeslider, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 210, -1));

        Button_Search.setText("Search");
        Button_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SearchActionPerformed(evt);
            }
        });
        add(Button_Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 90, -1));
    }// </editor-fold>

    private void Button_SearchActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // Perform Action**************************************************//***********************
        
    }


    // Variables declaration - do not modify
    private javax.swing.JButton Button_Search;
    private javax.swing.JRadioButton Radio_Color;
    private javax.swing.JRadioButton Radio_Shape;
    private javax.swing.JRadioButton Radio_Texture;
    private javax.swing.JSlider colorslider;
    private javax.swing.JSlider shapeslider;
    private javax.swing.JSlider textureslider;
    // End of variables declaration



    public int getcolorvalue(){

        if(Radio_Color.isSelected())
            return (colorslider.getValue());
        else return 0;
    }

    public int gettexturervalue(){

        if(Radio_Texture.isSelected())
            return (textureslider.getValue());
        else return 0;
    }

    public int getshapevalue(){

        if(Radio_Shape.isSelected())
            return (shapeslider.getValue());
        else return 0;
    }
}

}
