package com.grv.restproject.socialNetwork.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends JpaRepository<User, Integer> {
}
