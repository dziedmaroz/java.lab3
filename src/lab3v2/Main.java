/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package lab3v2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author lucian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int [] [] matr = readMatrix("input.in");
        System.out.print("Task 1 \n 1. Поменять местами строку, содержащую элемент с наибольшим значением в матрице сострокой, содержащей элемент с наименьшим значением. Вывести на экран получен­нуюматрицу. Для каждой строки с нулевым элементом на главной диагонали вывести ее номер и значение наибольшего из элементов этой строки.");
        task1(matr);
        System.out.print("Task 2 \n 15. Найти максимальное из чисел, встречающихся в заданной матрице ровно два раза.");
        task2(matr);
        System.out.print ("Task 3\n 29. Упорядочить ее столбцы по неубыванию их первых элементов.");
        task3(matr);
    }

     public static int[][] readMatrix (String filename)
    {
        int [] [] matr=null;
        int m=0;
        int n=0;
        FileInputStream fReader=null;
        InputStreamReader iStReader=null;
        BufferedReader bReader=null;

        try
        {
             fReader= new FileInputStream (filename);
             iStReader= new InputStreamReader(fReader);
             bReader = new BufferedReader (iStReader);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERR::File_Not_Found "+e.getLocalizedMessage());

        }
        String str = "";
        try
        {
            str = bReader.readLine();
        }
        catch (IOException e)
        {
            System.out.println ("ERR::IOException on reading 1st line " + e.getLocalizedMessage());

        }

        StringTokenizer sTok = new StringTokenizer (str);
        
        String tmpStr = sTok.nextToken();
        try
        {
            m = Integer.parseInt(tmpStr);

        }
        catch (NumberFormatException e)
        {
            System.out.println("ERR::NumberFormatException on parsing int " + e.getLocalizedMessage());
        }
        tmpStr = sTok.nextToken();
        try
        {
            n = Integer.parseInt(tmpStr);

        }
        catch (NumberFormatException e)
        {
            System.out.println("ERR::NumberFormatException on parsing int " + e.getLocalizedMessage());
        }

        matr = new int [m][n];
        for (int i=0;i<m;i++)
        {
            try
            {
                str = bReader.readLine();
            }
            catch (IOException e)
            {
                System.out.println ("ERR::IOException on reading"+ (i+1) +" line " + e.getLocalizedMessage());
            }
            sTok = new StringTokenizer (str);
            for (int j=0;j<n;j++)
            {
                try
                {
                    matr[i][j] = Integer.parseInt(sTok.nextToken());
                }
                catch (NumberFormatException e)
                {
                    System.out.println("ERR::NumberFormatException on parsing int " + e.getLocalizedMessage());
                }
            }
        }
        return matr;

    }

    public static void matrixPrint (int [][]matr)
    {
        System.out.println ("Matrix "+ matr.length + "x" + matr[0].length+":\n");
        for (int i=0;i<matr.length;i++)
        {
            for (int j=0;j<matr[i].length;j++)
            {
                System.out.printf("%4d ",matr[i][j]);
            }
            System.out.print ("\n");
        }
        System.out.print ("\n\n");
    }
    public static void task1 (int [][] matr)
    {
        matrixPrint(matr);

        int minXIndex = 0;
        int minYIndex = 0;

        int maxXindex = 0;
        int maxYIndex = 0;

        for (int i=0;i<matr.length;i++)
        {
            for (int j=0;j<matr[i].length;j++)
            {
                if (matr[i][j]<matr[minYIndex][minXIndex])
                {
                    minXIndex = j;
                    minYIndex = i;
                }

                if (matr [i][j]> matr [maxYIndex][maxXindex])
                {
                    maxXindex = j;
                    maxYIndex = i;
                }
            }
        }

        for (int i=0;i<matr[maxYIndex].length;i++)
        {
            int tmp = matr[maxYIndex][i];
            matr[maxYIndex][i] = matr[minYIndex][i];
            matr[minYIndex][i] = tmp;
        }
        matrixPrint(matr);
    }
    public static  void task2 (int [][] matr)
    {
       int [][] list = new int [matr.length*matr[0].length][2];
       int top = 0;
       for (int i=0;i<matr.length;i++)
       {
           for (int j=0;j<matr[i].length;j++)
           {
               boolean flag = true;
               for (int k=0;k<top;k++)
               {
                   if (list[k][0]==matr[i][j])
                   {
                       list[k][1]++;
                       flag = false;
                       break;
                   }
               }
               if (flag)
               {
                   list[top][0]=matr[i][j];
                   list[top][1]=1;
                   top++;
               }
           }
       }

       int max=-1;
       for (int i=0;i<top;i++)
       {
           if (list[i][1]==2)
           {
               if (max==-1)
               {
                    max=i;
               }
               else
               {
                   if (list[max][0]<list[i][0])
                   {
                       max=i;
                   }
               }
           }
       }
       System.out.println("MAX :"+ list[max][0]+"\n");
    }

    public static void task3 (int [][] matr)
    {
        for (int i=0;i<matr[0].length;i++)
        {
            for (int j=i+1;j<matr[0].length-1;j++)
            {
                if (matr[0][j]>matr[0][j+1])
                {
                    for (int ii=0;ii<matr.length;ii++)
                    {
                        int tmp =matr[ii][j];
                        matr[ii][j]=matr[ii][j+1];
                        matr[ii][j+1]=tmp;
                    }
                }
            }
        }
        matrixPrint(matr);
    }

}
