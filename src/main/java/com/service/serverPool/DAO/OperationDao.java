package com.service.serverPool.DAO;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OperationDao {
    public Record readRecord(Key key);

    public ArrayList<Record> readRecords(ArrayList<Key> keys);

    public boolean writeRecord(Key key, Bin bin) ;

    public  boolean writeRecord(Key key ,Bin...bins);

    public boolean deleteRecord(Key key);

}
