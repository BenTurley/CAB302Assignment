import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {

    private String directory;
    private String fileName;
    private String actualFilePath;
    public FileReaderWriter(String directory, String filename){
        this.directory = directory;
        this.fileName = filename;
        this.actualFilePath = directory + File.separator + filename;
    }

    public void WriteFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(actualFilePath))) {
            String fileContent = "Test text test text.";
            bufferedWriter.write(fileContent);
        }
        catch (IOException e) {
            System.out.println("An error occurred in WriteFile");
        }
    }

    public void PrintOutputLocation() {
        System.out.println("File was output to: " + System.getProperty("user.home"));
    }

    public static void main(String[] args) {
        FileReaderWriter test = new FileReaderWriter((System.getProperty("user.home")), "test.txt");
        test.WriteFile();
        test.PrintOutputLocation();
    }
}


