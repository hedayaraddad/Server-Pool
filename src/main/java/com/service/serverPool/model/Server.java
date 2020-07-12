package com.service.serverPool.model;

public class Server {
    private  int id ;
    private static int IdCount = 211 ;
    private String state ;
    private MemoryResource memoryResource ;

    public Integer requestedGigas ;

    public Server() {
    }

    public Server(MemoryResource memoryResource) {
        this.id =IdCount++ ;
        this.state = "Creating" ;
        this.memoryResource = memoryResource;
    }


    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MemoryResource getMemoryResource() {
        return memoryResource;
    }

    public void setMemoryResource(MemoryResource memoryResource) {
        this.memoryResource = memoryResource;
    }

    public void reallocate(int requestedGigaB){
        this.setMemoryResource(new MemoryResource(requestedGigaB));
    }
    public boolean isAvailable(int requestedGigaB){
       return requestedGigaB <= this.getMemoryResource().getAllocatedMemory();
    }

    public int useServer(int gigas){
        synchronized (this)
        {
            return this.getMemoryResource().useResource(gigas);
        }

    }

    @Override
    public String toString() {
        return String.format("[Server] ID:%d, State:%s, MemoryResource:%s",id,getState(),getMemoryResource());
    }

}
