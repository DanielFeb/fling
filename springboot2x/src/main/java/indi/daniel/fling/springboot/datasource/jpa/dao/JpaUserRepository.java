package indi.daniel.fling.springboot.datasource.jpa.dao;

import indi.daniel.fling.springboot.datasource.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 2018/12/20.
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {
}
