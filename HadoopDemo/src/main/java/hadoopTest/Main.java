package hadoopTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobTracker;
import org.apache.hadoop.mapreduce.Job;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static  void main(String args[]){
        Configuration conf = new Configuration();

        // 不设置该代码会出现错误：java.io.IOException: No FileSystem for scheme: hdfs
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        System.setProperty("HADOOP_USER_NAME", "hu123");
        String filePath = "hdfs://192.168.237.128:9000/data/input/my_wordcount.txt";
        Path path = new Path(filePath);
        try {
            FileSystem fs = FileSystem.get(new URI(filePath), conf);
            System.out.println( "READING ============================" );
            FSDataInputStream is = fs.open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str=br.readLine()) != null){
                //String content = br.readLine();
                System.out.println(str);
            }
            br.close();
            /*System.out.println("WRITING ============================");
            byte[] buff = "Hello Hadoop!\n".getBytes();
            FSDataOutputStream os = fs.create(path);
            os.write(buff, 0, buff.length);
            os.close();
            fs.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
