import java.util.*;

public class Tree{

  public static HashSet<Integer> targeted = new HashSet<>();

  public String[] difValsArray(String[][] data, int col){
    HashSet<String> visited = new HashSet<>();

    String[] difValsArray = new String[data[0].length];

    int k=0;
    // Checking how many different values the variable has
    for(int i=1; i < data.length; i++){
      if(data[i][col] == null) break;
      if(!visited.contains(data[i][col])){
        difValsArray[k] = data[i][col];
        visited.add(data[i][col]); // Since we haven't seen this value before, mark it now as visited
        k++;
      }
    }
    return difValsArray;
  }

  // Initial Entropy
  public static double initialEntropy(String[][] data){
    double entropy, plus, minus;
    int yesCount = 0, noCount = 0, total = 0;
    int targetCol = targetCol(data);

    // To know how many lines an attribute has
    for(int i=1; i < data.length; i++){
      if(data[i][0] == null) break;
      total++;
    }

    // To get number of number of negative results
    for(int i = 1; i < data.length; i++){
      if(data[i][0] == null) break;
      if(data[i][targetCol].equalsIgnoreCase("no")) noCount++;
    }

    // To get number of number of positive results
    for(int i = 1; i < data.length; i++){
      if(data[i][0] == null) break;
      if(data[i][targetCol].equalsIgnoreCase("yes")) yesCount++;
    }

    minus = (double)noCount / (double)total;
    plus = (double)yesCount / (double)total;

    entropy = -(plus*(Math.log(plus)/Math.log(2))) - (minus*(Math.log(minus)/Math.log(2)));

    if(Double.isNaN(entropy)) return 0;

    return entropy;
  }

  public double columnEntropy(String[][] data, int column){
    int[] occur = new int[difValsArray(data, column).length]; Arrays.fill(occur, 0);
    String[] valueList = difValsArray(data, column);

    int nYes = 0, nNo = 0;
    double plus, minus;
    double total = 0;
    double[] entropy = new double[valueList.length];

    for(int i=0; i < occur.length; i++){
      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if(data[k][column].equals(valueList[i])) occur[i]++; // Save the number of occurences of a certain value
      }
    }

    for(int i=0; i < occur.length; i++){
      nYes = 0; nNo = 0;
      plus = 0.0; minus = 0.0;

      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if((data[k][targetCol(data)].equalsIgnoreCase("no")) && data[k][column].equals(valueList[i])) nNo++;
      }

      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if((data[k][targetCol(data)].equalsIgnoreCase("yes")) && data[k][column].equals(valueList[i])) nYes++;
      }

      minus = (double)nNo / (double)(occur[i]);
      plus = (double)nYes / (double)(occur[i]);
      entropy[i] = -(plus*(Math.log(plus)/Math.log(2))) - (minus*(Math.log(minus)/Math.log(2)));
      if(Double.isNaN(entropy[i])) entropy[i] = 0;
    }

    for(int i=0; i < occur.length; i++){
      total += ((double)occur[i] / (double)(numLines(data)))*entropy[i];
    }

    return total;
  }

  // Auxiliar method to know in which collumn is the goal
  public static int targetCol(String[][] data){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null){
      col = j-1;
      break;
      }
    }
    return col;
  }

  // Information gain (based on entropy decrease)
  public double[] infoGain(String[][] data){
    String[] valueList;
    double initialEntropy = initialEntropy(data);
    double[] gain = new double[numCols(data)];

    // Loop starts in column 1 because ID doesn't count
    for(int j=1; j < numCols(data); j++){
      valueList = difValsArray(data, j); // Each collumn produces an array in which are contained the different values of it
      gain[j] = initialEntropy - columnEntropy(data, j);
    }
    return gain;
  }

  // Check what is the attribute with the bigger info gain
  public int checkMax(String[][] data){
    int target = -1;
    double max = 0.0;
    double[] gain = infoGain(data);

    for(int i=1; i < gain.length; i++){
      if(gain[i] > max && !targeted.contains(i)){
        max = gain[i];
        target = i;
      }
    }
    targeted.add(target);
    return target;
  }

  public int varLocation(String[][] data, String var){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null) break;
      if(data[0][j].equalsIgnoreCase(var)) col = j; // Returns the collumn where variable is
    }
    return col;
  }

  public int numLines(String[][] data){
    int line = 0;

    for(int i=0; i < data.length; i++){
      if(data[i][0] == null){
       line = i-1;
       break;
      }
    }
    return line;
  }

  public int numCols(String[][] data){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null){
       col = j-1;
       break;
      }
    }
    return col;
  }
}
