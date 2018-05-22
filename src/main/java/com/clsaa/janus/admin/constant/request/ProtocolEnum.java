package com.clsaa.janus.admin.constant.request;

import com.google.common.collect.ImmutableMap;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 支持协议,大写用逗号分隔
 *
 * @author 任贵杰
 * @summary 支持协议枚举
 * @since 2018/5/19
 */
public enum ProtocolEnum {
    /**
     * 支持协议:HTTP
     */
    HTTP(),

    /**
     * 支持协议:HTTPS
     */
    HTTPS(),
    /**
     * 支持协议:WS
     */
    WS();

    private static final ImmutableMap<String, ProtocolEnum> protocolEnumMap = ImmutableMap.of(
            ProtocolEnum.HTTP.name(), ProtocolEnum.HTTP,
            ProtocolEnum.HTTPS.name(), ProtocolEnum.HTTPS,
            ProtocolEnum.WS.name(), ProtocolEnum.WS);

    /**
     * 通过传入的协议字符串获取当前支持的协议字符串
     *
     * @param protocols 以逗号分隔的协议字符串
     * @return 格式化的以逗号分隔的协议字符串
     */
    public static String getSupportedProtocols(String protocols) {
        return Stream.of(protocols.split(","))
                .map(p -> protocolEnumMap.get(p.toUpperCase()))
                .filter(Objects::nonNull)
                .map(Enum::name)
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
    }

    /**
     * 检测传入参数是否支持WS
     *
     * @param protocols 以逗号分隔的协议字符串
     * @return true为可以, false为不可以
     */
    public static boolean enabledWS(String protocols) {
        return Stream.of(protocols.split(","))
                .map(p -> protocolEnumMap.get(p.toUpperCase()))
                .filter(Objects::nonNull)
                .map(Enum::name)
                .distinct()
                .collect(Collectors.toMap(s -> s, s -> s)).containsKey(ProtocolEnum.WS.name());
    }
}
