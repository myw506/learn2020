测试
http://localhost:2001/order/create?userId=1&productId=1&count=10&money=100

说明
seata 0.9

分布式事务流程
1.TM asks TC to begin a new global transaction. TC generates an XID representing the global transaction.
2.XID is propagated through microservices' invoke chain.
3.RM register local transaction as a branch of the corresponding global transaction of XID to TC.
4.TM asks TC for committing or rollbacking the corresponding global transaction of XID.
5.TC drives all branch transactions under the corresponding global transaction of XID to finish branch committing or rollbacking.