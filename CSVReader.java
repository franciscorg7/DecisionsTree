import java.util.*;
import java.io.*;

public class CSVReader{

  public String[][] readRest() throws FileNotFoundException{

    String[][] data = new String[100][100];

    File file = new File("restaurant.csv");

    // Obtain a matrix with data from CSV
    List<List<String>> lines = new ArrayList<>();
    Scanner inputStream = new Scanner(file);

    while(inputStream.hasNext()){
      String line = inputStream.next();
      String[] values = line.split(",");

      // Adds the currently parsed line to the matrix
      lines.add(Arrays.asList(values));
    }

    inputStream.close();

    // Iterate through the matrix and return it
    int lineNum = 0;
    for(List<String> line: lines){
      int columnNum = 0;
      for(String value: line){
        data[lineNum][columnNum] = value;
        columnNum++;
      }
      lineNum++;
    }

    return data;
  }

  public String[][] readIris() throws FileNotFoundException{

    String[][] data = new String[200][200];

    File file = new File("iris.csv");

    // Obtain a matrix with data from CSV
    List<List<String>> lines = new ArrayList<>();
    Scanner inputStream = new Scanner(file);

    while(inputStream.hasNext()){
      String line = inputStream.next();
      String[] values = line.split(",");

      // Adds the currently parsed line to the matrix
      lines.add(Arrays.asList(values));
    }

    inputStream.close();

    // Iterate through the matrix and return it
    int lineNum = 0;
    for(List<String> line: lines){
      int columnNum = 0;
      for(String value: line){
        data[lineNum][columnNum] = value;
        columnNum++;
      }
      lineNum++;
    }

    return data;
  }

  public String[][] readWeather() throws FileNotFoundException{

    String[][] data = new String[100][100];

    File file = new File("weather.csv");

    // Obtain a matrix with data from CSV
    List<List<String>> lines = new ArrayList<>();
    Scanner inputStream = new Scanner(file);

    while(inputStream.hasNext()){
      String line = inputStream.next();
      String[] values = line.split(",");

      // Adds the currently parsed line to the matrix
      lines.add(Arrays.asList(values));
    }

    inputStream.close();

    // Iterate through the matrix and return it
    int lineNum = 0;
    for(List<String> line: lines){
      int columnNum = 0;
      for(String value: line){
        data[lineNum][columnNum] = value;
        columnNum++;
      }
      lineNum++;
    }

    return data;
  }

  public void viewRest(String[][] data){

    for(int i=0; i < 13; i++){
      for(int j=0; j < 12; j++){
        System.out.print(data[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void viewIris(String[][] data){

    for(int i=0; i < 151; i++){
      for(int j=0; j < 6; j++){
        System.out.print(data[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void viewWeather(String[][] data){

    for(int i=0; i < 15; i++){
      for(int j=0; j < 6; j++){
        System.out.print(data[i][j] + " ");
      }
      System.out.println();
    }
  }
}
