package net.osslabz.bitcoin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HexFormat;
import org.bitcoinj.base.Address;
import org.bitcoinj.base.AddressParser;
import org.bitcoinj.base.BitcoinNetwork;
import org.bitcoinj.base.exceptions.AddressFormatException;
import org.junit.jupiter.api.Test;

class BitcoinUtilsTest {

    // Every address below encodes the same HASH160, so the P2PKH ones share one script hash and
    // the P2WPKH ones share another; the expected values were computed outside bitcoinj.
    private static final String P2PKH_SCRIPT_HASH = "8b01df4e368ea28f8dc0423bcf7a4923e3a12d307c875e47a0cfbf90b5c39161";

    private static final String P2WPKH_SCRIPT_HASH = "45dc3792fc06ee8c3b3e27ae390747f663ef0ac933aa29ade0ed0a390186bdfc";

    @Test
    void testScriptHashForLegacyP2PKH() {
        // examples taken from https://electrumx.readthedocs.io/en/latest/protocol-basics.html#script-hashes
        String reversedScriptHash =
                BitcoinUtils.convertAddressToReversedScriptHash(Network.MAIN_NET, "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        assertEquals("8b01df4e368ea28f8dc0423bcf7a4923e3a12d307c875e47a0cfbf90b5c39161", reversedScriptHash);
    }

    @Test
    void scriptHashOfMainNetSegwitAddressHashesItsWitnessProgram() {
        assertEquals(
                P2WPKH_SCRIPT_HASH,
                BitcoinUtils.convertAddressToReversedScriptHash(
                        Network.MAIN_NET, "bc1qvt5s0v2uhuna2sjnn84ldu8m2r4m3rcc4048ry"));
    }

    @Test
    void scriptHashOfTestNetAddressMatchesTheMainNetOneForTheSameKey() {
        assertEquals(
                P2PKH_SCRIPT_HASH,
                BitcoinUtils.convertAddressToReversedScriptHash(
                        Network.TEST_NET, "mpXwg4jMtRhuSpVq4xS3HFHmCmWp9NyGKt"));
    }

    @Test
    void scriptHashOfRegTestAddressMatchesTheMainNetOneForTheSameKey() {
        assertEquals(
                P2WPKH_SCRIPT_HASH,
                BitcoinUtils.convertAddressToReversedScriptHash(
                        Network.REG_NET, "bcrt1qvt5s0v2uhuna2sjnn84ldu8m2r4m3rccaqhe07"));
    }

    @Test
    void nullAddressStringIsRejectedWithAMessage() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> BitcoinUtils.convertAddressToReversedScriptHash(Network.MAIN_NET, (String) null));
        assertEquals("Address must not be null.", exception.getMessage());
    }

    @Test
    void malformedAddressStringIsRejected() {
        assertThrows(
                AddressFormatException.class,
                () -> BitcoinUtils.convertAddressToReversedScriptHash(Network.MAIN_NET, "not-an-address"));
    }

    @Test
    void addressOfAnotherNetworkIsRejected() {
        assertThrows(
                AddressFormatException.WrongNetwork.class,
                () -> BitcoinUtils.convertAddressToReversedScriptHash(
                        Network.MAIN_NET, "mpXwg4jMtRhuSpVq4xS3HFHmCmWp9NyGKt"));
    }

    @Test
    void scriptHashOfParsedAddressEqualsTheOneOfItsString() {
        Address address =
                AddressParser.getDefault(BitcoinNetwork.MAINNET).parseAddress("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");

        assertEquals(P2PKH_SCRIPT_HASH, BitcoinUtils.convertAddressToReversedScriptHash(address));
    }

    @Test
    void nullParsedAddressIsRejected() {
        assertThrows(
                IllegalStateException.class, () -> BitcoinUtils.convertAddressToReversedScriptHash((Address) null));
    }

    @Test
    void outputScriptOfP2PKHAddressIsPayToPubKeyHash() {
        Address address =
                AddressParser.getDefault(BitcoinNetwork.MAINNET).parseAddress("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");

        assertEquals(
                "76a91462e907b15cbf27d5425399ebf6f0fb50ebb88f1888ac",
                HexFormat.of().formatHex(BitcoinUtils.toOutputScript(address).program()));
    }

    @Test
    void outputScriptOfNullAddressIsRejected() {
        assertThrows(IllegalStateException.class, () -> BitcoinUtils.toOutputScript(null));
    }

    @Test
    void reverseBytesReturnsTheBytesInReverseOrder() {
        assertArrayEquals(new byte[] {3, 2, 1}, BitcoinUtils.reverseBytes(new byte[] {1, 2, 3}));
    }

    @Test
    void reverseBytesLeavesTheInputUntouched() {
        byte[] input = {1, 2, 3};

        BitcoinUtils.reverseBytes(input);

        assertArrayEquals(new byte[] {1, 2, 3}, input);
    }

    @Test
    void reverseBytesOfEmptyArrayIsEmpty() {
        assertArrayEquals(new byte[0], BitcoinUtils.reverseBytes(new byte[0]));
    }

    @Test
    void reverseBytesOfNullIsNull() {
        assertNull(BitcoinUtils.reverseBytes(null));
    }
}
