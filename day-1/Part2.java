import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Part2 {
    
    public static void main(String[] args) {
        Map<String, String> numbers = new LinkedHashMap<>();
        
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");
        
        File file  = new File("test.txt");
        
        try (Scanner sc = new Scanner(file);) {
            ArrayList<String> linesValues = new ArrayList<>();
            Map<Integer, String> positionOfEach = new TreeMap<>();
            List<String> toReplace = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                for (String number : numbers.keySet()) {
                    if (line.contains((CharSequence) number)) {
                        for (int i = line.indexOf(number); i >= 0; i = line.indexOf(number, i+1)) {
                            positionOfEach.put(i, number);
                        }
                    }
                }

                toReplace.addAll(positionOfEach.values());
                String formattedLine = "";

                if (toReplace.size() != 0) {
                    formattedLine = line.replace((CharSequence) toReplace.get(0), numbers.get(toReplace.get(0)))
                        .replace((CharSequence) toReplace.get(toReplace.size() - 1), numbers.get(toReplace.get(toReplace.size() - 1)));
                }else {
                    formattedLine = line;
                }

                char[] arrLine = formattedLine.toCharArray();

                String currentValue = "";
                for (int i = 0; i < arrLine.length; i++) {
                    if (Character.isDigit(arrLine[i])) {
                        currentValue += arrLine[i];
                    }
                }

                linesValues.add(currentValue);

                positionOfEach.clear();
                toReplace.clear();
            }

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

    public static Long sumAllValues(List<String> values) {
        Long sumOfAll = 0l;
        for (String value : values) {
            Long currentValue = Long.parseLong(value);
            sumOfAll += currentValue;
        }
        return sumOfAll;
    }

}
