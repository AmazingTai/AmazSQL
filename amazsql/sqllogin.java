package src.amazsql;

import java.util.Scanner;

/*   �û��ĵ�¼����
 * 1.�򿪴˳���֮������Ҫ��ʾ�û������û���
 * 2.���û��������жϣ������Ƿ���ڣ�
 * �������ڣ�����ʾ������Ϣ�� ���û�������;
 * �����ڣ�����ʾ�û�������������¼ ---���������ʾ��Ϣ����ȷ����ʾ�ѽ���sql;
 * */

public class sqllogin extends sql {
  
	public static String username = null;
	public static void sqllogin(){
		System.out.println("Please enter user name��");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		username = name;
		
		if(name .equals("root")){
			System.out.println("The user name is correct, please enter the password��");
			Scanner input1 = new Scanner(System.in);
			String password = input1.next();
			if(password.equals("root")){
				System.out.println("The password is entered correctly��welcome to Amazsql !");
				sql.sqldbroot();
			}
			else{
				System.out.println("Password input error, please re-login��");
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
