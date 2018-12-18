package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/*
 * 实现权限的赋予，并将其存下来
 * 对表的权限的赋予
 * 对数据库的权限的赋予
 * grant select, insert, update, delete on table_name of dbname to user;
 */
public class grant extends sql {
	public static void grantdb() {
		System.out.println("Please grant permissions of database to a user as follows：");
		System.out.println("grant permission1,permission2,... on db_name to user_name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		String[] ss1 = ss[1].split(",");
		String sss1 = "";

		// ss[1]是permission1,permission2,... ss[3]是数据库的名字 ss[5]是lijin
		if (ss[0].equals("grant") && ss[2].equals("on") && ss[4].equals("to")) {
			if (ss[5].equals("guest")) {
				File file = new File("mydatabase//" + ss[3]);
				if (file.exists()) {
					try {
                         File file1 = new File("user.xls");
                        
						Workbook wb = Workbook.getWorkbook(file1);

						WritableWorkbook wwb = Workbook.createWorkbook(file1, wb);
						WritableSheet ws = wwb.getSheet(0);
						int columnum = ws.getColumns();// 得到列数
						int rownum = ws.getRows();// 得到行数

						WritableCell cell1 = null;

						for (int i = 0; i < ss1.length; i++) {
							
							sss1 += ss1[i] + " ";
							 //System.out.println(sss1);
							
						}
						cell1 = new Label(1, 2, sss1+" "+"of "+ss[3]);
						ws.addCell(cell1);
						wwb.write();
						System.out.println("grant permissions of database to guest successfully !");
						wwb.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("the database is not exists !");
				}
			} else {
				System.out.println("the user is not exists !");
			}
		} else {
			System.out.println(
					"Sql statement input error, select the database failed! Please re-select your operating options：");
		}
	}
	public static void granttb(){
		//grant select, insert, update, delete on table_name of dbname to user;
		System.out.println("Please grant permissions of table to a user as follows：");
		System.out.println("grant permission1,permission2,... on tb_name of db_name to user_name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		String[] ss1 = ss[1].split(",");
		String sss1 = "";
		// ss[1]是permission1,permission2,... ss[3]是数据表的名字 ss[5]是数据库名字
		if (ss[0].equals("grant") && ss[2].equals("on") && ss[4].equals("of")&&ss[6].equals("to")) {
			if (ss[7].equals("guest")) {
				File file = new File("mydatabase//" + ss[5],ss[3]+".xls");
				if (file.exists()) {
					try {
                        
						File file1 = new File("user.xls");
						Workbook wb = Workbook.getWorkbook(file1);

						WritableWorkbook wwb = Workbook.createWorkbook(file1, wb);
						WritableSheet ws = wwb.getSheet(1);
						int columnum = ws.getColumns();// 得到列数
						int rownum = ws.getRows();// 得到行数

						WritableCell cell1 = null;

						for (int i = 0; i < ss1.length; i++) {
							
							sss1 += ss1[i] + " ";
							 //System.out.println(sss1);
							
						}
						cell1 = new Label(1, 2, sss1+" "+"on "+ss[3]+" of "+ss[5]);
						ws.addCell(cell1);
						wwb.write();
						System.out.println("grant permissions of table to guest successfully !");
						wwb.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("the user is not exists !");
			}
		} else {
			System.out.println(
					"Sql statement input error, Please re-select your operating options：");
		}
	}
}
