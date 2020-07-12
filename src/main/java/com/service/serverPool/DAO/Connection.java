package com.service.serverPool.DAO;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAerospikeRepositories
@EnableTransactionManagement
public class Connection {
    private static String host = "172.28.128.3";

    private static int port = 3000 ;

    private static ClientPolicy clientPolicy = new ClientPolicy();

    private final static AerospikeClient client = new AerospikeClient(clientPolicy,host,port);

    public Connection() {
    }

    @Bean
    public static AerospikeClient aerospikeClient() {
        clientPolicy.failIfNotConnected = true ;
        return client;
    }
}
