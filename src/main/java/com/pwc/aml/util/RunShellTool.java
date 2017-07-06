package com.pwc.aml.util;

/**
 * Created by aliu323 on 7/4/2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class RunShellTool {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private Integer port;

    public RunShellTool(String ipAddr, String userName, String password, Integer port, String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        this.port = port;
        if (charset != null) {
            this.charset = charset;
        }
    }

    public boolean login() throws IOException {
        conn = new Connection(ipAddr);
        conn.connect(); // 连接
        return conn.authenticateWithPassword(userName, password); // 认证
    }

    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                Session session = conn.openSession(); // 打开一个会话
                session.execCommand(cmds);
                in = session.getStdout();
                result = this.processStdout(in, this.charset);
                session.close();
                conn.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }
    
    public String execSSH(String cmds) throws IOException, JSchException{
    	
    	JSch jsch = new JSch();
    	com.jcraft.jsch.Session session = jsch.getSession(userName, ipAddr, port);
        session.setConfig("StrictHostKeyChecking", "no");
        
        session.setPassword(password);
        session.connect();
        
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(cmds);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");
        
        channelExec.disconnect();
        session.disconnect();
        
        return out;
    }

    public String processStdout(InputStream in, String charset) {

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        RunShellTool tool = new RunShellTool("172.27.69.76", "hadoop",
                "Welcome1",22, "utf-8");

        String result = tool.exec("echo 'Java'");
        System.out.print(result);

    }

}

