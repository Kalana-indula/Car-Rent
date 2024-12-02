package com.demo.carrent.repository;

import com.demo.carrent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user WHERE id_no= :idNo",nativeQuery = true)
    User findUserByIdNo(String idNo);
}
