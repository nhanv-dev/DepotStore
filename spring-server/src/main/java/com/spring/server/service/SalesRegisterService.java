package com.spring.server.service;

import org.springframework.data.domain.Page;

import com.spring.server.model.constant.ESalesRegisterStatus;
import com.spring.server.model.dto.SalesRegisterDto;

public interface SalesRegisterService {

    SalesRegisterDto findOneByUserId(Long userId);

    Page<SalesRegisterDto> findAll(int page, int size);

    Page<SalesRegisterDto> findAllByStatus(int page, int size, ESalesRegisterStatus status);

    SalesRegisterDto confirm(Long id);

    SalesRegisterDto save(SalesRegisterDto salesRegisterDto);


}
