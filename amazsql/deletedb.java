package src.amazsql;

import java.io.File;
import java.util.Scanner;

/*
 * 删除数据库的操作
 */
public class deletedb extends sql {
     public static void deldb(){
    	 System.out.println("Please drop the database as follows：");
 		System.out.println("drop database database-name;");
 		Scanner input3 = new Scanner(System.in);
 		String sentence = input3.nextLine();
 		
 		while(sentence.lastIndexOf(";")!=sentence.length()-1){
 			sentence = sentence+" "+input3.nextLine();
 		}

 		//String[] ss = sentence.split("\\s+");
 		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
 		String[] ss = sentence.split("\\s+");
 		//ss[2]是要删除的数据库的名字
 		File file = new File("mydatabase\\"+ss[2]);
 		if (file.exists()) {//判断文件是否存在  
 		     if (file.isFile()) {//判断是否是文件  
 		      file.delete();//删除文件   
 		     } else if (file.isDirectory()) {//否则如果它是一个目录  
 		      File[] files = file.listFiles();//声明目录下所有的文件 files[];  
 		      for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件  
 		       files[i].delete();//把每个文件用这个方法进行迭代  
 		      }  
 		      file.delete();//删除文件夹  
 		     }
 		     System.out.println("drop database "+ss[2]+" successful !");
 		    } else {  
 		     System.out.println("the database "+ss[2]+" is not exists !");  
 		    }  
     }
}
