package com.example.module4.repository;

import com.example.module4.model.Shop;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopRepository extends PagingAndSortingRepository<Shop,Long> {
}
