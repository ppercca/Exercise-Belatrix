package com.example.demo.logtraffic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTrafficRepository extends JpaRepository<LogTraffic, Long>{

}
