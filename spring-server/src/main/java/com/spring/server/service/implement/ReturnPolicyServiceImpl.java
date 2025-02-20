package com.spring.server.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.server.model.dto.ReturnPolicyDto;
import com.spring.server.model.entity.ReturnPolicy;
import com.spring.server.model.mapper.ReturnPolicyMapper;
import com.spring.server.repository.ReturnPolicyRepo;
import com.spring.server.service.ReturnPolicyService;

@Component
public class ReturnPolicyServiceImpl implements ReturnPolicyService {
    @Autowired
    private ReturnPolicyRepo returnPolicyRepo;

    @Override
    public List<ReturnPolicyDto> findAll() {
        List<ReturnPolicy> list = returnPolicyRepo.findAll();
        return new ArrayList<>(ReturnPolicyMapper.toDto(list));
    }
}
