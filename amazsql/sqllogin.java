package src.amazsql;

import java.util.Scanner;

/*   用户的登录功能
 * 1.打开此程序之后，首先要提示用户输入用户名
 * 2.对用户名进行判断，看其是否存在，
 * 若不存在，则提示错误信息： 该用户不存在;
 * 若存在，则提示用户输入密码来登录 ---密码错误提示信息，正确则提示已进入sql;
 * */

public class sqllogin extends sql {
  
	public static String username = null;
	public static void sqllogin(){
		System.out.println("Please enter user name：");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		username = name;
		
		if(name .equals("root")){
			System.out.println("The user name is correct, please enter the password：");
			Scanner input1 = new Scanner(System.in);
			String password = input1.next();
			if(password.equals("root")){
				System.out.println("The password is entered correctly，welcome to Amazsql !");
				sql.sqldbroot();
			}
			else{
				System.out.println("Password input error, please re-login：");
				sqllogin();
			}	
		}else if(name.equals("guest")){
			System.out.println("welcome to Amazsql !");
			sql.sqldbvis();
		}
			
		else{
			System.out.println("The user does not exist, please re-enter the user name:");
			sqllogin();
		}
	}
	
	

}
