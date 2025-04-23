package net.osslabz.bitcoin;

import org.bitcoinj.core.NetworkParameters;

public class NetworkParams {

    public static final NetworkParameters NETWORK_PARAMETERS_MAIN_NET = NetworkParameters.fromID(NetworkParameters.ID_MAINNET);

    public static final NetworkParameters NETWORK_PARAMETERS_REG_NET = NetworkParameters.fromID(NetworkParameters.ID_REGTEST);

    public static final NetworkParameters TESTNET_PARAMETERS_TEST_NET = NetworkParameters.fromID(NetworkParameters.ID_TESTNET);


    private NetworkParams() {

    }

}