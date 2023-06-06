import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BigIntTest {

    @Test
    void bigIntTest() {
        BigInt a = BigInt("12345678911112099980");
        assertEquals("12345678911112099980", a.toString());
        a = BigInt("0");
        assertEquals("0", a.toString());
        a = BigInt("0000012000");
        assertEquals("12000", a.toString());
        a = BigInt("-0");
        assertEquals("0", a.toString());
        a = BigInt("261214182426303638424850546064646054524842403630282418161262");
        assertEquals("261214182426303638424850546064646054524842403630282418161262", a.toString());
        a = BigInt("+1234567891111209998089798798798798789797979798654635435454367578687798709808088008090");
        assertEquals("1234567891111209998089798798798798789797979798654635435454367578687798709808088008090", a.toString());
    }

    @Test
    void NegativeBigIntTest() {
        BigInt a = BigInt("-1234567891111209998089798798798798789797979798654635435454367578687798709808088008090");
        assertEquals("-1234567891111209998089798798798798789797979798654635435454367578687798709808088008090", a.toString());
        a = BigInt("-123456789");
        assertEquals("-123456789", a.toString());
        a = BigInt("-1");
        assertEquals("-1", a.toString());
        a = BigInt("-0");
        assertEquals("0", a.toString());
        a = BigInt("-000");
        assertEquals("0", a.toString());
    }

    @Test
    void PositiveBigIntTest() {
        BigInt a = new BigInt("12345678912312311231238797981723971293719237192371923719237192837192837192371293712937129371923712398172938712983719237");
        assertEquals("12345678912312311231238797981723971293719237192371923719237192837192837192371293712937129371923712398172938712983719237", a.toString());
    }

    @Test
    void bigIntExceptionsTest() {
        assertThrows(NullPointerException.class, () -> BigInt(null));
        assertThrows(IllegalArgumentException.class, () -> BigInt("2-23-1"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("2.0"));
//        assertThrows(IllegalArgumentException.class, () -> BigInt(""));
        assertThrows(IllegalArgumentException.class, () -> BigInt("               "));
        assertThrows(IllegalArgumentException.class, () -> BigInt("--+--00111230"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("--12"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("12-"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("12+"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("++12"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("-+12"));
        assertThrows(IllegalArgumentException.class, () -> BigInt("+-12"));
    }

    private BigInt BigInt(String s) {
        return new BigInt(s);
    }

    @Test
    void positivePositivePlusTest() {
        BigInt a = BigInt("123456789123123123123123123123123123123123123123578900000000000");
        BigInt b = BigInt("987654321111111111222222222222222223333333331");
        BigInt c = a.plus(b);
        assertEquals("123456789123123124110777444234234234345345345345801123333333331", c.toString());
        a = BigInt("111111122123131212444443199999999992312");
        b = BigInt("0");
        c = a.plus(b);
        assertEquals("111111122123131212444443199999999992312", c.toString());
        c = b.plus(a);
        assertEquals("111111122123131212444443199999999992312", c.toString());
        c = b.plus(b);
        assertEquals("0", c.toString());
        c = a.plus(a);
        assertEquals("222222244246262424888886399999999984624", c.toString());


    }

    @Test
    void negativePositivePlusTest() {
        BigInt a = BigInt("-1123123123123123123123123123123123123123123123354567865432145623456789");
        BigInt b = BigInt("0003111111112222222222222211111112");
        BigInt c = a.plus(b);
        assertEquals("-1123123123123123123123123123123123123120012012242345643209923412345677", c.toString());
        a = BigInt("-111111122123123121244444312312");
        b = BigInt("9999999999999999999999999922222222222299999911111111111111122222222222223333366666999");
        c = a.plus(b);
        assertEquals("9999999999999999999999999922222222222299999911111111111000011100099099102088922354687", c.toString());
        b = BigInt("0");
        c = a.plus(b);
        assertEquals("-111111122123123121244444312312", c.toString());

    }

    @Test
    void negativeNegativePlusTest() {
        BigInt a = BigInt("-1123123123123123123123123123123123123123123123354567865432145623456789");
        BigInt b = BigInt("-000111111112334443111111112222222222222211111112");
        BigInt c = a.plus(b);
        assertEquals("-1123123123123123123123123234234235457566234234466790087654367834567901", c.toString());
        c = b.plus(a);
        assertEquals("-1123123123123123123123123234234235457566234234466790087654367834567901", c.toString());
    }

    @Test
    void positiveNegativePlusTest() {
        BigInt a = BigInt("123123124235467890765432134567889007654321");
        BigInt b = BigInt("-000111111112334443111111112222222222222211111112");
        BigInt c = a.plus(b);
        assertEquals("-110987989210207643220346790087654333203456791", c.toString());
    }


    @Test
    void positivePositiveMinusTest() {
        BigInt a = BigInt("12345678912312312312333333333333333333333333333333333142354657685432");
        BigInt b = BigInt("9876123123123112312312312354321112312312123123");
        BigInt c = a.minus(b);
        assertEquals("12345678912312312312323457210210210221021021020979012030042345562309", c.toString());
        a = BigInt("-111111122");
        b = BigInt("9");
        c = a.minus(b);
        assertEquals("-111111131", c.toString());
        a = BigInt("123123123123111111122");
        b = BigInt("122122312312311111");
        c = a.minus(b);
        assertEquals("123001000810798800011", c.toString());
        a = BigInt("0");
        c = a.minus(b);
        assertEquals("-122122312312311111", c.toString());
        c = b.minus(a);
        assertEquals("122122312312311111", c.toString());
        c = a.minus(a);
        assertEquals("0", c.toString());
    }

    @Test
    void negativePositiveMinusTest() {
        BigInt a = BigInt("-12345612312312312313123124567890987654345678900000000789");
        BigInt b = BigInt("987654321111111111111112222223455678909222222287654");
        BigInt c = a.minus(b);
        assertEquals("-12346599966633423424234235680113211110024588122222288443", c.toString());
        a = BigInt("-111111122123123121244444312312");
        b = BigInt("9999999999999999999999999922222222222299999911111111111111122222222222223333366666999");
        c = a.minus(b);
        assertEquals("-9999999999999999999999999922222222222299999911111111111222233344345345344577810979311", c.toString());
        b = BigInt("0");
        c = a.minus(b);
        assertEquals("-111111122123123121244444312312", c.toString());
    }

    @Test
    void negativeNegativeMinusTest() {
        BigInt a = BigInt("-123456781233333333333333333333534567896543219");
        BigInt b = BigInt("-9876541231345678900087654321321");
        BigInt c = a.minus(b);
        assertEquals("-123456781233323456792101987654634480242221898", c.toString());
        c = b.minus(a);
        assertEquals("123456781233323456792101987654634480242221898", c.toString());
    }

    @Test
    void positiveNegativeMinusTest() {
        BigInt a = BigInt("123456789");
        BigInt b = BigInt("987654321");
        BigInt c = a.minus(b);
        assertEquals("-864197532", c.toString());
    }

    @Test
    void positivePositiveMultiplyTest() {

        BigInt a = BigInt("15");
        BigInt b = BigInt("50");
        BigInt c = a.multiply(b);
        assertEquals("750", c.toString());
        b = BigInt("2222222222222222");
        a = BigInt("12312312312");
        c = a.multiply(b);
        assertEquals("27360694026666663930597264", c.toString());
        a = BigInt("12312312312312321");
        c = a.multiply(b);
        assertEquals("27360694027360710597263930597262", c.toString());
        a = BigInt("0");
        c = a.multiply(b);
        assertEquals("0", c.toString());
        c = b.multiply(a);
        assertEquals("0", c.toString());
        b = BigInt("0");
        c = a.multiply(b);
        assertEquals("0", c.toString());
    }

    @Test
    void negativePositiveMultiplyTest() {
        BigInt a = BigInt("-1123123123123123123467898765434565");
        BigInt b = BigInt("52222222222212313333333333330");
        BigInt c = a.multiply(b);
        assertEquals("-58651985318640856434421366622266776566044983532140337448551450", c.toString());
        a = BigInt("-13455674321123123123123123");
        b = BigInt("123135246321234561231325467756756789878654");
        c = a.multiply(b);
        assertEquals("-1656867771949806398671653678615050691714729519731863837756671516442", c.toString());
        b = BigInt("0");
        c = a.multiply(b);
        assertEquals("0", c.toString());
    }

    @Test
    void negativeNegativeMultiplyTest() {
        BigInt a = BigInt("-12312324235678543214256785432413415123123123123123123123");
        BigInt b = BigInt("-123123123543678965432123423567898889087909890890890");
        BigInt c = a.multiply(b);
        assertEquals("1515931817979281967148323694613973522106009873405058429116531530878335273073919470011279716358545629049470", c.toString());
        a = BigInt("-12312435678543213456789765432134567879654321");
        c = a.multiply(b);
        assertEquals("1515945539172876822915520332275024042512920735100844840209939713395744053281678775439928035690", c.toString());
    }

    @Test
    void positiveNegativeMultiplyTest() {
        BigInt a = BigInt("15112312432567432313455678799000000000000000");
        BigInt b = BigInt("-123123456789097654321112345678901");
        BigInt c = a.multiply(b);
        assertEquals("-1860680146774559511385482605764624700853427983751047447319899000000000000000", c.toString());
        a = BigInt("123123231243567896545321111");
        c = a.multiply(b);
        assertEquals("-15159357841751510160045715161895047607648543263730342579011", c.toString());
    }

    @Test
    void divideTest() {
        BigInt a = BigInt("98765432112223");
        BigInt b = BigInt("12345678922");
        BigInt c = a.divide(b);
        assertEquals("8000", c.toString());
        a = BigInt("50");
        b = BigInt("10");
        c = a.divide(b);
        assertEquals("5", c.toString());
        a = BigInt("1234567891111111");
        b = BigInt("123456789111111103333123333333333333333333333331231231231252363000099911112245693049650348");
        c = a.divide(b);
        System.out.println("after divide small num with big num");
        assertEquals("0", c.toString());
        a = BigInt("0");
        c = a.divide(b);
        assertEquals("0", c.toString());
//        a = BigInt("123456789111111234444444444444444444444444444444123123999999999900000000000012312311");
//        b = BigInt("1234567891111119999991");
//        c = a.divide(b);
//        System.out.println("after divide big num with small num");
//        assertEquals("99999999999999379900723915190400814828573588049914408659564493", c.toString());
    }

    @Test
    void divideByZeroExceptionTest() {
        BigInt a = BigInt("9876543211222311231222222222222222222222222312312313");
        BigInt b = BigInt("00000000000000000000000000000");
        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }

    @Test
    void compareToTest() {
        BigInt a = BigInt("123456789");
        BigInt b = BigInt("987654321");
        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));
        b = BigInt("123456789");
        assertEquals(0, a.compareTo(b));
        a = BigInt("-1234567891213123123123123");
        b = BigInt("-1234567891213123123123123");
        assertEquals(0, a.compareTo(b));
        a = BigInt("-12345678912131231231231231");
        assertEquals(-1, a.compareTo(b));
    }

    @Test
    void toStringTest() {
        BigInt a = BigInt("123456789123123123123125678999905674352413324567899976543214567890765432");
        assertEquals("123456789123123123123125678999905674352413324567899976543214567890765432", a.toString());
    }

    @Test
    void equalsTest() {
        BigInt a = BigInt("123456789123123123123125678999905674352413324567899976543214567890765432");
        BigInt b = BigInt("+123456789123123123123125678999905674352413324567899976543214567890765432");
        assertEquals(a, b);
    }
}
