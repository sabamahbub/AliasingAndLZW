import java.util.Scanner;
import java.io.File;

public class CS4551_Mahbub{
  public static void main(String[] args) throws Exception {
    System.out.println("--Welcome to Multimedia Software System--");
    Scanner input = new Scanner(System.in);
    int option = 0;
    boolean running = true;
    int m, n, k;
    while(running){
      System.out.println("Main Menu-----------------------------------");
      System.out.println("1. Aliasing");
      System.out.println("2. Dictionary Coding");
      System.out.println("3. Quit");
      System.out.println("Please enter the task number [1-3]:");
      option = input.nextInt();

      if(option == 1){
        System.out.println("Enter m, n, and k:");
        m = input.nextInt();
        n = input.nextInt();
        k = input.nextInt();

        CompressionImage images = new CompressionImage(512, 512, k, m, n);
        images.driver();
      }
      else if(option == 2){
        String filePath = "";
        File encodingFile;

        do{
          System.out.println("Enter file path:");
          filePath = input.next();
          encodingFile = new File(filePath);
          }
        while( !encodingFile.exists() );

        Encoder encoder = new Encoder(encodingFile);
        encoder.encode();

        Decoder decoder = new Decoder(encoder.getInitialValues(), encoder.getEncoded());
        decoder.decode();

        encoder.printChanges();
      }
      else{
        running = false;
      }  
    }
    input.close();
    System.out.println("--Good Bye--");
    System.exit(0);
  }

  public static void usage(){
    System.out.println("\nUsage: java CS4551_Main\n");
  }    
}
