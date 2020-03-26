DataId格式：
${prefix}-${spring.profile.active}.${file-extension}
prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。



命名空间
Namespace+Group+DataId

默认情况：
Namespace=public, Group=DEFAULT_GROUP, 默认Cluster是DEFAULT


Namespace主要用来实现隔离：
比方说我们现在有三个环境：开发、测试、生产，我们就可以创建三个Namespace，不同的Namespace之间是隔离的。

Group可以把不同的微服务划分到不同的分组里


Service就是微服务：一个Service可以包含多个Cluster（集群），Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟划分。
比方说为了容灾，将Service微服务分别部署在杭州机房和广州机房，
这时就可以给杭州机房的Service微服务骑一个集群名称（HZ）；
给广州机房的Service微服务起一个集群名称（GZ），还可以尽量让同一个机房的微服务互相调用，以提升性能。


最后是Instance，就是微服务的实例。


