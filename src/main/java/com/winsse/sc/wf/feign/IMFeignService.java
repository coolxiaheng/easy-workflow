package com.winsse.sc.wf.feign;

import com.winsse.sc.common.feign.IMRemoteClient;
import org.springframework.cloud.openfeign.FeignClient;

/**远程调用IM
 * @author tanwei
 * @version 1.0
 * 2021/10/27 15:11
 **/
@FeignClient(value = "im")
public interface IMFeignService extends IMRemoteClient {
}
