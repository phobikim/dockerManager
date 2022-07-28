package com.docker.dockermanager.controller;

import com.docker.dockermanager.util.ExcuteCmd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/docker")
public class DockerRegisterController {

    @PostMapping("/regist")
    public String dockerRegister(@RequestParam("dockerName") String name){
        System.out.println("name = " + name);
        return "dockerRegist";
    }

    @GetMapping("/regist")
    public String getDockerRegister(){
        return "dockerRegist";
    }

    @GetMapping("/dockerPs")
    public String getDockerPs(Model model) throws IOException, InterruptedException {
        ExcuteCmd excuteCmd = new ExcuteCmd();
        String result = excuteCmd.javaCmd();
        model.addAttribute("result" , result);
        return "dockerPs";
    }
}
