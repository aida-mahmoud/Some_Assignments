package lz78_ass; import java.io.*; 
import java.util.ArrayList;
import java.util.Scanner;
public class LZ78_ASS {
    public static void main(String[] args) { while (true){
        System.out.println("Enter    1_For Compress\n\t 2_for decompress\n\t 3_Exit");
         Scanner in =new Scanner(System.in);
    int choice = in.nextInt();     
    if(choice==1){ //Comp
        try{        BufferedWriter fin=null ;
        File ins= new File("theinput.txt");
        BufferedReader the_input =null;String s=null;
             the_input =new BufferedReader(new FileReader (ins)); s=the_input.readLine();
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
                        tags.add("<"+ IND +"," + null+">"); // System.out.println("<"+ IND +"," + null+">" );
                }
                else
                {   Dec.add(buf);  buf="";
                 tags.add("<"+ IND +"," + ch+">");IND=0; //System.out.println("<"+ IND +"," + ch+">" );  
                }
            }
           // System.out.println(Dec);
            File F = new File ("The decom_data.txt");
            fin = new BufferedWriter(new FileWriter(F));
            for(int i=0 ; i<tags.size();i++) {
                fin.write(tags.get(i));
            }
            fin.close();
        } catch (IOException ex) {
            System.out.println("Error");        } 
        } //////////////////////////////////////*************************************Decom
    else if(choice==2){          String d=""; 
    ArrayList <String> dec=new ArrayList <String> () ; dec.add(""); 
        try {  String str="" ;         
        File ins = new File("The decom_data.txt"); 
        BufferedReader the_output = new BufferedReader(new FileReader(ins));  String   st="";
        while((st=the_output.readLine())!=null){////////////////////////////////////////////////
         str=st;
        }
            for(int i=1;i< str.length()-1; i+=5) 
            { int pre;      char h=str.charAt(i);                    
                 pre = Character.getNumericValue(h);       
                char next='0' ; int a =i+2;
                next =str.charAt(a); d=Character.toString(h); char koma=str.charAt(i+1);
                if(pre==0)
                {
                    dec.add(Character.toString( next) ); 
                }
                else
                { 
            while(koma!=','){  //  System.out.println(h);
                  d+=Character.toString(koma)  ; 
                    i=i+1; 
                    koma=str.charAt(i+1);
                    if(koma==','){
                        next =str.charAt(i+2);
                        pre=Integer.parseInt(d); //System.out.println("pre"+d);
                      d="";  break;                  
                    } }                                            
                    if(next=='n'&&str.charAt(i+3)=='u' ){
                    String m=dec.get(pre);
                    dec.add(m );   break;}
                String v="";v=dec.get(pre);
                dec.add( v+Character.toString(next)); // System.out.println(next);
            }            }                  
                          BufferedWriter f=null ;
            File fread =new File ("Data.txt");
            f = new BufferedWriter (new FileWriter(fread));
            for(int i=0 ;i<dec.size();i++)
            {
                f.write(dec.get(i));
            }
            f.close();// System.out.println("str"+str);
         
        } catch (IOException ex) {
            System.out.println("Error");
        } 
    }   else break ;  }}}
    