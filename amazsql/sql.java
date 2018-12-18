package src.amazsql;

import java.util.InputMismatchException;
import java.util.Scanner;

public class sql {

	/*
	 * public static String delete_space(char[] a){ int addr = 0;//�ַ�������±�
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
		// ����Ҫʵ���û��ĵ�¼ -- ��sql������sqlloginʵ��
		sqllogin.sqllogin();
		// help -- database,index,table;help���ܵ�ʵ��
        if(sqllogin.username.equals("root")){ 
		sql.sqldbroot();	 
        }else{
        	sql.sqldbvis();
        }

	}
	public static void sqldbroot(){
		/*
		 * ���û���ʾ����
		 */
		System.out.println("Please select in the following operations��");
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
		System.out.println("Please enter the options��");
		while (true) {
			Scanner input2 = new Scanner(System.in);
			try{
			int option = input2.nextInt();
			switch (option) {
			case 1:
				// ���ݿ�Ľ��� -- ʶ��sql��create database ������
				createdb.createdb();
				break;
			case 2 :
				// �鿴���ݿ�
				sqlhelp.dbhelp();
				break;
			case 3:
				deletedb.deldb();
				break;
			case 4:
				// ѡ�����ݿⲢ���в���
				chooseAnddo.chooseANDdoroot();
				break;
			case 5:
				//����ĳ���û������ݿ�Ĳ���Ȩ��
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
			}catch(InputMismatchException e){  //��׽�쳣
			      System.out.println("The input format is incorrect !");
			}
			
		}
	}
	public static void sqldbvis(){
		/*
		 * ���û���ʾ����
		 */
		System.out.println("Please select in the following operations��");
		System.out.println("----------------------------");
		
		System.out.println("1.show  databases");
		
		System.out.println("2.select the database you want to operate and operate it");
		System.out.println("3.re_login");
		System.out.println("----------------------------");
		System.out.println("Please enter the options��");
		while (true) {
			Scanner input2 = new Scanner(System.in);
			try{
			int option = input2.nextInt();
			switch (option) {
			
			case 1 :
				// �鿴���ݿ�
				sqlhelp.dbhelp();
				break;
			
			case 2:
				// ѡ�����ݿⲢ���в���
				chooseAnddo.chooseANDdovis();
			case 3:
				sqllogin.sqllogin();
			}
			}catch(InputMismatchException e){  //��׽�쳣
			      System.out.println("The input format is incorrect !");
			}
			
		}
	}

}
