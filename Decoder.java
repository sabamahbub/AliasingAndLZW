import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Decoder{
    private ArrayList initialValues = new ArrayList<String>();
    private ArrayList dictionary = new ArrayList<String>();
    private ArrayList encodedInput = new ArrayList<String>();
    private ArrayList decodedOutput = new ArrayList<String>();

    public Decoder(ArrayList<String> initialValues, ArrayList<Integer> encodedInput){
        this.initialValues.addAll(initialValues);
        this.encodedInput.addAll(encodedInput);
    }

    public void buildDictionary(){
        dictionary.addAll(initialValues);

        int encoderCode = (int)encodedInput.get(0);
        String entry = (String)dictionary.get(encoderCode);
        decodedOutput.add(entry);

        int nextCode;
        String nextEntry = "";
        char test;

        for(int i = 1; i < encodedInput.size(); i++){
            nextCode = (int)encodedInput.get(i);
            nextEntry = (String)dictionary.get(encoderCode);
            if(dictionary.size() < nextCode){
                nextEntry = entry + entry.charAt(0);
            }
            else{
                nextEntry = (String)dictionary.get(nextCode);
            }
            decodedOutput.add(nextEntry);
            test = nextEntry.charAt(0);
            if(dictionary.size()<256)dictionary.add(entry+test);
            entry = nextEntry;
        }

    }

    public void printDecodedWithSpace(){
        System.out.println("Decoded Text as Dictionary Entries:");
        for(int i = 0; i < decodedOutput.size(); i++){
            System.out.print(decodedOutput.get(i) + " ");
        }
        System.out.println();
    }

     public void printDecoded(){
        System.out.println("\nDecoded Text:");
        for(int i = 0; i < decodedOutput.size(); i++){
            System.out.print(decodedOutput.get(i));
        }
        System.out.println();
    }

    public void printEncoded(){
        System.out.println("Encoded:");
        for(int i = 0; i < encodedInput.size(); i++){
            System.out.print(encodedInput.get(i));
        }
        System.out.println();
    }

    public void printDictionary(){
        System.out.println("\nDecoding Dictionary");
        System.out.println("Index\tEntry");
        for(int i = 0; i < dictionary.size(); i++){
            System.out.println(i + "\t" + dictionary.get(i));
        }
        System.out.println();
    }

    public void decode(){
        buildDictionary();
        // printDictionary();
        // printDecodedWithSpace();
        printDecoded();
    }
}
