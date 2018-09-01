package Packets;

import classes.DataTypeUtilities;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author miguelangel.garciar
 */
public class MarshalZone {
    
    public static int SIZE = 5;

    public float   zoneStart;   // Fraction (0..1) of way through the lap the marshal zone starts
    public short   zoneFlag;    // -1 = invalid/unknown, 0 = none, 1 = green, 2 = blue, 3 = yellow, 4 = red
    
    public MarshalZone (byte[] content){
        ByteBuffer bb = ByteBuffer.wrap(content);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        
        zoneStart = DataTypeUtilities.convert_float_vector(bb.getFloat());
        zoneStart = DataTypeUtilities.convert_uint8(bb.get());
    }
    
    public String getZoneFlag(){
        switch(zoneFlag){
            case -1: return "INVALID";
            case 0: return "NONE";
            case 1: return "GREEN";
            case 2: return "BLUE";
            case 3: return "YELLOW";
            case 4: return "RED";
            default: return "** UNKNOWN **";
        }
    }
    
    public String toString(){
        String ret = "Zone: "+zoneFlag+"\n";
        ret += "Flag: "+getZoneFlag()+"\n";
        return ret;
    }
}