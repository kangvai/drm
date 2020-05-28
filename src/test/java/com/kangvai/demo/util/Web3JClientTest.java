package com.kangvai.demo.util;

import com.kangvai.demo.generated.RecordContract;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * @author kangvai
 * @date 2020/5/27 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Web3JClientTest {
    /**
     * 以太币转账测试
     * @params []
     * @return void
     */
    @Test
    public void sendEtherFromReaderToAuthorTest() {
        String readerAddress = "0x740E2c36C1D40932afA5516DdDe91DE71417331b";
        String readerPrivateKey = "0x6575931f462a3cd3c69255e575fb1d77f7ec19e7c04fa1b22951d23b9ddea8d1";
        String authorAddress = "0xE6C765D8d2769dC5B976ae34176aEA3124bc6720";
        try {
            String transactionHash = Web3JClient.sendEtherFromReaderToAuthor(readerAddress,readerPrivateKey,authorAddress);
            log.info("transactionHash=======>"+transactionHash);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addRecordTest() throws Exception {
        String privateKey = "0x6575931f462a3cd3c69255e575fb1d77f7ec19e7c04fa1b22951d23b9ddea8d1";
        String contractAddress = "0x018af7d64cc0a9769029244f8c0ae791fe9550a4";
        Web3JClient.setContractAddress(contractAddress);
        RecordContract recordContract = Web3JClient.load(privateKey);
        String originalFileName = "夏天的风";
        String userName = "user";
        String timeStamp = "2020/0527/21/04";
        String hash = "123456";
        TransactionReceipt transactionReceipt = recordContract.addRecord(originalFileName, userName, timeStamp, hash).send();
        log.info("transactionReceipt======>"+transactionReceipt.getTransactionHash());
    }

    @Test
    public void getRecordLengthTest() throws Exception {
        String privateKey = "0x6575931f462a3cd3c69255e575fb1d77f7ec19e7c04fa1b22951d23b9ddea8d1";
        String contractAddress = "0x018af7d64cc0a9769029244f8c0ae791fe9550a4";
        Web3JClient.setContractAddress(contractAddress);
        RecordContract recordContract = Web3JClient.load(privateKey);
        BigInteger recordLen = recordContract.getRecordLength().send();
        String rLength = String.valueOf(recordLen);
        log.info("rLength=====>"+rLength);
    }

    @Test
    public void getRecordTest() throws Exception {
        String privateKey = "0x6575931f462a3cd3c69255e575fb1d77f7ec19e7c04fa1b22951d23b9ddea8d1";
        String contractAddress = "0x018af7d64cc0a9769029244f8c0ae791fe9550a4";
        Web3JClient.setContractAddress(contractAddress);
        RecordContract recordContract = Web3JClient.load(privateKey);
        Tuple5<String, String, String, String, String> result = recordContract.getRecord(BigInteger.valueOf(0)).send();
        String sender = result.component1();
        String workTitle = result.component2();
        String authorName = result.component3();
        String createTime1 = result.component4();
        String workHash = result.component5();
        log.info("sender=====>"+sender);
        log.info("workTitle=====>"+workTitle);
        log.info("authorName=====>"+authorName);
        log.info("createTime1=====>"+createTime1);
        log.info("workHash=====>"+workHash);
    }
}
