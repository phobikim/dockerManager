package com.docker.dockermanager.swagger;

import com.docker.dockermanager.swagger.UserReq;
import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;

@Api(tags = {"API - (TEST) Controller"})
@RestController
@RequestMapping("/test/api")
public class SwaggerTestController {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello~~";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="x" , value = "x 값" , required = true , dataType = "int" , paramType = "path") ,
            @ApiImplicitParam(name="param" , value = "y 값" , required = true , dataType = "int" , paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(@PathVariable int x, @RequestParam int param){
        return x+param;
    }

    @ApiResponse(code=502 , message="사용자의 나이가 20살 이상일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @GetMapping("/user")
    public UserReq user(UserReq userReq){
        return new UserReq(userReq.getName(),userReq.getAge());
    }

    @PostMapping("/user")
    public UserReq userPost(@RequestBody UserReq req){
        return new UserReq(req.getName(), req.getAge());
    }
}
