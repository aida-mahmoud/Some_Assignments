package genatic_algorithm_assign_2;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import java.io.*;
import static java.lang.Math.random;
import java.util.*;
import javafx.util.Pair;

public class Genatic_Algorithm_Assign_2 {

    static ArrayList<Chromosome> copy = new ArrayList<>();
    static ArrayList<Chromosome> Init_Pop = new ArrayList<>();
    static ArrayList<Float> Diff_BettweenYCYA = new ArrayList<>();
    static ArrayList<Float> Mean_S_E = new ArrayList<>();
    static ArrayList<Point> Points = new ArrayList<Point>();
    static int size, numOfSamples, degree, numOfPoints;
    static int t = 0;
    static int T = 10000;

    static public boolean com(float a[]) //compair arrays of coefficients
    {
        ArrayList<Float> d = new ArrayList<>();
        for (int j = 0; j < a.length; j++) {
            d.add(a[j]);
        }
        boolean x = false;
        for (int i = 0; i < Init_Pop.size(); i++) {
            //    for (int j =0 ;j<Init_Pop.get(i).genes.length;j++){
            if (Init_Pop.get(i).genes.equals(d)) {

                return true;
            } // }

        }
        return x;
    }

    /**
     * ********** CREATION OF INTIAL POPULATION OF COEFFECIENTS ***********
     */
    public static void Create_Init_pop() {
        for (int count = 0; count < 10000; count++) {
            Chromosome c = new Chromosome(degree+1);///// ai =>cromosome
            for (int ii = 0; ii < degree+1; ii++) ///genes of cromosome
            {
                Random rand = new Random();
                float randomNum =(float) -10.0 + rand.nextFloat() * ((float)10.0 + (float)10.0);
                c.genes[ii] = randomNum;            /////
            }
            if (!(com(c.genes))) {
                Init_Pop.add(c);
            }
        }

    }

    /**
     * *********************** EVALUATION OF THE CHROMOSOMES TO FIND THE VALUES
     * ************************
     */
    public static void Evaluation() {
        float sum = 0;
        for (int j = 0; j < Init_Pop.size(); j++) { ///
            float y_calc = 0;

            for (int x = 0; x < Points.size() - 1; x++) { ///all cromo     
                // System.out.println("x: "+x);
                for (int i = 0; i < degree; i++) {///genes

                    y_calc += (float) (Init_Pop.get(x).genes[i] * (Math.pow((Points.get(x).x), i)));
                }

                Diff_BettweenYCYA.add(y_calc - Points.get(x).y); ///

                Points.get(x).All_Diff.add(Points.get(x).y - y_calc);
                y_calc = 0;
                sum += (Math.pow((Diff_BettweenYCYA.get(x)), 2));
                // sum+=y_calc - Points.get(x).y;

            }
            float m = (1 / (Points.size()));
            Mean_S_E.add((sum / (Points.size())));
            //System.out.println("Mean_S_E   :::  "+Mean_S_E.size());

            //  sum = 0;
        }
        //  Mean_S_E.add(Math.pow(sum,2)/Points.size());

    }

    /**
     * ******************** SELECTION *************************
     */
    static ArrayList<Float> Selection() { //selection of the best half depends on best values 
        ArrayList<Float> copyOfOriginal = new ArrayList<>();
        copyOfOriginal.addAll(Mean_S_E);  //copy of original coe

        Collections.sort(copyOfOriginal);//////Mean square error bs mtrtb
        for (int j = 0; j < copyOfOriginal.size(); j++) {
            copy.add(Init_Pop.get(Mean_S_E.indexOf(copyOfOriginal.get(j))));
        }
        int s = copyOfOriginal.size();
        int i = s / 2;
        while (s > i) {
            copy.remove(i);
            copyOfOriginal.remove(i);
            s--;
        }
        //System.out.println("copyy  " + copy.size());
        //System.out.print(copyOfOriginal.size());
        return copyOfOriginal;

    }

