package src.amazsql;

import java.io.File;
import java.util.Scanner;
import java.lang.String;
/*
 * 此子类实现的主要功能是对各种help命令进行识别，然后分情况进行输出;
 * 1.输入“help database”命令，输出所有数据表、视图和索引的信息，同时显示其对象类型；
 * 2.输入“help table 表名”命令，输出数据表中所有属性的详细信息；
 * 3.输入“help view 视图名”命令，输出视图的定义语句；
 * 4.输入“help index 索引名”命令，输出索引的详细信息；
 * */
public class sqlhelp extends sql {

	public static void listDirectory(File dir) {
		// exists()方法用于判断文件或目录是否存在
		if (!dir.exists()) {
			System.out.println("directory：" + dir + "does not exist");
		}
		// isDirectory()方法用于判断File类的对象是否是目录
		if (!dir.isDirectory()) {
			System.out.println(dir + "is not the directory");

		}
		// list方法用于列出当前目录下的子目录和文件
		String[] filenames = dir.list(); // 返回的是字符串数组 直接子的名称 不包含子目录下的内容
		//filedbname = dir.list();
		for (int i = 0; i < filenames.length; i++) {
			System.out.println(filenames[i]);

		}


	}

	public static void listfile(File dir) {
		File files[] = dir.listFiles(); // 返回的是直接子目录（文件）的抽象
		// for (File file : files) {
		// System.out.println(file);
		if (files != null && files.length > 0) {

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					// 递归
					listfile(files[i]);
				} else {

					System.out.println(files[i].getName().substring(0, files[i].getName().indexOf(".")));
					//filename [i]= files[i].getName();//将获取的数据表的名字存到全局变量filename[];
				}
			}
		}
	}
	// dbhelp函数用来查看当前有哪些数据库

	public static void dbhelp() {
		System.out.println("Please enter the query in the following format：show databases;");

		Scanner input4 = new Scanner(System.in);
		String sentence = input4.nextLine();
		String[] ss = sentence.split("\\s+");

		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			sentence = sentence + " " + input4.nextLine();
		}
		if (ss[0].equals("show")) {
			if (ss[1].equals("databases;")) {
				System.out.println("The current database has：");
				File file = new File("mydatabase");

				System.out.println(file);

				sqlhelp.listDirectory(file);
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");

			}
		} else {
			System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");
		}

	}

	/*
	 * tbhelpcount函数用来查询某个数据库下面左右的数据表
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

					System.out.println("The data tables in the database of " + chooseAnddo.str + " are：");

					File file5 = new File("mydatabase//" + chooseAnddo.str);
					sqlhelp.listfile(file5);


				} else {
					System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");

				}
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");
			}
		} else {
			if (ss[0].equals("show")) {
				if ((ss[1].equals("tables;"))) {
					//for(String a:ss)
					//	System.out.println(a);

					System.out.println("The data tables in the database of " + chooseAnddo.str + " are：");
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
					System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");

				}
			} else {
				System.out.println("Sql Statement input error, query failed! Please reselect your operating options：");
			}
		}

	}
}