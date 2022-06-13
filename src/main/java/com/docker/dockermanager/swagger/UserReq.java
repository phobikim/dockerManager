package com.docker.dockermanager.swagger;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    @ApiModelProperty(value = "사용자의 이름" , example = "김민정" , required = true)
    private String name;
    @ApiModelProperty(value = "사용자의 나이" , example = "29" , required = true)
    private int age;
}
