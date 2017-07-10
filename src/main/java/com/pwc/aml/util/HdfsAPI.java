package com.pwc.aml.util;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
/*
* 使用此api需要添加HADOOP_USER_NAME=hadoop环境变量,否则会报权限问题
* */
public class HdfsAPI {


    public static FileSystem getHDFileSystem() throws IOException
    {
        Configuration conf =new Configuration();
        FileSystem fs=FileSystem.get(conf);
        return fs;
    }
    public static FileSystem getLocalFileSystem() throws IOException
    {
        Configuration conf =new Configuration();
		FileSystem fs=FileSystem.getLocal(conf);
        return fs;
    }
    //读文件
    public static void read(String fileName) throws Exception
    {
        FileSystem fs=getHDFileSystem();
        Path readPath=new Path(fileName);
        FSDataInputStream inStream =fs.open(readPath);

        try {
            //这边我直接输出到控制台,根据业务重写
            IOUtils.copyBytes(inStream, System.out, 4096,false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeStream(inStream);
        }

    }
    //写文件
    //第一参数是本地路径,或者服务器路径,第二个参数是要存到的hdfs的路径
    public static void write(String inputLocalFile,String outputHDFSFile) throws Exception
    {
        FileSystem hdfs=getHDFileSystem();
        FileSystem lfs=getLocalFileSystem();
        Path readPath=new Path(inputLocalFile);
        Path writePath=new Path(outputHDFSFile);

        FSDataInputStream inStream =lfs.open(readPath);

        //create方法是为了可以创建不存在的目录，包括父目录
        //同时它还实现了Progessable接口用来返回IO进度
		FSDataOutputStream outStream=hdfs.create(writePath,new Progressable() {

			@Override
			public void progress() {
				System.out.println("transport finished");

			}
		});

        try {
            //最后一个boolean表示拷贝结束后是否关闭输入输出流
            IOUtils.copyBytes(inStream, outStream, 4096,false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeStream(inStream);
            IOUtils.closeStream(outStream);
        }

    }
    //删除目录或文件
    public static boolean deleteFile(FileSystem fs,String deletePathStr) throws IOException {
        Path deletePath=new Path(deletePathStr);
        return  fs.delete(deletePath, true);
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        FileSystem fs= getHDFileSystem();
        Path writePath=new Path("/user/hadoop/tmp/sampleData/TRANS");
        //创建目录
//		boolean b =fs.mkdirs(Path);

//        System.out.println(b);
        FileSystem lfs=getLocalFileSystem();
//        System.out.println(lfs);
//        Path readPath=new Path("C:\\Andrew");
//        boolean b=lfs.exists(readPath);
//        System.out.println(b);
        write("C:\\Andrew\\abc.py","/user/hadoop/tmp/sampleData/abc.py");


    }

}

