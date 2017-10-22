package org.slsale.service.dictionary.impl;

import org.slsale.dao.dictionary.DictionaryMapper;
import org.slsale.pojo.Dictionary;
import org.slsale.service.dictionary.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dll on 2017/10/3.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;
    /**
     * 根据证件编码   获取证件类型
     *
     * @param dictionary
     * @return
     * @throws Exception
     */
    @Override
    public List<Dictionary> getListByTypeCode(Dictionary dictionary) throws Exception {
        return dictionaryMapper.getListByTypeCode(dictionary);
    }
}
