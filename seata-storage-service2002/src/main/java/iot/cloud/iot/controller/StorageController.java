package iot.cloud.iot.controller;

import iot.cloud.iot.domain.CommonResult;
import iot.cloud.iot.domain.Storage;
import iot.cloud.iot.service.StorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"减库成功");
    }

    @GetMapping("/storage/get/{id}")
    public CommonResult<Storage> getStorageById(@PathVariable("id") Long id){
        Storage storage = storageService.getStorageById(id);
        return new CommonResult(200,"查询成功", storage);
    }
}
