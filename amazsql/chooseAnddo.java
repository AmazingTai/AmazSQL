package src.amazsql;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

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
 * ����ĺ�������ʵ��ѡ�����ݿⲢ���в���
 */
public class chooseAnddo extends sql {

	public static void listDirectory(File dir) {
		// exists()���������ж��ļ���Ŀ¼�Ƿ����
		if (!dir.exists()) {
			System.out.println("directory��" + dir + "does not exist");
		}
		// isDirectory()���������ж�File��Ķ����Ƿ���Ŀ¼
		if (!dir.isDirectory()) {
			System.out.println(dir + "is not the directory");

		}
		// list���������г���ǰĿ¼�µ���Ŀ¼���ļ�
		String[] filenames = dir.list(); // ���ص����ַ������� ֱ���ӵ����� ��������Ŀ¼�µ�����
		//filedbname = dir.list();
		for(int i= 0;i<filenames.length;i++){
			System.out.println(filenames[i]);

		}


	}
	// ȫ�ֱ������������ݿ����������createtb�ĺ���
	public static String str = null;

	public static void chooseANDdoroot() {
		System.out.println("Please select the database as follows��");
		System.out.println("use database-name;");
/*    Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}
		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
		String[] ss = sentence.split("\\s+");

		str=ss[1];
		 //File file = new File("mydatabase\\"+chooseAnddo.str);
*/

		Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();

		String[] ss = sentence.split("\\s+");

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}

