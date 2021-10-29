#!/usr/bin/python3

import redis
import time

rp1 = redis.StrictRedis(host='localhost', port=12000, db=0)
rp2 = redis.StrictRedis(host='localhost', port=12002, db=0)

print ("set key1 on Node-1")
start = time.time()
alpha = 'New Key Inserted'
omega = None

start=time.time()
print (rp1.set('key1', alpha))
print ("get key1 from Node-1")
alpha = rp1.get('key1')
print ("Node-1: " , alpha)

print ("get key1 from Node-2")
#omega = rp2.get('key1')
#print("_Node-2: " , omega)
#print('Equal? ' , (omega == alpha))
limitter = 0
while alpha != omega: 
    omega = rp2.get('key1')
    end = time.time()
    #print("Node-2: " , omega)
    limitter = limitter + 1
    if limitter > 1000: 
        print('FAILED TO REPLICATE IN 1 SECOND')
        exit(0)
     

print("Replication Time: " , (end-start))
rp1.delete('key1')
print('Key removed')

exit(1)

print ("get key1 from cluster 2")
print (rp2.get('key1'))
end = time.time()
print('Runtime: ' , (end-start))
start = time.time()

print (rp2.get('key1'))
end = time.time()
print('Runtime: ' , (end-start))
start = time.time()

print (rp2.get('key1'))
end = time.time()
print('Runtime: ' , (end-start))
start = time.time()


