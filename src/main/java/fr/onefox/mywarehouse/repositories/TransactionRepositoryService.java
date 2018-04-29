package fr.onefox.mywarehouse.repositories;

import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.domain.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TransactionRepositoryService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> listAll(int limit, int offset) {
        return transactionRepository.findAll(PageRequest.of(offset, limit, Sort.Direction.DESC, "_id"));
    }

    public Transaction saveOrUpdate(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Long countByRefCodeAndType(String refCode, TransactionType type) {
        return transactionRepository.countByRefCodeAndType(refCode, type);
    }
}
