package org.slsale.service.authority.impl;

import org.slsale.dao.authority.AuthorityMapper;
import org.slsale.pojo.Authority;
import org.slsale.service.authority.AuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dll on 2017/10/21.
 * @author dll
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Resource
    private AuthorityMapper authorityMapper;
    /**
     * 获取权限列表
     *
     * @param authority
     * @return
     * @throws Exception
     */
    @Override
    public Authority getAuthority(Authority authority) throws Exception {
        return authorityMapper.getAuthority(authority);
    }
}
