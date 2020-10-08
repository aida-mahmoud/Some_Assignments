package genatic_algorithms.pkg3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author White Raven
 */
public class Genatic_Algorithms3 {

    public static ArrayList<FuzzyVariable> inputVars = new ArrayList<>();
    public static FuzzyVariable outputVar = new FuzzyVariable();

    public static void main(String[] args) {
        // TODO code application logic here

        int numOfInputVars ;
        FuzzyVariable fuzVar;
        FuzzySet fuzSet ;
        int numOfValuesForSet ;
        int valueForSet ;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter The ** Number Of Input ** Variables : ");
        numOfInputVars = in.nextInt();
        for (int i = 0; i < numOfInputVars; i++) {
            // read data
            fuzVar = new FuzzyVariable();
            System.out.print("Please Enter The ** Name **Of Variable " + (i+1 )+" : ");
            fuzVar.nameOfVar = in.next();
            System.out.print("Please Enter The **Crisp** Of Variable " + (i+1 )+" : ");
            fuzVar.crisp = in.nextInt();
            System.out.print("Please Enter The **Number Of Sets**  for  Variable " + (i+1 )+" : ");
            fuzVar.numOfSets = in.nextInt();
            for(int j = 0 ; j<fuzVar.numOfSets ; j++){
                fuzSet = new FuzzySet();
                System.out.println("Please Enter The **Name And The Type** Of Set " + (j+1 )+" For  Variable " + (i+1 )+" =>");
                fuzSet.nameOfSet = in.next();
                fuzSet.typeOfSet = in.next();
               numOfValuesForSet = getNumOfValuesFromTtype(fuzSet.typeOfSet);
               if(numOfValuesForSet==-1){
                System.out.println("Error , Invalid Input For The Type Of Set");
                return;
               }
               System.out.println("Please Enter The **Values** Of Set " + (j+1 )+" For  Variable " + (i+1 )+" => ");
               for(int k =0 ; k<numOfValuesForSet ; k++){
                   valueForSet = in.nextInt();
                   fuzSet.valuesOfSet.add(valueForSet);
               }
               fuzVar.varSets.add(fuzSet);
            }
            inputVars.add(fuzVar);
        }
        // OUTPUT  VARIABLE
        System.out.print("Please Enter The **Name **Of Output Variable : ");
        outputVar.nameOfVar = in.next();
        System.out.print("Please Enter The **Number Of  Sets** For Output Variable : ");        
        outputVar.numOfSets = in.nextInt();
        for(int j = 0 ; j<outputVar.numOfSets ; j++){ //5
            fuzSet = new FuzzySet();
            System.out.println("Please Enter The **Name And The Type** Of Set " + (j+1) +"For Output Variable =>");
            fuzSet.nameOfSet = in.next();
            fuzSet.typeOfSet = in.next();
            numOfValuesForSet = getNumOfValuesFromTtype(fuzSet.typeOfSet);
            if(numOfValuesForSet==-1){
                System.out.println("Error , Invalid Input For The Type Of Set");
                return;
            }
            System.out.println("Please Enter The **Values** Of Set " + (j+1) +" For OutPut Variable  =>");
            for(int k = 0 ; k<numOfValuesForSet ; k++){
                valueForSet = in.nextInt();
                fuzSet.valuesOfSet.add(valueForSet);
            }
            outputVar.varSets.add(fuzSet);
        }
        startFuzzification();
        /*** Read Rules ***/
        System.out.print("Please Enter The **Number Of Rules** : ");
       int numOfRules = in.nextInt();
       for(int i = 0 ; i<numOfRules ; i++){
           System.out.println("Please Enter The **Rule** (Number Of Premises + Rule) =>");
           int num = in.nextInt();
           String rule = in.nextLine();
           rule = removeSpace(rule);
           Sentence sen = new Sentence(num, rule );
       }
       inferenceOfRules();
       defuzzificationOutput();
        
    }

    private static int getNumOfValuesFromTtype(String typeOfSet) {
        if(typeOfSet.equals("triangle")){
            return 3;
        }
        if(typeOfSet.equals("trapezoidal")){
            return 4;
        }
        return -1;
    }

    private static void startFuzzification() {
        for(FuzzyVariable fv : inputVars){
            fv.fuzzy();
             System.out.println("\nVariable ( " + fv.nameOfVar+ " ) Fuzzification ");
            for(FuzzySet fs : fv.varSets){
                System.out.println("Set " + fs.nameOfSet + "  value is " +fs.fuzzificationValue );
            }
             System.out.println();
        }
    }

    private static void inferenceOfRules() {
        System.out.println("\nThe OutPut Variable " + outputVar.nameOfVar + " Inferance :");
        for(FuzzySet fs : outputVar.varSets){
            System.out.println("Set " + fs.nameOfSet + "  value is " +fs.fuzzificationValue );
        }
        System.out.println();
    }
    
    /*** DefuzzifyUsing Weighted Average  method***/
    private static void defuzzificationOutput() {
        ArrayList<Double> weights = new ArrayList<>();
        for(FuzzySet fs : outputVar.varSets){
            int sum = 0;
            for(Integer i : fs.valuesOfSet){
                sum +=i;
            }
            double weight = (double)(sum / fs.valuesOfSet.size());
            weights.add(weight);
        }
        double sumOfWeightedValues =0.0;
        double sumOfValues =0.0;
        for(int i = 0 ; i<weights.size() ; i++){
            sumOfWeightedValues += weights.get(i) * outputVar.varSets.get(i).fuzzificationValue;
            sumOfValues += outputVar.varSets.get(i).fuzzificationValue;
        }
        double defz = sumOfWeightedValues /sumOfValues;
        System.out.println("*************************");
        System.out.println("\nZ* = " + defz + "\n");
        System.out.println("*************************");
    }
    public static String removeSpace(String x){
        x= x.replace(" ", "");
        return x ;
    }

}
