import java.util.*;

public class Tree{

  public int entropy(String[][] data, String var){
    HashSet<String> visited = new HashSet<>();

    int col = varLocation(data, var);
    int counter = 0;

    // Checking how many different values the variable has
    for(int i=1; i < data.length; i++){
      if(data[i][col] == null) break;
      if(!visited.contains(data[i][col])){
        counter++;
        visited.add(data[i][col]); // Since we haven't seen this value before, mark it now as visited
      }
    }
    return counter; // DEBBUGING
  }

  public int varLocation(String[][] data, String var){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null) break;
      if(data[0][j].equals(var)) col = j; // Returns the collumn where variable is
    }
    return col;
  }
}
