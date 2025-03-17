package net.osslabz.bitcoin;


import java.util.HexFormat;
import java.util.Objects;
import org.bitcoinj.base.Address;
import org.bitcoinj.base.AddressParser;
import org.bitcoinj.base.BitcoinNetwork;
import org.bitcoinj.base.Sha256Hash;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BitcoinUtils {

    private static final Logger log = LoggerFactory.getLogger(BitcoinUtils.class);


    private BitcoinUtils() {
        // intentionally empty
    }


    public static String convertAddressToReversedScriptHash(BitcoinNetwork network, String addressString) {

        Objects.requireNonNull(addressString, "Address must not be null.");
        Address address = AddressParser.getDefault(network).parseAddress(addressString);

        return convertAddressToReversedScriptHash(address);
    }


    public static String convertAddressToReversedScriptHash(Address address) {

        Script outputScript = toOutputScript(address);
        byte[] sha256 = Sha256Hash.hash(outputScript.program());
        byte[] reversed = reverseBytes(sha256);
        return HexFormat.of().formatHex(reversed);
    }


    public static Script toOutputScript(Address address) {

        return ScriptBuilder.createOutputScript(address);
    }


    public static byte[] reverseBytes(byte[] array) {

        if (array == null) {
            return null;
        }

        byte[] reversed = new byte[array.length];

        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }
}