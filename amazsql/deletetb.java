package src.amazsql;

import java.io.File;
import java.util.Scanner;

//删除某个数据表
public class deletetb extends sql {
      public static void deletb(){
    	  System.out.println("Please drop the table as follows：");
   		System.out.println("drop table table-name;");
   		Scanner input3 = new Scanner(System.in);
   		String sentence = input3.nextLine();
   		
   		while(sentence.lastIndexOf(";")!=sentence.length()-1){
   			sentence = sentence+" "+input3.nextLine();
   		}
   		//String[] ss = sentence.split("\\s+");
   		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
   		String[] ss = sentence.split("\\s+");
   		//ss[2]是要删除的数据表的名字
    	//str存放的是use选择的数据库的名字
    	  File file = new File("mydatabase//"+chooseAnddo.str,ss[2]+".xls");

    	  if(file.exists()){
    		  file.delete();
    		  System.out.println("drop table "+ss[2]+" successful !");
    	  }else{
    		  System.out.println("the table is not exists !");
    	  }
    	  
      }
}
