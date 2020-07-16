////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ServerDetailsRequestModel
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
package com.service.serverPool.ui.model.request;


public class ServerDetailsRequestModel
{
    private int allocatedMemory ;

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
        return "ServerDetailsRequestModel{" +
                "allocatedMemory=" + allocatedMemory +
                '}';
    }
}
