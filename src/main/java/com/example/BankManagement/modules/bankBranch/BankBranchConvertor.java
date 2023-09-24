package com.example.BankManagement.modules.bankBranch;

import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import org.springframework.stereotype.Component;

@Component
public class BankBranchConvertor {

    public BankBranchDTO entityToDto (BankBranch bank){
        BankBranchDTO bankDTO = new BankBranchDTO();
        bankDTO.setBankId(bank.getBankId());
        bankDTO.setBranchName(bank.getBranchName());
        bankDTO.setBranchCode(bank.getBranchCode());
        bankDTO.setRoutingNumber(bank.getRoutingNumber());
        bankDTO.setEmployees(bank.getEmployees());
        bankDTO.setBranchAddress(bank.getBranchAddress());
        return bankDTO;
    }
}
