持久化配置

[
    {
        "resource": "/rateLimit/byUrl",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]

resource：资源名称
limitApp: 来源应用
grade: 阀值类型，0表示线程数，1表示QPS
count: 单机阀值
strategy: 流控模式，0表示直接，1表示关联，2表示链路；
controlBehavior: 流控效果，0表示快速失败，1表示Warm Up, 2表示排队等待；
clusterMode: 是否集群。