package com.service.serverPool.service;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.service.serverPool.DAO.OperationDao;
import com.service.serverPool.DAO.OperationDaoImpl;
import com.service.serverPool.DAO.ScanRecords;
import com.service.serverPool.model.MemoryResource;
import com.service.serverPool.model.Server;
import com.service.serverPool.util.config.DBParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DaoService {

    @Autowired
    OperationDaoImpl operationDao ;

    public Key convertIndexToKey(Integer id){
       return new Key(DBParameters.spaceName,DBParameters.set,id);

    }

    public Record retrieveOne(Integer id){
        Record record = operationDao.readRecord(convertIndexToKey(id));
        return  record ;
    }

    public Server getContent(Record record) {
        Server server = new Server();
        if(record != null){
            int id =Integer.parseInt(record.getValue("id").toString());
            String state =record.getValue("state").toString();
            int AM = Integer.parseInt(record.getValue("AM").toString());
            server =new Server(new MemoryResource(AM));
        }
        return server;
    }

    public Server save(Server server){
        Key key = new Key(DBParameters.spaceName,DBParameters.set,server.getId());
        Bin idBin = new Bin("id",server.getId());
        Bin stateBin = new Bin("state",server.getState());
        Bin AMBin = new Bin("AM",server.getMemoryResource().getAllocatedMemory());
        Bin UCBin = new Bin("UC",server.getMemoryResource().getUsageCount());
        operationDao.writeRecord(key,idBin,stateBin,AMBin,UCBin);
        return server ;
    }

    public ArrayList<Record> retrieveAll(){
        ScanRecords scanRecords = new ScanRecords();
        scanRecords.scan();
       return scanRecords.getRecords();

    }

    public ArrayList<Server> retrieveAll(ArrayList<Record> records){
        ArrayList<Server> servers =new ArrayList<>();
        for(Record record : records) {
            servers.add(getContent(record));
        }
        return servers ;
    }


}
