package com.service.serverPool.util.errorhandler;

public class exx extends Exception implements ErrMessage {
    @Override
    public String message() {
        return "there's an Aerospike Exception";
    }
}
