package com.zsp.ssh2study;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

@SpringBootTest
class Ssh2StudyApplicationTests {
    final static String IP="192.168.56.101";
    final static String USERNAME="root";
    final static String PASSWORD="zspzsp123";
    final static String PATH="/home";

    @Test
    void testCMD() throws IOException {
        Connection connection = new Connection(IP);
        connection.connect();
        connection.authenticateWithPassword(USERNAME,PASSWORD);
        Session session = connection.openSession();
        session.execCommand("docker ps");
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            System.out.println(line);
        }
    }


    @Test
    void testScandCMD() throws IOException {
        Connection connection = new Connection(IP);
        connection.connect();
        connection.authenticateWithPassword(USERNAME,PASSWORD);
        Session session = connection.openSession();

        Scanner in=new Scanner(System.in);
        while(in.hasNextInt())
        {//nextLine()接收句子；next()接收单个字符；nextInt()返回Int型
            String cmd = in.nextLine();

            session.execCommand(cmd);
            InputStream is = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
            /***********/
        }
        if (session != null) {
            session.close();
        }
        session.close();

    }

}
