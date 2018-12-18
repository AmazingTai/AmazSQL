package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/*
 * ʵ���û�Ȩ�޵��ջ�
 * revoke quanxian1,quanxian2... on db_name from user_name
 */
public class revoke extends sql {
	public static void revokedb() {
		// �ջ��û������ݿ��ĳЩ����Ȩ��
		System.out.println("Please revoke permissions of database from a user as follows��");
		System.out.println("revoke permission1,permission2,... on db_name from user_name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		String[] ss1 = ss[1].split(",");
		String sss1 = "";
		if (ss[0].equals("revoke") && ss[2].equals("on") && ss[4].equals("from")) {
			if (ss[5].equals("guest")) {
				File file = new File("mydatabase//" + ss[3]);
				if (file.exists()) {
					try {
                        File file1 = new File("user.xls");
                       
						Workbook wb = Workbook.getWorkbook(file1);

						WritableWorkbook wwb = Workbook.createWorkbook(file1, wb);
						WritableSheet ws = wwb.getSheet(0);
						int columnum = ws.getColumns();// �õ�����
						int rownum = ws.getRows();// �õ�����
						Sheet sheet = wb.getSheet(0);
						WritableCell cell1 = null;
						Cell cell = sheet.getCell(1, 2);
						String content = cell.getContents();//ԭ���д��Ȩ��   select drop create of db_name
                        
						for (int i = 0; i < ss1.length; i++) {
							
							sss1 += ss1[i] + " ";
							 //��Ҫ�ջص�Ȩ��ƴ�ӳ��ַ���  drop create
							
						}
						content = content.replaceAll(sss1, "");//���ջص�Ȩ����ԭ�����Ȩ���е�һ�γ��ֵ�
						cell1 = new Label(1, 2, content);
						ws.addCell(cell1);
						wwb.write();
						System.out.println("revoke permissions of database to guest successfully !");
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
				} else {
					System.out.println("the database is not exists !");
				}
			} else {
				System.out.println("the user is not exists !");
			}
		} else {
			System.out.println("Sql statement input error, Please re-select your operating options��");
		}
	}

	public static void revoketb() {
		// �ջ��û������ݱ��ĳЩ����Ȩ��
		//revoke quanxian1,quanxian2... on tb_name of db_name from user_name 
		System.out.println("Please revoke permissions of table from a user as follows��");
		System.out.println("revoke permission1,permission2,... on tb_name of db_name from user_name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		String[] ss1 = ss[1].split(",");
		String sss1 = "";
		// ss[1]��permission1,permission2,... ss[3]�����ݱ������ ss[5]�����ݿ�����
		if (ss[0].equals("revoke") && ss[2].equals("on") && ss[4].equals("of")&&ss[6].equals("from")) {
			if (ss[7].equals("guest")) {
				File file = new File("mydatabase//" + ss[5],ss[3]+".xls");
				if (file.exists()) {
					try {
                        
						File file1 = new File("user.xls");
						Workbook wb = Workbook.getWorkbook(file1);

						WritableWorkbook wwb = Workbook.createWorkbook(file1, wb);
						WritableSheet ws = wwb.getSheet(1);
						int columnum = ws.getColumns();// �õ�����
						int rownum = ws.getRows();// �õ�����
						Sheet sheet = wb.getSheet(1);
						
						Cell cell = sheet.getCell(1, 2);
						String content = cell.getContents();//ԭ���д��Ȩ��   select drop create on tb_name of db_name 
                        
						WritableCell cell1 = null;

						for (int i = 0; i < ss1.length; i++) {
							
							sss1 += ss1[i] + " ";
							 //�ջص�Ȩ��   drop create
							
						}
						content = content.replaceAll(sss1, "");
						cell1 = new Label(1, 2, content);
						ws.addCell(cell1);
						wwb.write();
						System.out.println("revoke permissions of table to guest successfully !");
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
					"Sql statement input error, Please re-select your operating options��");
		}
	}
}
