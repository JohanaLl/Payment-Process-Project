package com.paymentchain.account.repository;


import org.springframework.data.repository.CrudRepository;

import com.paymentchain.account.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
