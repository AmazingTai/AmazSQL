package src.amazsql;

import java.io.File;
import java.util.Scanner;

/*
 * ɾ�����ݿ�Ĳ���
 */
public class deletedb extends sql {
     public static void deldb(){
    	 System.out.println("Please drop the database as follows��");
 		System.out.println("drop database database-name;");
 		Scanner input3 = new Scanner(System.in);
 		String sentence = input3.nextLine();
 		
 		while(sentence.lastIndexOf(";")!=sentence.length()-1){
 			sentence = sentence+" "+input3.nextLine();
 		}

 		//String[] ss = sentence.split("\\s+");
 		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
 		String[] ss = sentence.split("\\s+");
 		//ss[2]��Ҫɾ�������ݿ������
 		File file = new File("mydatabase\\"+ss[2]);
 		if (file.exists()) {//�ж��ļ��Ƿ����  
 		     if (file.isFile()) {//�ж��Ƿ����ļ�  
 		      file.delete();//ɾ���ļ�   
 		     } else if (file.isDirectory()) {//�����������һ��Ŀ¼  
 		      File[] files = file.listFiles();//����Ŀ¼�����е��ļ� files[];  
 		      for (int i = 0;i < files.length;i ++) {//����Ŀ¼�����е��ļ�  
 		       files[i].delete();//��ÿ���ļ�������������е���  
 		      }  
 		      file.delete();//ɾ���ļ���  
 		     }
 		     System.out.println("drop database "+ss[2]+" successful !");
 		    } else {  
 		     System.out.println("the database "+ss[2]+" is not exists !");  
 		    }  
     }
}
