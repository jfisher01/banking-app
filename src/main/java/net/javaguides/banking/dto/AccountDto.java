package net.javaguides.banking.dto;

/* 
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
	//DTO -> Data Transfer Object
	
	private Long id;
	private String accountHolderName;
	private double balance;

}
*/

//Latest method in Java 17 using Record class to replace the previous method above

public record AccountDto (Long id,
	String accountHolderName,
	 double balance) {
	
	
	
}