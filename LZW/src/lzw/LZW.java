package lzw;
import java.util.ArrayList;import java.io.*;
import java.util.Scanner;
public class LZW {
    public static void main(String[] args) throws IOException {
        while (true){
        System.out.println("Enter    1_For Compress\n\t 2_for decompress\n\t 3_Exit");
         Scanner in =new Scanner(System.in);
    int choice = in.nextInt();     
    if(choice==1){
    ArrayList <String> mydic=new ArrayList<>();
     ArrayList <Integer> myinde=new ArrayList<>();
    String T ="", mystr =""; 
    File f=new File("original_data.txt");
        try {
            BufferedReader readdata=new BufferedReader(new FileReader(f));           String file="";
            while((file=readdata.readLine())!=null)
            {
                mystr=file;
            }
            } catch (FileNotFoundException ex) { }///////////////////////////////////////////
    char S=' ';
    int INDEX;
    for (char x =0 ;x<='~';x++)/////////////////fill the dic
    {
        mydic.add(Character.toString(x));
    } mydic.add("[Del]");////////////////fill my dic
            T+=mystr.charAt(0);int i;
            INDEX=mydic.indexOf(T);    // System.out.println("<"+str.length()+">");
            
    for( i=1;i <( mystr.length()+1);i++)
    {   
        if (i==mystr.length())
          { 
              if(mydic.contains(T))
                { 
               myinde.add(mydic.indexOf(T)) ;//System.out.println("<"+mydic.indexOf(T)+">"); 
                break;
    }
    else  {  mydic.add(T);  myinde.add(mydic.indexOf(T)) ;
    //System.out.println("<"+mydic.indexOf(T)+">");  
          break;
          }
    } //i=35-1 يعني اخر لوب// T=B
    else{ S=mystr.charAt(i); 
        if(mydic.contains(T+S) )
        {
            T+=S;          INDEX=mydic.indexOf(T); 
        }
        else 
        { INDEX=mydic.indexOf(T);
            mydic.add(T+S);
            T=Character.toString(S);//B
           myinde.add(INDEX) ;// System.out.println("<"+INDEX+">"); 
        }
        }  } System.out.println(myinde);
    try { File Fwrite =new File ("decom_data.txt");////////////?????????????????????????
        BufferedWriter  outind =null; 
        outind= new BufferedWriter (new FileWriter(Fwrite));
            for(int g=0 ;g< myinde.size();g++)
            {  
                outind.write('<');  outind.write(Integer.toString(myinde.get(g))); outind.write('>');
            }outind.close(); } catch (FileNotFoundException ex) { }  
    }
    
    
    /******************** Decompression *************************/
    else if(choice==2){
    ArrayList <String> dic=new ArrayList<>(); 
    ArrayList <Integer> dicint=new ArrayList<>();   String Cur ="",/* my data*/str="" ;
    File f=new File("decom_data.txt");
        try {
            BufferedReader readdata=new BufferedReader(new FileReader(f));           String file="";
            while((file=readdata.readLine())!=null)
            {
                str=file;
            }
        } catch (FileNotFoundException ex) { }
    String Pre="";//buff
    for (char x =0 ;x<='~';x++)//FILL the dic
    {
        dic.add(Character.toString(x));
    }dic.add("[Del]"); 
    String output="" ;/////////////
    for(String buf : str.split(">"))
    {
         buf=buf.substring(1);
        dicint.add(Integer.parseInt(buf));    
    }  /////////////////////////////////////////////
    int h=(dic.size() -1);
   int Index=0;//=dicint.get(0);//first index
   try{
for(int i=0;i<(dicint.size()) ;i++)
{  Index=dicint.get(i); 
    if( h <= Index)
    {   
        while (dic.contains(Pre))
      {
          Pre+=Pre.charAt(0);
      }     
      {dic.add(Pre);Cur=dic.get(Index);
    }
    }
    else 
    {    Cur=dic.get(Index);        
    Pre+=Cur.charAt(0); 
    if (!dic.contains(Pre))
    {dic.add(Pre);Pre=Cur;h=dic.size();}
    }
     output +=Cur ;
}
   //System.out.println(output);   System.out.println(output.length());
   File fwrite =new File ("myData.txt");
        BufferedWriter    out =null;out= new BufferedWriter (new FileWriter(fwrite));
            for(int i=0 ;i<output.length();i++)
            {
                out.write(output.charAt(i));
            }            out.close();       }
            catch (FileNotFoundException ex) {  System.out.println("Error");}
       }
    else break;}
    }   
}
