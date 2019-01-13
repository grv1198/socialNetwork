package com.grv.restproject.socialNetwork.service;

import com.grv.restproject.socialNetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends JpaRepository<User, Integer> {
}
