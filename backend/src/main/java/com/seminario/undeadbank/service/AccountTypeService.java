package com.seminario.undeadbank.service;

import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.Account;
import com.seminario.undeadbank.model.AccountTypeData;
import com.seminario.undeadbank.model.Classification;
import com.seminario.undeadbank.repository.AccountTypeDataRepository;
import com.seminario.undeadbank.repository.ClassificationRepository;
import com.seminario.undeadbank.utils.ClassificationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeService {

    private final AccountTypeDataRepository accountTypeDataRepository;
    private final ClassificationRepository classificationRepository;

    public AccountTypeData changeExchangeRate(Long accountTypeId, Double newExchangeRate) {
        var oAccountTypeData = accountTypeDataRepository.findById(accountTypeId);

        if (oAccountTypeData.isEmpty()) throw new BankException("El tipo de cuenta no existe");

        var accountTypeData = oAccountTypeData.get();
        accountTypeData.setExchangeRate(newExchangeRate);
        return accountTypeDataRepository.save(accountTypeData);
    }

    public AccountTypeData getByClassification(ClassificationEnum classificationEnum) {
        var oAccountType =  accountTypeDataRepository
                .findByAccountTypeClassification(classificationRepository
                        .findByInternalId(classificationEnum.getId()));

        if (oAccountType.isEmpty()) throw new BankException("El tipo de cuenta no existe");

        return oAccountType.get();
    }

    public double getExchangeRate(Account account) {
        var oAccountType =  accountTypeDataRepository
                .findByAccountTypeClassification(classificationRepository
                        .findById(account.getAccountClassification().getClassificationId())
                        .orElseThrow(() -> new BankException("El tipo de cuenta no existe")));

        if (oAccountType.isEmpty()) throw new BankException("No se encontro el tipo de cuenta");

        return oAccountType.get().getExchangeRate();
    }

    public List<AccountTypeData> getAllAccountTypeData(){
        return accountTypeDataRepository.findAll();
    }

}
