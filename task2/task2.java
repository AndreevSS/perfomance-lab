import java.io.*;

public class task2 {

   public static class shapeChecker {

       double[] vertexX = new double[4];
       double[] vertexY = new double[4];

       double leftX;
       double leftY;
       double topX;
       double topY;
       double rightX;
       double rightY;
       double bottomX;
       double bottomY;

       double height;
       double width;


       public shapeChecker(double[] arr) //Shape
       {
            for (int i = 0; i < 4; i++)
            {
                vertexX[i] = arr[i * 2];
                vertexY[i] = arr[i* 2 + 1];
            }

           //Зная, что обход идет по кругу, можно найти самую левую/верхнюю/правую/нижнюю точки
           if ((vertexX[0] <= vertexX[1])&&(vertexY[0] <= vertexY[1])&&(vertexX[1]<=vertexX[2])&&(vertexY[1]>=vertexY[2]))
           {
               leftX = vertexX[0];
               leftY = vertexY[0];
               topX=vertexX[1];
               topY=vertexY[1];
               rightX=vertexX[2];
               rightY=vertexY[2];
               bottomX=vertexX[3];
               bottomY=vertexY[3];
           }
           else
           if ((vertexX[0] <= vertexX[1])&&(vertexY[0] >= vertexY[1])&&(vertexX[1]>=vertexX[2])&&(vertexY[1]>=vertexY[2]))
           {
               leftX = vertexX[3];
               leftY = vertexY[3];
               topX=vertexX[0];
               topY=vertexY[0];
               rightX=vertexX[1];
               rightY=vertexY[1];
               bottomX=vertexX[2];
               bottomY=vertexY[2];
           }
           else
           if ((vertexX[0] >= vertexX[1])&&(vertexY[0] >= vertexY[1])&&(vertexX[1]>= vertexX[2])&&(vertexY[1]<=vertexY[2]))
           {
                leftX = vertexX[2];
               leftY = vertexY[2];
               topX=vertexX[3];
               topY=vertexY[3];
               rightX=vertexX[0];
               rightY=vertexY[0];
               bottomX=vertexX[1];
               bottomY=vertexY[1];

           }
           else
           if ((vertexX[0] >= vertexX[1])&&(vertexY[0] <= vertexY[1])&&(vertexX[1]<=vertexX[2])&&(vertexY[1]<=vertexY[2]))
           {

               leftX = vertexX[1];
               leftY = vertexY[1];
               topX=vertexX[2];
               topY=vertexY[2];
               rightX=vertexX[3];
               rightY=vertexY[3];
               bottomX=vertexX[0];
               bottomY=vertexY[0];
           }

           height = rightX - leftX;
           width = topY - bottomY;




 }
       public boolean isInVertex(double X, double Y) { //Проверка, совпадает ли точка (X,Y) с вершиной фигуры
           boolean cond;
           if ((X == vertexX[0] && Y == vertexY[0]) | (X == vertexX[1] && Y == vertexY[1]) | (X == vertexX[2] && Y == vertexY[2]) | (X == vertexX[3] && Y == vertexY[3])) {
               cond = true;
           } else {
               cond = false;
           }
           return cond;
       }

       public boolean isOnSide(double X, double Y) { //Проверка, совпадает ли точка (X,Y) со стороной фигуры
           boolean cond;
           double[] D = new double [4];

           D[0] = findD(leftX,leftY,topX,topY,X,Y);
           D[1] = findD(topX,topY,rightX,rightY,X,Y);
           D[2] = findD(rightX,rightY,bottomX,bottomY,X,Y);
           D[3] = findD(bottomX,bottomY,leftX,leftY,X,Y);

           if       (((X >= leftX) && (X <= topX) && (Y >= leftY) && (Y <= topY) && (D[0] == 0)) |
                     ((X >= topX) && (X <= rightX) && (Y >= rightY) && (Y <= topY) && (D[1] == 0))
                   | ((X <= rightX) && (X >= bottomX) && (Y >= rightY) && (Y <= bottomY) && (D[2] == 0))
                   | ((X >= leftX) && (X <= bottomX) && (Y >= bottomY) && (Y <= leftY) && (D[3] == 0)))
           {
               cond = true;
           } else { cond = false;}

           return cond;
       }
       public boolean isIn(double X, double Y) { //Проверка, совпадает ли точка (X,Y) со стороной фигуры
           boolean cond;
           double[] D = new double [4];

           D[0] = findD(leftX,leftY,topX,topY,X,Y);
           D[1] = findD(topX,topY,rightX,rightY,X,Y);
           D[2] = findD(rightX,rightY,bottomX,bottomY,X,Y);
           D[3] = findD(bottomX,bottomY,leftX,leftY,X,Y);

           if //     ( (D[0] > 0) && (D[3] > 0) && (D[1] < 0) && (D[2] < 0) )
           ( (D[0] > 0) && (D[3] > 0) && (D[1] > 0) && (D[2] > 0) )
           {
               cond = true;
           } else { cond = false;}

           return cond;
       }

   }

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

        double[] fileArray = new double[fileCount * 2]; //Массив по количеству строк * 2

        String[] splitString = fileString.split(" |\n");

        for (int i = 0; i < (fileCount * 2); i++) { //string to int

            fileArray[i] = Double.parseDouble(splitString[i].trim());
        }

        return fileArray;
    }

    public static double[] sort(double[] arr)
    {
        for (int i = 0; i < arr.length; i++) { //bubblesort asc
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] > arr[j+1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        };
       return arr;
    }

    public static double findD(double x1,double y1,double x2,double y2,double x3,double y3)
    {
        Double D;

        D = (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1);

        return D;
        };

    public static int checkAllConds(boolean isOnVertex,boolean isOnSide,boolean isIn)
    {
        int conds = 3;
        if (isIn) conds = 2;
        if (isOnSide) conds = 1;
        if (isOnVertex) conds = 0;
        return conds;
    };


    public static void main(String[] args) {

        double[] shapeArray =  readFile(args[0]);
        double[] PointsArray =  readFile(args[1]);

        shapeChecker Shape = new shapeChecker (shapeArray);

        for (int i = 0; i < PointsArray.length / 2; i++)
        {
            double X = PointsArray[i*2];
            double Y = PointsArray[i* 2 + 1];

            if (i < PointsArray.length / 2 - 1)
            System.out.print(checkAllConds(Shape.isInVertex(X,Y),Shape.isOnSide(X,Y),Shape.isIn(X,Y)) + "\n");
            else System.out.print(checkAllConds(Shape.isInVertex(X,Y),Shape.isOnSide(X,Y),Shape.isIn(X,Y)));

        }

    }
};