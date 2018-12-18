package src.amazsql;

import java.util.InputMismatchException;
import java.util.Scanner;

public class sql {

	/*
	 * public static String delete_space(char[] a){ int addr = 0;//字符数组的下标
	 * while([addr]==" "){ addr++; } return [addr]; }
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		System.out.println("-----------------------------");
		System.out.println("|                            |");
		System.out.println("-----------------------------");
		System.out.println("|         Amazing Tai        |");
		System.out.println("-----------------------------");
		System.out.println("|                            |");
		System.out.println("-----------------------------");
		// 首先要实现用户的登录 -- 用sql的子类sqllogin实现
		sqllogin.sqllogin();
		// help -- database,index,table;help功能的实现
        if(sqllogin.username.equals("root")){ 
		sql.sqldbroot();	 
        }else{
        	sql.sqldbvis();
        }

	}
	public static void sqldbroot(){
		/*
		 * 给用户提示操作
		 */
		System.out.println("Please select in the following operations：");
		System.out.println("----------------------------");
		System.out.println("1.build database");
		System.out.println("2.show  databases");
		System.out.println("3.delete  database");
		System.out.println("4.select the database you want to operate and operate it");
		System.out.println("5.Grant permissions of database to a user");
		System.out.println("6.Grant permissions of table to a user");
		System.out.println("7.Revoke permissions of database from a user");
		System.out.println("8.Revoke permissions of table from a user");
		System.out.println("9.show permissions of a user");
		System.out.println("10.re_login");
		System.out.println("----------------------------");
		System.out.println("Please enter the options：");
		while (true) {
			Scanner input2 = new Scanner(System.in);
			try{
			int option = input2.nextInt();
			switch (option) {
			case 1:
				// 数据库的建立 -- 识别sql的create database 库名；
				createdb.createdb();
				break;
			case 2 :
				// 查看数据库
				sqlhelp.dbhelp();
				break;
			case 3:
				deletedb.deldb();
				break;
			case 4:
				// 选择数据库并进行操作
				chooseAnddo.chooseANDdoroot();
				break;
			case 5:
				//赋予某个用户对数据库的操作权限
				grant.grantdb();
				break;
			case 6:
				grant.granttb();
				break;
			case 7:
				revoke.revokedb();
				break;
			case 8:
				revoke.revoketb();
				break;
			case 9:
				showper.show();
				break;
			case 10:
				sqllogin.sqllogin();
			}
			}catch(InputMismatchException e){  //扑捉异常
			      System.out.println("The input format is incorrect !");
			}
			
		}
	}
	public static void sqldbvis(){
		/*
		 * 给用户提示操作
		 */
		System.out.println("Please select in the following operations：");
		System.out.println("----------------------------");
		
		System.out.println("1.show  databases");
		
		System.out.println("2.select the database you want to operate and operate it");
		System.out.println("3.re_login");
		System.out.println("----------------------------");
		System.out.println("Please enter the options：");
		while (true) {
			Scanner input2 = new Scanner(System.in);
			try{
			int option = input2.nextInt();
			switch (option) {
			
			case 1 :
				// 查看数据库
				sqlhelp.dbhelp();
				break;
			
			case 2:
				// 选择数据库并进行操作
				chooseAnddo.chooseANDdovis();
			case 3:
				sqllogin.sqllogin();
			}
			}catch(InputMismatchException e){  //扑捉异常
			      System.out.println("The input format is incorrect !");
			}
			
		}
	}

}
