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
 * ɾ�����е�ĳЩ����
 * delete from tb_name where attribute = 'value';
 */
public class deletefromTb extends sql {
          public static void delfromtb(){
        	    System.out.println("Please delete some contents from table as follows��");
         		System.out.println("delete from tb_name where attribute = 'value';");
         		Scanner input3 = new Scanner(System.in);
         		String sentence = input3.nextLine();

         		while (sentence.lastIndexOf(";") != sentence.length() - 1) {
         			sentence = sentence + " " + input3.nextLine();
         		}

         		sentence = sentence.substring(0, sentence.lastIndexOf(";"));

         		String[] ss = sentence.split("\\s+");
         		/*
         		 * ss[2]�Ǳ���      ss[4]������    ss[6]�ǡ�value��
         		 */
         		if(ss[0].equals("delete")&&ss[1].equals("from")&&ss[3].equals("where")){
         			File file = new File("mydatabase//" + chooseAnddo.str, ss[2] + ".xls");
         			if(file.exists()){
         				jxl.Workbook workbook;
    					try {
    						workbook = Workbook.getWorkbook(file);
    						Sheet sheet = workbook.getSheet(0);
    						int columnum = sheet.getColumns();// �õ�����
    						int rownum = sheet.getRows();// �õ�����
    						int flag1 = 0;// ���ҵ�����µ�������ͬ������ʱ����¼�����ǵڼ���
    						int flag2 = 0;// ���ҵ�where������������ͬ������ʱ����¼�����ǵڼ���
    						int flag3 = 0;// ���ҵ�where�����ֵ��ĳһ�е���ֵ��ͬʱ����¼����һ��
    						for (int j = 0; j < columnum; j++) {
    							Cell cell = sheet.getCell(j, 0);
    							String content = cell.getContents();
    							String shuxing = content.substring(0, content.indexOf(" "));
    							String yueshu = content.substring(content.indexOf(" ") + 1);
                                 
    							if (ss[4].equals(shuxing)) {
    								flag2 = j;
    							}

    						}
    						for (int i = 0; i < rownum; i++) {
    							// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
    							Cell cl = sheet.getCell(flag2, i);

    							if (cl.getContents().equals(ss[6].substring(1, ss[6].lastIndexOf("'")))) {
    								flag3 = i;

    							}
    						}
    						// �������ϣ���delete��Ԫ����flag3�е�����
    						WritableWorkbook wwb = Workbook.createWorkbook(file, workbook);
    						WritableSheet ws = wwb.getSheet(0); 
    						 //Label label = new Label(flag1,flag3,ss[5].substring(1, ss[5].lastIndexOf("'")));
    					        
    							ws.removeRow(flag3);
    							
    					        wwb.write();
    					        System.out.println("delete some contents from the table successfully !");
    					        wwb.close();
    						// �رչ�����

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
         			System.out.println("Sql statement input error, select the database failed! Please re-select your operating options��");
         		}
          }
}
