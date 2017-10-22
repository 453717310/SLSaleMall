package org.slsale.service.authority;

import org.slsale.pojo.Authority;

/**
 * Created by dll on 2017/10/21.
 * @author dll
 */
public interface AuthorityService {
    /**
     * 获取权限列表
     * @param authority
     * @return
     * @throws Exception
     */
    Authority getAuthority(Authority authority) throws Exception;
}
