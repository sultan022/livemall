package com.chatapp.repository;

import com.chatapp.domain.UserData;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<UserData, Integer> {
}
