package com.zsp.ssh2study.controller;

import com.alibaba.fastjson.JSON;
import com.zsp.ssh2study.Service.LinuxService;
import com.zsp.ssh2study.Service.impl.LinuxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ssh2")
public class LinuxController {
    @Autowired
    LinuxServiceImpl linuxServiceImpl;
    @GetMapping("/useLinx/{cmd}")
    public   List<String> userLinux(@PathVariable String cmd){
        System.out.println(cmd);
        try {

            return linuxServiceImpl.useLinux(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/useCMDS/{cmd}")
    public   List<String> useCMDS(@PathVariable String cmd){
        System.out.println(cmd);
        try {

            return linuxServiceImpl.useLinux(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
