package genatic_algorithms.pkg3;

import static genatic_algorithms.pkg3.Genatic_Algorithms3.inputVars;
import static genatic_algorithms.pkg3.Genatic_Algorithms3.outputVar;
import static genatic_algorithms.pkg3.Genatic_Algorithms3.removeSpace;

/**
 * @author White Raven
 */
public class Sentence {
    int numOfPrem ;
    String [] subSent ;

    public Sentence(int numOfPrem, String rule) {
        this.numOfPrem = numOfPrem;
        subSent = rule.split("(((?<=AND)|(?=AND))|((?<=OR)|(?=OR))|((?<=THEN)|(?=THEN)))");
        double value = cropSet(subSent[0]);
        for(int i = 1 ; i<subSent.length-3 ;i+=2){
            if(subSent[i].equals("AND")){
                value = Math.min(value , cropSet(subSent[i+1]));
            }
            else if(subSent[i].equals("OR")) {
                  value = Math.max(value , cropSet(subSent[i+1]));
            }
            else {
                System.out.println("Error Invalid Input For The Rule ");
            }
        }
        setTheValueToSet(subSent[subSent.length-1], value);
    }

    public static double cropSet(String set) {
        String []values = set.split("=");
        double output  = 0.0 ;
        for(FuzzyVariable fv : inputVars){
            values[0] = removeSpace(values[0]);
            String nameOfVar = removeSpace(fv.nameOfVar);
            if(values[0].equals(nameOfVar)){
                for(FuzzySet fs : fv.varSets){
                    String nameOfSet = removeSpace(fs.nameOfSet);
                    values[1] = removeSpace(values[1]);
                    if(nameOfSet.equals(values[1])){
                        output =  fs.fuzzificationValue;
                    }
                }
            }
        }
        return  output;
    }
    public static void setTheValueToSet(String set , double value){
        String [] values = set.split("=");
        /* FIND THE SET */
        for(FuzzySet fs : outputVar.varSets){
            String s = removeSpace(fs.nameOfSet);
            values[1] = removeSpace(values[1]);
            if(s.equals(values[1])){
                fs.fuzzificationValue = value ;
            }
        }
    }
}
