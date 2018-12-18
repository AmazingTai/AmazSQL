package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.lang.String;

/*
 * ʵ�����ݱ�Ĳ�ѯ����
 * select ����1,����2...  from tbname where ����=" ";
 * tip:select sage from student where sage= ' 19'; zhuyukongge
 */

public class selectfromTb extends sql {

	public static String content = null;

	public static void sftbf(String sent) //������
	{
		//String ss[];


		sent = sent.substring(0, sent.lastIndexOf(";"));

		String[] ss = sent.split("\\s+");
		if (ss.length == 4) {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[1].equals("*")) {


				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1�� ȥ��';'������Ҳ����ļ�
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");


				if (file.exists()) {

					jxl.Workbook workbook;
					try {
						workbook = Workbook.getWorkbook(file);
						Sheet sheet = workbook.getSheet(0);
						int columnum = sheet.getColumns();// �õ�����
						int rownum = sheet.getRows();// �õ�����
						for (int j = 0; j < columnum; j++) {
							Cell cell = sheet.getCell(j, 0);
							String content = cell.getContents();
							String shuxing = content.substring(0, content.indexOf(" "));
							System.out.print(shuxing + "         ");
						}
						System.out.println();
						for (int i = 1; i < rownum; i++) {
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, i);
								String content = cell.getContents();

								System.out.print(content + "        ");

							}
							System.out.println();
						}

						// �رչ�����

						workbook.close();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {


					System.out.println("the data table is not exist !");
				}
			} else {
				System.out.println(
						"Sql statement input error, Please re-select your operating options��");
			}
		}
		// ��������where��ʱ��ss.length >4
		else {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[4].equals("where")) {

				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1�� ȥ��';'������Ҳ����ļ�
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");
				if (file.exists()) {
					if (ss[1].equals("*")) {
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, 0);
								String content = cell.getContents();
								String shuxing = content.substring(0, content.indexOf(" "));
								String yueshu = content.substring(content.indexOf(" ") + 1);

								if (ss[5].equals(shuxing)) {
									flag2 = j;
								}

							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵflag3�е�����
							System.out.println("The contents of the query  is :");
							for (int j = 0; j < columnum; j++) {
								Cell cell1 = sheet.getCell(j, flag3+2); //bug2//
								content = cell1.getContents();
								System.out.print(content + "  ");
								System.out.println();
							}

							// �رչ�����

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (ss[1].indexOf(",") == -1) {
						// ss[1]û�ж��ţ���ֻ��һ��������Ҫ��ѯ
						// �������Ҫ�������ļ��Ĺ�����

						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, 0);
								String content = cell.getContents();
								String shuxing = content.substring(0, content.indexOf(" "));
								String yueshu = content.substring(content.indexOf(" ") + 1);
								if (ss[1].equals(shuxing)) {
									flag1 = j;
								}
								if (ss[5].equals(shuxing)) {
									flag2 = j;
								}

							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵ��flag1�еģ�flag3�е�����
							Cell cell1 = sheet.getCell(flag1, flag3+2); //bug2//

							System.out.println("The contents of the query --" + ss[1] + "-- is :");
							content = cell1.getContents();

							System.out.println(content);
							// �رչ�����

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else { //bug
						// ss[1]�ж���

						String[] ss1 = ss[1].split(",");
						// ss1[0] ss1[1] ...
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int[] flag = new int[ss1.length];// ���Ҫ��ѯ������
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int i = 0; i < ss1.length; i++) {
								for (int j = 0; j < columnum; j++) {
									Cell cell = sheet.getCell(j, 0);
									String content = cell.getContents();
									String shuxing = content.substring(0, content.indexOf(" "));
									String yueshu = content.substring(content.indexOf(" ") + 1);
									if (ss1[i].equals(shuxing)) {
										flag[i] = j;
									}
									if (ss[5].equals(shuxing)) {
										flag2 = j;
									}

								}
							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵ��flag[i]�еģ�flag3�е�����
							for (int i = 0; i < ss1.length; i++) {
								Cell cell1 = sheet.getCell(flag[i], flag3+2);

								System.out.println("The contents of the query --" + ss1[i] + "-- is :");
								content = cell1.getContents();

								System.out.println(content);
								// �رչ�����
							}
							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("the data table is not exist !");
				}

			} else {
				System.out.println(
						"Sql statement input error,  Please re-select your operating options��");
			}
		}
	}
	public static void sftb() {
		System.out.println("Please Query the contents of the data table as follows��");
		System.out.println("select attribute1,attribute2... from tbname where attribute = '' ;");


		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}

		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");

		for (String a : ss
				) {
			System.out.println(a);
		}


		/*Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();
		String[] ss = sentence.split("\\s+");

		while(sentence.lastIndexOf(";") != sentence.length() - 1)
		{
			sentence = sentence + " " + input3.nextLine();
		}
*/
		/*
		 * ss[0]��select ss[1]������1,����2...����ֻ��һ��* ss[2]��from ss[3]������ѯ�ı������
		 * ss[4]��where ss[5]��attribute ss[7]�ǡ�value��
		 */

		// 4�������1.select * from tb 2.select��������
		// ��������:û��where��ʱ�򣬳��ȵ���4 �����Ϊ��where
		if (ss.length == 4) {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[1].equals("*")) {


				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1�� ȥ��';'������Ҳ����ļ�
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");


				if (file.exists()) {

					jxl.Workbook workbook;
					try {
						workbook = Workbook.getWorkbook(file);
						Sheet sheet = workbook.getSheet(0);
						int columnum = sheet.getColumns();// �õ�����
						int rownum = sheet.getRows();// �õ�����
						for (int j = 0; j < columnum; j++) {
							Cell cell = sheet.getCell(j, 0);
							String content = cell.getContents();
							String shuxing = content.substring(0, content.indexOf(" "));
							System.out.print(shuxing + "         ");
						}
						System.out.println();
						for (int i = 1; i < rownum; i++) {
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, i);
								 content = cell.getContents();

								System.out.print(content + "        ");

							}
							System.out.println();
						}

						// �رչ�����

						workbook.close();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {


					System.out.println("the data table is not exist !");
				}
			} else {
				System.out.println(
						"Sql statement input error, Please re-select your operating options��");
			}
		}
		// ��������where��ʱ��ss.length >4
		else {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[4].equals("where")) {

				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1�� ȥ��';'������Ҳ����ļ�
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");
				if (file.exists()) {
					if (ss[1].equals("*")) {
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, 0);
								String content = cell.getContents();
								String shuxing = content.substring(0, content.indexOf(" "));
								String yueshu = content.substring(content.indexOf(" ") + 1);

								if (ss[5].equals(shuxing)) {
									flag2 = j;
								}

							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵflag3�е�����
							System.out.println("The contents of the query  is :");
							for (int j = 0; j < columnum; j++) {
								Cell cell1 = sheet.getCell(j, flag3+2);
								content = cell1.getContents();
								System.out.print(content + "  ");
								System.out.println();
							}

							// �رչ�����

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (ss[1].indexOf(",") == -1) {
						// ss[1]û�ж��ţ���ֻ��һ��������Ҫ��ѯ
						// �������Ҫ�������ļ��Ĺ�����

						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int j = 0; j < columnum; j++) {
								Cell cell = sheet.getCell(j, 0);
								String content = cell.getContents();
								String shuxing = content.substring(0, content.indexOf(" "));
								String yueshu = content.substring(content.indexOf(" ") + 1);
								if (ss[1].equals(shuxing)) {
									flag1 = j;
								}
								if (ss[5].equals(shuxing)) {
									flag2 = j;
								}

							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵ��flag1�еģ�flag3�е�����
							Cell cell1 = sheet.getCell(flag1, flag3+2);

							System.out.println("The contents of the query --" + ss[1] + "-- is :");

							content = cell1.getContents();
							System.out.println(content);
							// �رչ�����

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else { //bug
						// ss[1]�ж���

						String[] ss1 = ss[1].split(",");
						// ss1[0] ss1[1] ...
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// �õ�����
							int rownum = sheet.getRows();// �õ�����
							int flag1 = 0;// ���ҵ����ѯ��������ͬ������ʱ����¼�����ǵڼ���
							int[] flag = new int[ss1.length];// ���Ҫ��ѯ������
							int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
							int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
							for (int i = 0; i < ss1.length; i++) {
								for (int j = 0; j < columnum; j++) {
									Cell cell = sheet.getCell(j, 0);
									String content = cell.getContents();
									String shuxing = content.substring(0, content.indexOf(" "));
									String yueshu = content.substring(content.indexOf(" ") + 1);
									if (ss1[i].equals(shuxing)) {
										flag[i] = j;
									}
									if (ss[5].equals(shuxing)) {
										flag2 = j;
									}

								}
							}
							for (int i = 0; i < rownum; i++) {
								// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// �������ϣ���select������ֵ��flag[i]�еģ�flag3�е�����
							for (int i = 0; i < ss1.length; i++) {
								Cell cell1 = sheet.getCell(flag[i], flag3+2);

								System.out.println("The contents of the query --" + ss1[i] + "-- is :");
								content = cell1.getContents();
								System.out.println(content);
								// �رչ�����
							}
							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("the data table is not exist !");
				}

			} else {
				System.out.println(
						"Sql statement input error,  Please re-select your operating options��");
			}
		}
	}
}


