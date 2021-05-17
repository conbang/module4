package com.example.module4.repository;

import com.example.module4.model.LoginUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<LoginUser, Long> {
}
