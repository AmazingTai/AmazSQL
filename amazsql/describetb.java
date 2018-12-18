package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*
 * 查看某个表的结构
 * 输入describe tbname ，输出表的各个属性及其约束条件
 * 读某个工作簿里的某个表的第一行的各个单元格的内容
 */
public class describetb extends sql {
	public static void desctb() {
		System.out.println("Please describe the table as follows：");
		//System.out.println("describe table-name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}
		// String[] ss = sentence.split("\\s+");
		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		String[] ss = sentence.split("\\s+");
		//ss[1]存的是要描述的表的名字
		if (ss[0].equals("help")) {
			if (ss[0].equals("help") && ss[1].equals("table")) {
				File file = new File("mydatabase//" + chooseAnddo.str, ss[2] + ".xls");
				if (file.exists()) {

					//获得所需要操作的文件的工作薄
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
							String yueshu = content.substring(content.indexOf(" ") + 1);

							System.out.println("|      Field :      |" + " " + shuxing);
							System.out.println("|   Restrictions :  |" + " " + yueshu);

							//temp[i]=content;
						}
						//关闭工作薄

						workbook.close();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("the database table is not exists !");
				}
			} else {
				System.out.println("Sql statement input error, select the database failed! Please re-select your operating options：");
			}

		} else {
			if (ss[0].equals("describe") && ss[1].equals("table")) {
				File file = new File("mydatabase//" + chooseAnddo.str, ss[2] + ".xls");
				if (file.exists()) {

					//获得所需要操作的文件的工作薄
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
							String yueshu = content.substring(content.indexOf(" ") + 1);

							System.out.println("|      Field :      |" + " " + shuxing);
							System.out.println("|   Restrictions :  |" + " " + yueshu);

							//temp[i]=content;
						}
						//关闭工作薄

						workbook.close();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("the database table is not exists !");
				}
			} else {
				System.out.println("Sql statement input error, select the database failed! Please re-select your operating options：");
			}
		}
	}
}