    static void CrossOver(ArrayList<Chromosome> pop, ArrayList<Float> Evaluation) {///*************************
        float[] min = {10000, 10000};
     //   System.out.print(pop.size() + "*****" + Evaluation.size());

        int[] minIndex = {-1, -1};
        int loop = 0;
        double Pc = 0.7;
        int sizzzzzze = pop.size() / 2;
        if (random() < Pc) {
            while (sizzzzzze > 0) {
                while (loop < 2) {
                    for (int i = 0; i < pop.size(); i++) {
                        int index = Init_Pop.indexOf(pop.get(i));
                        if (Evaluation.get(index) < min[loop]) {
                            min[loop] = Evaluation.get(index);
                            minIndex[loop] = i;
                        }
                    }
                    if (minIndex[0] == -1 || minIndex[1] == -1) {
                        break;
                    }

                    Evaluation.set(minIndex[loop], (float) 10000.0);

                    loop++;
                }
                //  System.out.print(minIndex[0]+"*****"+minIndex[1] );
                if (minIndex[0] == -1 || minIndex[1] == -1) {
                    break;
                }
                float[] indi1 = {0}, indi2 = {0};
                for (int i = 0; i < pop.get(minIndex[0]).genes.length; i++) {
                    if (i < pop.get(minIndex[0]).genes.length / 2) {
                        indi1[i] = pop.get(minIndex[0]).genes[i];
                        indi2[i] = pop.get(minIndex[1]).genes[i];

                    } else {
                        indi1[i] = pop.get(minIndex[1]).genes[i];
                        indi2[i] = pop.get(minIndex[0]).genes[i];
                    }
                }
                indi1 = Mutation(indi1);
                indi2 = Mutation(indi2);
                pop.get(minIndex[0]).genes = indi1;
                pop.get(minIndex[1]).genes = indi2;
                min[0] = 10000;
                min[1] = 10000;
                loop = 0;
                sizzzzzze--;
            }
        }
        for (int i = 0, j = pop.size(); i < pop.size(); i++, j++) {
            Init_Pop.add(j, pop.get(i));
        }
    }

//        for (int i = 0, j = pop.size(); i < pop.size(); i++, j++) {
    //         Init_Pop.add(j, pop.get(i));
    //System.out.println("inside loop");
    static float[] Mutation(float[] gens) {
        double Pm = 0.1, Delta;
        int indexToFlip = gens.length / 2; //// String 
        int UB = 10, LB = -10;
        boolean upper = false;
        if (random() <= Pm) {
            if (random() > 0.5) {
                upper = true;
            }
            if (upper) {
                Delta = UB - gens[indexToFlip];
            } else {
                Delta = gens[indexToFlip] - LB;
            }
            int b = (int) (Math.random() * 5 + 1);
            gens[indexToFlip] = (float) (Delta * (1 - power(random(), power((1 - t) / T, b))));
        }
        t++;
        return gens;
    }

    /**
     * ****************************************************
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File("input-2.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int reader;
        String line;
        reader = Integer.parseInt(bufferedReader.readLine());
        numOfSamples = reader;
        for (int i = 0; i < numOfSamples; i++) {
            line = bufferedReader.readLine();
            String[] values = line.split(" ");
            numOfPoints = Integer.parseInt(values[0]);
            degree = Integer.parseInt(values[1]);
            for (int j = 0; j < numOfPoints; j++) {
                line = bufferedReader.readLine();
                String[] point = line.split(" ");
                float x = Float.parseFloat(point[0]);
                float y = Float.parseFloat(point[1]);
                Point p = new Point(x, y);
                Points.add(p);
            }

            //*************
            Create_Init_pop();
            //System.out.println("size"+Init_Pop.size());
            Evaluation();
            //Selection();
            CrossOver(copy, Selection());

            /**
             * *
             * HERE WE DO OUR OPERATIONS OF THE ALGORITHM
             *
             * EVALUATE SELECTION CROSSOVER MUTAION REPLACEMENT
             */
            /**
             * MY WRITE TO FILE FUNCTION
             */
            float min = (float) Collections.min(Mean_S_E);///

            int n = Mean_S_E.indexOf(min);///index of point
             
            System.out.println("case "+(i+1)+": "+min);
            for(int y=0;y<Init_Pop.get(n).genes.length;y++){
              
            System.out.print(Init_Pop.get(n).genes[y]+"  ");
            }
            System.out.println();
            //float f = Collections.min(Points.get(n).All_Diff); ///min diff
            //int n2 = Points.get(n).All_Diff.indexOf(f);
            //System.out.println(Init_Pop.get(n2).genes);
            //System.out.println(min);
            Points.clear();
            Init_Pop.clear();
            Diff_BettweenYCYA.clear();
            Mean_S_E.clear();
            copy.clear();
        }

    }
}
