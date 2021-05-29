package com.example.mybatisdemo.blockchain;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import java.util.Scanner;

public class client {
    static Logger logger = LoggerFactory.getLogger(client.class);

    private BcosSDK bcosSDK;
    private Client client;
    private CryptoKeyPair cryptoKeyPair;

    public void initialize() throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bcosSDK = context.getBean(BcosSDK.class);
        client = bcosSDK.getClient(1);
        cryptoKeyPair = client.getCryptoSuite().createKeyPair();
        client.getCryptoSuite().setCryptoKeyPair(cryptoKeyPair);
        logger.debug("create client for group1, account address is " + cryptoKeyPair.getAddress());
    }
    public void deployTest1AndRecordAddr() {

        try {
            Test1 test1 = Test1.deploy(client, cryptoKeyPair);
            System.out.println(
                    " deploy Test1 success, contract address is " + test1.getContractAddress());

            recordTest1Addr(test1.getContractAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy Test1 contract failed, error message is  " + e.getMessage());
        }
    }
    public void recordTest1Addr(String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }
    public static void Usage() {
        System.out.println(" Usage:");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient deploy");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient query account");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient register account value");
        System.out.println(
                "\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient transfer from_account to_account amount");
        System.exit(0);
    }
    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }
    public void setRate(BigInteger ra) throws Exception {
        String contractAddress=loadAssetAddr();
        Test1 test1=Test1.load(contractAddress,client,cryptoKeyPair);
        test1.setRate(ra);
    }
    public void signIn(BigInteger id, String name, BigInteger balance, String password) throws Exception {
        String contractAddress=loadAssetAddr();
        Test1 test1=Test1.load(contractAddress,client,cryptoKeyPair);
        test1.signIn(id,name,balance,password);
    }
    public void logIn(BigInteger id, String password) throws Exception {
        String contractAddress=loadAssetAddr();
        Test1 test1=Test1.load(contractAddress,client,cryptoKeyPair);
        test1.logIn(id,password);
    }
    public BigInteger query(BigInteger id) throws Exception {
        String contractAddress=loadAssetAddr();
        Test1 test1=Test1.load(contractAddress,client,cryptoKeyPair);
        return test1.query(id);
    }
    public static void main(String[] args) throws Exception {

        client client = new client();
        client.initialize();
        String s="deploy";
        Scanner scanner=new Scanner(System.in);
        while (true){
            s=scanner.next();
            boolean b=false;
            switch (s) {
                case "deploy":{
                    client.deployTest1AndRecordAddr();
                    break;}
                case "setRate":{
                    client.setRate(BigInteger.valueOf(1000));
                    break;}
                case "signIn":{
                    client.signIn(BigInteger.valueOf(1), "test",BigInteger.valueOf(10),"test");
                    break;}
                case "logIn":{
                    client.logIn(BigInteger.valueOf(1),"test");
                    break;}
                case "query":{
                    client.query(BigInteger.valueOf(1));
                    break;}
                default:{
                    b=true;
                    break;}
            }
            if (b)
                break;
        }

    }

}
