package src.amazsql;
/*���ݱ��Լ��������
 * not_null;primary_key;
 *
 * 2.��������������ʾ��sql���ʱ�����ҵ���һ����(�����ŵ�λ��,�Ѹ÷���֮ǰ���ַ��������ʶ������ȡ�������.
 * 3.��ȡ�е�����,��ÿ������֮ǰ���ַ��������ó�����Ϊ
 * �����ݱ�洢��xls�ļ�
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

	// ȫ�ֱ����������ļ������Ƹ�createfile����
	public static String filename = null;
	public static int column = 0;// �������
	public static String sqlsentence = null;// �����ö��Ÿ�����sql���


	public static void createtb() {
		System.out.println("Please create the data table in the following format��");
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

		// 44--46Ϊ��ȡ���ݱ�����֣�������Ӧ���ļ�
		String[] ss = sentence.split("\\s+");
			String[] ss1 = sentence.split(",");
		column = ss1.length;//��������
		sqlsentence = sentence.substring(0, sentence.lastIndexOf(")"));
		filename = ss[2].substring(0, ss[2].lastIndexOf("("));
		createFile(filename);
    }
	public static void createFile(String filename1) {
		
		//ss1����ַ�����������  ss1[0]:create table ����(����1�� Լ������
		//ss1[1]:����2�� Լ������
		//ss2[2]:����3�� Լ������);
		String[] ss1 = sqlsentence.split(",");
		//String ss1str = ss1[0].toString();
		//String [] ss11 = ss1[0].split(" ");
		ss1[0] = ss1[0].substring(ss1[0].indexOf("(")+1);//��һ����������ֵ��ss11[0]
		                                                   //ss11[3]
		/*//forѭ����������������ֵ��ss1
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
				// successfully��");
				//System.out.println(createtb.column);
				if (workbook != null) {
					// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
					WritableSheet sheet = workbook.createSheet(filename1, 0);
					// �� row �� column
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
				System.out.println("The data table was created successfully��");

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

			// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
			// �Լ���Ԫ������Ϊtest

		} else {
			System.out.println("The data table already exists��");
		}

	}
}
