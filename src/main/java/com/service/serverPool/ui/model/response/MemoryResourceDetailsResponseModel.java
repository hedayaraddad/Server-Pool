////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: MemoryResourceDetailsResponseModel
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

public class MemoryResourceDetailsResponseModel
{
    private int usageCount ;
    private int allocatedMemory ;

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
        return "MemoryResourceDetailsResponseModel{" +
                "usageCount=" + usageCount +
                ", allocatedMemory=" + allocatedMemory +
                '}';
    }
}
