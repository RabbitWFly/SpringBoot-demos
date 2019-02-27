package com.rabbit.service.impl;

import com.github.pagehelper.PageHelper;
import com.rabbit.dao.LearnResourceMapper;
import com.rabbit.domain.LearnResource;
import com.rabbit.model.LeanQueryLeanListReq;
import com.rabbit.service.LearnService;
import com.rabbit.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author chentao
 * Date 2019/2/21
 * Description
 **/
@Service
public class LearnServiceImpl extends BaseService<LearnResource> implements LearnService {

    @Autowired
    private LearnResourceMapper learnResourceMapper;

    @Override
    public void deleteBatch(Long[] ids) {
        Arrays.stream(ids).forEach(id->learnResourceMapper.deleteByPrimaryKey(id));
    }

    @Override
    public List<LearnResource> queryLearnResourceList(Page<LeanQueryLeanListReq> page) {
        PageHelper.startPage(page.getPage(), page.getRows());
        return learnResourceMapper.queryLearnResouceList(page.getCondition());
    }
}

