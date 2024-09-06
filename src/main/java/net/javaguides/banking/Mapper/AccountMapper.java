package net.javaguides.banking.Mapper;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

public class AccountMapper {
	
public static Account mapToAccount(AccountDto accountDto) {
Account account = new Account(
		
		/*
		accountDto.getId(),
		accountDto.getAccountHolderName(),
		accountDto.getBalance()
		*/
		
		//Latest method in Java 17 and above uses Record class. 
		//This class invoke constructor, getters and setters etc automatically using encapsulation
		 
		accountDto.id(),
		accountDto.accountHolderName(),
		accountDto.balance()
			);
     
     return account;
}

public static AccountDto mapToAccountDto(Account account) {
	AccountDto accountDto = new AccountDto(
	account.getId(),
	account.getAccountHolderName(),
	account.getBalance()
);
	return accountDto;
}

}


