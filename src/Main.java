import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    static HashMap<Integer, ArrayList<String>> hashMapObj = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please state your choice... UPPER case or lower case (U or L):");
        String caseType = scanner.nextLine();  // Read user input

        System.out.println("Please state your choice...\n" +
                "Color of characters (R or Y):");
        String color = scanner.nextLine();  // Read user input
        System.out.println("color is: " + color);  // Output user input

        System.out.println("Please state your choice...\n" +
                "How many characters to shift (number between 1-3): ");
        int shiftNumber = scanner.nextInt();  // Read user input

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("./textfile.txt"),
                            Charset.forName("UTF-8")));
            int c;
            int charCounter = 1;
            while((c = reader.read()) != -1) {
                char character = (char) c;
                ArrayList<String> values = new  ArrayList<>();
                String originalLetter = String.valueOf(character);
                values.add(originalLetter);
                hashMapObj.put(charCounter, values);
                charCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace(); // File couldn't found
        }
        System.out.println("_____ORIGINAL HASHMAP_____");
        System.out.println(hashMapObj);
        System.out.println(" ");


        System.out.println("_____AFTER CASE CHANGED______");
        changeCaseType(hashMapObj, caseType);
        System.out.println(" ");


        System.out.println("______AFTER SHIFTED__________");
        shiftLetters(hashMapObj, shiftNumber);
        System.out.println(" ");

        System.out.println("______AFTER COLORED_________");
        changeColor(hashMapObj, color);
        System.out.println(" ");
    }

    public static void changeColor (HashMap<Integer, ArrayList<String>> map, String color){



        map.forEach((index,arrayList) -> {
            if(color.equals("r")  || color.equals("R") ){
                String letter = arrayList.get(0);
                letter = new String(ANSI_RED + letter + ANSI_RESET);
                arrayList.add(letter);
            }else if( color.equals("y") || color.equals("Y")){
                String letter = arrayList.get(0);
                letter = new String(ANSI_YELLOW + letter + ANSI_RESET);
                arrayList.add(letter);
            }
        });

        System.out.println(map.values());
    }


    public static void shiftLetters (HashMap<Integer, ArrayList<String>> map, int shiftNumber){

        map.forEach((index,arrayList) -> {
            int asciiCode = (int) arrayList.get(0).charAt(0)+shiftNumber;
            char shiftedLetter = (char) asciiCode;
            arrayList.add(String.valueOf(shiftedLetter));
            // System.out.println(arrayList.get(0)+ " shifted-> " + String.valueOf(shiftedLetter));

        });

        System.out.println(map.values());

    }


    public static void changeCaseType (HashMap<Integer, ArrayList<String>> map, String caseType){

        map.forEach((index,arrayList) -> {
            if(caseType.equals("l")  || caseType.equals("L") ){
                arrayList.add(arrayList.get(0).toLowerCase());
            }else if( caseType.equals("U") || caseType.equals("u")){
                arrayList.add(arrayList.get(0).toUpperCase());
            }
        });
        System.out.println(map.values());
    }

}

