package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import jxl.write.*;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.Cell;
import jxl.Sheet;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/*
 * 实现对表的插入
 * insert into tbname(shuxing1,shuxing2,shuxing3....)
 * values("zhi1","zhi2","zhi3"........);
 * tip:空格的使用
 */

public class inserttb extends sql {
     public static void iserttb() {
		 System.out.println("Please insert the table as follows：");
		 System.out.println("insert into tbname (Attributes_1_name,Attributes_2_name,Attributes_3_name....)"
				 + "values('value_1','value_2','value_3'...);");
 		Scanner input3 = new Scanner(System.in);
 		String sentence = input3.nextLine();

 		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
 			sentence = sentence + " " + input3.nextLine();
 		}
 		
 		sentence = sentence.substring(0, sentence.lastIndexOf(";"));
 	
 		String[] ss = sentence.split("\\)");
 		/*

		 Scanner input3 = new Scanner(System.in);
		 String sentence = input3.nextLine();
		 String[] ss = sentence.split("\\s+");


		 while (sentence.lastIndexOf(";") != sentence.length() - 1) {
			 sentence = sentence + " " + input3.nextLine();
		 }
*/
 		/*
 		 * ss[0]存的是insert into tbname(Attributes_1_name,Attributes_2_name,Attributes_3_name....
 		 * ss[1]存的是values('value_1','value_2','value_3'...
 		 */
		 String[] ss1 = ss[0].split(" ");//ss1[0]是insert   ss1[1]是into   ss1[2]是tbname(Attributes_1_name,Attributes_2_name,Attributes_3_name....
		 String tbname = ss1[2].substring(0, ss1[2].indexOf("("));//在ss1[2]中提取tbname
		 ss[0] = ss[0].substring(ss[0].indexOf("(") + 1);
		 String[] attributes = ss[0].split(",");//将各个需插入的属性存到attributes
		 ss[1] = ss[1].substring(ss[1].indexOf("(") + 1);
		 String[] values = ss[1].split(",");//values[0] == 'value_1'.....
		 //逐个单词判断

		/* for (String a : ss)
			 System.out.println(a);

		 for (String a : ss1)
			 System.out.println(a);
		 System.out.println(tbname);
		 for (String a: attributes)
			 System.out.println(a);
		 for (String a: values)
			 System.out.println(a);*/


	 //}}
 		if(ss1[0].equals("insert")){


 			if(ss1[1].equals("into")){
 				//for (String a :ss)
 				//	System.out.println(a);

				//chooseAnddo.str = chooseAnddo.str.substring(0,chooseAnddo.str.length() - 1);
 				 File file = new File("mydatabase//"+chooseAnddo.str,tbname+".xls");
 				 System.out.println(chooseAnddo.str);
				//for (String a : ss)
				//	System.out.println(a);
 				 if(file.exists()){
 					 //获得所需要操作的文件的工作薄  
 			         //WritableWorkbook workbook;
 					try {
 						
					    Workbook wb = Workbook.getWorkbook(file);
						 
 						WritableWorkbook wwb = Workbook.createWorkbook(file,wb);
 						WritableSheet ws = wwb.getSheet(0);  
 						 int columnum = ws.getColumns();// 得到列数
 						 int rownum = ws.getRows();// 得到行数  
 						 if(attributes.length==columnum){
 							 for (int j = 0; j <columnum ; j++) {
 								Cell cell  = ws.getCell(j, 0);  
 								WritableCell cell1 = null;
 								String content = cell.getContents();
 					            String shuxing = content.substring(0, content.indexOf(" "));
 					            if(attributes[j].equals(shuxing)){
 					            	cell1 = new Label(j, rownum, values[j].substring(1, values[j].length()-1));
 					            	
										ws.addCell(cell1);
									
 					            }
 					           
 					       } 
 							System.out.println("insert table "+tbname+"successfully !");
 						 }
 						
 						 else{
 							System.out.println("The number of requirements is not the same as the actual quantity !");
 						 }
 						 wwb.write();
							
							wwb.close(); 
 					}  catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
 					  
 				 }else{
 					 System.out.println("the database table is not exists !");
 				 }
 			}else{
 				 System.out.println("Sql statement input error, select the database failed! Please re-select your operating options：");
 			}
 		}else{
 			 System.out.println("Sql statement input error, select the database failed! Please re-select your operating options：");
 		}
 		
 		
     }
}
