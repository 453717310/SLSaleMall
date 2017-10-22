package org.slsale.service.menu;

import org.slsale.dao.menu.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dll on 2017/9/23.
 */
@Service
public class FunctionServiceImpl implements FunctionService {
    @Resource
    private FunctionMapper functionMapper;
    /**
     * 根据角色id 获取一级菜单
     *
     * @param authority
     * @return
     * @throws Exception
     */
    @Override
    public List<Function> getFirstFunctionList(Authority authority) throws Exception {
        return functionMapper.getFirstFunctionList(authority);
    }

    /**
     * 根据父级id  与角色id 获取二级菜单
     *
     * @param function
     * @return
     * @throws Exception
     */
    @Override
    public List<Function> getTwoFunctionList(Function function) throws Exception {
        return functionMapper.getTwoFunctionList(function);
    }

    /**
     * 获取一级功能
     *
     * @param function
     * @return
     * @throws Exception
     */
    @Override
    public List<Function> getFirstFunction(Function function) throws Exception {
        return functionMapper.getFirstFunction(function);
    }
}
