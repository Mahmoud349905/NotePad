
package notepadsample;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class NotePadSample extends JFrame implements ActionListener,Runnable{
    // int x = 0;
    JButton open,save,count,remove,replaceAll,cancel;
    JLabel pathLb,removeLb,pathsave,key,newKey;
    JTextField pathTf,removeTf,newPath,keyTf,newKeyTf;
    JTextArea notepad;
    JPanel head,center;
    String keyval,newKeyval,txt,output;
    JProgressBar dataTransfer;
    int check=0 ;
    
    int x;
    JFrame f1=new JFrame();
    Font f = new Font("Arial", Font.BOLD + Font.ITALIC, 30);
    Timer time = new Timer(0, this);
    Thread th =new Thread(this);
/********************************************************************************/
    public NotePadSample() {
        
        
       open             =new JButton("Open");
        save            =new JButton("save");
        count           =new JButton("Count");
        remove          =new JButton("Remove");
        replaceAll      =new JButton("Replace All");
        cancel          =new JButton("Cancel");
        pathLb          =new JLabel("File Path");
        removeLb        =new JLabel("A Word to remove");
        pathsave        =new JLabel("Path to save");
        key             =new JLabel("A Key");
        newKey          =new JLabel("New Key");
        dataTransfer= new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
/********************************************************************************/
        center          =new JPanel();
        head            =new JPanel();
        removeTf        =new JTextField();
        pathTf          =new JTextField();
        newPath         =new JTextField();
        keyTf           =new JTextField();
        newKeyTf        =new JTextField();
        notepad         =new JTextArea();
        
        pathLb.setSize(200, 40);
        head.setLayout(new GridLayout(5,3, 10, 10));

//************************************************************************************//
        open.addActionListener(this);
        save.addActionListener(this);
        count.addActionListener(this);
        remove.addActionListener(this);
        cancel.addActionListener(this);
        replaceAll.addActionListener(this);
        
 //************************************************************************************//
        //dataTransfer.setSize(400, 80);
        f1.setLayout(null);
        dataTransfer.setBounds(0, 20, 400, 30);
        f1.add(dataTransfer);
       
        
        head.add(pathLb);
        head.add(pathTf);
        head.add(open);
        head.add(pathsave);
        head.add(newPath);
        head.add(save); 
        head.add(removeLb);
        head.add(removeTf);
        head.add(remove);
        head.add(key);
        head.add(keyTf);
        head.add(replaceAll);
        head.add(newKey);
        head.add(newKeyTf);
        head.add(cancel);
        
        
        dataTransfer.setFont(f);
        dataTransfer.setBackground(Color.LIGHT_GRAY);
        dataTransfer.setForeground(Color.GREEN);
        dataTransfer.setBorderPainted(true);
        dataTransfer.setStringPainted(true);
        
/****************************************************************************************/
        setLayout(new BorderLayout());
        add(head,BorderLayout.NORTH);
        add(notepad,BorderLayout.CENTER);
        add(count,BorderLayout.SOUTH);
/****************************************************************************************/
        setVisible(true);
        setLocation(300, 80);
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        f1.setSize(400, 100);
        f1.setVisible(false);
        
        f1.setLocation(300, 200);
        
        
    }
    
    
/******************************************************************************/
    
    public static void main(String[] args) {

     new NotePadSample();
    }
    
    
/********************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
       
                
        if(e.getSource()==open){  
            
           
             //pack();       this make the size of the frame suitable for the componnents 
            notepad.setText("");
            String path =pathTf.getText();
            File f=new File(path);
            try {
                Scanner s=new Scanner(f);
                if(check==0){
                    
                    
                f1.setVisible(true);
                th.start();
                check++;
                }else{
                    dataTransfer.setValue(0);
                    f1.setVisible(true);
                    th.resume();
                    
                }
                while(s.hasNext()){
                    
                notepad.append(s.nextLine()+"\n");
                
                //JOptionPane.showMessageDialog(null, "Open File done !!! ");
                
                }
                
                s.close();
                
            } catch (FileNotFoundException ex) {
                System.out.println("Error happpen during open the file");
            }
            
            
            
        }
/******************************************************************************/
        else if(e.getSource()==save){
            //x=0;
            dataTransfer.setValue(0);
            th.resume();
            //th.start();
             String path =newPath.getText();
             File f=new File(path);
                try {
                    f1.setVisible(true);
//                th.start();
                    
                    PrintWriter p=new PrintWriter(f);
                    String ss=notepad.getText();
                    p.append(ss);        
                    p.close();
                    
                } catch (FileNotFoundException ex) {
                     System.out.println("Error happpen during Save the file");
                }
            
        }
/*******************************************************************************/
        else if(e.getSource()==remove){
            String ss=notepad.getText().trim();
            String st=removeTf.getText();
            ss=ss.replaceAll(st, "");
            notepad.setText(ss);   
        }
/******************************************************************************/
        else if(e.getSource()==count){
            int wordsCount=0;
            int lineCount=0;
            int numOfLetters=0;
            
                String path =pathTf.getText();
                File f=new File(path);
          
            try {
                 Scanner s = new Scanner(f);
                 
            while(s.hasNext()){
               s.nextLine();
               
                lineCount++;
            }
            output="Number of Lines in the File are :  "+lineCount+"Lines\n";

            s.close();
               Scanner s1 = new Scanner(f);
            while(s1.hasNext()){
                 String str=s1.next();
                 numOfLetters+=str.length();
                 wordsCount++;
           
            }
            output+="Number Of Words are : "+wordsCount+"Words\n";
            output+="Number Of Letters are : "+numOfLetters+"Letters\n";
            JOptionPane.showMessageDialog(null, output);
            s1.close();
            }catch (FileNotFoundException ex) {
                System.out.println("Error happpen during Count the file");
            }          
            
        }
/**********************************************************************************/
        
        else if(e.getSource()==replaceAll)
        {
            
        txt=notepad.getText().trim();
        keyval=keyTf.getText().trim();
        newKeyval=newKeyTf.getText().trim();
        
        if(txt.length()>0 && keyval.length()>0)
        {
            txt=txt.replaceAll(keyval, newKeyval);
            notepad.setText(txt);
        }
        
    }
/******************************************************************************/
        else if(e.getSource()==cancel){
            System.exit(0);
        }
    }

    @Override
    public void run() {
            f1.setVisible(true);
            while(x<=100){
          
             try {
                 //JOptionPane.showMessageDialog(null, "this is a test for the seond time ");
                th.sleep(5);
                 dataTransfer.setValue(x++);
             } catch (Exception ex) {
                System.out.println("Error Happend in progress bar !!!!!");
            }
             
             if(x==101){
                 th.suspend();
                 x=0;
             }
                }
            f1.setVisible(false);
            
            
           // time.stop();
            
    }

  
}
