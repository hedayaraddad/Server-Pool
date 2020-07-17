package com.service.serverPool.service.impl;

import com.service.serverPool.io.entity.ServerEntity;
import com.service.serverPool.repository.ServerRepository;
import com.service.serverPool.service.ServerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
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
        lServerEntity.setMemoryAllocation(70);
        lServerEntity.setUsageCounter(0);

        lServerEntities.add(lServerEntity) ;

        when(serverRepository_.findAll(anyString())).thenReturn(lServerEntities);
    }
}