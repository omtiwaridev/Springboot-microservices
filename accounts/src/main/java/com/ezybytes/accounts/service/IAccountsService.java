package com.ezybytes.accounts.service;

import com.ezybytes.accounts.dto.CustomerDto;

/**
 * @param  - CustomerDto Object
 */

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
