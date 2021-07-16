package com.tmobile.infrastructure.redis;

import redis.clients.jedis.Jedis; 

public class RedisTest { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("redistest"); 
      System.out.println("Connection to server sucessfully"); 
      //check whether server is running or not 
      System.out.println("Server is running: "+jedis.ping()); 
   } 
} 
