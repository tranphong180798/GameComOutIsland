//Tran Do dai phong 1720173
//Nguyen Duc Phuong  1720173
package mousemoveoutisland;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import static java.lang.Math.random;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class MouseMoveOutIsland extends JFrame {
  final static  private int mouse=1;
  final static private int water=-1;
  final static private int bay=2;
  final static private int allowedGo=0;
     static int data;
     static int SaveMap[][];
     static int  soDong;
     static int  soCot;
     static int vitrix;
     static int vitriy;
     private Container cn;
     private JPanel pn0,pn,pn1,pn2; 
     private static JFrame frame;
     static int maxSize=20;
     private static String Url;
     private JButton Loading,OPT1,OPT2,OPT3,Ketqua;
     private JLabel title;
     static String w=null;
     static String input;
     int MapSave[][];
     static FileOutputStream file;
     public MouseMoveOutIsland() {
       System.out.println("==========");
       cn = getContentPane();
       Loading=new JButton();
        Ketqua=new JButton();
        Loading.setText("lOADINGMAP");
        Ketqua.setText("RESULTS");
        pn=new JPanel();
        pn.add(Loading);
        pn.add(Ketqua);
        pn.setLayout(new GridLayout(2,0,20,20));
       cn.setLayout(new BorderLayout());
        cn.add(pn,BorderLayout.WEST);
        OPT1=new JButton("OPT1");
        OPT2=new JButton("OPT2");
        OPT3=new JButton("OPT3");
        pn1=new JPanel();
        pn1.setLayout(new GridLayout(3,0,10,5));
        pn1.add(OPT1);
        pn1.add(OPT2);
        pn1.add(OPT3);
        cn.add(pn1,BorderLayout.EAST);
        Loading.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
              CreateButton();
              String urlFile="src\\SaveMapOptUser.txt";
              File temp = new File(urlFile);
              if (temp.exists()) {
                        RandomAccessFile raf = null;
                          try {
                              raf = new RandomAccessFile(temp, "rw");
                              raf.setLength(0);
                          } catch (FileNotFoundException ex) {
                              Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (IOException ex) {
                              Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                          }} }
            });
         //OP1
        OPT1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(Url!=null)
              {
                 readFileNoCheckOut(Url,input);
                ranDomAndMoveOpt1();
              }
              else {
                  JOptionPane.showMessageDialog(frame, "bạn đã chưa Loading fileMap.Hãy bấm nút loang để loadMap", "OPT1", JOptionPane.INFORMATION_MESSAGE);
              }
            }});
        //OPT2
        OPT2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(Url!=null)
              {
                   readFileNoCheckOut(Url,input);
                 ranDomAndMove100TakeDataForUser();
            }
              else {
                  JOptionPane.showMessageDialog(frame, "bạn đã chưa Loading fileMap.Hãy bấm nút loang để loadMap", "OPT1", 
                              JOptionPane.INFORMATION_MESSAGE);
              }}});
        //ÔPT3
         OPT3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(Url!=null)
              {
                    readFileNoCheckOut(Url,input);
                   ranDomAndMove100InTime();
               }
              else {
                  JOptionPane.showMessageDialog(frame, "bạn đã chưa Loading fileMap.Hãy bấm nút loang để loadMap", "OPT1", 
                              JOptionPane.INFORMATION_MESSAGE);
              }}});
         Ketqua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlFile="src\\SaveMapOptUser.txt";
                File temp = new File(urlFile);
                if(Url!=null)
                {
                   try {
                    BufferedReader br = new BufferedReader(new FileReader(urlFile));
                    int i;
                       while ((i = br.read()) != -1) {
                           System.out.print((char) i);
                          w=w+String.valueOf((char)i);
                        }
                       JOptionPane.showMessageDialog(frame, w, "RESULTS", 
                              JOptionPane.INFORMATION_MESSAGE);
                       br.close();
                       if (temp.exists()) {
                        RandomAccessFile raf = null;
                          try {
                              raf = new RandomAccessFile(temp, "rw");
                              raf.setLength(0);
                          } catch (FileNotFoundException ex) {
                              Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (IOException ex) {
                              Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                          }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
                else
                {
                     JOptionPane.showMessageDialog(frame, "Kết quả chỉ hiển thị khi bấm nút LOADING và chạy 1 trong các OPT hay là tất cả ", "RESULTS", 
                              JOptionPane.INFORMATION_MESSAGE);
                }
             }   
        });
         setTitle("GAME MOUSEMOVEOUTISLAND");
        setSize(800,700);
        setVisible(true);
    }
 public void CreateButton()
        
