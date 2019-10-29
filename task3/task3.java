import java.io.*;

public class task3 {

    public static double[] readFile(String fileName)
    {

        String fileString = "";
        int fileCount = 1;

        try (FileReader reader = new FileReader(fileName)) {
            int c;
            int i = 0;
            while ((c = reader.read()) != -1) {
                fileString = fileString + (char) c;
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < fileString.length(); i++) {
            if (fileString.charAt(i) == '\n')
                fileCount++;
        }

        double[] fileArray = new double[fileCount]; //Массив по количеству строк

        String[] splitString = fileString.split(" |\n");

        for (int i = 0; i < (fileCount); i++) { //string to int
            fileArray[i] = Double.parseDouble(splitString[i].trim());
        }

        return fileArray;
    }

    public static double[] sort(double[] arr)
    {
        for (int i = 0; i < arr.length; i++) { //bubblesort asc
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] < arr[j+1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        };
        return arr;
    }

    public static int equalIndex(double[] arr,double val)
    {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
        if (arr[i] == val) {
            index = i;
        };
        };
        return ++index;
    }

    public static void main(String[] args) {

        double[] result = new double[16];
        double[] resultSorted = new double[16];

        double[]cash1 =  readFile(args[0] + "/Cash1.txt");
        double[]cash2 =  readFile(args[0] + "/Cash2.txt");
        double[]cash3 =  readFile(args[0] + "/Cash3.txt");
        double[]cash4 =  readFile(args[0] + "/Cash4.txt");
        double[]cash5 =  readFile(args[0] + "/Cash5.txt");

        for (int i = 0; i < 16; i++)
        {
            result[i] = cash1[i] + cash2[i] +cash3[i] +cash4[i] + cash5[i];
            resultSorted[i] = cash1[i] + cash2[i] +cash3[i] +cash4[i] + cash5[i];
        }

        sort(resultSorted);


       //      System.out.print("\n");
        System.out.print(equalIndex(result,resultSorted[0]));



    }
};