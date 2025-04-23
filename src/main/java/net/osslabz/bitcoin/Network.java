package net.osslabz.bitcoin;

import java.util.Arrays;
import java.util.Objects;
import org.bitcoinj.core.NetworkParameters;

public enum Network {


    MAIN_NET(NetworkParams.NETWORK_PARAMETERS_MAIN_NET),


    REG_NET(NetworkParams.NETWORK_PARAMETERS_REG_NET),


    TEST_NET(NetworkParams.TESTNET_PARAMETERS_TEST_NET);


    private final NetworkParameters networkParameters;


    Network(NetworkParameters networkParameters) {
        this.networkParameters = networkParameters;
    }


    public NetworkParameters getNetworkParameters() {
        return networkParameters;
    }


    public static Network fromNetworkParameters(NetworkParameters networkParameters) {
        Objects.requireNonNull(networkParameters, "networkParameters must not be null");
        return Arrays.stream(Network.values()).filter(n -> n.networkParameters.getId().equals(networkParameters.getId())).findAny().orElseThrow();
    }
}