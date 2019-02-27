package com.rabbit.dao;

import com.rabbit.domain.LearnResource;
import com.rabbit.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface LearnResourceMapper extends MyMapper<LearnResource> {
    List<LearnResource> queryLearnResouceList(Map<String,Object> map);

}