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
    public static int column = 0;// �������
    public static String sqlsentence = null;// �����ö��Ÿ�����sql���
    public  static String Sqlc;

    private static String[] insert(String[] arr, String str) {
        int size = arr.length;  //��ȡ���鳤��
        String[] tmp = new String[size + 1];  //�½���ʱ�ַ������飬��ԭ�������ϳ��ȼ�һ
        for (int i = 0; i < size; i++){  //�ȱ�����ԭ�����ַ�������������ӵ���ʱ�ַ�������
            tmp[i] = arr[i];
        }
        tmp[size] = str;  //������������Ҫ׷�ӵ�����
        return tmp;  //����ƴ����ɵ��ַ�������
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
        System.out.println("Please create the data table in the following format��");
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



        column = ss1.length;//��������
        sqlsentence = sentence.substring(0, sentence.lastIndexOf(")"));
        filename = ss[2].substring(0, ss[2].lastIndexOf("("));
        filename1 = ss[2].substring(0, ss[2].lastIndexOf("("));
        CreateFile(filename);
        CreateFile_view(filename1);


    }


    public static void CreateFile(String filename1) {

        //ss1����ַ�����������  ss1[0]:create table ����(����1�� Լ������
        //ss1[1]:����2�� Լ������
        //ss2[2]:����3�� Լ������);
        String[] ss1 = sqlsentence.split(",");
        //String ss1str = ss1[0].toString();
        //String [] ss11 = ss1[0].split(" ");
        ss1[0] = ss1[0].substring(ss1[0].indexOf("(") + 1);//��һ����������ֵ��ss11[0]
        //ss11[3]
		/*//forѭ����������������ֵ��ss1
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
            file.createNewFile();// �����ļ�
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);// ���·����׼�����
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
            System.out.println("The view already exists��");
        }
       /* if(!file1.exists()){
            try{
                file1.createNewFile();// �����ļ�
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

        //ss1����ַ�����������  ss1[0]:create table ����(����1�� Լ������
        //ss1[1]:����2�� Լ������
        //ss2[2]:����3�� Լ������);
        String[] ss1 = sqlsentence.split(",");
        //String ss1str = ss1[0].toString();
        //String [] ss11 = ss1[0].split(" ");
        ss1[0] = ss1[0].substring(ss1[0].indexOf("(") + 1);//��һ����������ֵ��ss11[0]
        //ss11[3]
		/*//forѭ����������������ֵ��ss1
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
                file1.createNewFile();// �����ļ�
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
                System.out.println("�ļ�������!");
            }
        } catch (Exception e) {
            System.out.println("�ļ���ȡ����!");
        }
    }
}
