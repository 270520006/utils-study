package com.zsp.ssh2study.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface LinuxService {
    List<String> useLinux(String cmd) throws IOException;

}
