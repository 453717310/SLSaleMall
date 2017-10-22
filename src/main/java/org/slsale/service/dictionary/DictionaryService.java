package org.slsale.service.dictionary;

import org.slsale.pojo.Dictionary;

import java.util.List;

/**
 * Created by dll on 2017/10/3.
 */
public interface DictionaryService {
    /**
     * 根据证件编码   获取证件类型
     * @param dictionary
     * @return
     * @throws Exception
     */
    List<Dictionary> getListByTypeCode(Dictionary dictionary) throws Exception;
}
