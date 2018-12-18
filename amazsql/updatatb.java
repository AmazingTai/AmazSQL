package src.amazsql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/*
 * 实现更新表的操作
 * UPDATE 表名称  SET 列名称  = 新值  WHERE 列名称  = 某值
 */
public class updatatb extends sql {
           public static void update(){
        	System.out.println("Please update the data table as follows：");
       		System.out.println("update tbname set attribute1 = 'new_value' where attribute2 = 'old_value' ;");
       		Scanner input3 = new Scanner(System.in);
       		String sentence = input3.nextLine();

       		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
       			sentence = sentence + " " + input3.nextLine();
       		}

       		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

       		String[] ss = sentence.split("\\s+");
       		if(ss[0].equals("update")&&ss[2].equals("set")&&ss[6].equals("where")){
       			File file = new File("mydatabase//" + chooseAnddo.str, ss[1] + ".xls");
       			if(file.exists()){
       				jxl.Workbook workbook;
					try {
						workbook = Workbook.getWorkbook(file);
						Sheet sheet = workbook.getSheet(0);
						int columnum = sheet.getColumns();// 得到列数
						int rownum = sheet.getRows();// 得到行数
						int flag1 = 0;// 当找到与更新的属性相同的属性时，记录下这是第几列
						int flag2 = 0;// 当找到where条件的属性相同的属性时，记录下这是第几列
						int flag3 = 0;// 当找到where后的数值与某一行的数值相同时，记录下哪一行
						for (int j = 0; j < columnum; j++) {
							Cell cell = sheet.getCell(j, 0);
							String content = cell.getContents();
							String shuxing = content.substring(0, content.indexOf(" "));
							String yueshu = content.substring(content.indexOf(" ") + 1);
                             if(ss[3].equals(shuxing)){
                            	 flag1 = j;
                             }
							if (ss[7].equals(shuxing)) {
								flag2 = j;
							}

						}
						for (int i = 0; i < rownum; i++) {
							// 匹配where条件后的数值和表格中的某一列的数值相等
							Cell cl = sheet.getCell(flag2, i);

							if (cl.getContents().equals(ss[9].substring(1, ss[9].lastIndexOf("'")))) {
								flag3 = i;

							}
						}
						// 经过以上，须update的属性值是flag1列，flag3行的内容，将ss[5]写到此单元格中
						WritableWorkbook wwb = Workbook.createWorkbook(file, workbook);
						WritableSheet ws = wwb.getSheet(0); 
						 Label label = new Label(flag1,flag3,ss[5].substring(1, ss[5].lastIndexOf("'")));
					        
							ws.addCell(label);
							
					        wwb.write();
					        System.out.println("update the table successfully !");
					        wwb.close();
						// 关闭工作薄

						workbook.close();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
       			}else{
       				System.out.println("the data table is not exists !");
       			}
       		}else{
       		System.out.println("Sql statement input error, select the database failed! Please re-select your operating options：");
       		}
           }
}
