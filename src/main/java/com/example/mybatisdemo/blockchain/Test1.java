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
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
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
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061108a806100206000396000f3006080604052600436106100d0576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630aed3ca1146100d557806323c826e0146101165780632581a790146101575780632587eb4e1461018e57806330643449146101bb57806344649a731461027657806350d089111461035157806384afaa7c146103c457806396d7da5014610405578063afdac68d14610430578063b32f765614610471578063c9df8133146104b2578063ee72b1f7146104f3578063f6fb39ed1461057e575b600080fd5b3480156100e157600080fd5b50610100600480360381019080803590602001909291905050506105bf565b6040518082815260200191505060405180910390f35b34801561012257600080fd5b506101556004803603810190808035906020019092919080359060200190929190803590602001909291905050506105de565b005b34801561016357600080fd5b5061018c60048036038101908080359060200190929190803590602001909291905050506106e1565b005b34801561019a57600080fd5b506101b960048036038101908080359060200190929190505050610708565b005b3480156101c757600080fd5b506101e660048036038101908080359060200190929190505050610712565b6040518080602001858152602001848152602001838152602001828103825286818151815260200191508051906020019080838360005b8381101561023857808201518184015260208101905061021d565b50505050905090810190601f1680156102655780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561028257600080fd5b5061033760048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061081f565b604051808215151515815260200191505060405180910390f35b34801561035d57600080fd5b506103c260048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610942565b005b3480156103d057600080fd5b506103ef60048036038101908080359060200190929190505050610970565b6040518082815260200191505060405180910390f35b34801561041157600080fd5b5061041a61098f565b6040518082815260200191505060405180910390f35b34801561043c57600080fd5b5061045b60048036038101908080359060200190929190505050610999565b6040518082815260200191505060405180910390f35b34801561047d57600080fd5b506104b06004803603810190808035906020019092919080359060200190929190803590602001909291905050506109b8565b005b3480156104be57600080fd5b506104f1600480360381019080803590602001909291908035906020019092919080359060200190929190505050610b04565b005b3480156104ff57600080fd5b5061056460048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610c07565b604051808215151515815260200191505060405180910390f35b34801561058a57600080fd5b506105bd600480360381019080803590602001909291908035906020019092919080359060200190929190505050610dd2565b005b6000806000838152602001908152602001600020600301549050919050565b6080604051908101604052806040805190810160405280600781526020017f726563656970740000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610674929190610ef9565b5060208201518160010155604082015181600201556060820151816003015590505080600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b8060028190555050565b6060600080600060016000868152602001908152602001600020600001600160008781526020019081526020016000206001015460016000888152602001908152602001600020600201546001600089815260200190815260200160002060030154838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108095780601f106107de57610100808354040283529160200191610809565b820191906000526020600020905b8154815290600101906020018083116107ec57829003601f168201915b5050505050935093509350935093509193509193565b6000610829610f79565b6000151560008088815260200190815260200160002060060160009054906101000a900460ff16151514156108615760009150610939565b60e0604051908101604052808781526020018681526020018581526020016000815260200160008152602001848152602001600115158152509050806000808881526020019081526020016000206000820151816000015560208201518160010190805190602001906108d5929190610ef9565b5060408201518160020155606082015181600301556080820151816004015560a0820151816005019080519060200190610910929190610ef9565b5060c08201518160060160006101000a81548160ff021916908315150217905550905050600191505b50949350505050565b80600080848152602001908152602001600020600501908051906020019061096b929190610fb9565b505050565b6000806000838152602001908152602001600020600401549050919050565b6000600354905090565b6000806000838152602001908152602001600020600201549050919050565b80600a60008086815260200190815260200160002060030154600c028115156109dd57fe5b056000808681526020019081526020016000206002015403131515610a0157600080fd5b806000808581526020019081526020016000206002016000828254019250508190555080600080858152602001908152602001600020600301600082825401925050819055506080604051908101604052806040805190810160405280600481526020017f6c6f616e0000000000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610add929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b806000808581526020019081526020016000206002016000828254039250508190555080600080858152602001908152602001600020600301600082825403925050819055506080604051908101604052806040805190810160405280600581526020017f726570617900000000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610be0929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b6000816040516020018082805190602001908083835b602083101515610c425780518252602082019150602081019050602083039250610c1d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610cab5780518252602082019150602081019050602083039250610c86565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000808581526020019081526020016000206005016040516020018082805460018160011615610100020316600290048015610d4f5780601f10610d2d576101008083540402835291820191610d4f565b820191906000526020600020905b815481529060010190602001808311610d3b575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515610d985780518252602082019150602081019050602083039250610d73565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b806000808481526020019081526020016000206002015412151515610df657600080fd5b806000808581526020019081526020016000206002016000828254019250508190555080600080848152602001908152602001600020600201600082825403925050819055506080604051908101604052806040805190810160405280600381526020017f627579000000000000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610ed2929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f3a57805160ff1916838001178555610f68565b82800160010185558215610f68579182015b82811115610f67578251825591602001919060010190610f4c565b5b509050610f759190611039565b5090565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481","019282601f10610ffa57805160ff1916838001178555611028565b82800160010185558215611028579182015b8281111561102757825182559160200191906001019061100c565b5b5090506110359190611039565b5090565b61105b91905b8082111561105757600081600090555060010161103f565b5090565b905600a165627a7a7230582008759a179e6ad97d173035e28d5732de2701f29ca1acfa490af811d51025ca9c0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061108a806100206000396000f3006080604052600436106100d0576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632d3831e5146100d55780633fc8e73a146101025780633fdc0a7c146101bd578063412c523d14610248578063460ff3081461027f5780634f145a5e146102c05780635ff07681146103015780637322ef18146103745780638a524f01146103b5578063ad79d3aa146103f6578063b022920a14610437578063bd00808814610478578063f870b5d614610553578063fbb2e1aa1461057e575b600080fd5b3480156100e157600080fd5b50610100600480360381019080803590602001909291905050506105bf565b005b34801561010e57600080fd5b5061012d600480360381019080803590602001909291905050506105c9565b6040518080602001858152602001848152602001838152602001828103825286818151815260200191508051906020019080838360005b8381101561017f578082015181840152602081019050610164565b50505050905090810190601f1680156101ac5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3480156101c957600080fd5b5061022e60048036038101908080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106d6565b604051808215151515815260200191505060405180910390f35b34801561025457600080fd5b5061027d60048036038101908080359060200190929190803590602001909291905050506108a1565b005b34801561028b57600080fd5b506102be6004803603810190808035906020019092919080359060200190929190803590602001909291905050506108c8565b005b3480156102cc57600080fd5b506102eb600480360381019080803590602001909291905050506109ef565b6040518082815260200191505060405180910390f35b34801561030d57600080fd5b5061037260048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a0e565b005b34801561038057600080fd5b506103b3600480360381019080803590602001909291908035906020019092919080359060200190929190505050610a3c565b005b3480156103c157600080fd5b506103e060048036038101908080359060200190929190505050610b3f565b6040518082815260200191505060405180910390f35b34801561040257600080fd5b5061042160048036038101908080359060200190929190505050610b5e565b6040518082815260200191505060405180910390f35b34801561044357600080fd5b50610476600480360381019080803590602001909291908035906020019092919080359060200190929190505050610b7d565b005b34801561048457600080fd5b5061053960048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610c80565b604051808215151515815260200191505060405180910390f35b34801561055f57600080fd5b50610568610da3565b6040518082815260200191505060405180910390f35b34801561058a57600080fd5b506105bd600480360381019080803590602001909291908035906020019092919080359060200190929190505050610dad565b005b8060028190555050565b6060600080600060016000868152602001908152602001600020600001600160008781526020019081526020016000206001015460016000888152602001908152602001600020600201546001600089815260200190815260200160002060030154838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106c05780601f10610695576101008083540402835291602001916106c0565b820191906000526020600020905b8154815290600101906020018083116106a357829003601f168201915b5050505050935093509350935093509193509193565b6000816040516020018082805190602001908083835b60208310151561071157805182526020820191506020810190506020830392506106ec565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561077a5780518252602082019150602081019050602083039250610755565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600080858152602001908152602001600020600501604051602001808280546001816001161561010002031660029004801561081e5780601f106107fc57610100808354040283529182019161081e565b820191906000526020600020905b81548152906001019060200180831161080a575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156108675780518252602082019150602081019050602083039250610842565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614905092915050565b80600080848152602001908152602001600020600201600082825401925050819055505050565b8060008084815260200190815260200160002060020154121515156108ec57600080fd5b806000808581526020019081526020016000206002016000828254019250508190555080600080848152602001908152602001600020600201600082825403925050819055506080604051908101604052806040805190810160405280600381526020017f6275790000000000000000000000000000000000000000000000000000000000815250815260200184815260200183815260200182815250600160006003600081548092919060010191905055815260200190815260200160002060008201518160000190805190602001906109c8929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b6000806000838152602001908152602001600020600401549050919050565b806000808481526020019081526020016000206005019080519060200190610a37929190610f79565b505050565b806000808581526020019081526020016000206002016000828254039250508190555080600080858152602001908152602001600020600301600082825403925050819055506080604051908101604052806040805190810160405280600581526020017f726570617900000000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610b18929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b6000806000838152602001908152602001600020600201549050919050565b6000806000838152602001908152602001600020600301549050919050565b6080604051908101604052806040805190810160405280600781526020017f726563656970740000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610c13929190610ef9565b5060208201518160010155604082015181600201556060820151816003015590505080600080858152602001908152602001600020600401600082825401925050819055508060008085815260200190815260200160002060040160008282540392505081905550505050565b6000610c8a610ff9565b6000151560008088815260200190815260200160002060060160009054906101000a900460ff1615151415610cc25760009150610d9a565b60e060405190810160405280878152602001868152602001858152602001600081526020016000815260200184815260200160011515815250905080600080888152602001908152602001600020600082015181600001556020820151816001019080519060200190610d36929190610ef9565b5060408201518160020155606082015181600301556080820151816004015560a0820151816005019080519060200190610d71929190610ef9565b5060c08201518160060160006101000a81548160ff021916908315150217905550905050600191505b50949350505050565b6000600354905090565b80600a60008086815260200190815260200160002060030154600c02811515610dd257fe5b056000808681526020019081526020016000206002015403131515610df657600080fd5b806000808581526020019081526020016000206002016000828254019250508190555080600080858152602001908152602001600020600301600082825401925050819055506080604051908101604052806040805190810160405280600481526020017f6c6f616e0000000000000000000000000000000000000000000000000000000081525081526020018481526020018381526020018281525060016000600360008154809291906001019190505581526020019081526020016000206000820151816000019080519060200190610ed2929190610ef9565b50602082015181600101556040820151816002015560608201518160030155905050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f3a57805160ff1916838001178555610f68565b82800160010185558215610f68579182015b82811115610f67578251825591602001919060010190610f4c565b5b509050610f759190611039565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610fba57805160ff1916838001178555610fe8565b82800160010185558215610fe8579182015b82811115610fe7578251825591602001919060","010190610fcc565b5b509050610ff59190611039565b5090565b60e0604051908101604052806000815260200160608152602001600081526020016000815260200160008152602001606081526020016000151581525090565b61105b91905b8082111561105757600081600090555060010161103f565b5090565b905600a165627a7a723058205061b1ef63c9a65dc3926ac7821e428ef806489007afe6ed4a637a5b55c2001c0029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getLoans\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"receipt\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"addBalance\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"ra\",\"type\":\"int256\"}],\"name\":\"setRate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getDeals\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"balance\",\"type\":\"int256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"signIn\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"setPassword\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getReceiptAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getNumberOfDeals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"query\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"bankId\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"loans\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"companyId\",\"type\":\"uint256\"},{\"name\":\"bankId\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"repay\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"password\",\"type\":\"string\"}],\"name\":\"logIn\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id1\",\"type\":\"uint256\"},{\"name\":\"id2\",\"type\":\"uint256\"},{\"name\":\"amount\",\"type\":\"int256\"}],\"name\":\"buy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final String FUNC_GETLOANS = "getLoans";

    public static final String FUNC_RECEIPT = "receipt";

    public static final String FUNC_ADDBALANCE = "addBalance";

    public static final String FUNC_SETRATE = "setRate";

    public static final String FUNC_GETDEALS = "getDeals";

    public static final String FUNC_SIGNIN = "signIn";

    public static final String FUNC_SETPASSWORD = "setPassword";

    public static final String FUNC_GETRECEIPTAMOUNT = "getReceiptAmount";

    public static final String FUNC_GETNUMBEROFDEALS = "getNumberOfDeals";

    public static final String FUNC_QUERY = "query";

    public static final String FUNC_LOANS = "loans";

    public static final String FUNC_REPAY = "repay";

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
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt receipt(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECEIPT,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void receipt(BigInteger id1, BigInteger id2, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RECEIPT,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForReceipt(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_RECEIPT,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
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
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void addBalance(BigInteger id, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDBALANCE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddBalance(BigInteger id, BigInteger amount) {
        final Function function = new Function(
                FUNC_ADDBALANCE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
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
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(ra)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setRate(BigInteger ra, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETRATE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(ra)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetRate(BigInteger ra) {
        final Function function = new Function(
                FUNC_SETRATE,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(ra)),
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

    public Tuple4<String, BigInteger, BigInteger, BigInteger> getDeals(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_GETDEALS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                (String) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue());
    }

    public Boolean signIn(BigInteger id, String name, BigInteger balance, String password) throws ContractException {
        final Function function = new Function(FUNC_SIGNIN,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(balance),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt setPassword(BigInteger id, String password) {
        final Function function = new Function(
                FUNC_SETPASSWORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setPassword(BigInteger id, String password, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETPASSWORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(password)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetPassword(BigInteger id, String password) {
        final Function function = new Function(
                FUNC_SETPASSWORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(password)),
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
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger getNumberOfDeals() throws ContractException {
        final Function function = new Function(FUNC_GETNUMBEROFDEALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger query(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_QUERY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt loans(BigInteger id, BigInteger bankId, BigInteger amount) {
        final Function function = new Function(
                FUNC_LOANS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void loans(BigInteger id, BigInteger bankId, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_LOANS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForLoans(BigInteger id, BigInteger bankId, BigInteger amount) {
        final Function function = new Function(
                FUNC_LOANS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getLoansInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LOANS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue()
        );
    }

    public TransactionReceipt repay(BigInteger companyId, BigInteger bankId, BigInteger amount) {
        final Function function = new Function(
                FUNC_REPAY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void repay(BigInteger companyId, BigInteger bankId, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REPAY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRepay(BigInteger companyId, BigInteger bankId, BigInteger amount) {
        final Function function = new Function(
                FUNC_REPAY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(bankId),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getRepayInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REPAY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue()
        );
    }

    public Boolean logIn(BigInteger id, String password) throws ContractException {
        final Function function = new Function(FUNC_LOGIN,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id),
                        new org.fisco.bcos.sdk.abi.datatypes.Utf8String(password)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt buy(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void buy(BigInteger id1, BigInteger id2, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForBuy(BigInteger id1, BigInteger id2, BigInteger amount) {
        final Function function = new Function(
                FUNC_BUY,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id1),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id2),
                        new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(amount)),
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
