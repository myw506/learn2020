package iot.cloud.iot.service;

import iot.cloud.iot.domain.CommonResult;
import iot.cloud.iot.domain.Storage;

public interface StorageService {

    void decrease(Long productId, Integer count);

    Storage getStorageById(Long id);
}
