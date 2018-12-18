package src.amazsql;
/*数据表的约束条件：
 * not_null;primary_key;
 *
 * 2.当读到了如上所示的sql语句时，先找到第一个“(”符号的位置,把该符号之前的字符串提出来识别来获取表的名字.
 * 3.提取列的名字,将每个逗号之前的字符串单独拿出来作为
 * 将数据表存储在xls文件
 *
 */

//import java.awt.Label;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
//import jxl.*;
import jxl.write.*;
import jxl.Workbook;
import jxl.Cell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import java.io.File;


public class createtb extends sql {

	// 全局变量用来传文件的名称给createfile函数
	public static String filename = null;
	public static int column = 0;// 表的列数
	public static String sqlsentence = null;// 保存用逗号隔开的sql语句


	public static void createtb() {
		System.out.println("Please create the data table in the following format：");
		System.out.println("----------------------------");
		System.out.println("|create table table-name(         ");
		System.out.println("|Attributes_1_name  Restrictions, ");
		System.out.println("|Attributes_2_name  Restrictions, ");
		System.out.println("|   .                             ");
		System.out.println("|   .                             ");
		System.out.println("|   .                             ");
		System.out.println("|Attributes_n_name  Restrictions);");
		System.out.println("----------------------------");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();
		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input3.nextLine();
		}
		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

		// 44--46为提取数据表的名字，创建相应的文件
		String[] ss = sentence.split("\\s+");
			String[] ss1 = sentence.split(",");
		column = ss1.length;//几个属性
		sqlsentence = sentence.substring(0, sentence.lastIndexOf(")"));
		filename = ss[2].substring(0, ss[2].lastIndexOf("("));
		createFile(filename);
    }
	public static void createFile(String filename1) {
		
		//ss1这个字符数组里存的是  ss1[0]:create table 表名(属性1名 约束条件
		//ss1[1]:属性2名 约束条件
		//ss2[2]:属性3名 约束条件);
		String[] ss1 = sqlsentence.split(",");
		//String ss1str = ss1[0].toString();
		//String [] ss11 = ss1[0].split(" ");
		ss1[0] = ss1[0].substring(ss1[0].indexOf("(")+1);//第一个属性名赋值给ss11[0]
		                                                   //ss11[3]
		/*//for循环将各个属性名赋值给ss1
        for(int n = 1;n<createtb.column;n++){
        	ss1[n] = ss1[n].substring(0, ss1[n].indexOf(" "));
        }*/

		//chooseAnddo.str = chooseAnddo.str.substring(0,chooseAnddo.str.length() - 1);
		File file = new File("mydatabase//" + chooseAnddo.str, filename1 + ".xls");
		if (!file.exists()) {
			WritableWorkbook workbook = null;
			try {
				workbook = Workbook.createWorkbook(file);
				// System.out.println("The data table was created
				// successfully！");
				//System.out.println(createtb.column);
				if (workbook != null) {
					// 生成名为“第一页”的工作表，参数0表示这是第一页
					WritableSheet sheet = workbook.createSheet(filename1, 0);
					// 行 row 列 column
					for (String str:ss1
						 ) {
						System.out.println(str);

					}
					for (int j = 0; j < createtb.column; j++) {
						Label label = new Label(j, 0, ss1[j]);
						sheet.addCell(label);
						
					}
					workbook.write();
					//label = null;
					workbook.close();
					
				}
				// sheet.addCell(label);
				System.out.println("The data table was created successfully！");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

		} else {
			System.out.println("The data table already exists！");
		}

	}
}
