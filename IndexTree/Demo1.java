package IndexTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Demo1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //假设将对象信息写入到obj.txt文件中，事先已经在硬盘中建立了一个obj.txt文件
        File f = new File("1.txt");
        //writeObjec(f);
        readObject(f);
        System.out.println("OK");
    }


    //把文件中的对象信息读取出来-------->对象的反序列化
    public static void readObject(File f) throws IOException, ClassNotFoundException{
        FileInputStream inputStream = new FileInputStream(f);//创建文件字节输出流对象
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

    }
}