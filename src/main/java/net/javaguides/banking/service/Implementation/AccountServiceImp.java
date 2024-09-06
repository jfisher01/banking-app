package net.javaguides.banking.service.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.banking.Exception.AccountException;
import net.javaguides.banking.Mapper.AccountMapper;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.TransferFundDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;

@Service
public class AccountServiceImp implements AccountService {

	private AccountRepository accountRepository;
	
	
	
	/**
	 * @param accountRepository
	 */
	public AccountServiceImp(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).
				orElseThrow(()-> new AccountException("Account does not exist"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
			
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).
				orElseThrow(()-> new AccountException("Account does not exist"));
		
		if(account.getBalance() < amount) {
			throw new AccountException("Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountRepository.findAll();
	return	accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
				
	}



	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).
				orElseThrow(()-> new AccountException("Account does not exist"));
		
		accountRepository.deleteById(id);
	}



	@Override
	public void transferFunds(TransferFundDto transferFundDto) {
		// TODO Auto-generated method stub
		
	//Retreive the account from which we send the money
	Account fromAccount =  accountRepository
	.findById(transferFundDto.fromAccountId()).orElseThrow(()-> new AccountException("Account does not exist"));
		
	//Retrive the account to which we send the amount
	Account toAccount = accountRepository
	.findById(transferFundDto.toAccountId()).orElseThrow(()-> new AccountException("Account does not exist"));
	
	//Debit the amount fromAccount object
	fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());
	
	//Credit the amount to account object
	toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount() );
	
	
	accountRepository.save(fromAccount);
	accountRepository.save(toAccount);
	}
	
	

}














