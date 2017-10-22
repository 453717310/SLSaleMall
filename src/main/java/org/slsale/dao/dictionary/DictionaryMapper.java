package org.slsale.dao.dictionary;

import org.slsale.pojo.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dll on 2017/10/3.
 * 数据字典的功能接口
 */
@Repository
public interface DictionaryMapper {
    /**
     * 根据证件编码   获取证件类型
     * @param dictionary
     * @return
     * @throws Exception
     */
    List<Dictionary> getListByTypeCode(Dictionary dictionary) throws Exception;
}
