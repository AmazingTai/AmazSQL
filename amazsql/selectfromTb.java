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
 * 实现数据表的查询功能
 * select 属性1,属性2...  from tbname where 属性=" ";
 * tip:select sage from student where sage= ' 19'; zhuyukongge
 */

public class selectfromTb extends sql {

	public static String content = null;

	public static void sftbf(String sent) //触发器
	{
		//String ss[];


		sent = sent.substring(0, sent.lastIndexOf(";"));

		String[] ss = sent.split("\\s+");
		if (ss.length == 4) {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[1].equals("*")) {


				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1处 去掉';'，否侧找不到文件
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");


				if (file.exists()) {

					jxl.Workbook workbook;
					try {
						workbook = Workbook.getWorkbook(file);
						Sheet sheet = workbook.getSheet(0);
						int columnum = sheet.getColumns();// 得到列数
						int rownum = sheet.getRows();// 得到行数
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

						// 关闭工作薄

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
						"Sql statement input error, Please re-select your operating options：");
			}
		}
		// 以下是有where的时候，ss.length >4
		else {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[4].equals("where")) {

				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1处 去掉';'，否侧找不到文件
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");
				if (file.exists()) {
					if (ss[1].equals("*")) {
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值flag3行的内容
							System.out.println("The contents of the query  is :");
							for (int j = 0; j < columnum; j++) {
								Cell cell1 = sheet.getCell(j, flag3+2); //bug2//
								content = cell1.getContents();
								System.out.print(content + "  ");
								System.out.println();
							}

							// 关闭工作薄

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (ss[1].indexOf(",") == -1) {
						// ss[1]没有逗号，即只有一个属性需要查询
						// 获得所需要操作的文件的工作薄

						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值是flag1列的，flag3行的内容
							Cell cell1 = sheet.getCell(flag1, flag3+2); //bug2//

							System.out.println("The contents of the query --" + ss[1] + "-- is :");
							content = cell1.getContents();

							System.out.println(content);
							// 关闭工作薄

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else { //bug
						// ss[1]有逗号

						String[] ss1 = ss[1].split(",");
						// ss1[0] ss1[1] ...
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int[] flag = new int[ss1.length];// 存放要查询的列数
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值是flag[i]列的，flag3行的内容
							for (int i = 0; i < ss1.length; i++) {
								Cell cell1 = sheet.getCell(flag[i], flag3+2);

								System.out.println("The contents of the query --" + ss1[i] + "-- is :");
								content = cell1.getContents();

								System.out.println(content);
								// 关闭工作薄
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
						"Sql statement input error,  Please re-select your operating options：");
			}
		}
	}
	public static void sftb() {
		System.out.println("Please Query the contents of the data table as follows：");
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
		 * ss[0]是select ss[1]是属性1,属性2...或者只是一个* ss[2]是from ss[3]是所查询的表的名字
		 * ss[4]是where ss[5]是attribute ss[7]是‘value’
		 */

		// 4种情况，1.select * from tb 2.select单个属性
		// 两个大类:没有where的时候，长度等于4 其余的为有where
		if (ss.length == 4) {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[1].equals("*")) {


				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1处 去掉';'，否侧找不到文件
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");


				if (file.exists()) {

					jxl.Workbook workbook;
					try {
						workbook = Workbook.getWorkbook(file);
						Sheet sheet = workbook.getSheet(0);
						int columnum = sheet.getColumns();// 得到列数
						int rownum = sheet.getRows();// 得到行数
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

						// 关闭工作薄

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
						"Sql statement input error, Please re-select your operating options：");
			}
		}
		// 以下是有where的时候，ss.length >4
		else {
			if (ss[0].equals("select") && ss[2].equals("from") && ss[4].equals("where")) {

				//ss[3]=ss[3].substring(0,ss[3].length() - 1);  //bug1处 去掉';'，否侧找不到文件
				File file = new File("mydatabase//" + chooseAnddo.str, ss[3] + ".xls");
				if (file.exists()) {
					if (ss[1].equals("*")) {
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值flag3行的内容
							System.out.println("The contents of the query  is :");
							for (int j = 0; j < columnum; j++) {
								Cell cell1 = sheet.getCell(j, flag3+2);
								content = cell1.getContents();
								System.out.print(content + "  ");
								System.out.println();
							}

							// 关闭工作薄

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (ss[1].indexOf(",") == -1) {
						// ss[1]没有逗号，即只有一个属性需要查询
						// 获得所需要操作的文件的工作薄

						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值是flag1列的，flag3行的内容
							Cell cell1 = sheet.getCell(flag1, flag3+2);

							System.out.println("The contents of the query --" + ss[1] + "-- is :");

							content = cell1.getContents();
							System.out.println(content);
							// 关闭工作薄

							workbook.close();
						} catch (BiffException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else { //bug
						// ss[1]有逗号

						String[] ss1 = ss[1].split(",");
						// ss1[0] ss1[1] ...
						jxl.Workbook workbook;
						try {
							workbook = Workbook.getWorkbook(file);
							Sheet sheet = workbook.getSheet(0);
							int columnum = sheet.getColumns();// 得到列数
							int rownum = sheet.getRows();// 得到行数
							int flag1 = 0;// 当找到与查询的属性相同的属性时，记录下这是第几列
							int[] flag = new int[ss1.length];// 存放要查询的列数
							int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
							int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
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
								// 匹配where条件后的数值和表格中的某一列的数值相等
								Cell cl = sheet.getCell(flag2, i);

								if (cl.getContents().equals(ss[7].substring(1, ss[7].lastIndexOf("'")))) {
									flag3 = i;

								}
							}
							// 经过以上，须select的属性值是flag[i]列的，flag3行的内容
							for (int i = 0; i < ss1.length; i++) {
								Cell cell1 = sheet.getCell(flag[i], flag3+2);

								System.out.println("The contents of the query --" + ss1[i] + "-- is :");
								content = cell1.getContents();
								System.out.println(content);
								// 关闭工作薄
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
						"Sql statement input error,  Please re-select your operating options：");
			}
		}
	}
}


