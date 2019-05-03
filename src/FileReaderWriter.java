import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
    
    private String actualFilePath;
    private String fileContent;
    public FileReaderWriter(String directory, String fileName, String fileContent){

        this.actualFilePath = directory + File.separator + fileName;
        this.fileContent = fileContent;
    }

    public void WriteFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(actualFilePath))) {
            //String fileContent = "Test text test text.";
            bufferedWriter.write(this.fileContent);
        }
        catch (IOException e) {
            System.out.println("An error occurred in WriteFile");
        }
    }

    public void PrintOutputLocation() {
        System.out.println("File was output to: " + System.getProperty("user.home"));
    }

    public static void main(String[] args) {
        FileReaderWriter test = new FileReaderWriter((System.getProperty("user.home")), "test.txt", "This is a test string");
        test.WriteFile();
        test.PrintOutputLocation();
    }
}


