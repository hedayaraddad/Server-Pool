package com.service.serverPool.DAO;

import com.aerospike.client.*;
import com.aerospike.client.policy.Priority;
import com.aerospike.client.policy.ScanPolicy;
import com.service.serverPool.util.config.DBParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ScanRecords implements ScanCallback {
    private int recordCount;
    private ArrayList<Record> records =new ArrayList<>();
    @Autowired
    AerospikeClient client = DBParameters.client ;

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void scan() throws AerospikeException {
        try {
            ScanPolicy policy = new ScanPolicy();
            policy.concurrentNodes = true;
            policy.priority = Priority.LOW;
            policy.includeBinData = false;

            client.scanAll(policy, DBParameters.spaceName, DBParameters.set, this);
            System.out.println("Records " + recordCount);
        }
        finally {
            client.close();
        }
    }
    @Override
    public void scanCallback(Key key, Record record) throws AerospikeException {
        recordCount++;
        records.add(record);
        if ((recordCount % 10000) == 0) {
            System.out.println("Records " + recordCount);
        }
    }


}
