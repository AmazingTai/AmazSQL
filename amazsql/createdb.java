package src.amazsql;

import java.io.File;
import java.util.Scanner;

/*
 * 实现数据库的建立，利用java的
 */
public class createdb extends sql {
	public static void createdb() {
		System.out.println("Please create the database as follows：");
		System.out.println("create database database-name;");
		Scanner input3 = new Scanner(System.in);
		String sentence = input3.nextLine();
		
		while(sentence.lastIndexOf(";")!=sentence.length()-1){
			sentence = sentence+" "+input3.nextLine();
		}
		//String[] ss = sentence.split("\\s+");
		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
		String[] ss = sentence.split("\\s+");

		
		if (ss[0].equals("create")) {
			if (ss[1].equals("database")) {
				if (ss[2].length() > 1) {
					File database = new File("mydatabase", ss[2]);// 在mydatabase文件夹下面创建二级文件夹
					if (!database.exists()) {
						database.mkdirs();
						System.out.println("database" +" "+ss[2] + " "+"build successly");
					} else if (database.exists()) {
						System.out.println("The database already exists！");
					}
				}else{
					System.out.println("Database name format input error, database creation failed! Please re-select your operating options：");
				}
			} else {
				System.out.println("SQL Statement input error，database creation failed! Please re-select your operating options：");

			}
		} else {
			System.out.println("SQL Statement input error，database creation failed! Please re-select your operating options：");
		}
	}
}
