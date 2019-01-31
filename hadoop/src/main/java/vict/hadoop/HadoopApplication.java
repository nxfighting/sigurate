package vict.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class HadoopApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HadoopApplication.class, args);
        String uri = "http://192.168.85.130:50075/logs/hadoop-root-datanode-localhost.localdomain.log";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), configuration);
        FSDataInputStream in = null;
        in = fileSystem.open(new Path(uri));
//		FileStatus fileStatus=fileSystem.getFileStatus(new Path(uri));
//		byte[] buffer=new byte[1024];
//		in.read(4096, buffer, 0, 1024);
        IOUtils.copyBytes(in, System.out, 4096, false);
        IOUtils.closeStream(in);
    }

}

