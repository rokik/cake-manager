package com.waracle.cakemanager.init;

import com.waracle.cakemanager.contract.Cake;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cakeClient", url = "${feign.cake.init.baseUrl}")
public interface CakeClient {
    @RequestMapping(method = RequestMethod.GET, value = "${feign.cake.init.cakes}", consumes = "application/json")
    List<Cake> downloadCakes();
}
