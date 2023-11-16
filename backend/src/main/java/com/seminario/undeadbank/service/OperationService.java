package com.seminario.undeadbank.service;

import com.seminario.undeadbank.dto.TransactionRequestDto;
import com.seminario.undeadbank.dto.TransactionResponseDto;
import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.Transaction;
import com.seminario.undeadbank.repository.ClassificationRepository;
import com.seminario.undeadbank.repository.ParameterRepository;
import com.seminario.undeadbank.repository.TransactionRepository;
import com.seminario.undeadbank.utils.ClassificationEnum;
import com.seminario.undeadbank.utils.ResponseState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final ParameterRepository parameterRepository;
    private final ClassificationRepository classificationRepository;

    @Transactional
    public TransactionResponseDto deposit(TransactionRequestDto transactionDto) {
        var existsAccount = accountService.existsByAccountId(transactionDto.destinationAccount());

        if (!existsAccount) throw new BankException("La cuenta destino ingresada no existe")
                .withStatus(HttpStatus.NOT_FOUND)
                .withTitle("Cuenta no encontrada");

        var account = accountService.findByAccountId(transactionDto.destinationAccount());


        var isAccountActive =
                account.getStatusClassification().getInternalId() == ClassificationEnum.ACTIVE.getId();

        if (!isAccountActive) return new TransactionResponseDto(0L,
                ResponseState.BAD_REQUEST.getCode(), "La cuenta no esta activa");

        var referenceNumber = parameterRepository.findReferenceNumberValueByName("transaction.referenceNumber")
                .orElseThrow(() -> new BankException("No se pudo obtener el numero de referencia"));

        var transaction = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(account)
                .previousBalance(account.getBalance())
                .amount(transactionDto.amount())
                .datetime(LocalDateTime.now())
                .description("Deposito realizado desde la plataforma")
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.CREDIT.getId())
                )
                .build();
        account.setBalance(account.getBalance() + transactionDto.amount());
        accountService.save(account);
        transactionRepository.save(transaction);
        parameterRepository.setReferenceNumberValueByName("transaction.referenceNumber", referenceNumber + 1);

        return new TransactionResponseDto(referenceNumber, ResponseState.SUCCESS.getCode(), "Transaccion exitosa");
    }

    public TransactionResponseDto withdraw(TransactionRequestDto transactionDto) {
        var existsAccount = accountService.existsByAccountId(transactionDto.destinationAccount());

        if (!existsAccount) throw new BankException("La cuenta origen ingresada no existe")
                .withStatus(HttpStatus.NOT_FOUND)
                .withTitle("Cuenta no encontrada");

        var account = accountService.findByAccountId(transactionDto.destinationAccount());

        var isAccountActive =
                account.getStatusClassification().getInternalId() == ClassificationEnum.ACTIVE.getId();

        if (!isAccountActive) return new TransactionResponseDto(0L,
                ResponseState.BAD_REQUEST.getCode(), "La cuenta no esta activa");

        var haveEnoughBalance = accountService.validateBalance(transactionDto.amount(), transactionDto.destinationAccount());

        if (!haveEnoughBalance)
            return new TransactionResponseDto(0L,
                    ResponseState.INSUFFICIENT_MONEY.getCode(), "No hay suficiente saldo en la cuenta origen");

        var referenceNumber = parameterRepository.findReferenceNumberValueByName("transaction.referenceNumber")
                .orElseThrow(() -> new BankException("No se pudo obtener el numero de referencia"));

        var transaction = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(account)
                .previousBalance(account.getBalance())
                .amount(transactionDto.amount())
                .datetime(LocalDateTime.now())
                .description("Retiro realizado desde la plataforma")
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.DEBIT.getId())
                )
                .build();

        account.setBalance(account.getBalance() - transactionDto.amount());
        accountService.save(account);
        transactionRepository.save(transaction);
        parameterRepository.setReferenceNumberValueByName("transaction.referenceNumber", referenceNumber + 1);

        return new TransactionResponseDto(referenceNumber, ResponseState.SUCCESS.getCode(), "Transaccion exitosa");
    }

//    private void sendReport(String reportName, Map<String, Object> params, HttpServletRequest req, HttpServletResponse resp) {
//        String jasper_reports = req.getServletContext().getRealPath("/resources/jasper_reports/");
//        System.out.println(jasper_reports + reportName);
//        try (InputStream inputStream = new FileInputStream(jasper_reports + reportName);) {
//            resp.setContentType("application/pdf");
//            resp.addHeader("Content-disposition", "attachment; filename=report.pdf");
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionDB.getConnection());
//            OutputStream out = resp.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
//            out.flush();
//            out.close();
//        } catch (IOException | JRException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void adminReports(String split, HttpServletRequest req, HttpServletResponse resp) {
//        StringBuilder reportName = new StringBuilder("admin/adm_");
//        Map<String, Object> params = new HashMap<>();
//        switch (split) {
//            case "categories_income", "top_emp_by_income" -> {
//                reportName.append(split);
//                var initialDate = Date.valueOf(req.getParameter("initial_date"));
//                var finalDate = Date.valueOf(req.getParameter("final_date"));
//                params.put("initial_date", initialDate);
//                params.put("final_date", finalDate);
//            }
//            case "commission_history", "top_emp_by_offers" -> reportName.append(split);
//        }
//        reportName.append(".jasper");
//        sendReport(reportName.toString(), params, req, resp);
//    }

}
