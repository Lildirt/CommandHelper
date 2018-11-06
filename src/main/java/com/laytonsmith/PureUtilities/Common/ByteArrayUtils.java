package com.laytonsmith.PureUtilities.Common;

/**
 *
 * @author Cailin
 */
public class ByteArrayUtils {
	private final boolean useUpper;
	private final String middleUpper = " X0 X1 X2 X3 X4 X5 X6 X7 X8 X9 XA XB XC XD XE XF ";
	private final String middleLower = " x0 x1 x2 x3 x4 x5 x6 x7 x8 x9 xa xb xc xd xe xf ";
	/**
	 * Creates a new ByteArrayUtils object with default options
	 */
	public ByteArrayUtils() {
		useUpper = true;
	}

	public ByteArrayUtils(boolean useUpperCase) {
		this.useUpper = useUpperCase;
	}
	public String baToHexTable(byte [] ba) {
        StringBuilder b = new StringBuilder();
        StringBuilder c1 = new StringBuilder();
        StringBuilder c2 = new StringBuilder();
        b.append("Address    |").append(useUpper ? middleUpper : middleLower).append("| ASCII            |\n");
        for(int i = 0; i < ba.length + (16 - (ba.length % 16)); i++) {
            if(i % 16 == 0) {
                // First line, the address
                b.append(String.format("0x%07" + (useUpper?"X":"x"), i / 16)).append(useUpper ? "X " : "x ");
            }
            if(i < ba.length) {
                byte by = ba[i];
                c1.append(toHex(by)).append(" ");
                char w = '.';
                if(by != 0) {
                    w = (char) by;
                }
                if(Character.isISOControl(by)) {
                    w = '.';
                }
                c2.append(w);
            } else {
                c1.append(".. ");
                c2.append(".");
            }
            if(i % 16 == 15) {
                // End of line, construct line
                b.append("| ").append(c1.toString()).append("| ")
                        .append(c2.toString()).append(" |\n");
                c1 = new StringBuilder();
                c2 = new StringBuilder();
            }
        }
        return b.toString();
    }

    private final static char[] upperHexArray = "0123456789ABCDEF".toCharArray();
	private final static char[] lowerHexArray = "0123456789abcdef".toCharArray();
    private String toHex(byte b) {
        int v = b & 0xFF;
		if(useUpper) {
			return new String(new char[]{upperHexArray[v >>> 4], upperHexArray[v & 0x0F]});
		} else {
			return new String(new char[]{lowerHexArray[v >>> 4], lowerHexArray[v & 0x0F]});
		}
    }
}
