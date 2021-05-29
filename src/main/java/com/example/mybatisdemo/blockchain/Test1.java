package com.example.mybatisdemo.blockchain;

import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class Test1 extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610ae2806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630aed3ca1146100b457806323c826e0146100f55780632581a790146101365780632587eb4e1461016d57806344649a731461019a57806350d089111461025d57806384afaa7c146102d057806388d12eda14610311578063afdac68d14610348578063ee72b1f714610389578063f6fb39ed14610414575b600080fd5b3480156100c057600080fd5b506100df60048036038101908080359060200190929190505050610455565b6040518082815260200191505060405180910390f35b34801561010157600080fd5b50610134600480360381019080803590602001909291908035906020019092919080359060200190929190505050610474565b005b34801561014257600080fd5b5061016b60048036038101908080359060200190929190803590602001909291905050506104bf565b005b34801561017957600080fd5b50610198600480360381019080803590602001909291905050506104e6565b005b3480156101a657600080fd5b5061025b60048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104f0565b005b34801561026957600080fd5b506102ce60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610607565b005b3480156102dc57600080fd5b506102fb60048036038101908080359060200190929190505050610635565b6040518082815260200191505060405180910390f35b34801561031d57600080fd5b506103466004803603810190808035906020019092919080359060200190929190505050610654565b005b34801561035457600080fd5b50610373600480360381019080803590602001909291905050506106f8565b6040518082815260200191505060405180910390f35b34801561039557600080fd5b506103fa60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610717565b604051808215151515815260200191505060405180910390f35b34801561042057600080fd5b506104536004803603810190808035906020019092919080359060200190929190803590602001909291905050506108e2565b005b6000806000838152602001908152602001600020600301549050919050565b80600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b8060018190555050565b6104f8610951565b6000151560008087815260200190815260200160002060060160009054906101000a900460ff16151514151561052d57600080fd5b60e0604051908101604052808681526020018581526020018481526020016000815260200160008152602001838152602001600115158152509050806000808781526020019081526020016000206000820151816000015560208201518160010190805190602001906105a1929190610991565b5060408201518160020155606082015181600301556080820151816004015560a08201518160050190805190602001906105dc929190610991565b5060c08201518160060160006101000a81548160ff0219169083151502179055509050505050505050565b806000808481526020019081526020016000206005019080519060200190610630929190610a11565b505050565b6000806000838152602001908152602001600020600401549050919050565b80600a60008085815260200190815260200160002060030154600c0281151561067957fe5b05600080858152602001908152602001600020600201540313151561069d57600080fd5b80600080848152602001908152602001600020600201600082825401925050819055506103e860015482028115156106d157fe5b05600080848152602001908152602001600020600301600082825401925050819055505050565b6000806000838152602001908152602001600020600201549050919050565b6000816040516020018082805190602001908083835b602083101515610752578051825260208201915060208101905060208303925061072d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156107bb5780518252602082019150602081019050602083039250610796565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600080858152602001908152602001600020600501604051602001808280546001816001161561010002031660029004801561085f5780601f1061083d57610100808354040283529182019161085f565b820191906000526020600020905b81548152906001019060200180831161084b575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156108a85780518252602082019150602081019050602083039250610883565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b80600080848152602001908152602001600020600201541215151561090657600080fd5b80600080858152602001908152602001600020600201600082825401925050819055508060008084815260200190815260200160002060020160008282540392505081905550505050565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109d257805160ff1916838001178555610a00565b82800160010185558215610a00579182015b828111156109ff5782518255916020019190600101906109e4565b5b509050610a0d9190610a91565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a5257805160ff1916838001178555610a80565b82800160010185558215610a80579182015b82811115610a7f578251825591602001919060010190610a64565b5b509050610a8d9190610a91565b5090565b610ab391905b80821115610aaf576000816000905550600101610a97565b5090565b905600a165627a7a723058207d091c1a2904513ba48fedd749ccbabab4bc1d2a9b2939ec84936b54d330b9650029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610ae2806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632d3831e5146100b45780633fdc0a7c146100e1578063412c523d1461016c578063460ff308146101a35780634f145a5e146101e45780635ff076811461022557806387df8697146102985780638a524f01146102cf578063ad79d3aa14610310578063b022920a14610351578063bd00808814610392575b600080fd5b3480156100c057600080fd5b506100df60048036038101908080359060200190929190505050610455565b005b3480156100ed57600080fd5b5061015260048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061045f565b604051808215151515815260200191505060405180910390f35b34801561017857600080fd5b506101a1600480360381019080803590602001909291908035906020019092919050505061062a565b005b3480156101af57600080fd5b506101e2600480360381019080803590602001909291908035906020019092919080359060200190929190505050610651565b005b3480156101f057600080fd5b5061020f600480360381019080803590602001909291905050506106c0565b6040518082815260200191505060405180910390f35b34801561023157600080fd5b5061029660048036038101908080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106df565b005b3480156102a457600080fd5b506102cd600480360381019080803590602001909291908035906020019092919050505061070d565b005b3480156102db57600080fd5b506102fa600480360381019080803590602001909291905050506107b1565b6040518082815260200191505060405180910390f35b34801561031c57600080fd5b5061033b600480360381019080803590602001909291905050506107d0565b6040518082815260200191505060405180910390f35b34801561035d57600080fd5b506103906004803603810190808035906020019092919080359060200190929190803590602001909291905050506107ef565b005b34801561039e57600080fd5b5061045360048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061083a565b005b8060018190555050565b6000816040516020018082805190602001908083835b60208310151561049a5780518252602082019150602081019050602083039250610475565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561050357805182526020820191506020810190506020830392506104de565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008085815260200190815260200160002060050160405160200180828054600181600116156101000203166002900480156105a75780601f106105855761010080835404028352918201916105a7565b820191906000526020600020905b815481529060010190602001808311610593575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156105f057805182526020820191506020810190506020830392506105cb565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b80600080848152602001908152602001600020600201541215151561067557600080fd5b80600080858152602001908152602001600020600201600082825401925050819055508060008084815260200190815260200160002060020160008282540392505081905550505050565b6000806000838152602001908152602001600020600401549050919050565b806000808481526020019081526020016000206005019080519060200190610708929190610951565b505050565b80600a60008085815260200190815260200160002060030154600c0281151561073257fe5b05600080858152602001908152602001600020600201540313151561075657600080fd5b80600080848152602001908152602001600020600201600082825401925050819055506103e8600154820281151561078a57fe5b05600080848152602001908152602001600020600301600082825401925050819055505050565b6000806000838152602001908152602001600020600201549050919050565b6000806000838152602001908152602001600020600301549050919050565b80600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b6108426109d1565b6000151560008087815260200190815260200160002060060160009054906101000a900460ff16151514151561087757600080fd5b60e0604051908101604052808681526020018581526020018481526020016000815260200160008152602001838152602001600115158152509050806000808781526020019081526020016000206000820151816000015560208201518160010190805190602001906108eb929190610a11565b5060408201518160020155606082015181600301556080820151816004015560a0820151816005019080519060200190610926929190610a11565b5060c08201518160060160006101000a81548160ff0219169083151502179055509050505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061099257805160ff19168380011785556109c0565b828001600101855582156109c0579182015b828111156109bf5782518255916020019190600101906109a4565b5b5090506109cd9190610a91565b5090565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a5257805160ff1916838001178555610a80565b82800160010185558215610a80579182015b82811115610a7f578251825591602001919060010190610a64565b5b509050610a8d9190610a91565b5090565b610ab391905b80821115610aaf576000816000905550600101610a97565b5090565b905600a165627a7a723058202812c7dd43034b2270f352d5ab359290a4d13bbdb6d828f5c6b1fae5b8c1aacc0029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getLoans\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"receipt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"addBalance\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"ra\",\"type\":\"int256\"}],\"name\":\"setRate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"int256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"signIn\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"setPassword\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getReceiptAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"loans\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"query\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"logIn\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"buy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final String FUNC_GETLOANS = "getLoans";

    public static final String FUNC_RECEIPT = "receipt";

    public static final String FUNC_ADDBALANCE = "addBalance";

    public static final String FUNC_SETRATE = "setRate";

    public static final String FUNC_SIGNIN = "signIn";

    public static final String FUNC_SETPASSWORD = "setPassword";

    public static final String FUNC_GETRECEIPTAMOUNT = "getReceiptAmount";

    public static final String FUNC_LOANS = "loans";

    public static final String FUNC_QUERY = "query";

    public static final String FUNC_LOGIN = "logIn";

    public static final String FUNC_BUY = "buy";

    protected Test1(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public BigInteger getLoans(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_GETLOANS, 
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt receipt(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECEIPT, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void receipt(BigInteger id1, BigInteger id2, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RECEIPT, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForReceipt(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECEIPT, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getReceiptInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_RECEIPT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public TransactionReceipt addBalance(BigInteger id, BigInteger amount) {
        final Function function = new Function(
                FUNC_ADDBALANCE, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void addBalance(BigInteger id, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDBALANCE, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddBalance(BigInteger id, BigInteger amount) {
        final Function function = new Function(
                FUNC_ADDBALANCE, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, BigInteger> getAddBalanceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public TransactionReceipt setRate(BigInteger ra) {
        final Function function = new Function(
                FUNC_SETRATE, 
                Arrays.<Type>asList(new Int256(ra)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setRate(BigInteger ra, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETRATE, 
                Arrays.<Type>asList(new Int256(ra)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetRate(BigInteger ra) {
        final Function function = new Function(
                FUNC_SETRATE, 
                Arrays.<Type>asList(new Int256(ra)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getSetRateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt signIn(BigInteger id, String name, BigInteger balance, String password) {
        final Function function = new Function(
                FUNC_SIGNIN, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(name),
                new Int256(balance),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void signIn(BigInteger id, String name, BigInteger balance, String password, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SIGNIN, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(name),
                new Int256(balance),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSignIn(BigInteger id, String name, BigInteger balance, String password) {
        final Function function = new Function(
                FUNC_SIGNIN, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(name),
                new Int256(balance),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple4<BigInteger, String, BigInteger, String> getSignInInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SIGNIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple4<BigInteger, String, BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public TransactionReceipt setPassword(BigInteger id, String password) {
        final Function function = new Function(
                FUNC_SETPASSWORD, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setPassword(BigInteger id, String password, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETPASSWORD, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetPassword(BigInteger id, String password) {
        final Function function = new Function(
                FUNC_SETPASSWORD, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, String> getSetPasswordInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETPASSWORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public BigInteger getReceiptAmount(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_GETRECEIPTAMOUNT, 
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt loans(BigInteger id, BigInteger amount) {
        final Function function = new Function(
                FUNC_LOANS, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void loans(BigInteger id, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_LOANS, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForLoans(BigInteger id, BigInteger amount) {
        final Function function = new Function(
                FUNC_LOANS, 
                Arrays.<Type>asList(new Uint256(id),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, BigInteger> getLoansInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LOANS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public BigInteger query(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_QUERY, 
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Boolean logIn(BigInteger id, String password) throws ContractException {
        final Function function = new Function(FUNC_LOGIN, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt buy(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_BUY, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void buy(BigInteger id1, BigInteger id2, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_BUY, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForBuy(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_BUY, 
                Arrays.<Type>asList(new Uint256(id1),
                new Uint256(id2),
                new Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getBuyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_BUY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public static Test1 load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Test1(contractAddress, client, credential);
    }

    public static Test1 deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Test1.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
