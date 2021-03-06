import java.util.*;
import java.io.*;

public class Main{

  public static CSVReader reader = new CSVReader();
  public static Tree tree = new Tree();

  public static void main(String[] args) throws FileNotFoundException{

    System.out.print("CSV to read: ");
    Scanner stdin = new Scanner(System.in);
    String filename = stdin.next();

    System.out.println();
    reader.view(reader.read(filename));
    System.out.println();

    String[][] data = reader.read(filename);
    double[] gain = new double[data[0].length];

    System.out.println("Target col: " + tree.targetCol(data));

    System.out.println("Initial entropy: " + tree.initialEntropy(data));

    System.out.println("Entropy: " + tree.columnEntropy(data, 5));

    System.out.println("Max: " + tree.checkMax(data));

    String[] array = tree.difValsArray(data, tree.varLocation(data, "Pat"));

    System.out.print("Values: ");

    for(int i=0; i < array.length; i++){
      if(array[i] == null) break;
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }
}
