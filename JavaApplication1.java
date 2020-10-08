 package javaapplication1; import java.io.*;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner; import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
 import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger; import java.time.DateTimeException;
public class JavaApplication1 {    
    public static void main(String[] args)  { 
       String input ="aidajkh hh>hshshdu";
               int a=input.indexOf('>');
        
        String con =input.substring(0, a);System.out.println(con);
       String con2 =input.substring(a+1, input.length());System.out.println(con2);
        }       
    } 
 
        
        
}} 
       /* Scanner In =new Scanner(System.in);
    int choice = In.nextInt();   if(choice==1){        
        BufferedWriter f=null ;
        try {
            ArrayList <String> dec=new ArrayList <String> () ;
            File ins= new File("The output.txt");
            BufferedReader the_output =new BufferedReader(new FileReader (the_output)); 
            int h=theoutput.r
            String str="<0,a><0,b><1,a><2,a><4,a><0,c><0,d><6,d><1,null>" ;
            dec.add(" ");
            for(int i=1;i< str.length()-1; i=i+5)
            {
                int pre = Character.getNumericValue(str.charAt(i));
                char next =str.charAt(i+2);
                if(pre==0)
                {
                    dec.add(Character.toString( next) );
                }
                else
                { if(next=='n'&&str.charAt(i+3)=='u' ){
                    String m=dec.get(pre);
                    dec.add(m );   break;}
                String v=dec.get(pre);
                dec.add( v+Character.toString(next));
                }          
            }   File fread =new File ("Read_Data.txt");
            f = new BufferedWriter (new FileWriter(fread));
            for(int i=0 ;i<dec.size();i++)
            {
                f.write(dec.get(i));
            }
            f.close();
            System.out.println(dec);
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    if(choice==2){ 
        try{        BufferedWriter fin=null ;
       String s="abaababbaaabababbbbb";
            ArrayList <String> Dec=new ArrayList <String> () ;
            ArrayList <String> tags=new ArrayList <String> () ;
            char ch;
            String buf="";
            Dec.add("");
            int IND=0 , ind ;
            for( int p=0 ; p <s.length() ; p++)///////////////////////////////////////////
            {
                ch=s.charAt(p);
                buf+=ch;
                ind =Dec.indexOf(buf);
                if (ind!=-1) ///موجود
                {
                    IND=ind;    if(p==s.length()-1)   
                        tags.add("<"+ IND +"," + null+">");// System.out.println("<"+ IND +"," + null+">" );
                }
                else
                {   Dec.add(buf);buf="";
                 tags.add("<"+ IND +"," + ch+">"); //System.out.println("<"+ IND +"," + ch+">" );  IND=0;
                }
            }
            System.out.println(Dec);
            File F = new File ("The output.txt");
            fin = new BufferedWriter(new FileWriter(F));
            for(int i=0 ; i<tags.size();i++) {
                fin.write(tags.get(i));
            }
            fin.close();
            //F.close();
        } catch (IOException ex) {
            System.out.println("Error");
        } 
          
        }
	}
    }
      
    
		File inputFile = new File(path) ;
		BufferedReader theInput = new BufferedReader(new FileReader(inputFile));
		int reader=theInput.read();
		StringBuilder data = new StringBuilder();
		while(reader != -1) {
			data.append((char)reader);
			reader=theInput.read();
		}*/
/*
		******************* SAVE TO FILE ******************
		File compFile = new File ("The Tags.txt");
		BufferedWriter file = new BufferedWriter(new FileWriter(compFile));
		for(int i=0 ; i<indInDuc.size();i++) {
			file.write(indInDuc.get(i).toString());
			file.write(nextChar.get(i));
			//file.write("\n");
		}
		file.close();
		theInput.close();
	}
 **/