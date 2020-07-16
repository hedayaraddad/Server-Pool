////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerEntity
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
package com.service.serverPool.io.entity;

import java.io.Serializable;

public class ServerEntity implements Serializable
{
    private static final long serialVersionUID = 2L;
    private int id ;
    private int serverId ;
    private String state ;
    private int MemoryAllocation ;
    private int usageCounter ;

    public int getId()
    {
        return id;
    }

    public void setId(int aInId)
    {
        id = aInId;
    }

    public int getServerId()
    {
        return serverId;
    }

    public void setServerId(int aInServerId)
    {
        serverId = aInServerId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String aInState)
    {
        state = aInState;
    }

    public int getMemoryAllocation()
    {
        return MemoryAllocation;
    }

    public synchronized void setMemoryAllocation(int aInMemoryAllocation)
    {
        MemoryAllocation = aInMemoryAllocation;
    }

    public int getUsageCounter()
    {
        return usageCounter;
    }

    public synchronized void setUsageCounter(int aInUsageCounter)
    {
        usageCounter = aInUsageCounter;
    }
}
