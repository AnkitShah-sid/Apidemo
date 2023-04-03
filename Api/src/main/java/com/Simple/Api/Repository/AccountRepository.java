package com.Simple.Api.Repository;

import com.Simple.Api.Entity.AccountEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan("com.Simple.Api.Repository")


public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

}
