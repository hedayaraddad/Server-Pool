////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerServiceImpl
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

import com.service.serverPool.io.entity.ServerEntity;
import com.service.serverPool.repository.CustomAerospikeRepository;
import com.service.serverPool.shared.dto.ServerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerServiceImpl implements ServerService
{
    @Autowired
    CustomAerospikeRepository serverRepository ;

    public static ArrayList<ServerDto> servers = new ArrayList<ServerDto>(100) ;
    private int requestedGigabytes ;

    static{
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
        servers.add(lServerDto1) ;
        servers.add(lServerDto2) ;
        servers.add(lServerDto3);
    }
    public ServerServiceImpl()
    {
    }

    public ServerServiceImpl(int aInRequestedGigabytes)
    {
        requestedGigabytes = aInRequestedGigabytes;
    }

    @Override
    public ServerDto createServer(ServerDto server)
    {
        ServerEntity lServerEntity = new ServerEntity() ;
        BeanUtils.copyProperties(server,lServerEntity);

        ServerEntity lSavedServerEntity = serverRepository.save(lServerEntity) ;

        ServerDto aOutServerDto = new ServerDto() ;
        BeanUtils.copyProperties(lSavedServerEntity,aOutServerDto);

        add(aOutServerDto) ;

        return aOutServerDto ;
    }

    public synchronized ArrayList<ServerDto> assignServersToList()
    {
        ArrayList<ServerDto> aOuLServerDtos = new ArrayList<>() ;
        ArrayList<ServerEntity> lServerEntities = serverRepository.findAll("servers") ;
        for(ServerEntity lServerEntity : lServerEntities)
        {
            ServerDto lServerDto = new ServerDto() ;
            BeanUtils.copyProperties(lServerEntity,lServerDto) ;
            aOuLServerDtos.add(lServerDto) ;
        }
        return aOuLServerDtos ;
    }

    @Override
    public ServerDto findAvailable(int aInRequestedGigaBytes)
    {
        if(servers.isEmpty()){
            servers = assignServersToList() ;
        }
        ServerDto aOutAvailableServerDto = getAvailableNonLockedServer(aInRequestedGigaBytes) ;
        if(aOutAvailableServerDto == null)
        {
            aOutAvailableServerDto =getNotAvailableNonLockedServer(aInRequestedGigaBytes) ;
        }
        if(aOutAvailableServerDto == null)
        {
            aOutAvailableServerDto = createNewServer() ;
        }

        return aOutAvailableServerDto ;
    }

    @Override
    public boolean isLocked(ServerDto aInServerDto)
    {
        return Thread.holdsLock(aInServerDto);
    }

    @Override
    public boolean isMemoryAvailable(ServerDto aInServerDto , int aInRequestedGigaBytes)
    {
        return aInRequestedGigaBytes <= aInServerDto.getAllocatedMemory();
    }

    public synchronized ServerDto getAvailableNonLockedServer(int aInRequestedGigaBytes)
    {
        ServerDto aOutAvailableServerDto = null;
        for(ServerDto server : servers) {
            if (isMemoryAvailable(server,aInRequestedGigaBytes)) {
                if ((!isLocked(server))){
                    if(server.getState() == "Active")
                    {
                        aOutAvailableServerDto = server;
                        break;
                    }

                }
            }
        }

        return aOutAvailableServerDto;
    }

    public synchronized ServerDto getNotAvailableNonLockedServer(int aInRequestedGigaBytes)
    {
        ServerDto aOutAvailableServerDto = null;
        for (ServerDto server : servers) {
            if (!isLocked(server)) {
                if(server.getState() == "Active")
                {
                    System.out.println("no space so we allocate");
                    server.setAllocatedMemory(aInRequestedGigaBytes);
                    System.out.println(server.toString());
                    aOutAvailableServerDto = server;
                    break;
                }
            }
        }

        return aOutAvailableServerDto ;
    }
    public ServerDto createNewServer()
    {
        ServerDto aOutServerDto =new ServerDto() ;
        int lIdCounter = ServerDto.getIdCounter() ;
        aOutServerDto.setId(lIdCounter++);

        aOutServerDto.setAllocatedMemory(70);
        aOutServerDto.setState("Creating");
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException aInE)
        {
            aInE.printStackTrace();
        }
        aOutServerDto.setState("Active");

        return createServer(aOutServerDto);
    }

    @Override
    public ServerDto useServer(ServerDto aInServerDto ,int aInRequestedGiga)
    {
        ServerEntity lServerEntity =new ServerEntity() ;
        BeanUtils.copyProperties(aInServerDto,lServerEntity);

        int lNewMemoryValue = lServerEntity.getAllocatedMemory() - aInRequestedGiga ;
        lServerEntity.setAllocatedMemory(lNewMemoryValue);
        int lNewUsageCounter = lServerEntity.getUsageCount() + aInRequestedGiga ;
        lServerEntity.setUsageCount(lNewUsageCounter);

        ServerEntity lSavedServerEntity = serverRepository.save(lServerEntity) ;

        ServerDto aOutServerDto = new ServerDto() ;
        BeanUtils.copyProperties(lSavedServerEntity,aOutServerDto) ;

        remove(aInServerDto);
        add(aOutServerDto);

        return aOutServerDto ;
    }

    public synchronized void add(ServerDto aInServerDto){
        servers.add(aInServerDto);
    }

    public synchronized void remove(ServerDto aInServerDto){
        servers.remove(aInServerDto);
    }

    public synchronized ArrayList<ServerDto> getServers(){
        return servers ;
    }


    @Override
    public ServerDto call() throws Exception
    {
        ServerDto lAvailableServerDto = findAvailable(requestedGigabytes);
        ServerDto aOutUsedServerDto = useServer(lAvailableServerDto,requestedGigabytes) ;
        return aOutUsedServerDto;
    }
}
