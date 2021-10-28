package com.tmobile.infrastructure.redis;

import org.redisson.*;
import org.redisson.api.*;
import org.redisson.config.*;

public class TestClusterConnectivity {

    public static void main( String[] args ) {
	try{
	Config config = new Config();
        //config.useClusterServers().addNodeAddress("redis://localhost:12000", "redis://localhost:12002");
	config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://localhost:9443", "redis://localhost:9445"); 
        RedissonClient redisson = Redisson.create(config);

        // operations with Redis based Lock

        // implements java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("simpleLock");
        lock.lock();

        try {
           // do some actions
        } finally {
           lock.unlock();
        }

        // operations with Redis based Map

        // implements java.util.concurrent.ConcurrentMap
        RMap<String, String> map = redisson.getMap("simpleMap");
        map.put("mapKey", "This is a map value");

        String mapValue = map.get("mapKey");
        System.out.println("stored map value: " + mapValue);

        redisson.shutdown();
	}catch(Exception ex){
		ex.printStackTrace();
	}
    }
}
