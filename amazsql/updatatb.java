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
 * ʵ�ָ��±�Ĳ���
 * UPDATE ������  SET ������  = ��ֵ  WHERE ������  = ĳֵ
 */
public class updatatb extends sql {
           public static void update(){
        	System.out.println("Please update the data table as follows��");
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
                             if(ss[3].equals(shuxing)){
                            	 flag1 = j;
                             }
							if (ss[7].equals(shuxing)) {
								flag2 = j;
							}

						}
						for (int i = 0; i < rownum; i++) {
							// ƥ��where���������ֵ�ͱ���е�ĳһ�е���ֵ���
							Cell cl = sheet.getCell(flag2, i);

							if (cl.getContents().equals(ss[9].substring(1, ss[9].lastIndexOf("'")))) {
								flag3 = i;

							}
						}
						// �������ϣ���update������ֵ��flag1�У�flag3�е����ݣ���ss[5]д���˵�Ԫ����
						WritableWorkbook wwb = Workbook.createWorkbook(file, workbook);
						WritableSheet ws = wwb.getSheet(0); 
						 Label label = new Label(flag1,flag3,ss[5].substring(1, ss[5].lastIndexOf("'")));
					        
							ws.addCell(label);
							
					        wwb.write();
					        System.out.println("update the table successfully !");
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
