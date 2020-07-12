package com.service.serverPool.recource;

import com.service.serverPool.model.MemoryResource;
import com.service.serverPool.model.Resource;
import com.service.serverPool.service.ServiceProvider;
import com.service.serverPool.util.config.PoolExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/serverpool")
public class PoolController {


    @GetMapping("/allocate/{Gbyte}")
    public void allocate(@PathVariable Integer gigabytes){
        PoolExecuter.pool.execute(new ServiceProvider(gigabytes));
    }


}
