package IndexTree;

/**
 * Created by hms on 2016/12/12.
 */
import javafx.scene.chart.PieChart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.text.*;
import java.util.Calendar;

public class BplusTree implements B , Serializable{

    /** 根节点 */
    protected Node root;

    /** 阶数，M值 */
    protected int order;

    /** 叶子节点的链表头*/
    protected Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public Object get(Comparable key) {
        return root.get(key);
    }

    @Override
    public void remove(Comparable key) {
        root.remove(key, this);

    }

    @Override
    public void insertOrUpdate(Comparable key, Object obj) {
        root.insertOrUpdate(key, obj, this);
    }

    public BplusTree(int order){
        if (order < 3) {
            System.out.print("order must be greater than 2");
            System.exit(0);
        }
        this.order = order;
        root = new Node(true, true);
        head = root;
    }

    //测试
    public static void main(String[] args) throws IOException {
        BplusTree tree = new BplusTree(6);
        Random random = new Random();
        long current = System.currentTimeMillis();
        for (int j = 0; j < 100000; j++) {
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.insertOrUpdate(randomNumber, randomNumber);
            }

            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.remove(randomNumber);
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        int search = 80;
        System.out.print(tree.get(search));
        Node next = tree.getHead();
        int count = 0;
        while(true){
            if(next == null) break;
            ++count;
            List<Entry<Comparable, Object>> entries = next.getEntries();
            File file = new File(String.valueOf(count) + ".txt");
            next.setFile(file);
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(next);
            objectOutputStream.close();
            next = next.getNext();
        }
        File treeFile = new File("BplusTree.txt");
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(treeFile));
        objectOutputStream.writeObject(tree);
        objectOutputStream.close();

       /*FileInputStream ios = new FileInputStream("BplusTree.txt");

        ObjectInputStream iis = new ObjectInputStream(ios);

        String date = (String) iis.readObject();

        System.out.println(date);

*/
    }
}
