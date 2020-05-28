package com.kangvai.demo.generated;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.11.
 */
@SuppressWarnings("rawtypes")
public class RecordContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b0c806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806303e9e609146100465780635ca042661461022657806397fd28b314610240575b600080fd5b6100636004803603602081101561005c57600080fd5b5035610473565b60405180866001600160a01b03166001600160a01b0316815260200180602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b838110156100c45781810151838201526020016100ac565b50505050905090810190601f1680156100f15780820380516001836020036101000a031916815260200191505b5085810384528851815288516020918201918a019080838360005b8381101561012457818101518382015260200161010c565b50505050905090810190601f1680156101515780820380516001836020036101000a031916815260200191505b50858103835287518152875160209182019189019080838360005b8381101561018457818101518382015260200161016c565b50505050905090810190601f1680156101b15780820380516001836020036101000a031916815260200191505b50858103825286518152865160209182019188019080838360005b838110156101e45781810151838201526020016101cc565b50505050905090810190601f1680156102115780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b61022e6106f6565b60408051918252519081900360200190f35b61022e6004803603608081101561025657600080fd5b810190602081018135600160201b81111561027057600080fd5b82018360208201111561028257600080fd5b803590602001918460018302840111600160201b831117156102a357600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156102f557600080fd5b82018360208201111561030757600080fd5b803590602001918460018302840111600160201b8311171561032857600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561037a57600080fd5b82018360208201111561038c57600080fd5b803590602001918460018302840111600160201b831117156103ad57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156103ff57600080fd5b82018360208201111561041157600080fd5b803590602001918460018302840111600160201b8311171561043257600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506106fd945050505050565b6000606080606080600080878154811061048957fe5b600091825260209182902060059091020180546001808301805460408051601f60026000199685161561010002969096019093169490940491820187900487028401870190528083526001600160a01b039093169a50929450928301828280156105345780601f1061050957610100808354040283529160200191610534565b820191906000526020600020905b81548152906001019060200180831161051757829003601f168201915b50505060028085018054604080516020601f6000196101006001871615020190941695909504928301859004850281018501909152818152959a5090935091508301828280156105c55780601f1061059a576101008083540402835291602001916105c5565b820191906000526020600020905b8154815290600101906020018083116105a857829003601f168201915b5050505060038301805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529498509192508301828280156106555780601f1061062a57610100808354040283529160200191610655565b820191906000526020600020905b81548152906001019060200180831161063857829003601f168201915b5050505060048301805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529497509192508301828280156106e55780601f106106ba576101008083540402835291602001916106e5565b820191906000526020600020905b8154815290600101906020018083116106c857829003601f168201915b505050505091505091939590929450565b6000545b90565b6000610707610a10565b506040805160a0810182523381526020808201888152928201879052606082018690526080820185905260008054600180820180845583805285517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563600590940293840180546001600160a01b0319166001600160a01b03909216919091178155965180519697949692959194889490936107ca937f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5649092019290910190610a48565b50604082015180516107e6916002840191602090910190610a48565b5060608201518051610802916003840191602090910190610a48565b506080820151805161081e916004840191602090910190610a48565b50505003905081608001516040518082805190602001908083835b602083106108585780518252601f199092019160209182019101610839565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902082600001516001600160a01b03167f545ea107073cf754af92d9a090b8d20ab1afa150460bf512fc89f48572fde64a84602001518560400151866060015160405180806020018060200180602001848103845287818151815260200191508051906020019080838360005b838110156109085781810151838201526020016108f0565b50505050905090810190601f1680156109355780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b83811015610968578181015183820152602001610950565b50505050905090810190601f1680156109955780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156109c85781810151838201526020016109b0565b50505050905090810190601f1680156109f55780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a39695505050505050565b6040518060a0016040528060006001600160a01b03168152602001606081526020016060815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a8957805160ff1916838001178555610ab6565b82800160010185558215610ab6579182015b82811115610ab6578251825591602001919060010190610a9b565b50610ac2929150610ac6565b5090565b6106fa91905b80821115610ac25760008155600101610acc56fea165627a7a7230582091e921364950770eb7fe27e50006fcb0b483350dc8f96ffa0253faa3768252610029";

    public static final String FUNC_GETRECORD = "getRecord";

    public static final String FUNC_GETRECORDLENGTH = "getRecordLength";

    public static final String FUNC_ADDRECORD = "addRecord";

    public static final Event ADDRECORD_EVENT = new Event("AddRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>(true) {}));
    ;

    @Deprecated
    protected RecordContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RecordContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RecordContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RecordContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple5<String, String, String, String, String>> getRecord(BigInteger _index) {
        final Function function = new Function(FUNC_GETRECORD, 
                Arrays.<Type>asList(new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, String, String, String>>(function,
                new Callable<Tuple5<String, String, String, String, String>>() {
                    @Override
                    public Tuple5<String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getRecordLength() {
        final Function function = new Function(FUNC_GETRECORDLENGTH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addRecord(String _title, String _authorName, String _timeStamp, String _workHash) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new Utf8String(_title),
                new Utf8String(_authorName),
                new Utf8String(_timeStamp),
                new Utf8String(_workHash)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<AddRecordEventResponse> getAddRecordEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ADDRECORD_EVENT, transactionReceipt);
        ArrayList<AddRecordEventResponse> responses = new ArrayList<AddRecordEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AddRecordEventResponse typedResponse = new AddRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._workHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._title = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._authorName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._timeStamp = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddRecordEventResponse> addRecordEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AddRecordEventResponse>() {
            @Override
            public AddRecordEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ADDRECORD_EVENT, log);
                AddRecordEventResponse typedResponse = new AddRecordEventResponse();
                typedResponse.log = log;
                typedResponse._sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._workHash = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._title = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._authorName = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._timeStamp = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddRecordEventResponse> addRecordEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDRECORD_EVENT));
        return addRecordEventFlowable(filter);
    }

    @Deprecated
    public static RecordContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RecordContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RecordContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RecordContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RecordContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RecordContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RecordContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RecordContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RecordContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RecordContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RecordContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RecordContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<RecordContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RecordContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RecordContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RecordContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AddRecordEventResponse extends BaseEventResponse {
        public String _sender;

        public byte[] _workHash;

        public String _title;

        public String _authorName;

        public String _timeStamp;
    }
}
