package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {

}
