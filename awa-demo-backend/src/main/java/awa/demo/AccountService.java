package awa.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    public void create(Account account) {
    }

    public Account get(UUIDKey id) {
        return null;
    }

    public void update(Account account) {
    }

    public void delete(UUIDKey id) {
    }

    public List<Account> findAll() {
        return new ArrayList<>();
    }
}
