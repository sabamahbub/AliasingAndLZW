import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Encoder{

    private ArrayList initialValues = new ArrayList<String>();
    private ArrayList dictionary = new ArrayList<String>();
    private ArrayList encodedOutput = new ArrayList<Integer>();
    private String originalInput;
    private String encodedString;

    public Encoder(File file){
        try{
            this.encodedString = "";
            Scanner input = new Scanner(file);
            this.originalInput = input.nextLine();
            input.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void buildInitialValues(){
        String current;
        for(int i = 0; i < originalInput.length(); i++){
            if(originalInput.length() == 256) break;
            current = "" + originalInput.charAt(i);
            if(!initialValues.contains(current)) {
                initialValues.add(current);
            }
        }
    }

    public void buildDictionary(){
        if(initialValues.size() == 0) buildInitialValues();
        dictionary.addAll(initialValues);

        String test = "";
        char next;

        for(int i = 0; i < originalInput.length(); i++){
            next = originalInput.charAt(i);
            if(dictionary.contains(test + next)){test += next;}
            else{
                if(dictionary.size() < 256){ dictionary.add(test + next);}
                encodedOutput.add(dictionary.indexOf(test));
                test = "" + next;
            }
        }
        encodedOutput.add(dictionary.indexOf(test));
    }

    public String getOriginal(){
        return this.originalInput;
    }

    public void printEncoded(){
        System.out.println("Encoded Text:");
        for(int i = 0; i < encodedOutput.size(); i++){
            System.out.print(encodedOutput.get(i) + " ");
        }
        System.out.println();
    }

    public void printDictionary(){
        System.out.println("\nEncoding Dictionary");
        System.out.println("Index\tEntry");
        for(int i = 0; i < dictionary.size(); i++){
            System.out.println(i + "\t" + dictionary.get(i));
        }
        System.out.println();
    }

    public void printInitial(){
        System.out.println("\nInitial List");
        System.out.println("Index\tEntry");
        for(int i = 0; i < initialValues.size(); i++){
            System.out.println(i + "\t" + initialValues.get(i));
        }
        System.out.println();
    }

    public ArrayList<Integer> getEncoded(){
        return this.encodedOutput;
    }

    public ArrayList<String> getInitialValues(){
        return this.initialValues;
    }

    public void printChanges(){
        Double sizeBeforeCompression = (double)8 * originalInput.length();
        Double sizeAfterCompression = (double)8 * encodedOutput.size();
        Double compressionRatio = (double)sizeBeforeCompression / sizeAfterCompression;

        System.out.println("\nSize Before Conversion: "+sizeBeforeCompression);
        System.out.println("Size After Conversion: "+sizeAfterCompression);
        System.out.println("Compression Ratio: "+compressionRatio);
    }

    public void encode(){
        buildDictionary();
        printInitial();
        printDictionary();
        System.out.println("Original Text:\n"+getOriginal()+"\n");
        printEncoded();
    }
}