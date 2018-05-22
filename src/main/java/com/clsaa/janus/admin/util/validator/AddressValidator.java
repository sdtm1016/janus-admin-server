package com.clsaa.janus.admin.util.validator;

import java.util.regex.Pattern;

/**
 * @author 任贵杰
 * @version v1
 * @summary 判断是否为网址, 如http://blog.bai.net或http://#blog#.bai.net,##中的值作为占位符,在路由时可以被替换
 * @since 2018/5/22
 */
public class AddressValidator {

    public static final Pattern PATTERN = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-#-~]+).)+([A-Za-z0-9-~\\/])+$");

    public AddressValidator() {
        throw new UnsupportedOperationException();
    }

    public static boolean isAddress(String address) {
        return PATTERN.matcher(address).matches();
    }
}
