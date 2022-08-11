package com.docker.dockermanager.controller;

import com.docker.dockermanager.entity.AccountManager;
import com.docker.dockermanager.entity.DockerManager;
import com.docker.dockermanager.service.AccountManagerService;
import com.docker.dockermanager.service.DockerManagerService;
import com.docker.dockermanager.type.STATE;
import com.docker.dockermanager.util.HealthCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = {"API - (DOCKER MANAGER) Controller"})
@Controller
@RequestMapping("/docker")
public class DockerManagerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DockerManagerService dockerManagerService;
    @Autowired
    private AccountManagerService accountManagerService;

    private HealthCheck healthCheck;

    @ApiOperation(value = "도커의 LIST 를 조회하는 메소드")
    @GetMapping("/home")
    public String list(Model model){
        List<DockerManager> dockerManagers = dockerManagerService.showList();
        model.addAttribute("dockerMangers" , dockerManagers);
        logger.trace("로깅 테스트 : debug");
//        return "../static/index";
        return "dockerManagerList";
    }
    @GetMapping("/server/{serverCode}")
    public String selectServer(@PathVariable String serverCode, Model model) {
//        if(serverCode.equals("192.168.0.182")){
//            System.out.println("serverIp = " + serverCode);
//        }
        String serverIp = null;
        switch (serverCode){
            case "1" :
                serverIp = STATE.STAG_SERVER.getCode();
                break;
            case "2" :
                serverIp = STATE.DEV_SERVER.getCode();
                break;
            case "3" :
                serverIp = STATE.DB_SERVER.getCode();
                break;
            default:
        }
        List<DockerManager> dockerManagers = dockerManagerService.showSelectServer(serverIp);
        model.addAttribute("dockerMangers" , dockerManagers);

        System.out.println("serverCode = " + serverCode);
        return "dockerManagerList";
    }

    @ApiOperation(value = "도커 ID 를 통해 도커 상세 정보를 보여주는 페이지로 이동")
    @ApiImplicitParam(name ="dockerId", value="dockerId 값", required = true , dataType = "String")
    @GetMapping("/containers/{dockerId}")
    public String selectOne(@PathVariable String dockerId, Model model){
        if(dockerId.equals("favicon.ico")){
            return "dockerManagerList";
        }
//        DockerManager docker = dockerManagerService.findById(dockerId);
        AccountManager account = accountManagerService.findById(dockerId);
        model.addAttribute("account" , account);
        return "dockerDetail";
    }

    @ApiOperation(value = "GET - 도커를 수정 후 수정된 도커 정보 가져오는 메소드")
    @GetMapping("/containers/{dockerId}/edit")
    public String editForm(@PathVariable String dockerId , Model model){
        DockerManager docker = dockerManagerService.findById(dockerId);
        model.addAttribute("docker",docker);
        return "editDockerManagerForm";
    }

    @ApiOperation(value = "POST - 도커를 수정하는 메소드")
    @ApiImplicitParam(name ="dockerId", value="dockerId 값", required = true , dataType = "String")
    @PostMapping("/containers/{dockerId}/edit")
    public String edit(@PathVariable String dockerId , @ModelAttribute("dockerManager") DockerManager dockerManager){
        dockerManagerService.updateById(dockerId,dockerManager);
        return "redirect:/docker/home/{dockerId}";
    }



    @ApiOperation(value = "도커의 IP,PORT 를 이용하여 기동상태 체크하는 메소드")
    @GetMapping("/containers/health-check")
    public String healthCheck(Model model) throws IOException {
        List<DockerManager> dockerManagers = dockerManagerService.showList();
        dockerManagerService.updateState(dockerManagers);
        model.addAttribute("dockerMangers" , dockerManagers);
        return "redirect:/docker/home";
    }

}
