package com.spring.server.service;

import java.util.List;

import com.spring.server.model.dto.ReturnPolicyDto;

public interface ReturnPolicyService {

    List<ReturnPolicyDto> findAll();

}
