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
    private MemoryResourceDetailsResponseModel
            memoryResourceDetailsResponseModel_;

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

    public MemoryResourceDetailsResponseModel getMemoryResourceDetailsResponseModel()
    {
        return memoryResourceDetailsResponseModel_;
    }

    public void setMemoryResourceDetailsResponseModel(
            MemoryResourceDetailsResponseModel aInMemoryResourceDetailsResponseModel)
    {
        memoryResourceDetailsResponseModel_ =
                aInMemoryResourceDetailsResponseModel;
    }

    @Override
    public String toString()
    {
        return "ServerRest{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", memoryResourceDetailsResponseModel_=" +
                memoryResourceDetailsResponseModel_ +
                '}';
    }
}
