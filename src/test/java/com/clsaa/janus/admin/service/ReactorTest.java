package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.exception.AccessDeniedException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/24
 */
public class ReactorTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int func() {
        return 2;
    }

    public static void main(String[] args) {
        new ReactorTest().reactorFunc();
    }

    public void reactorFunc(){
        Mono.create(monoSink -> monoSink.success(func()))
                .doOnError(e -> logger.error(e.getMessage()+"2312312312"))
                .map(count -> {
                    BizAssert.allowed((int) count == 1, BizCodes.ERROR_INSERT);
                    return null;
                })
                .onErrorMap(original -> new AccessDeniedException(1, "123.onErrorMap"))
        .block();
    }
}
