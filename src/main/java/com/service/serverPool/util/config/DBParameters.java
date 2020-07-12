package com.service.serverPool.util.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.service.serverPool.DAO.Connection;

public class DBParameters {
    public  static AerospikeClient client = Connection.aerospikeClient();
    public static WritePolicy writePolicy =new WritePolicy();
    public static Policy policy =new WritePolicy() ;
    public static ScanPolicy scanPolicy =new ScanPolicy() ;
    public static String spaceName="test";
    public static  String set ="servers";
}
