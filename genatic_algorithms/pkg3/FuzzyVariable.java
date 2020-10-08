package genatic_algorithms.pkg3;

import java.util.ArrayList;

/**
 * @author White Raven
 */
public class FuzzyVariable {
    public String nameOfVar;
    public int crisp ;
    public int numOfSets;
    public ArrayList<FuzzySet> varSets = new ArrayList<>();
    
    public void fuzzy(){
        for(FuzzySet fs : varSets){
            for(int i = 0 ; i<fs.valuesOfSet.size()-1;i++){
                if(crisp>= fs.valuesOfSet.get(i) && crisp<=fs.valuesOfSet.get(i+1))
                    fs.fuzzificationValue = intersection(crisp ,  fs.valuesOfSet.get(i) , fs.valuesOfSet.get(i+1) , i , fs.valuesOfSet.size());
            }
        }
    }

    public double intersection(int crisp, Integer x1, Integer x2, int position , int size) {
        double b;
        double slope;
        int y1 = 0 , y2 = 0;
        if(size == 3){
            if(position == 0 ){
                y1 = 0;
                y2 = 1;
            }
            else{
                y1 = 1;
                y2 = 0;
            }
        }
        if(size == 4){
            if(position == 0){
                y1 = 0;
                y2 = 1;
            }
            else if(position == 1){
                y1 = y2=1;
            }
            else{
                y1 = 1;
                y2 = 0;
            }
        }
        slope = (double)(y2-y1)/(double)(x2-x1);
        b = y1 - (slope*x1);
        return crisp*slope + b;
    }
    
}