		File file = new File("mydatabase");
		chooseAnddo.listDirectory(file);
		 if(file.exists()){
		 	//System.out.println(file);
		if (ss[0].equals("use")) {

			if (ss[1].length() > 1) {
				
				//System.out.println(sqlhelp.filedbname.toString());
				
				
					System.out.println("You have chosen the database:" + " " + ss[1]);
					str = ss[1];
				    //chooseAnddo.str = chooseAnddo.str.substring(0,chooseAnddo.str.length() - 1);
					str = str.substring(0,str.length() - 1);
					System.out.println("Please select your operation under this database��");
					System.out.println("----------------------------");
					System.out.println("1.create table");
					System.out.println("2.Show the tables under the current database");
					System.out.println("3.delete table");
					System.out.println("4.Show the data structure in the data table");
					System.out.println("5.Insert the contents into the data table");
					System.out.println("6.Query the contents of the data table");
					System.out.println("7.update the table");
					System.out.println("8.delete some contents from the table");
					System.out.println("9.exit current database");
					System.out.println("10.view");
					System.out.println("----------------------------");
					System.out.println("11.help databases");
					System.out.println("12.help table");
					System.out.println("13.help view ");
					System.out.println("14.help index");
					System.out.println("----------------------------");
					System.out.println("Please enter the options��");
					while (true) {
						
						Scanner input5 = new Scanner(System.in);
						try{
						int option = input5.nextInt();
						switch (option) {
						case 1:
							// ���ݱ�Ľ��� -- ʶ��sql��create table ������
							createtb.createtb();
							break;
						case 2:
							// �鿴��ǰ���ݿ�������Щ���ݱ�
							sqlhelp.tbhelpcount();
							break;
						case 3:
							// ɾ��ĳ�����ݱ�
							deletetb.deletb();
							break;
						case 4:
							//�鿴ĳ����Ľṹ
							describetb.desctb();
							break;
						case 5:
							//�������ݵ�ĳ������
							inserttb.iserttb();
							break;
						case 6:
							//��ѯ���ݱ������
							selectfromTb.sftb();
							break;
						case 7:
							//���±�
							updatatb.update();
							break;
						case 8:
							//ɾ�����е�ĳЩ����
							deletefromTb.delfromtb();
							break;
						case 9:
							sql.sqldbroot();
							break;
						case 10:
							//��ͼ
							view.view();
							break;
						case 11:
							//help databases;
							sqlhelp.tbhelpcount();
							break;
						case 12:
							//help table
							describetb.desctb();
							break;
						case 13:
							//help view
							view.ViewPrint();
							break;
						case 14:
							//help index
							view.view();
							break;

						}
						}catch(InputMismatchException e){  //��׽�쳣
						      System.out.println("The input format is incorrect !");
						}
					}
				
				
			} else {
				System.out.println(
						"Database name format input error, select database failed! Please re-select your operating options��");
			}

		} else {
			System.out.println(
					"Sql statement input error, select the database failed! Please re-select your operating options��");
		}
		 }else{
			 System.out.println(
						"database "+str+" is not exists ��");
			}
		 }
		// final String dbname = ss[1].substring(0, ss[1].lastIndexOf(";"));
	public static void chooseANDdovis() {
		System.out.println("Please select the database as follows��");
		System.out.println("use database-name;");
		/*
		Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}
		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
		String[] ss = sentence.split("\\s+");
		str=ss[1];
		*/

		Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();

		String[] ss = sentence.split("\\s+");

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}

		 //File file = new File("mydatabase//"+chooseAnddo.str);

		File file = new File("mydatabase");
		//chooseAnddo.listDirectory(file);
		
		File file1 = new File("user.xls");
		jxl.Workbook workbook;
		
		 if(file.exists()){
		if (ss[0].equals("use")) {
			System.out.println(ss);
			if (ss[1].length() > 1) {
				
				//System.out.println(sqlhelp.filedbname.toString());
				   int option_flag = 3;
				
					System.out.println("You have chosen the database:" + " " + ss[1]);
					str = ss[1];
				    str = str.substring(0,str.length() - 1);
					System.out.println("Please select your operation under this database��");
					System.out.println("----------------------------");
					
					System.out.println("1.Show the tables under the current database");
					
					System.out.println("2.Show the data structure in the data table");
					
					System.out.println("3.Create the table under the current database");
					option_flag++;
					System.out.println("4.delete table");
					option_flag++;
					System.out.println("5.Insert the contents into the data table");
					option_flag++;
					System.out.println("6.Query the contents of the data table");
					option_flag++;
					System.out.println("7.update the table");
					option_flag++;
					System.out.println("8.delete some contents from the table");
				
					
					System.out.println("9.exit current database");
					//System.out.println("")
					System.out.println("----------------------------");
					System.out.println("Please enter the options��");
					while (true) {
						
						Scanner input5 = new Scanner(System.in);
						try{
						int option = input5.nextInt();
						switch (option) {
						
						case 1:
							// �鿴��ǰ���ݿ�������Щ���ݱ�
							sqlhelp.tbhelpcount();
							break;
						
						case 2:
							//�鿴ĳ����Ľṹ
							describetb.desctb();
							break;
							
						case 3:	
							// ���ݱ�Ľ��� -- ʶ��sql��create table ����
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("create") != -1)
									createtb.createtb();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}
						case 4:	
							// ɾ��ĳ�����ݱ�
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("delete") != -1)
									deletetb.deletb();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}	
						case 5:	
							//�������ݵ�ĳ������
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("insert") != -1)
									inserttb.iserttb();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}	
						case 6:	
							//��ѯ���ݱ������
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("select") != -1)
									selectfromTb.sftb();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}	
						case 7:	
							///���±�
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("update") != -1)
									updatatb.update();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}
						case 8:	
							////ɾ�����е�ĳЩ����
							{
							try {
								workbook = Workbook.getWorkbook(file1);
								Sheet sheet = workbook.getSheet(0);

								Cell cell = sheet.getCell(1, 2);
								String content = cell.getContents();

								if(content.indexOf("delete") != -1)
									deletefromTb.delfromtb();
								else 
									System.out.println("Permission denied");
								// �رչ�����
								} catch (BiffException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
							}	
						case 9:
							sql.sqldbvis();;
						}
						}catch(InputMismatchException e){  //��׽�쳣
						      System.out.println("The input format is incorrect !");
						}
					}
				
				
			} else {
				System.out.println(
						"Database name format input error, select database failed! Please re-select your operating options��");
			}

		} else {
			System.out.println(
					"Sql statement input error, select the database failed! Please re-select your operating options��");
		}
		 }else{
			 System.out.println(
						"database "+str+" is not exists ��");
			}
		 }
	}


