////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerService
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
package com.service.serverPool.service;

import com.service.serverPool.shared.dto.ServerDto;

import java.util.concurrent.Callable;

public interface ServerService extends Callable<ServerDto>
{
    ServerDto createServer(ServerDto aInServerDto);

    ServerDto findAvailable(int aInGigaByte) ;

    boolean isLocked(ServerDto aInServerDto) ;

    boolean isMemoryAvailable(ServerDto aInServerDto , int aInRequestedGigaBytes);

    ServerDto useServer(ServerDto aInServerDto ,int aInRequestedGigaBytes);


}
