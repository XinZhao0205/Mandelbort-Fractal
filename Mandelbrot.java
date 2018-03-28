import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;


public class Mandelbrot{
 int xRes;
 int yRes;
 double cxMin;
 double cxMax;
 double cyMin;
 double cyMax;
 public int[][] xtable;
 ColorList colors;

 public Mandelbrot(int xRes,int yRes, double cxMin, double cxMax, double cyMin, double cyMax){
      this.xRes = xRes;
      this.yRes = yRes;
      this.cxMin = cxMin;
      this.cxMax = cxMax;
      this.cyMin = cyMin;
      this.cyMax = cyMax;
   colors = new ColorList();
   xtable = new int[yRes][xRes];

  
  int maxIter=255;
  for(int y=0;y<yRes;y++){
   for(int x=0;x<xRes;x++){
     int iter=0;
   double i=cxMin + 1.0*x/(xRes-1)*(cxMax-cxMin);
   double j=cyMin + 1.0*y/(yRes-1)*(cyMax-cyMin);
   ComplexNumber c = new ComplexNumber(i,j);
   ComplexNumber z= new ComplexNumber(0,0);
   while(iter<maxIter && z.abs()<2){
     z.squared();
     z.add(c);
     iter++;
   }
   if(z.abs()>=2){
    xtable[y][x] = iter;
   }
   else {
    xtable[y][x] = 0;
   }
  }
     
  }
 }//Mandelbrot constructor

 public static void setPixel(Graphics g, int x, int y, int red, int grn, int blu){
  Color c = new Color(red,grn,blu);
  g.setColor(c);
  g.drawLine(x,y,x,y);
 }//setPixel

 public void displayFractal(Graphics g){
  for(int y=0;y<yRes;y++){
   for(int x=0;x<xRes;x++){
    Color c = colors.colList.get(xtable[y][x]);
    setPixel(g,x,y,c.getRed(),c.getGreen(),c.getBlue()); 
   }
  }
 }
 public void saveFractal(String fileName) throws IOException{
    //According to your route, you can change it
    File f = new File( "/Users/Sophie/Desktop/XinZhao_2002778508_HW4/" + fileName);
    FileOutputStream fout = new FileOutputStream(f);
    PrintStream out = new PrintStream(fout);
    
    out.println("P3\r\n" + xRes + " " + yRes + "\r\n255\r\n");
    for(int y=0;y<yRes;y++) {
      for(int x=0;x<xRes;x++) {   
       Color c = colors.colList.get(xtable[y][x]);
       
     //displayFractal(Graphics g);
        out.println(c.getRed()+ " " + c.getGreen() + " " + c.getBlue());    
      }
    }
    out.close();
  }

  public static void main(String[] args) throws IOException{
   //ColorList cols = new ColorList();
    Mandelbrot mand = new Mandelbrot(640,480,-2,1,-1,1);
   BufferedImage surface = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
   JLabel view = new JLabel(new ImageIcon(surface));
   Graphics g1 = surface.getGraphics();
   JFrame frame = new JFrame();
   frame.setSize(640,480);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setContentPane(view);
  
    mand.displayFractal(g1);
    mand.saveFractal("Mandelbrot.ppm");


    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
    
    
    
 }
}