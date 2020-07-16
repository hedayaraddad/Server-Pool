////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: MemoryResourceDto
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

public class MemoryResourceDto
{
    public static final int maxSize = 100 ;
    private int usageCount = 0; //totalusage
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
        if(aInAllocatedMemory <= maxSize)
        {
            allocatedMemory = aInAllocatedMemory;
        }
        // else throw the exception

    }
}
