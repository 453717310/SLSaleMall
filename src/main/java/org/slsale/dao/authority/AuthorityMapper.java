package org.slsale.dao.authority;

import org.slsale.pojo.Authority;
import org.springframework.stereotype.Repository;

/**
 * Created by dll on 2017/10/21.
 * @author dll
 */
@Repository
public interface AuthorityMapper {
    /**
     * 获取权限列表
     * @param authority
     * @return
     * @throws Exception
     */
    Authority getAuthority(Authority authority) throws Exception;
}
