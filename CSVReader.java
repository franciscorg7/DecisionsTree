import java.util.*;
import java.io.*;

public class CSVReader{

  public String[][] read(String filename) throws FileNotFoundException{

    String[][] data = new String[500][500];

    File file = new File(filename + ".csv");

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

  public void view(String[][] data){

    for(int i=0; i < data.length; i++){
      for(int j=0; j < data[0].length; j++){
        if(data[i][j] == null) break; // data[][] is size-fixed, so we need to make sure we ignore null data
        System.out.print(data[i][j] + " ");
      }
      if(data[i][0] == null) break; // data[][] is size-fixed, so we need to make sure we ignore null data
      System.out.println();
    }
  }
}
