package net.osslabz.bitcoin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bitcoinj.base.BitcoinNetwork;
import org.junit.jupiter.api.Test;


class BitcoinUtilsTest {


    @Test
    void testScriptHashForLegacyP2PKH() {
        // examples taken from https://electrumx.readthedocs.io/en/latest/protocol-basics.html#script-hashes
        String reversedScriptHash = BitcoinUtils.convertAddressToReversedScriptHash(BitcoinNetwork.MAINNET, "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        assertEquals("8b01df4e368ea28f8dc0423bcf7a4923e3a12d307c875e47a0cfbf90b5c39161", reversedScriptHash);
    }
}