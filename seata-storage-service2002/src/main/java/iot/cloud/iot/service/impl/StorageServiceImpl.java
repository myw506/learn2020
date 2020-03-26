package iot.cloud.iot.service.impl;

import iot.cloud.iot.dao.StorageDao;
import iot.cloud.iot.domain.CommonResult;
import iot.cloud.iot.domain.Storage;
import iot.cloud.iot.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService{
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }

    @Override
    public Storage getStorageById(Long id) {
        return storageDao.getStorageById(id);
    }
}
