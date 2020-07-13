package com.service.serverPool.model;

public class MemoryResource implements Resource{
    public static final int maxSize = 100 ;
    private int usageCount = 0; //totalusage
    private int allocatedMemory ;// realspace

    public MemoryResource(int allocatedMemory) {
        //if statement the max is 100
        if(allocatedMemory > maxSize){
          //  throw
        }
        this.allocatedMemory = allocatedMemory;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public void setAllocatedMemory(int allocatedMemory) {
        this.allocatedMemory = allocatedMemory;
    }

    public static int getMaxSize() {
        return maxSize;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public int getAllocatedMemory() {
        return allocatedMemory;
    }

    @Override
    public int useResource(int gigaBytes) {
        setAllocatedMemory(allocatedMemory-gigaBytes);
        setUsageCount(getUsageCount()+gigaBytes);
        return allocatedMemory ;
    }

    @Override
    public String toString() {
        return "MemoryResource{" +
                "usageCount=" + usageCount +
                ", allocatedMemory=" + allocatedMemory +
                '}';
    }
}
