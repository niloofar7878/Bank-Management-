package com.example.BankManagement.modules.account;

import com.example.BankManagement.modules.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConvertor {

    public AccountDTO entityToDto(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setCardId(account.getCardId());
        accountDTO.setAccountBalance(account.getAccountBalance());
        accountDTO.setAccountStatus(accountDTO.getAccountStatus());

        String customerInfo = "Customer Name : "+ account.getCustomerInfo().getFirstName()+ " "+ account.getCustomerInfo().getLastName()+ " , "
                +"Father Name: "+account.getCustomerInfo().getFatherName()+ " , "+ "Customer Number : "+ account.getCustomerInfo().getCustomerNumber();
        accountDTO.setCustomerInfo(customerInfo);

        String bankBranch = "Bank Branch : "+ account.getBankBranch().getBranchName()+ " , "+ "Branch Code : " + account.getBankBranch().getBranchCode()+
                " , "+ "Routing Number : "+ account.getBankBranch().getRoutingNumber()+ " , "+ "Branch Address : "+
                account.getBankBranch().getBranchAddress();
        accountDTO.setBankBranch(bankBranch);

        accountDTO.setCreateDateTime(account.getCreateDateTime());

        return accountDTO;
    }

    public List<AccountDTO> dtoList (List<Account> accountList){
        return accountList.stream().map(account -> entityToDto(account)).collect(Collectors.toList());
    }
}
