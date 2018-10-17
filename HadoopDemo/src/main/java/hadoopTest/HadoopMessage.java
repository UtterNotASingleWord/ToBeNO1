package hadoopTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;//stop-all.sh
import java.net.InetSocketAddress;

import static java.util.Arrays.binarySearch;

public class HadoopMessage {


    private static Configuration getConf() {
        Configuration conf = new Configuration();
        // 这句话很关键，这些信息就是hadoop配置文件中的信  
        //conf.set("mapred.job.tracker","192.168.102.136:9001");
        conf.set("fs.default.name", "hdfs://192.168.237.128:9000");
        System.setProperty("HADOOP_USER_NAME", "hu123");
        return conf;
    }

    public static void getDateNodeHost() throws IOException {
        Configuration conf = getConf();
        FileSystem fs = FileSystem.get(conf);
        DistributedFileSystem hdfs = (DistributedFileSystem) fs;
        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
        for (int i = 0; i < dataNodeStats.length; i++) {
            System.out.println("DataNode_" + i + "_Name:" + dataNodeStats[i].getHostName());
        }

    }

    public static void initHDFS() throws IOException {
        // 1获取文件系统
        Configuration configuration = getConf();
        FileSystem fs = FileSystem.get(configuration);

        //2.打印文件系统到控制台
        System.out.println(fs.toString());

        fs.close();
    }

    public static void makdirTest() throws Exception {
        FileSystem fs = null;
        boolean mkdirs = fs.mkdirs(new Path("/hu/hahaha"));
        System.out.println(mkdirs);
    }

    public static void deleteTest() throws Exception {
        FileSystem fs = null;
        boolean delete = fs.delete(new Path("/hu"), true);//true， 递归删除
        System.out.println(delete);
    }

    public static void listTest(FileSystem fs) throws Exception {
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            System.err.println(fileStatus.getPath() + "=================" + fileStatus.toString());
        }
        //会递归找到所有的文件
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus next = listFiles.next();
            String name = next.getPath().getName();
            Path path = next.getPath();
            System.out.println(name + "---" + path.toString());
        }
    }
    public static void TraceBack( int[ ][ ] s,int i,int j)
    {
        if(i==j) return;
        TraceBack(s,i,s[i][j]);
        TraceBack(s,s[i][j]+1,j);
        System.out.println("Multiply A"+i+","+s[i][j]+"and A"+(s[i][j]+1)+","+j);
    }
    public static void MatrixChain(int []p,int n,int[][] m,int [][] s)
    {
        for (int i = 1; i <=n; i++) {
            m[i][i] = 0;
        }
        for (int r = 2; r <= n; r++) {      //r表示链长，取值2~n
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;                //j依次取值i+1,i+2,……,n
                m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];
                //即m[i][j] = m[i][i]+m[i+1][j]+ p[i-1]*p[i]*p[j]
                s[i][j] = i;                    //i为初始断开位置
                for (int k = i + 1; k < j; k++) {//依次设断开位置为i+1,i+2,……
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static  void main(String args[]) throws IOException {
        /*getDateNodeHost();
        initHDFS();
        FileSystem fs = FileSystem.get(getConf());
        listTest(fs);*/
        RpcTest proxy = RPC.getProxy(RpcTest.class, 1l, new InetSocketAddress("localhost", 15000), new Configuration());
        System.out.println(proxy.communication());
    }



}