import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String args[]){

        ArrayList<String> linesValues = new ArrayList<>();
        File file  = new File("test2.txt");

        try (Scanner sc = new Scanner(file);) {
            while (sc.hasNextLine()) {
                String replacedLine = replaceAllCardinalNumbers(sc.nextLine());
                System.out.println(replacedLine);
                char[] line = replacedLine.toCharArray();
                System.out.println(line);

                String currentValue = "";
                for (int i = 0; i < line.length; i++) {
                    if (Character.isDigit(line[i])) {
                        currentValue += line[i];
                    }
                }
                linesValues.add(currentValue);
            }
            System.out.println(formatAllValues(linesValues));
            System.out.println(sumAllValues(formatAllValues(linesValues)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> formatAllValues(List<String> values) {
        ArrayList<String> formattedValues = new ArrayList<>();
        for (String value : values) {  
            if (value.length() == 1) {
                formattedValues.add(value + value);
            }else if (value.length() > 2) {
                char[] arrValue = value.toCharArray();
                String newValue = "" + arrValue[0] + arrValue[arrValue.length - 1];
                formattedValues.add(newValue);
            }else {{
                formattedValues.add(value);
            }}
        }
        return formattedValues;
    }

    public static Integer sumAllValues(List<String> values) {
        Integer sumOfAll = 0;
        for (String value : values) {
            Integer currentValue = Integer.parseInt(value);
            sumOfAll += currentValue;
        }
        return sumOfAll;
    }

    //day 1 - part 2
    public static String replaceAllCardinalNumbers(String value) {
        return value.replace("one", "1")
            .replace("two", "2")
            .replace("three", "3")
            .replace("four", "4")
            .replace("five", "5")
            .replace("six", "6")
            .replace("seven", "7")
            .replace("eigth", "8")
            .replace("nine", "9");
    }
}