{   if(readFile())
            {
                JButton b[][]=new JButton[soDong][soCot];
                pn2=new JPanel();
                        pn2.setLayout(new GridLayout(soDong,soCot-1,10,5));
                         for(int i=0;i<soDong;i++)
                            {
                               for(int j=0;j<soCot;j++)
                                     {
                                        if(SaveMap[i][j]==water)
                                        {
                                        b[i][j]=new JButton(new ImageIcon("./src/dies.png"));
                                        }
                                        else if(SaveMap[i][j]==mouse)
                                        {
                                            b[i][j]=new JButton(new ImageIcon("./src/1.jpg"));
                                        }
                                         else if(SaveMap[i][j]==bay)
                                         {
                                             b[i][j]=new JButton(new ImageIcon("./src/123.jpg"));
                                         }
                                        else if(SaveMap[i][j]==allowedGo)
                                         {
                                             b[i][j]=new JButton("0");
                                         }
                                        pn2.add(b[i][j]);
                                         cn.add(pn2,BorderLayout.CENTER);
                                      }}} 
}
 public static void ranDomAndMoveOpt1(){
        Random rd=new Random();
        int number ;
         int counterDie=0;
         int counterBay=0;
         int counterOutIsLand=0;
         int counterMove=0;
         int dieHungry=0;
         int map[][]=SaveMap; 
         int temp;
         int a=vitrix;
         int b=vitriy;
         FileOutputStream file;
      try {
          file = new FileOutputStream("src\\showMap.txt", true);
          PrintWriter outFile = new PrintWriter(file);
          outFile.println();
          outFile.print("OPT1:");
      OUTER:
      for (int j = 0; j<100; j++) {
          number = rd.nextInt(4);
          System.out.println("\n");
          System.out.println(number);
          temp =map[a][b];
          switch (number) {
              case 0:
                  map[a][b]=mouse;
                  if(map[a-1][b]==bay)
                  {
                      System.out.println("con chuột thấy cái bay");
                      j--;
                      counterBay++;
                      continue;
                  }
                  if (map[a-1][b]==-1) {
                      System.out.println("con chuot da chet");
                      counterDie++;
                      break OUTER;
                  }
                  if (map[a-1][b]==0&&(a==0)&&b!=0&&b!=soCot-1) {
                      map[a-1][b]=temp;
                      System.out.println("con chuot da ra khoi dao");
                      counterOutIsLand++;
                      break OUTER;
                  }
                  map[a-1][b]=temp;
                  a=a-1;
                  break;
              case 1:
                  map[a][b]=mouse;
                  if(map[a][b+1]==bay)
                  {
                      System.out.println("con chuột thấy cái bay");
                      j--;
                      counterBay++;
                      continue;
                  }
                  if (map[a][b+1]==-1) {
                      System.out.println("con chuot da chet");
                      counterDie++;
                      break OUTER;
                  }
                  if (map[a][b+1]==0&&b==soCot-1&&a!=0&&a!=soDong-1) {
                      map[a][b+1]=temp;
                      System.out.println("con chuot da ra khoi dao");
                      counterOutIsLand++;
                      break OUTER;
                  }
                  map[a][b+1]=temp;
                  b=b+1;
                  break;
              case 2:
                  map[a][b]=mouse;
                  if(map[a+1][b]==bay)
                  {
                      System.out.println("con chuột thấy cái bay");
                      j--;
                      counterBay++;
                      continue;
                  }
                  if (map[a+1][b]==-1) {
                      System.out.println("con chuot da chet");
                      counterDie++;
                      break OUTER;
                  }
                  if (map[a-1][b]==0&&(a==soDong-1)&&b!=0&&b!=soCot-1) {
                      map[a+1][b]=temp;
                      System.out.println("con chuot da ra khoi dao");
                      counterOutIsLand++;
                      break OUTER;
                  }
                  map[a+1][b]=temp;
                  a=a+1;
                  break;
              case 3:
                  map[a][b]=mouse;
                  if(map[a][b-1]==bay)
                  {
                      System.out.println("con chuột thấy cái bay");
                      j--;
                      counterBay++;
                      continue;
                  }
                  if (map[a][b-1]==-1) {
                      System.out.println("con chuot da chet");
                      counterDie++;
                      break OUTER;
                  }
                  if (map[a][b-1]==0&&b==0&&a!=0&&a!=soDong-1) {
                      map[a][b-1]=temp;
                      System.out.println("con chuot da ra khoi dao");
                      counterOutIsLand++;
                      break OUTER;
                  }
                 map[a][b-1]=temp;
                  b=b-1;
                  break;
              default:
                  break;
          }
          counterMove++;
           if(counterMove==100)
                  {
                      dieHungry++;
                  }
      }
      outFile.println();
             for(int i=0;i<soDong;i++)
             {
                 System.out.println();
                 outFile.println();
                 for(int j=0;j<soCot;j++)
                 {
                     System.out.print(map[i][j]+"  ");
                     String f=map[i][j]+"  ";
                     outFile.print(f);
                 }
             }
             outFile.close();
             int l=1;
                infomation(counterMove,counterBay,counterDie,dieHungry,counterOutIsLand,l);
                
                } catch (FileNotFoundException ex) {
          Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
      } }
 public static void infomation(int counterMove,int counterBay,int counterDie, int dieHungry,int counterOutIsLand,int l){
                  String Info="OPT"+l+":"+"\n"+"Số lần di chuyển của con chuột: "+counterMove+"\n"+
                        "Số lần con chuột gặp cái bay: "+counterBay+"\n"+
                         "Số lần con chuột chết: "+counterDie+"\n"+
                         "Số lần con chuột chết đói: "+dieHungry+"\n"+
                         "Số lần con chuột ra khỏi đảo"+counterOutIsLand;
             JOptionPane.showMessageDialog(frame, Info, "OPT1", 
                              JOptionPane.INFORMATION_MESSAGE);
             String opt1 = Info;
                try {
                    file = new FileOutputStream("src\\SaveMapOptUser.txt", true);
                     PrintWriter outFileSave = new PrintWriter(file);
                     outFileSave.println();
                     outFileSave.print(opt1);
                      outFileSave.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                }
 }
    public static void ranDomAndMove100TakeDataForUser(){
         String input= JOptionPane.showInputDialog("nhập số mà bạn muốn random :");
        try{
          data=Integer.parseInt(input);
           if(data<0)
           {
               JOptionPane.showMessageDialog(frame, "bạn đã nhâp số âm,bạn hãy nhập số từ 0 đến 10 chẳng hạn ", "MAP", 
                              JOptionPane.INFORMATION_MESSAGE);
                 ranDomAndMove100TakeDataForUser();
           }
            Random rd=new Random(((10*data^2+data*100)%4));
         int number ;
         int counterDie=0;
         int counterBay=0;
         int counterOutIsLand=0;
         int counterMove=0;
         int dieHungry=0;
         
         FileOutputStream files;
          try {
          files = new FileOutputStream("src\\showMap.txt", true);
          PrintWriter outFile = new PrintWriter(files);
          outFile.println();
          outFile.print("OPT2:");
         for(int x=0;x<100;x++)
         {  int map[][]=new int[soDong][soCot];
             for(int i=0;i<soDong;i++)
               {
                   for(int j=0;j<soCot;j++)
                   {
                       
                       map[i][j] =SaveMap[i][j];                  
                   }
               }
             int temp;
                int a=vitrix;
                int b=vitriy;
               OUTER:
               for (int j = 0; j<100; j++) {
                   number = rd.nextInt(4);
                   System.out.println("\n");
                   System.out.println(number);
                   temp =map[a][b];
                   switch (number) {
                       case 0:
                           map[a][b]=mouse;
                           if(map[a-1][b]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a-1][b]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a-1][b]==0&&(a==0)&&b!=0&&b!=soCot-1) {
                               map[a-1][b]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a-1][b]=temp;
                           a=a-1;
                           break;
                       case 1:
                           map[a][b]=mouse;
                           if(map[a][b+1]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a][b+1]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a][b+1]==0&&b==soCot-1&&a!=0&&a!=soDong-1) {
                               map[a][b+1]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a][b+1]=temp;
                           b=b+1;
                           break;
                       case 2:
                           map[a][b]=mouse;
                           if(map[a+1][b]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a+1][b]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a+1][b]==0&&(a==soDong-1)&&b!=0&&b!=soCot-1) {
                               map[a+1][b]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a+1][b]=temp;
                           a=a+1;
                           break;
                       case 3:
                           map[a][b]=mouse;
                           if(map[a][b-1]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a][b-1]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a][b-1]==0&&b==0&&a!=0&&a!=soDong-1) {
                               map[a][b-1]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a][b-1]=temp;
                           b=b-1;
                           break;
                       default:
                           break;
                   }
                   counterMove++;
                    if(counterMove==100)
                  {
                      dieHungry++;
                  }
               }
              outFile.println();
              outFile.println("lan :"+x);
             for(int i=0;i<soDong;i++)
             {
                 System.out.println();
                 outFile.println();
                 for(int j=0;j<soCot;j++)
                 {
                     System.out.print(map[i][j]+"  ");
                     String f=map[i][j]+"  ";
                     outFile.print(f);
                 }}}
             outFile.close();
             int l=2;
           infomation(counterMove,counterBay,counterDie,dieHungry,counterOutIsLand,l);
               }catch(HeadlessException | NumberFormatException ex)
                   {
                    JOptionPane.showMessageDialog(frame, "bạn đã nhập kiểu dữ kiệu dạng chữ.Xin vui lòng nhập số ", "MAP", JOptionPane.INFORMATION_MESSAGE);
                    ranDomAndMove100TakeDataForUser();
                   }
        catch (FileNotFoundException ex) {
          Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
       }}catch (Exception ex) {
          Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
      }}
   public static void ranDomAndMove100InTime(){
          try {      
           Random rd = new Random();
             rd.setSeed(System.currentTimeMillis());    
         int number ;
         int counterDie=0;
         int counterBay=0;
         int counterOutIsLand=0;
         int counterMove=0;
         int dieHungry=0;
        
         FileOutputStream files;
          try {
          files = new FileOutputStream("src\\showMap.txt", true);
          PrintWriter outFile = new PrintWriter(files);
          outFile.println();
          outFile.print("OPT3:");
         for(int x=0;x<100;x++)
         {  int map[][]=new int[soDong][soCot];
             for(int i=0;i<soDong;i++)
               {
                   for(int j=0;j<soCot;j++)
                   {
                      map[i][j] =SaveMap[i][j];                  
                   }
               }
                int temp;
                int a=vitrix;
                int b=vitriy;
               OUTER:
               for (int j = 0; j<100; j++) {
                   number = rd.nextInt()%4;
                   System.out.println("\n");
                   System.out.println(number);
                   temp =map[a][b];
                   switch (number) {
                       case 0:
                           map[a][b]=mouse;
                           if(map[a-1][b]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a-1][b]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a-1][b]==0&&(a==0)&&b!=0&&b!=soCot-1) {
                               map[a-1][b]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a-1][b]=temp;
                           a=a-1;
                           break;
                       case 1:
                           map[a][b]=mouse;
                           if(map[a][b+1]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a][b+1]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a][b+1]==0&&b==soCot-1&&a!=0&&a!=soDong-1) {
                               map[a][b+1]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a][b+1]=temp;
                           b=b+1;
                           break;
                       case 2:
                           map[a][b]=mouse;
                           if(map[a+1][b]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a+1][b]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a+1][b]==0&&(a==soDong-1)&&b!=0&&b!=soCot-1) {
                               map[a+1][b]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a+1][b]=temp;
                           a=a+1;
                           break;
                       case 3:
                           map[a][b]=mouse;
                           if(map[a][b-1]==bay)
                           {
                               System.out.println("con chuột thấy cái bay");
                               j--;
                               counterBay++;
                               continue;
                           }   if (map[a][b-1]==-1) {
                               System.out.println("con chuot da chet");
                               counterDie++;
                               break OUTER;
                           }
                           if (map[a][b-1]==0&&b==0&&a!=0&&a!=soDong-1) {
                               map[a][b-1]=temp;
                               System.out.println("con chuot da ra khoi dao");
                               counterOutIsLand++;
                               break OUTER;
                           }
                           map[a][b-1]=temp;
                           b=b-1;
                           break;
                       default:
                           break;
                   }
                   counterMove++;
                    if(counterMove==100)
                  {
                      dieHungry++;
                  }
               }
             outFile.println();
              outFile.println("lan :"+x);
             for(int i=0;i<soDong;i++)
             {
                 System.out.println();
                 outFile.println();
                 for(int j=0;j<soCot;j++)
                 {
                     System.out.print(map[i][j]+"  ");
                     String f=map[i][j]+"  ";
                     outFile.print(f);
                 }
             }
             }
         outFile.close();
         int l=3;
          infomation(counterMove,counterBay,counterDie,dieHungry,counterOutIsLand,l);
                } catch (HeadlessException | FileNotFoundException ex) {
                    Logger.getLogger(MouseMoveOutIsland.class.getName()).log(Level.SEVERE, null, ex);
                }
          }catch (Exception ex) {
                    System.out.println(" ");
                    ranDomAndMove100InTime();
          }
    }
    
        public static boolean readFileNoCheckOut(String url,String input)
        {
            try {
            Scanner inFile=new Scanner(new File(url));
            JOptionPane.showMessageDialog(frame, "bạn đã loading được MAP", "MAP", 
                              JOptionPane.INFORMATION_MESSAGE);
            while(inFile.hasNext()){
                int sodong=inFile.nextInt();
                int socot=inFile.nextInt();
                soDong=sodong;
                soCot=socot;
                if(sodong>maxSize||socot>maxSize)
                {
                    JOptionPane.showMessageDialog(frame, "bạn đã nhập file quá kích thước,hãy thử file Map dưới 20X20", "MAP", 
                              JOptionPane.INFORMATION_MESSAGE);
                    readFile();
                }
                int Map[][]=new int[sodong][socot];
                System.out.println();
                for(int i=0;i<sodong;i++)
                {
                    for(int j=0;j<socot;j++){
                       
                        Map[i][j] = inFile.nextInt();
                         
                       
                    }
                }
                SaveMap=new int[sodong][socot];
               for(int i=0;i<sodong;i++)
               {
                   for(int j=0;j<socot;j++)
                   {
                       SaveMap[i][j]=Map[i][j];
                       if(SaveMap[i][j]==mouse){
                            vitrix=i;
                            vitriy=j;
                       }
                   }
               }
                for (int i = 0; i < sodong; i++) {
                    for (int j = 0; j < socot; j++) {
                        System.out.print(Map[i][j] + "\t");
                        }
        // sau khi viết xong 1 dòng thi xuống hàng
        System.out.println("\n");   
                    }
            }
            
            inFile.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("no open file "+input);
            JOptionPane.showMessageDialog(frame, "bạn đã nhập sai tên file vui lòng nhập lại", "MAP", 
                              JOptionPane.INFORMATION_MESSAGE);
            readFile();
          }
            return false;
        }
     public static boolean readFile(){
          
         try{ input= JOptionPane.showInputDialog("nhập file mà bạn muốn load map :");
         String url ="src\\"+input;
         System.out.println(url);
         Url=url;
         readFileNoCheckOut(Url,input);
         } catch(Exception ex )
         {
             System.out.println("");
         }
        return true;
     }
    public static void main(String[] args) {
        new MouseMoveOutIsland();
     }
    
}
