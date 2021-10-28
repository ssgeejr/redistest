package com.tmobile.infrastructure.redis;

import redis.clients.jedis.Jedis;

public class RedisClusterTest {

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
	Jedis jedis = new Jedis("localhost", 12000);
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Cluster-1 is running: [" + jedis.ping() + "]");
	jedis = new Jedis("localhost", 12002);
        System.out.println("Cluster-2 is running: [" + jedis.ping() + "]");
    }
}
