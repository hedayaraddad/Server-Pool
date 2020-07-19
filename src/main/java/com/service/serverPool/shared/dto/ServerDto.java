////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerDto
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
package com.service.serverPool.shared.dto;

import java.io.Serializable;

public class ServerDto implements Serializable
{
    private static final long serialVersionUID =
            129552318L;
    private int id ;
    private static int IdCounter = 211 ;
    private String state ;
    private int allocatedMemory ;
    private int usageCount ;

    public int getId()
    {
        return id;
    }

    public void setId(int aInId)
    {
        id = aInId;
    }

    public static int getIdCounter()
    {
        return IdCounter;
    }

    public static void setIdCounter(int aInIdCounter)
    {
        IdCounter = aInIdCounter;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String aInState)
    {
        state = aInState;
    }

    public int getAllocatedMemory()
    {
        return allocatedMemory;
    }

    public void setAllocatedMemory(int aInAllocatedMemory)
    {
        allocatedMemory = aInAllocatedMemory;
    }

    public int getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(int aInUsageCount)
    {
        usageCount = aInUsageCount;
    }

    @Override
    public String toString()
    {
        return "ServerDto{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", allocatedMemory=" + allocatedMemory +
                ", usageCount=" + usageCount +
                '}';
    }
}
