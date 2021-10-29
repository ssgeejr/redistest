package com.tmobile.infrastructure.redis;

import java.util.*;
import java.util.Map.Entry;

import redis.clients.jedis.*;

import org.redisson.*;
import org.redisson.api.*;
import org.redisson.config.*;

import net.bytebuddy.asm.Advice.This;

public class TestClusterConnectivity {

    public static void main( String[] args ) {
    	new TestClusterConnectivity().ClusterTest();
    }
    
	public void ClusterTest() {
    	try {
    		
//    		--these aren't working because the cluser is set to localhost IP's .. the cluster will need to be rebuilt to support public IP connectivity
	
    		 JedisCluster jedisCluster = new JedisCluster(new HostAndPort("localhost", 12000));
    		 String key = "testkey";
    		 jedisCluster.set(key,"random-value");
    		 
    		 Thread.sleep(2000);
    		 System.out.println("Fetched Value for '"  + key + "': " + jedisCluster.get(key));
    		 
//    		 Set<Entry<String, JedisPool>> nodes =  jedisCluster.getClusterNodes().entrySet();
//    		 jedisCluster.set("cluster","istrue");
    		 
//    		 jedisCluster.ping
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    		
	public void JedisTestAlpha() {
    	try {
    		
//    		--these aren't working because the cluser is set to localhost IP's .. the cluster will need to be rebuilt to support public IP connectivity
	
//    		 JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redistest", 12000));
    		 Jedis jedis = new Jedis("redistest", 12000);
    		 String key = "testkey";
    		 jedis.set(key,"random-value");
    		 
    		 Thread.sleep(2000);
    		 jedis = new Jedis("redistest", 12002);
    		 System.out.println("Fetched Value for '"  + key + "': " + jedis.get(key));
    		 
//    		 Set<Entry<String, JedisPool>> nodes =  jedisCluster.getClusterNodes().entrySet();
//    		 jedisCluster.set("cluster","istrue");
    		 
//    		 jedisCluster.ping
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void JedisTestBeta() {
    	try {
    	
    		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
    		
    		jedisClusterNode.add(new HostAndPort("redistest", 12000));
    		JedisPoolConfig cfg = new JedisPoolConfig();
    		cfg.setMaxTotal(16);
    		cfg.setMaxIdle(8);
    		cfg.setMaxWaitMillis(10000);
    		cfg.setTestOnBorrow(true); 
    		
//    		JedisCluster jc = new JedisCluster(jedisClusterNode);
    		JedisCluster jc = new JedisCluster(jedisClusterNode, 10000, 1, cfg);
    		
    		
    		
//    		   public void setKeyInRedis(String key, String value) {
//    			      getRedisCluster.set(key, value);
//    			   }
//
//    			   public String getValueByKey(String key) {
//    			      return (String) getRedisCluster.get(key);
    		
//    		jc.set("foo", "bar");
//    		String value = jc.get("foo");
    		
    		
//    	    System.out.println(value);
//    	    try {
//    	        jc.close();
//    	    } catch (Exception e) {
//    	        e.printStackTrace();
//    	    }
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    	
   public void RedissonTest() {
    	
	try{
		Config config = new Config();
        //config.useClusterServers().addNodeAddress("redis://localhost:12000", "redis://localhost:12002");
		config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://redistest:12000", "redis://redistest:12002"); 
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
