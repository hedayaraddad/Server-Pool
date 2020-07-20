////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerController
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Hedaya
//
// Nokia - Confidential
// Do not use, distribute, or copy without consent of Nokia.
// Copyright (c) 2020 Nokia. All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.service.serverPool.ui.controller;

import com.service.serverPool.service.ServerService;
import com.service.serverPool.shared.dto.ServerDto;
import com.service.serverPool.ui.model.request.ServerDetailsRequestModel;
import com.service.serverPool.ui.model.response.ServerRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("servers")
public class ServerController
{
    @Autowired
    ServerService serverService ;

    int MAX_T = 10 ;
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    @PostMapping
    public ServerRest requestServerAllocation(@RequestBody ServerDetailsRequestModel aInServerDetailsRequestModel)
            throws ExecutionException, InterruptedException
    {
        ServerRest aOutServerRest =new ServerRest() ;

        ServerDto lServerDto = new ServerDto() ;
        BeanUtils.copyProperties(aInServerDetailsRequestModel,lServerDto) ;

        Future<ServerDto> lFutureServerDto = pool.submit(serverService);

        BeanUtils.copyProperties(lFutureServerDto.get(),aOutServerRest);

        return aOutServerRest ;
    }
}
