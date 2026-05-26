package net.osslabz.bitcoin;

import org.bitcoinj.base.BitcoinNetwork;

public enum Network {


    MAIN_NET(BitcoinNetwork.MAINNET),


    REG_NET(BitcoinNetwork.REGTEST),


    TEST_NET(BitcoinNetwork.TESTNET);


    private final BitcoinNetwork bitcoinNetwork;


    Network(BitcoinNetwork bitcoinNetwork) {
        this.bitcoinNetwork = bitcoinNetwork;
    }

    BitcoinNetwork getBitcoinNetwork() {
        return bitcoinNetwork;
    }
}