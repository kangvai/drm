package com.kangvai.demo.util;

import com.kangvai.demo.generated.RecordContract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Web3JClient {

    private  static String pk;

    private static String contractAddress;

    private static final BigInteger GAS_PRICE = BigInteger.valueOf(1);

    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(800000);

    private static final Web3j WEB_3J = Web3j.build(new HttpService("http://localhost:8545/"));

    public static String getPk() {
        return pk;
    }

    public static void setPk(String pk) {
        Web3JClient.pk = pk;
    }

    public static  String getContractAddress() {
        return contractAddress;
    }

    public static  void setContractAddress(String contractAddress) {
        Web3JClient.contractAddress = contractAddress;
    }

    public static RecordContract deploy(String pk) throws Exception {
        Credentials credentials = Credentials.create(pk);
        RecordContract recordContract = RecordContract.deploy(
                WEB_3J,credentials, GAS_PRICE, GAS_LIMIT
        ).send();  // ether value of contract
        String contractAddress = recordContract.getContractAddress();
        recordContract.setContractAddress(contractAddress);
        return recordContract;
    }

    public static RecordContract load(String pk) {
        Credentials credentials = Credentials.create(pk);
        RecordContract recordContract = RecordContract.load(
                contractAddress,WEB_3J,credentials, GAS_PRICE, GAS_LIMIT
        );

        return recordContract;
    }


    /***
     * 交易
     * @params [readerAddress, readerPrivateKey, AuthorAddress]
     * @return java.lang.String
     */
    public static String sendEtherFromReaderToAuthor (String readerAddress, String readerPrivateKey, String authorAddress)
            throws ExecutionException, InterruptedException {
        String ownAddress =  readerAddress;
        String ownPk = readerPrivateKey;
        String toAddress = authorAddress;
        Credentials credentials = Credentials.create(ownPk);

        //getNonce（这里的Nonce我也不是很明白，大概是交易的笔数吧）
        EthGetTransactionCount ethGetTransactionCount = WEB_3J.ethGetTransactionCount(
                 ownAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        //创建交易，这里是转0.5个以太币
        BigInteger value = Convert.toWei("0.5", Convert.Unit.ETHER).toBigInteger();
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                  nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);

        //签名Transaction，这里要对交易做签名
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        //发送交易
        EthSendTransaction ethSendTransaction =
                WEB_3J.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();
        return transactionHash;
    }
}
