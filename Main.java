import java.util.*;
import java.io.*;

public class Main{

  public static CSVReader reader = new CSVReader();

  public static void main(String[] args) throws FileNotFoundException{

    reader.viewRest(reader.readRest());
    System.out.println();
    reader.viewIris(reader.readIris());
    System.out.println();
    reader.viewWeather(reader.readWeather());
    System.out.println();
  }
}
