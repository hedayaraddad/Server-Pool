package com.service.serverPool.service.impl;

import com.service.serverPool.io.entity.ServerEntity;
import com.service.serverPool.repository.ServerRepository;
import com.service.serverPool.service.ServerServiceImpl;
import com.service.serverPool.shared.dto.ServerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerServiceImplTest
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
class ServerServiceImplTest
{
    @InjectMocks
    ServerServiceImpl serverService_ ;

    @Mock
    ServerRepository serverRepository_;


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAvailable()
    {
    }

    @Test
    void assignServersToList()
    {
        ArrayList<ServerEntity> lServerEntities = new ArrayList<>();

        ServerEntity lServerEntity =new ServerEntity() ;
        lServerEntity.setId(500);
        lServerEntity.setServerId(500);
        lServerEntity.setState("Active");
        lServerEntity.setAllocatedMemory(70);
        lServerEntity.setUsageCount(0);

        lServerEntities.add(lServerEntity) ;

        when(serverRepository_.findAll(anyString())).thenReturn(lServerEntities);

        ArrayList<ServerDto> lTestServerDtos = serverService_.assignServersToList();
        assertNotNull(lTestServerDtos);
    }

    @Test
    void createServer()
    {
        ServerEntity lTestServerEntity =new ServerEntity() ;
        lTestServerEntity.setId(500);
        lTestServerEntity.setServerId(500);
        lTestServerEntity.setState("Active");
        lTestServerEntity.setAllocatedMemory(70);
        lTestServerEntity.setUsageCount(0);

        when(serverRepository_.save(any())).thenReturn(lTestServerEntity);

        ServerDto lTestServerDto = new ServerDto() ;
        lTestServerDto.setId(500);
        lTestServerDto.setState("Active");
        lTestServerDto.setAllocatedMemory(70);
        lTestServerDto.setUsageCount(0);


        ServerDto lTestResultServerDto = serverService_.createServer(lTestServerDto);

        assertNotNull(lTestResultServerDto);
        assertEquals(lTestResultServerDto.getId(),lTestServerDto.getId());
        assertEquals(lTestResultServerDto.getAllocatedMemory(),lTestServerDto.getAllocatedMemory());

    }

    @Test
    void isLocked()
    {
    }

    @Test
    void isMemoryAvailable()
    {
        ServerDto lServerDto = new ServerDto() ;
        lServerDto.setId(200);
        lServerDto.setState("Active");
        lServerDto.setAllocatedMemory(50);
        lServerDto.setUsageCount(0);

        boolean isAvailable = serverService_.isMemoryAvailable(lServerDto,70);
        assertEquals(true,isAvailable);
    }

    @Test
    void getAvailableNonLockedServer()
    {
    }

    @Test
    void getNotAvailableNonLockedServer()
    {
    }

    @Test
    void createNewServer()
    {
    }

    @Test
    void getMemoryResourceDto()
    {
    }

    @Test
    void useServer()
    {
    }

    @Test
    void add()
    {
    }

    @Test
    void remove()
    {
    }

    @Test
    void getServers()
    {
    }

    @Test
    void call()
    {
    }
}