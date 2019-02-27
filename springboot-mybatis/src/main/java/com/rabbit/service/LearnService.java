package com.rabbit.service;

import com.rabbit.domain.LearnResource;
import com.rabbit.model.LeanQueryLeanListReq;
import com.rabbit.util.Page;

import java.util.List;

public interface LearnService extends IService<LearnResource> {

    List<LearnResource> queryLearnResourceList(Page<LeanQueryLeanListReq> page);

    void deleteBatch(Long[] ids);
}
