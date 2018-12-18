package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*
 * show一个用户的权限，包括对数据库和数据表的
 */
public class showper extends sql {
   public static void show(){
	   System.out.println("Please show permissions of a user as follows：");
		System.out.println("show permissions of user_name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		if(ss[0].equals("show")&&ss[1].equals("permissions")&&ss[2].equals("of")) {
			if (ss[3].equals("guest")) {
				File file = new File("user.xls");
				jxl.Workbook workbook;
				try {
					workbook = Workbook.getWorkbook(file);
					Sheet sheet = workbook.getSheet(0);


					Cell cell = sheet.getCell(1, 2);
					String content = cell.getContents();

					System.out.print("the permissions of guest to database :" + " " + content);

					System.out.println();
					Sheet sheet1 = workbook.getSheet(1);
					Cell cell1 = sheet1.getCell(1, 2);
					String content1 = cell1.getContents();

					System.out.print("the permissions of guest to table :" + " " + content1);
					System.out.println();
					// 关闭工作薄

					workbook.close();
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (ss[3].equals("root")) {
				File file = new File("user.xls");
				jxl.Workbook workbook;
				try {
					workbook = Workbook.getWorkbook(file);
					Sheet sheet = workbook.getSheet(0);


					Cell cell = sheet.getCell(1, 1);
					String content = cell.getContents();

					System.out.print("the permissions of root to database :" + " " + content);

					System.out.println();
					Sheet sheet1 = workbook.getSheet(1);
					Cell cell1 = sheet1.getCell(1, 1);
					String content1 = cell1.getContents();

					System.out.print("the permissions of root to table :" + " " + content1);
					System.out.println();
					// 关闭工作薄

					workbook.close();
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("the user is not exists !");
			}
		}else{
			System.out.println(
					"Sql statement input error, Please re-select your operating options：");
		}
   }
}
