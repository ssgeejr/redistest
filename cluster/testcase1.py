import redis

rp1 = redis.StrictRedis(host='localhost', port=12000, db=0)
rp2 = redis.StrictRedis(host='localhost', port=12002, db=0)

print ("set key1 123 in cluster 1")
print (rp1.set('key1', '123'))
print ("get key1 cluster 1")
print (rp1.get('key1'))

print ("get key1 from cluster 2")
print (rp2.get('key1'))
