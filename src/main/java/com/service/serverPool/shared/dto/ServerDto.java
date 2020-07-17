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
    private MemoryResourceDto memoryResource ;

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


    public String getState()
    {
        return state;
    }

    public void setState(String aInState)
    {
        state = aInState;
    }

    public MemoryResourceDto getMemoryResource()
    {
        return memoryResource;
    }

    public void setMemoryResource(
            MemoryResourceDto aInMemoryResource)
    {
        memoryResource = aInMemoryResource;
    }
}
