package awa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/account"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public void create(@RequestBody Account account){
        accountService.create(account);
    }

    @GetMapping(path = {"/{id}"})
    public Account findOne(@PathVariable("id") UUIDKey id){
        return accountService.get(id);
    }

    @PutMapping
    public void update(@RequestBody Account account){
        accountService.update(account);
    }

    @DeleteMapping(path ={"/{id}"})
    public void delete(@PathVariable("id") UUIDKey id) {
        accountService.delete(id);
    }

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }


}
