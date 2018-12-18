package src.amazsql;

import java.io.File;
import java.util.Scanner;
import java.lang.String;
/*
 * ������ʵ�ֵ���Ҫ�����ǶԸ���help�������ʶ��Ȼ�������������;
 * 1.���롰help database���������������ݱ���ͼ����������Ϣ��ͬʱ��ʾ��������ͣ�
 * 2.���롰help table ���������������ݱ����������Ե���ϸ��Ϣ��
 * 3.���롰help view ��ͼ������������ͼ�Ķ�����䣻
 * 4.���롰help index ����������������������ϸ��Ϣ��
 * */
public class sqlhelp extends sql {

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
		for (int i = 0; i < filenames.length; i++) {
			System.out.println(filenames[i]);

		}


	}

	public static void listfile(File dir) {
		File files[] = dir.listFiles(); // ���ص���ֱ����Ŀ¼���ļ����ĳ���
		// for (File file : files) {
		// System.out.println(file);
		if (files != null && files.length > 0) {

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					// �ݹ�
					listfile(files[i]);
				} else {

					System.out.println(files[i].getName().substring(0, files[i].getName().indexOf(".")));
					//filename [i]= files[i].getName();//����ȡ�����ݱ�����ִ浽ȫ�ֱ���filename[];
				}
			}
		}
	}
	// dbhelp���������鿴��ǰ����Щ���ݿ�

	public static void dbhelp() {
		System.out.println("Please enter the query in the following format��show databases;");

		Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();
		String[] ss = sentence.split("\\s+");

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}
		if (ss[0].equals("show")) {
			if (ss[1].equals("databases;")) {
				System.out.println("The current database has��");
				File file = new File("mydatabase");

				System.out.println(file);

				sqlhelp.listDirectory(file);
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");

			}
		} else {
			System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");
		}

	}

	/*
	 * tbhelpcount����������ѯĳ�����ݿ��������ҵ����ݱ�
	 */
	public static void tbhelpcount() {
		System.out.println("Please enter the query in the following format:");
		/*Scanner input5 = new Scanner(System.in);
		String sentence = input5.nextLine();
		
		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			
			sentence = sentence +" "+ input5.nextLine();
		}
		//sentence = sentence.substring(0, sentence.lastIndexOf(";"));
		String[] ss = sentence.split("\\s+");
		//System.out.println((ss[1].substring(0, ss[1].lastIndexOf(";"))));
		for(String a:ss)
			System.out.println(a); */

		Scanner input5 = new Scanner(System.in);
		String sentence = input5.nextLine();
		String[] ss = sentence.split("\\s+");


		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input5.nextLine();
		}
		if (ss[0].equals("help")) {
			if (ss[0].equals("help")) {
				if ((ss[1].equals("databases;"))) {
					//for(String a:ss)
					//	System.out.println(a);

					System.out.println("The data tables in the database of " + chooseAnddo.str + " are��");

					File file5 = new File("mydatabase//" + chooseAnddo.str);
					sqlhelp.listfile(file5);


				} else {
					System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");

				}
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");
			}
		} else {
			if (ss[0].equals("show")) {
				if ((ss[1].equals("tables;"))) {
					//for(String a:ss)
					//	System.out.println(a);

					System.out.println("The data tables in the database of " + chooseAnddo.str + " are��");
					//File file = new File("mydatabase\\"+chooseAnddo.str);

/*				File file = new File("mydatabase\\"+chooseAnddo.str);
				File file1 = new File("mydatabase//"+chooseAnddo.str);
				File file3 = new File (chooseAnddo.str);
				System.out.println(file3);
				System.out.println(file);
				System.out.println(file1);
				sqlhelp.listDirectory(file);
				sqlhelp.listDirectory(file1);
				sqlhelp.listDirectory(file3);
*/


					File file5 = new File("mydatabase//" + chooseAnddo.str);
					sqlhelp.listfile(file5);


				} else {
					System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");

				}
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options��");
			}
		}

	}
}