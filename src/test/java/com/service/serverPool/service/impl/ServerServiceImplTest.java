package com.service.serverPool.service.impl;

import com.service.serverPool.io.entity.ServerEntity;
import com.service.serverPool.repository.ServerRepository;
import com.service.serverPool.service.ServerServiceImpl;
import com.service.serverPool.shared.dto.ServerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Mock
    ArrayList<ServerDto> serverDtos_;

    @Mock
    Iterator<ServerDto> serverDtoIterator_ ;


    @SuppressWarnings("unchecked")
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
        ServerDto lServerDto1 = new ServerDto() ;
        lServerDto1.setId(200);
        lServerDto1.setState("Active");
        lServerDto1.setAllocatedMemory(50);
        lServerDto1.setUsageCount(0);

        ServerDto lServerDto2 = new ServerDto() ;
        lServerDto2.setId(100);
        lServerDto2.setState("Active");
        lServerDto2.setAllocatedMemory(30);
        lServerDto2.setUsageCount(0);

        ServerDto lServerDto3 = new ServerDto() ;
        lServerDto3.setId(300);
        lServerDto3.setState("Active");
        lServerDto3.setAllocatedMemory(20);
        lServerDto3.setUsageCount(0);

        doCallRealMethod().when(serverDtos_).forEach(any(Consumer.class));

        when(serverDtos_.iterator()).thenReturn(serverDtoIterator_);
        when(serverDtoIterator_ .hasNext()).thenReturn(true, true, true, false);
        when(serverDtoIterator_ .next()).thenReturn(lServerDto1)
                .thenReturn(lServerDto2).thenReturn(lServerDto3);


        //ServerDto lServerDto = serverService_.getAvailableNonLockedServer(10);
        assertEquals(3,serverDtos_.size());
        //assertNotNull(lServerDto);
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
    void getServers()
    {
    }

    @Test
    void call()
    {
    }
}