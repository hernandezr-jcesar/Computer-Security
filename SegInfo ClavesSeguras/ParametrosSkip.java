/* Fichero con los parametros de SKIP para 1024 bits
* Parametros sacados de
* http://www.skip-vpn.org/spec/numbers.html
*/
import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;
public class ParametrosSkip{
	private static final String strModulo1024 =
		"F488FD584E49DBCD20B49DE49107366B336C380D451D"
		+ "0F7C88B31C7C5B2D8EF6F3C923C043F0A55B188D8EBB"
		+ "558CB85D38D334FD7C175743A31D186CDE33212CB52A"
		+ "FF3CE1B1294018118D7C84A70A72D686C40319C80729"
		+ "7ACA950CD9969FABD00A509B0246D3083D66A45D419F"
		+ "9C7CBD894B221926BAABA25EC355E92F78C7";
	// Modulo (n)
	private static final BigInteger modulo1024 = new BigInteger(strModulo1024,16);
	// Base (g)
	private static final BigInteger base1024 = BigInteger.valueOf(2);
	// Parametros DH
	public static final DHParameterSpec parametrosDH = new DHParameterSpec(modulo1024,base1024);
}

