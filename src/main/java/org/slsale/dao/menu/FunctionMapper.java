package org.slsale.dao.menu;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dll on 2017/9/23.
 * 功能列表
 */
@Repository
public interface FunctionMapper {
    /**
     * 根据角色id 获取一级菜单
     * @param authority
     * @return
     * @throws Exception
     */
    List<Function> getFirstFunctionList(Authority authority) throws Exception;

    /**
     * 根据父级id  与角色id 获取二级菜单
     * @param function
     * @return
     * @throws Exception
     */
    List<Function> getTwoFunctionList(Function function) throws Exception;

    /**
     * 获取一级功能
     * @param function
     * @return
     * @throws Exception
     */
    List<Function> getFirstFunction(Function function) throws Exception;
}
