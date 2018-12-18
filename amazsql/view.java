package src.amazsql;

import java.io.IOException;
import java.io.OutputStream;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import jxl.*;
import jxl.write.*;
import jxl.Workbook;
import jxl.Cell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import java.io.File;
import java.util.Arrays;

import java.util.Scanner;

public class view extends sql {

    public static String filename = null;
    public static String filename1 = null;
    public static int column = 0;// 表的列数
    public static String sqlsentence = null;// 保存用逗号隔开的sql语句
    public  static String Sqlc;

    private static String[] insert(String[] arr, String str) {
        int size = arr.length;  //获取数组长度
        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
        for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
            tmp[i] = arr[i];
        }
        tmp[size] = str;  //在最后添加上需要追加的数据
        return tmp;  //返回拼接完成的字符串数组
    }

    public static void exchange(String temp[]){
        int head=0;
        int tail=temp.length-1;
        for(int i=0;i<temp.length/2;i++){
            String k=temp[head];
            temp[head]=temp[tail];
            temp[tail]=k;
            head++;
            tail--;
        }
    }



    public static void view(){
        System.out.println("Please create the data table in the following format：");
        System.out.println("----------------------------");
        System.out.println("|create view_name as");
        System.out.println("|select column_name);");
        System.out.println("|from table_name);");
        System.out.println("|where condition);");
        System.out.println("----------------------------");

        Scanner input3 = new Scanner(System.in);
        String sentence = input3.nextLine();
        while (sentence.lastIndexOf(";") != sentence.length() - 1) {
            sentence = sentence + " " + input3.nextLine();
        }
        sentence = sentence.substring(0, sentence.lastIndexOf(";"));

        String[] ss = sentence.split("\\s+");
        String[] ss1 = sentence.split(",");



        column = ss1.length;//几个属性
        sqlsentence = sentence.substring(0, sentence.lastIndexOf(")"));
        filename = ss[2].substring(0, ss[2].lastIndexOf("("));
        filename1 = ss[2].substring(0, ss[2].lastIndexOf("("));
        CreateFile(filename);
        CreateFile_view(filename1);


    }


    public static void CreateFile(String filename1) {

        //ss1这个字符数组里存的是  ss1[0]:create table 表名(属性1名 约束条件
        //ss1[1]:属性2名 约束条件
        //ss2[2]:属性3名 约束条件);
        String[] ss1 = sqlsentence.split(",");
        //String ss1str = ss1[0].toString();
        //String [] ss11 = ss1[0].split(" ");
        ss1[0] = ss1[0].substring(ss1[0].indexOf("(") + 1);//第一个属性名赋值给ss11[0]
        //ss11[3]
		/*//for循环将各个属性名赋值给ss1
        for(int n = 1;n<createtb.column;n++){
        	ss1[n] = ss1[n].substring(0, ss1[n].indexOf(" "));
        }*/
        sqlsentence =  sqlsentence + ");";
        //System.out.println(sqlsentence);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss1.length; i++) {
            sb.append(ss1[i]);
        }

        ss1 = insert(ss1, ";");
        String s = sb.toString();

        s = s + ";";
        // System.out.println(s);
        selectfromTb.sftbf(s);
        String aa = selectfromTb.content;
        String[] as = aa.split("\\s+");


        File file = new File("mydatabase//" + chooseAnddo.str, filename1 + "_view.txt");
       // File file1 = new File("myview//" + chooseAnddo.str, filename1 + "_view.txt");

        if (!file.exists()){

        try {
            file.createNewFile();// 创建文件
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);// 重新分配标准输出流
            //System.out.println("world");
            selectfromTb.sftbf(s);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        else {
            System.out.println("The view already exists！");
        }
       /* if(!file1.exists()){
            try{
                file1.createNewFile();// 创建文件
                FileWriter fw = null;
                fw = new FileWriter(file1);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(sqlsentence,0,sqlsentence.length());
                out.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }*/

    }
    public static void CreateFile_view(String filename1) {

        //ss1这个字符数组里存的是  ss1[0]:create table 表名(属性1名 约束条件
        //ss1[1]:属性2名 约束条件
        //ss2[2]:属性3名 约束条件);
        String[] ss1 = sqlsentence.split(",");
        //String ss1str = ss1[0].toString();
        //String [] ss11 = ss1[0].split(" ");
        ss1[0] = ss1[0].substring(ss1[0].indexOf("(") + 1);//第一个属性名赋值给ss11[0]
        //ss11[3]
		/*//for循环将各个属性名赋值给ss1
        for(int n = 1;n<createtb.column;n++){
        	ss1[n] = ss1[n].substring(0, ss1[n].indexOf(" "));
        }*/
        sqlsentence =  sqlsentence + ");";
        //System.out.println(sqlsentence);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss1.length; i++) {
            sb.append(ss1[i]);
        }

        ss1 = insert(ss1, ";");
        String s = sb.toString();

        s = s + ";";
        // System.out.println(s);
        selectfromTb.sftbf(s);
        String aa = selectfromTb.content;
        String[] as = aa.split("\\s+");



        File file1 = new File("myview//" + chooseAnddo.str, filename1 + "_view.txt");

        if(!file1.exists()){
            try{
                file1.createNewFile();// 创建文件
                FileWriter fw = null;
                fw = new FileWriter(file1);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(sqlsentence,0,sqlsentence.length());
                out.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
    public static void ViewPrint(){
        Scanner input3 = new Scanner(System.in);
        String sentence = input3.nextLine();
        String[] ss = sentence.split("\\s+");


        while (sentence.lastIndexOf(";") != sentence.length() - 1) {
            sentence = sentence + " " + input3.nextLine();
        }

        filename1 = ss[2].substring(0, ss[2].lastIndexOf(";"));

       // System.out.println(filename1);

        try {
            File file1 = new File("myview//" + chooseAnddo.str, filename1 + "_view.txt");
            if(file1.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file1), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
    }
}
