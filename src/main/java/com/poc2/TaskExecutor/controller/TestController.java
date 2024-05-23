package com.poc2.TaskExecutor.controller;

import com.poc2.TaskExecutor.entity.Product;
import com.poc2.TaskExecutor.entity.Transactions;
import com.poc2.TaskExecutor.service.ProductService;
import com.poc2.TaskExecutor.service.TransactionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@RestController
@AllArgsConstructor
public class TestController {
    private ProductService productService;

    private TransactionService transactionService;

    @Qualifier(value = "taskExecutorTest")
    private TaskExecutor testExecutor;

/*
    @GetMapping("/hello1")
    public List<Product> index1() {
        return productService.getAllProduct();
    }*/

    @GetMapping("/hello2")
    public List<Transactions> index2() {
        return transactionService.getAllTransactions();
    }

    //Multithreading works
    // productService.getAllProduct() runs after transactions1,transactions2,transactions3,transactions4
    @GetMapping("/hello3")
    public void index3() {
        CompletableFuture<Void> completableFuture;
        /*We need to make a  following work*/
       /* completableFuture = CompletableFuture.runAsync(()->{
            testExecutor.execute(()-> {
                List<Transactions> transactions1 = transactionService.getAllTransactions();
                List<Transactions> transactions2 = transactionService.getAllTransactions();
                List<Transactions> transactions3 = transactionService.getAllTransactions();
                List<Transactions> transactions4 = transactionService.getAllTransactions();
            });
        });*/

        completableFuture = CompletableFuture.runAsync(()->{
                List<Transactions> transactions1 = transactionService.getAllTransactions();
                List<Transactions> transactions2 = transactionService.getAllTransactions();
                List<Transactions> transactions3 = transactionService.getAllTransactions();
                List<Transactions> transactions4 = transactionService.getAllTransactions();
        });

        completableFuture.thenRun(()-> testExecutor.execute(()->productService.getAllProduct()));
    }

    @GetMapping("/hello4")
    public void index4() {
        CompletableFuture<Void> completableFuture;
        /*We need to make a  following work*/
       /* completableFuture = CompletableFuture.runAsync(()->{
            testExecutor.execute(()-> {
                List<Transactions> transactions1 = transactionService.getAllTransactions();
                List<Transactions> transactions2 = transactionService.getAllTransactions();
                List<Transactions> transactions3 = transactionService.getAllTransactions();
                List<Transactions> transactions4 = transactionService.getAllTransactions();
            });
        });*/

        List<Integer> allTransactionId = transactionService.getAllTransactionId();
        List<List<Integer>> allTransactionIdBatch = batchList(allTransactionId);
        completableFuture = CompletableFuture.runAsync(()->{
            for (List<Integer> batch: allTransactionIdBatch) {
                testExecutor.execute(()-> {
                    transactionService.workWithTransactionId(batch);
                });
            }

        });

        //completableFuture.thenRun(()-> testExecutor.execute(()->productService.getAllProduct()));
    }


    @GetMapping("/hello5")
    public void index5() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i=0;i<3;i++) {
            testExecutor.execute(()-> {
               doSomeWork();
               countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I got executed again!!!!");
    }

    private void doSomeWork(){
        System.out.println("Going to sleep");
       try{
           Thread.sleep(2000);
           System.out.println("i have waken up!!!!!");
       }catch (Exception e){

       }
        for (int i=0;i<10;i++) {
            System.out.println(Thread.currentThread().getName()+" is executing in do some work!!!!!!!");
            try {
                System.out.println("Going to sleep again!!!!");
                Thread.sleep(500);
                System.out.println("i have waken up.Time to run!!!!!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public  List<List<Integer>> batchList(List<Integer> data) {
        int batchSize = 500;
        List<List<Integer>> batches = new ArrayList<>();
        int startIndex = 0;

        for (Integer item : data) {
            // Check if a new batch is needed or add to the current one
            if (batches.isEmpty() || batches.get(batches.size() - 1).size() == batchSize) {
                batches.add(new ArrayList<>());
            }
            batches.get(batches.size() - 1).add(item);
            startIndex++;
        }

        return batches;
    }


}
