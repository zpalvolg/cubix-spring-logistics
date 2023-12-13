package hu.cubix.spring.zpalvolgyi.logistics.controller;

import hu.cubix.spring.zpalvolgyi.logistics.dto.AddressDto;
import hu.cubix.spring.zpalvolgyi.logistics.mapper.AddressMapper;
import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import hu.cubix.spring.zpalvolgyi.logistics.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

    @GetMapping
    public List<AddressDto> findAll(){
        List<Address> addresses = addressService.findAll();
        return addressMapper.addressesToDtos(addresses);
    }
}
