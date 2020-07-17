////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerRepository
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
package com.service.serverPool.repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.service.serverPool.io.entity.ServerEntity;
import com.service.serverPool.util.config.DBParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ServerRepository implements CustomAerospikeRepository
{
    @Autowired
    AerospikeClient client = DBParameters.client ;

    public Key convertIndexToKey(Integer id){
        return new Key(DBParameters.spaceName,DBParameters.serversSet,id);

    }

    public ServerEntity convertRecordToServerPar(Record aInRecord)
    {
        ServerEntity aOutServerEntity = new ServerEntity() ;

        aOutServerEntity.setId(Integer.parseInt(aInRecord.getValue("PK").toString()));
        aOutServerEntity.setServerId(Integer.parseInt(aInRecord.getValue("PK").toString()));
        aOutServerEntity.setState(aInRecord.getValue("state").toString());
        aOutServerEntity.setMemoryAllocation(Integer.parseInt(aInRecord.getValue("AM").toString()));
        aOutServerEntity.setUsageCounter(Integer.parseInt(aInRecord.getValue("UC").toString()));

        return aOutServerEntity ;
    }

    @Override
    public synchronized ServerEntity find(ServerEntity aInServerEntity)
    {
        Key key =convertIndexToKey(aInServerEntity.getId());
        return convertRecordToServerPar(client.get(DBParameters.policy, key));
    }

    @Override
    public synchronized ArrayList<ServerEntity> findAll(String aInSetName)
    {
        ArrayList<ServerEntity> aOutServerEntities= new ArrayList<>();

        ScanRecords scanRecords = new ScanRecords();
        scanRecords.scan(aInSetName);
        ArrayList<Record> lRecords = scanRecords.getRecords();

        for(Record lRecord : lRecords)
        {
            aOutServerEntities.add(convertRecordToServerPar(lRecord)) ;
        }
        return aOutServerEntities ;
    }

    @Override
    public synchronized ServerEntity save(ServerEntity aInServerEntity)
    {
        ServerEntity aOutLServerEntity = aInServerEntity;
        Key key = new Key(DBParameters.spaceName, DBParameters.serversSet,
                              aInServerEntity.getId());

        Bin idBin = new Bin("id", aInServerEntity.getServerId());
        Bin stateBin = new Bin("state", aInServerEntity.getState());
        Bin AMBin = new Bin("AM", aInServerEntity.getMemoryAllocation());
        Bin UCBin = new Bin("UC", aInServerEntity.getUsageCounter());

        client.put(DBParameters.writePolicy, key,idBin, stateBin, AMBin, UCBin);

        return aInServerEntity;
    }

    @Override
    public synchronized ServerEntity delete(ServerEntity aInServerEntity)
    {
        ServerEntity aOutLServerEntity = aInServerEntity;
        Key key =convertIndexToKey(aInServerEntity.getId());
        client.delete(DBParameters.writePolicy, key);
        return  aOutLServerEntity ;
    }
}
