
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import javax.swing.Timer;

public class Oyun extends JPanel implements KeyListener,ActionListener{
    Timer timer=new Timer(5,this);
    private BufferedImage imagechicken,imagegoldenegg,imagebasket,imagecat,imagebg;
    private Chicken chicken=new Chicken(300,0);// tam ortadan başlar
    private ArrayList<Shoot> shoots=new ArrayList<>();
    private ArrayList<Shoot> shoots2=new ArrayList<>();
    private Shoot tmp,tmpL,tmp2,tmpR;
    private Basket basket=new Basket(310);
    private Basket basket2=new Basket(515,2);
    private int score=0;
    Random random=new Random();
    Random random1=new Random();
    private Cat catR=new Cat(400,350);
    private Cat catL=new Cat(200,250);
    private int level=1;
    private int numberofbasket=3;
    private boolean control=false;
    private Cat cat3=new Cat(-600,-600);
    private int combo=0,combo2=0;

public boolean collision(){
    for(Shoot at: shoots){
        if(new Rectangle(at.getX(),at.getY(),72,68).intersects(new Rectangle(basket.getX(),basket.getY(),72,68))){
            tmp=at;
            return true;
        }
    }
        
    return false;
}
public boolean collision2(){
    for(Shoot at: shoots2){
        if(new Rectangle(at.getX(),at.getY(),72,68).intersects(new Rectangle(basket2.getX(),basket2.getY(),72,68))){
            tmp2=at;
            return true;
        }
    }
        
    return false;
}        
public boolean collisioncat(Cat a){
    return new Rectangle(chicken.getX(),chicken.getY(),40,80).intersects(new Rectangle(a.getX(),a.getY(),46,60));
}
public boolean collisioncombo(){
    for(Shoot at: shoots){
        if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(basket.getX(),basket.getY(),80,74))){
            tmpL=at;
            return true;
        }
    }
        
    return false;
}
public boolean collisioncombo2(){
    for(Shoot at: shoots2){
        if(new Rectangle(at.getX(),at.getY(),42,41).intersects(new Rectangle(basket2.getX(),basket2.getY(),80,74))){
            tmpR=at;
            return true;
        }
    }
        
    return false;
}

public Oyun(){
   try {
        imagechicken=ImageIO.read(new FileImageInputStream(new File("Flying Chicken\\flyingchicken.png")));
        imagebasket=ImageIO.read(new FileImageInputStream(new File("Flying Chicken\\basket.png")));
        imagegoldenegg=ImageIO.read(new FileImageInputStream(new File("Flying Chicken\\goldenegg.png")));
        imagecat=ImageIO.read(new FileImageInputStream(new File("Flying Chicken\\cat.png")));
        imagebg=ImageIO.read(new FileImageInputStream(new File("Flying Chicken\\bg.jpg")));
    } catch (IOException ex) {
        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
    }   
            setBackground(Color.BLACK);
            timer.start(); 
}

