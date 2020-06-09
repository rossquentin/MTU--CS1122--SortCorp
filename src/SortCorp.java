import java.util.ArrayList;
import java.util.Collections;

public class SortCorp implements Comparable<SortCorp>{

    private String myString;
    int editDistance;

    /**
     * Returns the minimum value of i1, i2, and i3.
     */
    private int sortCorpMin(int i1, int i2, int i3){
        int lower = (i1 < i2) ? i1 : i2;
        return (lower < i3) ? lower : i3;
    }

    /**
     * The editDistance value of a SortCorp object is based on
     * how far from the string "SortCorp" that the myString string
     * is measured in "edits". 
     * An edit is one of the three following things:
     * an insertion of a new character
     * a deletion of an existing character
     * a substitution of an existing char with a new one.
     */ 
    public SortCorp(String myString){
        this.myString = myString;
        String goal = "SortCorp".toLowerCase();
        String start = myString.toLowerCase();
        
        // Compute Edit Distance
        int[][] distance = new int[start.length()+1][goal.length()+1];

        // start distance from an empty string ""IF 
        // by dropping every character
        for(int i = 0; i <= start.length(); i++){
            distance[i][0] = i;
        }
        // end distance from an empty string ""
        // by inserting every character
        for(int i = 0; i <= goal.length(); i++){
            distance[0][i] = i;
        }

        for(int j = 1; j <= goal.length(); j++){
            for(int i = 1; i <= start.length(); i++){
                int subCost = (start.charAt(i-1) == goal.charAt(j-1)) ? 0 : 1;
                distance[i][j] = sortCorpMin( 
                                      distance[i-1][j] + 1, //delete
                                      distance[i][j-1] + 1, //insert
                                      distance[i-1][j-1] + subCost //substitute
                                    );
            }
        }
        editDistance = distance[start.length()][goal.length()];
    }

    /**
     * The larger the edit distance, the lower ranked the word is...
     */
    public int compareTo(SortCorp o){
        return o.editDistance - this.editDistance;
    }

    /**
     * The toString method we all know and love
     */
    public String toString(){
        return "( S: " + myString + " [ED:" + editDistance +  "] )";
    }

    /**
     * A demonstration of edit distance
     */
    public static void main(String[] args){
        ArrayList<SortCorp> sc = new ArrayList<SortCorp>();

        sc.add(new SortCorp("Sort"));
        sc.add(new SortCorp("SortC"));
        sc.add(new SortCorp("So"));
        sc.add(new SortCorp("SortCor"));
        sc.add(new SortCorp("Sor"));
        sc.add(new SortCorp("SportCorp"));
        sc.add(new SortCorp("StCorp"));
        sc.add(new SortCorp("SortCo"));
        sc.add(new SortCorp("SortCorp"));
        sc.add(new SortCorp("S"));
        sc.add(new SortCorp("ScottPomerville"));
        Collections.sort(sc);
        
        System.out.println(sc);

    }

}
