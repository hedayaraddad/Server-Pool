package com.service.serverPool.service;

import com.service.serverPool.model.MemoryResource;
import com.service.serverPool.model.Server;
import com.service.serverPool.util.config.PoolExecuter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider implements  Runnable{

    public static final ArrayList<Server> servers = new ArrayList<Server>(100) ;

    private final Integer requestedGigas;

    static {
        servers.add(new Server(new MemoryResource(30)));
        servers.add(new Server(new MemoryResource(40)));
        servers.add(new Server(new MemoryResource(50)));

    }

    public ServiceProvider(Integer requestedGigas) {
        this.requestedGigas = requestedGigas;
    }

    public Server createNewServer(){
        Server server =new Server(new MemoryResource(50));
        servers.add(server);
        return server ;
    }

    public Server findAvailable(int requestedGigaB){
        Server availableServer ;
        for(Server server : servers){
            if ((!isLocked(server)) ){
                if(server.isAvailable(requestedGigaB)) {
                    availableServer = server ;
                }
                else{
                    server.reallocate(requestedGigaB);
                }
            }
        }
        availableServer = createNewServer()  ;
        return availableServer ;
    }

    public boolean isLocked(Server server){
        return Thread.holdsLock(server);
    }

    @Override
    public void run() {
        Server availableServer = findAvailable(requestedGigas);
        availableServer.useServer(requestedGigas);
        //update DB



    }
}
