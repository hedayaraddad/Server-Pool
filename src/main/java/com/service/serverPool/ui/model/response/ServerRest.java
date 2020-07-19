////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerRest
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
package com.service.serverPool.ui.model.response;

public class ServerRest
{
    private int id ;
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

    public String getState()
    {
        return state;
    }

    public void setState(String aInState)
    {
        state = aInState;
    }

    public int getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(int aInUsageCount)
    {
        usageCount = aInUsageCount;
    }

    public int getAllocatedMemory()
    {
        return allocatedMemory;
    }

    public void setAllocatedMemory(int aInAllocatedMemory)
    {
        allocatedMemory = aInAllocatedMemory;
    }

    @Override
    public String toString()
    {
        return "ServerRest{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", usageCount=" + usageCount +
                ", allocatedMemory=" + allocatedMemory +
                '}';
    }
}
