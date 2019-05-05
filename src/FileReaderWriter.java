import java.io.*;
import java.util.ArrayList;

public class FileReaderWriter {
    private String actualFilePath;
    private String fileContent;
    public FileReaderWriter(String directory, String fileName){

        this.actualFilePath = directory + File.separator + fileName;

    }

    /**
     * Writes to file based on object private variables
     */
    public void WriteFile(String fileContent) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(actualFilePath))) {
            this.fileContent = fileContent;
            bufferedWriter.write(fileContent);
        }
        catch (IOException e) {
            System.out.println("IOException in WriteFile");
        }
    }

    /**
     * Prints outputted file directory
     */
    public void PrintOutputLocation() {
        System.out.println("File was output to: " + this.actualFilePath);
    }


    /**
     * Prints string written to file to the console
     */
    public void PrintOutputString() {
        System.out.println("Outputted file contents: " + this.fileContent);
    }

    public ArrayList<String> ReadFile() {
        ArrayList<String> output = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(actualFilePath))) {
            String line = bufferedReader.readLine();

            while(line!=null) {
                //System.out.println(line);
                output.add(line);
                line = bufferedReader.readLine();
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("File was not found.");
        }
        catch(IOException e) {
            System.out.println("IOException in ReadFile");
        }
        return output;
    }

    public static void main(String[] args) {
        //Writer test
        FileReaderWriter test = new FileReaderWriter((System.getProperty("user.home")), "test.txt");
        test.WriteFile("This is a test string");
        test.PrintOutputLocation();
        test.PrintOutputString();
        System.out.println("---");
        //Reader test
        FileReaderWriter test2 = new FileReaderWriter((System.getProperty("user.home")), "test.txt");
        test2.ReadFile();
    }
}


