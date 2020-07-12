package com.service.serverPool.DAO;

import com.aerospike.client.*;
import com.service.serverPool.util.config.DBParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OperationDaoImpl implements OperationDao{
    @Autowired
    AerospikeClient client = DBParameters.client ;

    @Override
    public Record readRecord(Key key) {
        return client.get(DBParameters.policy, key);
    }

    @Override
    public ArrayList<Record> readRecords(ArrayList<Key> keys) {
        ArrayList<Record> records=new ArrayList<Record>();
        for(int j=0;j<keys.size();j++) {
            records.add(client.get(DBParameters.policy, keys.get(j)));
        }
        return records;
    }

    @Override
    public boolean writeRecord(Key key, Bin bin) {
        boolean success =true ;
        try {

            client.put(DBParameters.writePolicy, key, bin);
        }
        catch(AerospikeException ex) {
            success=false;
        }
        return success;
    }

    @Override
    public boolean writeRecord(Key key, Bin... bins) {
        boolean success =true ;
        try {

            client.put(DBParameters.writePolicy, key, bins);
        }
        catch(AerospikeException ex) {
            success=false;
        }
        return success;
    }

    @Override
    public boolean deleteRecord(Key key) {
        return client.delete(DBParameters.writePolicy, key);
    }


}
