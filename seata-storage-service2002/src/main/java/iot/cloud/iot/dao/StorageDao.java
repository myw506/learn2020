package iot.cloud.iot.dao;

import iot.cloud.iot.domain.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);


    Storage getStorageById(@Param("id") Long id);
}
