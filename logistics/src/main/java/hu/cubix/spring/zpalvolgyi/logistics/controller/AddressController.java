package hu.cubix.spring.zpalvolgyi.logistics.controller;

import hu.cubix.spring.zpalvolgyi.logistics.dto.AddressDto;
import hu.cubix.spring.zpalvolgyi.logistics.mapper.AddressMapper;
import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import hu.cubix.spring.zpalvolgyi.logistics.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

    @PostMapping
    public AddressDto create(@RequestBody AddressDto addressDto){
        if(!addressService.validRequest(addressDto,"Create",null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Address address = addressMapper.dtoToAddress(addressDto);
        Address savedAddress = addressService.save(address);

        return addressMapper.addressToDto(savedAddress);
    }

    @GetMapping
    public List<AddressDto> findAll(){
        List<Address> addresses = addressService.findAll();
        return addressMapper.addressesToDtos(addresses);
    }

    @GetMapping("/{id}")
    public AddressDto findById(@PathVariable long id) {
        Address address = addressService.findById(id);

        if(address == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return addressMapper.addressToDto(address);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        addressService.deleteById(id);
    }

    @PutMapping("/{id}")
    public AddressDto updateById(@PathVariable long id, @RequestBody AddressDto addressDto){
        if(!addressService.validRequest(addressDto,"Update",id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Address checkAddressById = addressService.findById(id);

        if(checkAddressById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Address address = addressMapper.dtoToAddress(addressDto);
        Address updatedAddress = addressService.save(address);

        return addressMapper.addressToDto(updatedAddress);
    }

    @PostMapping(value = "/search")
    public List<AddressDto> findByExample(@RequestBody AddressDto exampleDto, Pageable pageable) {
        Address example = addressMapper.dtoToAddress(exampleDto);
        Page<Address> page = addressService.findByExample(example, pageable);
        return addressMapper.addressesToDtos(page.getContent());
    }
}
