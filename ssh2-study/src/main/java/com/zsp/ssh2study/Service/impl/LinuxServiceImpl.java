package com.zsp.ssh2study.Service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.zsp.ssh2study.Service.LinuxService;
import com.zsp.ssh2study.common.ConnectionAuth;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class LinuxServiceImpl implements LinuxService {
    public List<String> useLinux(String cmd) throws IOException {
        List list = new ArrayList<>();
        Connection connection = new Connection(ConnectionAuth.IP);
        connection.connect();
        connection.authenticateWithPassword(ConnectionAuth.USERNAME,ConnectionAuth.PASSWORD);
        Session session = connection.openSession();
        session.execCommand(cmd);
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        for (String line =bf.readLine() ;  line!=null ; line = bf.readLine()) {
            list.add(line);
        }
        return list;
    }


}
