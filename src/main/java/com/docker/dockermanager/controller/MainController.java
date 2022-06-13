package com.docker.dockermanager.controller;

import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.service.DockerManagerService;
import com.docker.dockermanager.util.HealthCheck;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = {"API - (DOCKER MANAGER) Controller"})
@Controller
@RequestMapping("/dockerMain")
public class MainController {
    @Autowired
    private DockerManagerService dockerManagerService;

    private HealthCheck healthCheck;

    @ApiOperation(value = "도커의 LIST 를 조회하는 메소드")
    @GetMapping("")
    public String list(Model model){
        List<DockerManager> dockerManagers = dockerManagerService.showList();
        model.addAttribute("dockerMangers" , dockerManagers);
//        return "../static/index";
        return "dockerManagerList";
    }

    @ApiOperation(value = "도커 ID 를 통해 도커 상세 정보를 보여주는 페이지로 이동")
    @ApiImplicitParam(name ="dockerId", value="dockerId 값", required = true , dataType = "String")
    @GetMapping("/{dockerId}")
    public String selectOne(@PathVariable String dockerId, Model model){
        if(dockerId.equals("favicon.ico")){
            return "dockerManagerList";
        }
        DockerManager docker = dockerManagerService.findById(dockerId);
        model.addAttribute("docker" , docker);
        return "dockerDetail";
    }

    @ApiOperation(value = "GET - 도커를 수정 후 수정된 도커 정보 가져오는 메소드")
    @GetMapping("{dockerId}/edit")
    public String editForm(@PathVariable String dockerId , Model model){
        DockerManager docker = dockerManagerService.findById(dockerId);
        model.addAttribute("docker",docker);
        return "editDockerManagerForm";
    }

    @ApiOperation(value = "POST - 도커를 수정하는 메소드")
    @ApiImplicitParam(name ="dockerId", value="dockerId 값", required = true , dataType = "String")
    @PostMapping("{dockerId}/edit")
    public String edit(@PathVariable String dockerId , @ModelAttribute("dockerManager") DockerManager dockerManager){
        dockerManagerService.updateById(dockerId,dockerManager);
        return "redirect:/dockerMain/{dockerId}";
    }



    @ApiOperation(value = "도커의 IP,PORT 를 이용하여 기동상태 체크하는 메소드")
    @GetMapping("/healthCheck")
    public String healthCheck(Model model) throws IOException {
        List<DockerManager> dockerManagers = dockerManagerService.showList();
        dockerManagerService.updateState(dockerManagers);
        model.addAttribute("dockerMangers" , dockerManagers);
        return "redirect:/dockerMain";
    }

}
