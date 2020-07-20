////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: CustomAerospikeRepository
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

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.service.serverPool.io.entity.ServerEntity;

import java.util.ArrayList;

public interface CustomAerospikeRepository
{
    ServerEntity find(ServerEntity aInServerEntity);

    ArrayList<ServerEntity> findAll(String aInSetName);

    ServerEntity save(ServerEntity aInServerEntity) ;

    ServerEntity delete(ServerEntity aInServerEntity);
}