@Override
public void paint(Graphics g){
    super.paint(g);
    g.drawImage(imagebg,0,0,this);
    g.setColor(Color.BLACK);
    g.drawString("Score:"+score,525,20);
    g.drawString("Level:"+level,10,20);
    g.drawString("Number of Remaining basket:"+numberofbasket,400,550);
    if(combo==3){
        g.drawString("COMBO X3", 100,20);
    }
    if(combo2==3){
        g.drawString("COMBO X3", 400,20);
    }
        for(Shoot at:shoots){
            if(at.getX()<0){
                shoots.remove(at);
            }
            if(chicken.getY()>=590){
                for(Shoot at1:shoots){
                    shoots.remove(at1);
                }
            }
        }
        for(Shoot at:shoots2){
            if(at.getX()>600){
                shoots2.remove(at);
            }
            if(chicken.getY()>=590){
                for(Shoot at1:shoots2){
                    shoots2.remove(at1);
                }
            }
        }
    g.drawImage(imagechicken,chicken.getX(),chicken.getY(),imagechicken.getWidth()/10,imagechicken.getHeight()/10,this);
    if(combo==3)
        g.drawImage(imagebasket,basket.getX(),basket.getY(),imagebasket.getWidth()/8, imagebasket.getHeight()/8, this);
    else
        g.drawImage(imagebasket,basket.getX(),basket.getY(),imagebasket.getWidth()/10, imagebasket.getHeight()/10, this);
    if(combo2==3)
        g.drawImage(imagebasket,basket2.getX(),basket2.getY(),imagebasket.getWidth()/8,imagebasket.getHeight()/8, this);
    else
        g.drawImage(imagebasket,basket2.getX(),basket2.getY(),imagebasket.getWidth()/10,imagebasket.getHeight()/10, this);
    for(Shoot at:shoots){
        g.drawImage(imagegoldenegg,at.getX(),at.getY(),imagegoldenegg.getWidth()/100,imagegoldenegg.getHeight()/100,this);
        }
    for(Shoot at:shoots2){
        g.drawImage(imagegoldenegg,at.getX(),at.getY(),imagegoldenegg.getWidth()/100,imagegoldenegg.getHeight()/100,this);
        }
    g.drawImage(imagecat,catR.getX(),catR.getY(),imagecat.getWidth()/10,imagecat.getHeight()/10,this);
    g.drawImage(imagecat,catL.getX(),catL.getY(),imagecat.getWidth()/10,imagecat.getHeight()/10,this);
    if(control){
        g.drawImage(imagecat,cat3.getX(),cat3.getY(),imagecat.getWidth()/10,imagecat.getHeight()/10,this);
        }
    
    if(level == 1 && score>=500){  
        timer.stop();
        String message="Congratulations,Level 1 Is Completed(Score:"+score+">>>For the Level 2 press the 'Space' button.";
        score=0;
        combo=0; //combo için
        combo2=0; // combo sag taraf
        JOptionPane.showMessageDialog(this,message);
        numberofbasket=5;
        level++;
        chicken.setY(600);
        chicken.moveY=4;
        Shoot.moveX=8;
        timer.start();    
        }
    if(level == 2 && score>=500){  
        timer.stop();
        String message="Congratulations,Level 2 Is Completed(Score:"+score+">>>For the Level 3 press the 'Space' button.";
        score=0;
        combo=0; //combo için
        combo2=0; // combo sag taraf
        JOptionPane.showMessageDialog(this,message);
        numberofbasket=8;
        level++;
        chicken.setY(600);
        chicken.moveY=3;
        Shoot.moveX=8;
        control=true;
        timer.start();    
        }
    if(level == 3 && score>=800){  
        timer.stop();
        String message="Congratulations,Level 3 Is Completed(Score:"+score+">>>END OF THE GAME<<<)"; 
        JOptionPane.showMessageDialog(this,message);
        control=false;
        System.exit(0);  
        }
    
     if(collisioncat(catR))
            {
                timer.stop();
                score=0;
                combo=0;
                combo2=0;
            switch (level) {
                case 1 -> numberofbasket=3;
                case 2 -> numberofbasket=5;
                case 3 -> numberofbasket=8;
                default -> {
                }
            }
                String message="Meowww!!Cat caught you.Press 'space' to try again.";
                JOptionPane.showMessageDialog(this,message);
                chicken.setY(600);
                timer.start();
            }
     if(collisioncat(catL))
            {
                timer.stop();
                score=0;
                combo=0;
                combo2=0;
            switch (level) {
                case 1 -> numberofbasket=3;
                case 2 -> numberofbasket=5;
                case 3 -> numberofbasket=8;
                default -> {
                }
            }
                String message="Meowww!!Cat caught you.Press 'space' to try again.";
                JOptionPane.showMessageDialog(this,message);
                chicken.setY(600);
                timer.start();
            }
     if(collisioncat(cat3))
            {
                timer.stop();
                score=0;
                combo=0;
                combo2=0;
            switch (level) {
                case 1 -> numberofbasket=3;
                case 2 -> numberofbasket=5;
                case 3 -> numberofbasket=8;
                default -> {
                }
            }
                String message="Meowww!!Cat caught you.Press 'space' to try again.";
                JOptionPane.showMessageDialog(this,message);
                chicken.setY(600);
                timer.start();
            }
      if(collisioncombo() && basket.combo(combo))
            {
                numberofbasket--;
                score+=200;
                shoots.remove(tmpL);
                basket.setY(-100);
                if(level==3)
                {
                    combo=0;
                }
            }
      else{
             if(collision()){  
                numberofbasket--;
                score+=100;
                shoots.remove(tmp);
                basket.setY(-100);
                combo++;
            }
        }
      if(collisioncombo2() && basket2.combo(combo2)){
                numberofbasket--;
                score+=200;
                shoots2.remove(tmpR);
                basket2.setY(-100);
                 if(level==3){
                    combo2=0;
                }
            }
            else{
              if(collision2()){   
                numberofbasket--;
                score+=100;
                shoots2.remove(tmp2);
                basket2.setY(-100);
                combo2++;
            }
        }
    
}

@Override
public void repaint(){
    super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

@Override
public void keyTyped(KeyEvent e){
       
        
    }

@Override
public void keyPressed(KeyEvent e) {
            
    int a=e.getKeyCode();
               
    switch (a) {
        case KeyEvent.VK_LEFT -> {
            if(chicken.getX()<=50){
                chicken.setX(50);
            }
           else{
                chicken.setX(chicken.getX()-chicken.moveX);
            }
        }
        case KeyEvent.VK_RIGHT -> {
            if(chicken.getX()>=500){
                chicken.setX(500);
            }
            else{
                chicken.setX(chicken.getX()+chicken.moveX);
            }
        }
        case KeyEvent.VK_A -> shoots.add(new Shoot(chicken.getX()+30,chicken.getY()));
        case KeyEvent.VK_D -> shoots2.add(new Shoot(chicken.getX()+30,chicken.getY()));
        default -> {
        }
    }
    }

@Override
public void keyReleased(KeyEvent e) {
        
}

 @Override
public void actionPerformed(ActionEvent e) {
        
    for(Shoot shoot:shoots){   
        shoot.setX(shoot.getX()-Shoot.moveX);
    }
    for(Shoot shoot:shoots2){
        shoot.setX(shoot.getX()+Shoot.moveX);
    }
    if(basket.getY()<20 && basket.getY()!=-100){   
        basket.setY(500);
    }
    if(basket2.getY()<20 && basket2.getY()!= -100){
        basket2.setY(500);
    }
    chicken.setY(chicken.getY()+chicken.moveY);
    if(chicken.getY()>=600){   
        chicken.setY(0);
        basket.setY(20+random.nextInt(400));
        basket2.setY(20+random.nextInt(400));
        catR.setX((300+random1.nextInt(200)));
        catR.setY((120+random1.nextInt(380)));
        catL.setX((60+random1.nextInt(240)));
        catL.setY((120+random1.nextInt(380)));
        if(control){
            cat3.setX(250+random1.nextInt(130));
            cat3.setY(200+random1.nextInt(250));
        }   
   }          
    repaint();
}
}