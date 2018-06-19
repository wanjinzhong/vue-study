package com.example.demo.controller;

import com.example.demo.dao.entity.Company;
import com.example.demo.dao.repository.CompanyRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@RequestMapping("api")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "companies", method = RequestMethod.GET)
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "company/{companyId}", method = RequestMethod.GET)
    public Company queryById(@PathVariable("companyId") Integer companyId) {
        return companyRepository.findOne(companyId);
    }

    @GetMapping(value = "company")
    public List<Company> queryByName(@RequestParam("name") String name) {
        return companyRepository.findByNameLike("%" + name + "%");
    }
}
