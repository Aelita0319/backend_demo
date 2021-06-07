package com.example.mybatisdemo.blockchain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Test1 extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610b06806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630aed3ca1146100b457806323c826e0146100f55780632581a790146101365780632587eb4e1461016d57806344649a731461019a57806350d089111461027557806384afaa7c146102e857806388d12eda14610329578063afdac68d14610360578063ee72b1f7146103a1578063f6fb39ed1461042c575b600080fd5b3480156100c057600080fd5b506100df6004803603810190808035906020019092919050505061046d565b6040518082815260200191505060405180910390f35b34801561010157600080fd5b5061013460048036038101908080359060200190929190803590602001909291908035906020019092919050505061048c565b005b34801561014257600080fd5b5061016b60048036038101908080359060200190929190803590602001909291905050506104d7565b005b34801561017957600080fd5b50610198600480360381019080803590602001909291905050506104fe565b005b3480156101a657600080fd5b5061025b60048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610508565b604051808215151515815260200191505060405180910390f35b34801561028157600080fd5b506102e660048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061062b565b005b3480156102f457600080fd5b5061031360048036038101908080359060200190929190505050610659565b6040518082815260200191505060405180910390f35b34801561033557600080fd5b5061035e6004803603810190808035906020019092919080359060200190929190505050610678565b005b34801561036c57600080fd5b5061038b6004803603810190808035906020019092919050505061071c565b6040518082815260200191505060405180910390f35b3480156103ad57600080fd5b5061041260048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061073b565b604051808215151515815260200191505060405180910390f35b34801561043857600080fd5b5061046b600480360381019080803590602001909291908035906020019092919080359060200190929190505050610906565b005b6000806000838152602001908152602001600020600301549050919050565b80600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b8060018190555050565b6000610512610975565b6000151560008088815260200190815260200160002060060160009054906101000a900460ff161515141561054a5760009150610622565b60e0604051908101604052808781526020018681526020018581526020016000815260200160008152602001848152602001600115158152509050806000808881526020019081526020016000206000820151816000015560208201518160010190805190602001906105be9291906109b5565b5060408201518160020155606082015181600301556080820151816004015560a08201518160050190805190602001906105f99291906109b5565b5060c08201518160060160006101000a81548160ff021916908315150217905550905050600191505b50949350505050565b806000808481526020019081526020016000206005019080519060200190610654929190610a35565b505050565b6000806000838152602001908152602001600020600401549050919050565b80600a60008085815260200190815260200160002060030154600c0281151561069d57fe5b0560008085815260200190815260200160002060020154031315156106c157600080fd5b80600080848152602001908152602001600020600201600082825401925050819055506103e860015482028115156106f557fe5b05600080848152602001908152602001600020600301600082825401925050819055505050565b6000806000838152602001908152602001600020600201549050919050565b6000816040516020018082805190602001908083835b6020831015156107765780518252602082019150602081019050602083039250610751565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156107df57805182526020820191506020810190506020830392506107ba565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008085815260200190815260200160002060050160405160200180828054600181600116156101000203166002900480156108835780601f10610861576101008083540402835291820191610883565b820191906000526020600020905b81548152906001019060200180831161086f575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156108cc57805182526020820191506020810190506020830392506108a7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b80600080848152602001908152602001600020600201541215151561092a57600080fd5b80600080858152602001908152602001600020600201600082825401925050819055508060008084815260200190815260200160002060020160008282540392505081905550505050565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109f657805160ff1916838001178555610a24565b82800160010185558215610a24579182015b82811115610a23578251825591602001919060010190610a08565b5b509050610a319190610ab5565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a7657805160ff1916838001178555610aa4565b82800160010185558215610aa4579182015b82811115610aa3578251825591602001919060010190610a88565b5b509050610ab19190610ab5565b5090565b610ad791905b80821115610ad3576000816000905550600101610abb565b5090565b905600a165627a7a72305820291aec5cbbeeb3a927409c24bd94fb98c0da3b7de725a2e73edff21ec99841fc0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610b06806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632d3831e5146100b45780633fdc0a7c146100e1578063412c523d1461016c578063460ff308146101a35780634f145a5e146101e45780635ff076811461022557806387df8697146102985780638a524f01146102cf578063ad79d3aa14610310578063b022920a14610351578063bd00808814610392575b600080fd5b3480156100c057600080fd5b506100df6004803603810190808035906020019092919050505061046d565b005b3480156100ed57600080fd5b5061015260048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610477565b604051808215151515815260200191505060405180910390f35b34801561017857600080fd5b506101a16004803603810190808035906020019092919080359060200190929190505050610642565b005b3480156101af57600080fd5b506101e2600480360381019080803590602001909291908035906020019092919080359060200190929190505050610669565b005b3480156101f057600080fd5b5061020f600480360381019080803590602001909291905050506106d8565b6040518082815260200191505060405180910390f35b34801561023157600080fd5b5061029660048036038101908080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106f7565b005b3480156102a457600080fd5b506102cd6004803603810190808035906020019092919080359060200190929190505050610725565b005b3480156102db57600080fd5b506102fa600480360381019080803590602001909291905050506107c9565b6040518082815260200191505060405180910390f35b34801561031c57600080fd5b5061033b600480360381019080803590602001909291905050506107e8565b6040518082815260200191505060405180910390f35b34801561035d57600080fd5b50610390600480360381019080803590602001909291908035906020019092919080359060200190929190505050610807565b005b34801561039e57600080fd5b5061045360048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610852565b604051808215151515815260200191505060405180910390f35b8060018190555050565b6000816040516020018082805190602001908083835b6020831015156104b2578051825260208201915060208101905060208303925061048d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561051b57805182526020820191506020810190506020830392506104f6565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008085815260200190815260200160002060050160405160200180828054600181600116156101000203166002900480156105bf5780601f1061059d5761010080835404028352918201916105bf565b820191906000526020600020905b8154815290600101906020018083116105ab575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561060857805182526020820191506020810190506020830392506105e3565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b80600080848152602001908152602001600020600201541215151561068d57600080fd5b80600080858152602001908152602001600020600201600082825401925050819055508060008084815260200190815260200160002060020160008282540392505081905550505050565b6000806000838152602001908152602001600020600401549050919050565b806000808481526020019081526020016000206005019080519060200190610720929190610975565b505050565b80600a60008085815260200190815260200160002060030154600c0281151561074a57fe5b05600080858152602001908152602001600020600201540313151561076e57600080fd5b80600080848152602001908152602001600020600201600082825401925050819055506103e860015482028115156107a257fe5b05600080848152602001908152602001600020600301600082825401925050819055505050565b6000806000838152602001908152602001600020600201549050919050565b6000806000838152602001908152602001600020600301549050919050565b80600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b600061085c6109f5565b6000151560008088815260200190815260200160002060060160009054906101000a900460ff1615151415610894576000915061096c565b60e060405190810160405280878152602001868152602001858152602001600081526020016000815260200184815260200160011515815250905080600080888152602001908152602001600020600082015181600001556020820151816001019080519060200190610908929190610a35565b5060408201518160020155606082015181600301556080820151816004015560a0820151816005019080519060200190610943929190610a35565b5060c08201518160060160006101000a81548160ff021916908315150217905550905050600191505b50949350505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109b657805160ff19168380011785556109e4565b828001600101855582156109e4579182015b828111156109e35782518255916020019190600101906109c8565b5b5090506109f19190610ab5565b5090565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a7657805160ff1916838001178555610aa4565b82800160010185558215610aa4579182015b82811115610aa3578251825591602001919060010190610a88565b5b509050610ab19190610ab5565b5090565b610ad791905b80821115610ad3576000816000905550600101610abb565b5090565b905600a165627a7a72305820d0c45cedc33ef213eaaf3e00630e8ff63f3145e0099aa3e6259053e1f8223e110029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getLoans\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"receipt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"addBalance\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"ra\",\"type\":\"int256\"}],\"name\":\"setRate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"int256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"signIn\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"setPassword\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getReceiptAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"loans\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"query\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"logIn\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"buy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

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

    public Boolean signIn(BigInteger id, String name, BigInteger balance, String password) throws ContractException {
        final Function function = new Function(FUNC_SIGNIN, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(name),
                new Int256(balance),
                new Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
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
