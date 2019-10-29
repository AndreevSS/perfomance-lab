import java.io.*;

public class task1 {

    public static double percentile(int[] array, int percent) //Процентили
    {
        double percCoef = 0;
        double value = 0;
        percCoef = (double)percent/100 * (array.length - 1) + 1;
        int perc1 = (int)Math.floor(percCoef) - 1;
        value = array[perc1] + (double)(Math.round(percCoef%1*100))/100 * (array[perc1 + 1] - array[perc1]);
        return value;
    }

    public static void main(String[] args) {

        int fileCount = 1;
        int ArraySum = 0;
        double perc;
        double avg = 0;
        double med;
        double max;
        double min;
        String fileString = "";


        try (FileReader reader = new FileReader(args[0])) {
            int c;
            int i = 0;
            while ((c = reader.read()) != -1) {
                fileString = fileString + (char) c;
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

//        System.out.print(fileString);


        for (int i = 0; i < fileString.length(); i++) {
            if (fileString.charAt(i) == '\n')
                fileCount++;
        }

        //  System.out.print("\nВсего: " + fileCount);

        int[] fileArray = new int[fileCount]; //Массив по количеству строк

        String[] splitString = fileString.split("\n");

        for (int i = 0; i < fileCount; i++) { //string to int

            fileArray[i] = Integer.parseInt(splitString[i].trim());
            //        System.out.println("intInArray: "+ fileArray[i]);

        }

        for (int i = 0; i < fileCount; i++) { //bubblesort asc
            for (int j = 0; j < fileCount - i - 1; j++)
            {
                if (fileArray[j] > fileArray[j+1]) {
                    int temp = fileArray[j];
                    fileArray[j] = fileArray[j + 1];
                    fileArray[j + 1] = temp;
                }

            }
        };

        for (int i = 0; i < fileCount; i++) { //sum
            ArraySum += fileArray[i];
            //   System.out.println("InSortArray: "+ fileArray[i]);
        };

        perc = percentile(fileArray,90);
        med = percentile(fileArray,50);
        max = fileArray[fileCount - 1];
        min = fileArray[0];
        avg = (float)ArraySum/fileCount;


        System.out.printf(java.util.Locale.US,"%.2f",perc);
        System.out.printf(java.util.Locale.US,"\n%.2f",med);
        System.out.printf(java.util.Locale.US,"\n%.2f",max);
        System.out.printf(java.util.Locale.US,"\n%.2f",min);
        System.out.printf(java.util.Locale.US,"\n%.2f",avg);

        //   System.out.println(fileString);
    }
}