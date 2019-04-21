import java.util.*;
import java.io.*;

public class Main{

  public static CSVReader reader = new CSVReader();

  public static void main(String[] args) throws FileNotFoundException{

    System.out.print("CSV to read: ");
    Scanner stdin = new Scanner(System.in);
    String filename = stdin.next();

    System.out.println();
    reader.view(reader.read(filename));
    System.out.println();
  }
}
