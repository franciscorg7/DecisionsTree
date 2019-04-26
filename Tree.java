import java.util.*;

public class Tree{

  public static HashSet<Integer> targeted = new HashSet<>();

  public static double initialEntropy(String[][] data){
    double entropy, plus, minus;
    int yesCount = 0, noCount = 0, total = 0;
    int goalCol = goalCol(data);

    // To know how many lines an attribute has
    for(int i=1; i < data.length; i++){
      if(data[i][0] == null) break;
      total++;
    }

    for(int i = 1; i < data.length; i++){
      if(data[i][0] == null) break;
      if(data[i][goalCol].equalsIgnoreCase("no")) noCount++;
    }

    for(int i = 1; i < data.length; i++){
      if(data[i][0] == null) break;
      if(data[i][goalCol].equalsIgnoreCase("yes")) yesCount++;
    }

    minus = (double)noCount / (double)total;
    plus = (double)yesCount / (double)total;

    entropy = -(plus*(Math.log(plus)/Math.log(2))) - (minus*(Math.log(minus)/Math.log(2)));
    if(Double.isNaN(entropy)) return 0;

    return entropy;
  }

  // Auxiliar method to know in which collumn is the goal
  public static int goalCol(String[][] data){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null) break;
      col = j;
    }
    return col;
  }

  public int difVals(String[][] data, String var){
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
    return counter;
  }

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

  public double[] columnGain(String[][] data, double[] gain) {
    String[] valueList;
    double initialEntropy = initialEntropy(data);

    for(int i=0; i < data.length; i++) {
      valueList = difValsArray(data, i); // Each collumn produces an array in which are contained the different values of it
      gain[i] = columnEntropy(data, valueList, i, initialEntropy);
    }
    return gain;
  }

  public int checkMax(String[][] data, double[] gain){
    int target = -1;
    double max = 0.0;
    Arrays.fill(gain, 0.0); columnGain(data, gain);
    for(int i=0; i < gain.length; i++){
      if(gain[i] > max && !targeted.contains(i)){
        max = gain[i];
        target = i;
      }
    }
    if(target == -1) return -1;

    targeted.add(target);
    return target;
  }

  public double columnEntropy(String[][] data, String[] valueList, int column, double initialEntropy) {
    int[] occur = new int[valueList.length];
    int nYes = 0, nNo = 0;
    double gain, plus, minus;
    double total = 0;
    double[] entropy = new double[valueList.length];

    for(int i=0; i < occur.length; i++){
      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if(data[k][column].equals(valueList[i])) occur[i]++; // Save the number of occurences of a certain value
      }
    }

    for(int i=0; i < occur.length; i++){
      reset(nYes); reset(nNo);
      plus = 0.0;
      minus = 0.0;

      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if((data[k][goalCol(data)].equalsIgnoreCase("no")) && data[k][column].equals(valueList[i])) nNo++;
      }

      for(int k=1; k < data.length; k++){
        if(data[k][column] == null) break;
        if((data[k][goalCol(data)].equalsIgnoreCase("yes")) && data[k][column].equals(valueList[i])) nYes++;
      }

      minus = (double)nNo / (double)(occur[i]);
      plus = (double)nYes / (double)(occur[i]);
      entropy[i] = -(plus*(Math.log(plus)/Math.log(2))) - (minus*(Math.log(minus)/Math.log(2)));
      if(Double.isNaN(entropy[i])) entropy[i] = 0;
    }

    for(int i=0; i < occur.length; i++){
      total += ((double)occur[i] / (double)(numLines(data)))*entropy[i]; // Information value
    }

    gain = initialEntropy - total; // Column gain
    return gain;
  }

  public int varLocation(String[][] data, String var){
    int col = 0;

    for(int j=0; j < data[0].length; j++){
      if(data[0][j] == null) break;
      if(data[0][j].equals(var)) col = j; // Returns the collumn where variable is
    }
    return col;
  }

  public int reset(int a){
    a = 0; return a;
  }

  public int numLines(String[][] data){
    int line = 0;

    for(int i=0; i < data.length; i++){
      if(data[i][0] == null) line = i-1;
    }
    return line;
  }

}
