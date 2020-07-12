package com.service.serverPool.model;

public interface  Resource {
    String resourceName = null;
    String unit = null ;

    public int useResource(int gigas); // change it when have multiple resource

}

