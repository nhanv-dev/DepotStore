package com.spring.server.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.server.model.constant.ESalesRegisterStatus;
import com.spring.server.model.dto.SalesRegisterDto;
import com.spring.server.model.entity.SalesRegister;
import com.spring.server.model.entity.Shop;
import com.spring.server.model.entity.User;
import com.spring.server.model.mapper.SalesRegisterMapper;
import com.spring.server.repository.RatingInfoRepo;
import com.spring.server.repository.SalesRegisterRepo;
import com.spring.server.repository.ShopRepo;
import com.spring.server.repository.UserRepo;
import com.spring.server.service.SalesRegisterService;
import com.spring.server.service.ShopService;

@Component
public class SalesRegisterServiceImpl implements SalesRegisterService {
    @Autowired
    ShopService shopService;
    @Autowired
    SalesRegisterRepo salesRegisterRepo;
    @Autowired
    RatingInfoRepo ratingInfoRepo;
    @Autowired
    ShopRepo shopRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public SalesRegisterDto findOneByUserId(Long userId) {
        SalesRegister salesRegister = salesRegisterRepo.findOneByUser_Id(userId);
        if (salesRegister == null) return null;
        return SalesRegisterMapper.toDto(salesRegister);
    }

    @Override
    public Page<SalesRegisterDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return SalesRegisterMapper.toDto(salesRegisterRepo.findAll(pageable));
    }

    @Override
    public Page<SalesRegisterDto> findAllByStatus(int page, int size, ESalesRegisterStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        return null;
    }

    @Override
    public SalesRegisterDto save(SalesRegisterDto salesRegisterDto) {
        User user = userRepo.findOneById(salesRegisterDto.getUserId());
        SalesRegister salesRegister = SalesRegisterMapper.toEntity(salesRegisterDto);
        salesRegister.setUser(user);
        salesRegister.setStatus(ESalesRegisterStatus.PENDING);
        salesRegister = salesRegisterRepo.save(salesRegister);
        return SalesRegisterMapper.toDto(salesRegister);
    }

    @Override
    @Transactional
    public SalesRegisterDto confirm(Long id) {
        SalesRegister salesRegister = salesRegisterRepo.findOneById(id);
        Shop shop = new Shop();
        shop.setShopName(salesRegister.getShopName());
        shop.setPhoneNumber(salesRegister.getShopPhone());
        shop.setEmail(salesRegister.getShopEmail());
        shop.setWarehouseRegionName(salesRegister.getWarehouseRegionName());
        shop.setCity(salesRegister.getCity());
        shop.setDistrict(salesRegister.getDistrict());
        shop.setWards(salesRegister.getWards());
        shop.setAddressDetail(salesRegister.getAddressDetail());
        shop.setUser(salesRegister.getUser());
        shopService.save(shop);
        salesRegister.setStatus(ESalesRegisterStatus.CONFIRMED);
        salesRegister = salesRegisterRepo.save(salesRegister);
        return SalesRegisterMapper.toDto(salesRegister);
    }
}
