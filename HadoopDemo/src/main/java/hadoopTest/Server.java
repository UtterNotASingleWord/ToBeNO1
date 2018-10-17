package hadoopTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class Server {
    public static  void main(String args[]) throws IOException {
        Configuration configuration=new Configuration();
        RPC.Builder builder=new RPC.Builder(configuration);
        builder.setBindAddress("localhost").setPort(15000).setProtocol(RpcTest.class).setInstance(new RpcTestImpl());
        RPC.Server server =builder.build();
        server.start();
    }
}
