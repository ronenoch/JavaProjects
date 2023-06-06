import java.util.ArrayList;
import java.util.Objects;

public class BigInt implements Comparable<BigInt> {
    private ArrayList<Integer> numberList;
    private boolean isPositive;

    public BigInt(String number) {
        boolean reachedNonZeroChar = false;

        this.numberList = new ArrayList<Integer>();
        if ('-' == number.charAt(0)) {
            isPositive = false;
            number = number.substring(1);
        } else if ('+' == number.charAt(0)) {
            isPositive = true;
            number = number.substring(1);
        } else {
            isPositive = true;
        }

        for (String digit : number.split("(?!^)")) {
            if ("0".equals(digit) && !reachedNonZeroChar) {
                continue;
            }
            reachedNonZeroChar = true;
            try {
                this.numberList.add(Integer.parseInt(digit));

            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        if (0 == this.numberList.size()) {
            isPositive = true;
            this.numberList.add(0);
        }
    }

    /**
     * Removes the left zeros of the number.
     */
    private void trimLeftZeros() {
        /* I need this variable because the list's size can be reduced during the loop */
        int sizeTmp = this.numberList.size() - 1;
        for (int i = 0; i < sizeTmp; i++) {
            if (0 != this.numberList.get(0)) {
                break;
            }
            this.numberList.remove(0);
        }
    }

    public BigInt() {
        this.numberList = new ArrayList<Integer>();
        this.isPositive = true;
    }

    /**
     * inner function that adds the absolute other number to the absolute self-number
     *
     * @param other - the number to add
     * @return the result
     */
    private BigInt innerAbsAdd(BigInt other) {
        int myDigit = 0;
        int otherDigit = 0;
        int currentValue = 0;
        int carry = 0;
        int maxIndex = Math.max(other.numberList.size(), this.numberList.size());
        BigInt result = new BigInt();


        for (int i = 0; i < maxIndex; i++) {
            /* getting the digits from the right to left. If we pass the number's size, we put 0 in the current_digit*/
            if (i >= this.numberList.size()) {
                myDigit = 0;
            } else {
                myDigit = this.numberList.get(this.numberList.size() - 1 - i);
            }
            if (i >= other.numberList.size()) {
                otherDigit = 0;
            } else {
                otherDigit = other.numberList.get(other.numberList.size() - 1 - i);
            }
            currentValue = myDigit + otherDigit + carry;
            if (currentValue > 9) {
                currentValue = currentValue - 10;
                carry = 1;

            } else {
                carry = 0;
            }

            result.numberList.add(0, currentValue);
        }
        if (carry > 0) {
            result.numberList.add(0, 1);
        }
        result.isPositive = true;
        return result;
    }

    /**
     * Plus operation. add other number to this number
     *
     * @param other the other bigint to add.
     * @return the result
     */
    public BigInt plus(BigInt other) {
        BigInt result;
        BigInt tmp_int;

        if (this.isPositive && !other.isPositive) {
            tmp_int = new BigInt(other.toString().replace("-", "+"));
            return this.minus(tmp_int);
        } else if (!this.isPositive && other.isPositive) {
            tmp_int = new BigInt(this.toString().replace("-", "+"));
            return other.minus(tmp_int);
        } else if (!this.isPositive /* && !other.isPositive */) {
            result = innerAbsAdd(other);
            result.isPositive = false;
            return result;
        } else {
            return innerAbsAdd(other);
        }
    }

    /**
     * Inner function to calculate absolute number MINUS absolute number
     *
     * @param other the other BigInt
     * @return the result
     */
    private BigInt minusWithAbsoluteValues(BigInt other) {
        int myDigit = 0;
        int otherDigit = 0;
        int currentValue = 0;
        int carry = 0;
        int maxIndex = Math.max(other.numberList.size(), this.numberList.size());
        BigInt result = new BigInt();

        for (int i = 0; i < maxIndex; i++) {
            /* getting the digits from the right to left. If we pass the number's size, we put 0 in the current_digit*/
            if (i >= this.numberList.size()) {
                myDigit = 0;
            } else {
                myDigit = this.numberList.get(this.numberList.size() - 1 - i);
            }
            if (i >= other.numberList.size()) {
                otherDigit = 0;
            } else {
                otherDigit = other.numberList.get(other.numberList.size() - 1 - i);
            }
            currentValue = myDigit - otherDigit - carry;
            if (currentValue < 0) {
                currentValue = currentValue + 10;
                carry = 1;

            } else {
                carry = 0;
            }

            result.numberList.add(0, currentValue);
        }
        result.isPositive = (carry == 0);

        /* trim left zeros */
        result.trimLeftZeros();

        return result;
    }

    /**
     * Minus operation. Decrease other number from this number
     *
     * @param other the other bigint to reduce.
     * @return the result
     */
    public BigInt minus(BigInt other) {
        BigInt result;

        if (this.isPositive && !other.isPositive) {
            return this.innerAbsAdd(other);
        } else if (!this.isPositive && other.isPositive) {
            result = other.innerAbsAdd(this);
            result.isPositive = false;
            return result;
        } else if (!this.isPositive /* && !other.is_positive */) {
            /* negative minus negative is like negative plus positive that is like: (|other| minus |this|)
             * so if my number is bigger than the other, then the abs value of the other number is bigger than my number,
             * so I need to do (other absolute-minus this) and the result will be positive.
             * else, my absolute value is bigger than the other's so the result will be negative and I need to calculate
             * this by -(|this| - |other|)  */
            result = (this.compareTo(other) >= 0) ? other.minusWithAbsoluteValues(this) : this.minusWithAbsoluteValues(other);
            result.isPositive = (this.compareTo(other) >= 0);
            return result;
        } else {
            /* both positive */
            /* reducing the smaller number from the bigger number and updating the sign */
            result = (this.compareTo(other) < 0) ? other.minusWithAbsoluteValues(this) : this.minusWithAbsoluteValues(other);
            result.isPositive = (this.compareTo(other) >= 0);
            return result;
        }

    }

    /**
     * Compares absolute the absolute value of two bigints.
     *
     * @param other The bigint to compare with
     * @return negative number if the other is greater, 0 if equal, positive number if the current number is bigger.
     */
    private int compareAbsValue(BigInt other) {
        int myDigit;
        int otherDigit;

        if (this.numberList.size() > other.numberList.size()) {
            return 1;
        } else if (this.numberList.size() < other.numberList.size()) {
            return -1;
        }

        for (int i = 0; i < this.numberList.size(); i++) {

            myDigit = this.numberList.get(i);

            otherDigit = other.numberList.get(i);
            if (myDigit == otherDigit) {
                continue;
            }
            return (myDigit > otherDigit) ? 1 : -1;

        }
        return 0;
    }

    /**
     * Multiply one bigInt with another
     *
     * @param other the other bigint to multiply.
     * @return the result
     */
    public BigInt multiply(BigInt other) {
        int currentValue = 0;
        BigInt result = new BigInt("0");
        BigInt tmpResult;

        /* checking for zero to be more efficient */
        if (0 == other.compareAbsValue(result)) {
            return result;
        }

        for (int i = 0; i < this.numberList.size(); i++) {
            currentValue = this.numberList.get(this.numberList.size() - 1 - i);
            tmpResult = new BigInt("0");
            /* for each digit of the first number, I add the other bigInt to a temp big int,
             and then add zeros to multiply by (10 ^ position) */
            while (0 != currentValue) {
                tmpResult = tmpResult.plus(other);
                currentValue--;
            }
            /* adding the correct amount of zeros to the right of the number.*/
            for (int j = i; j > 0; j--) {
                tmpResult.numberList.add(0);
            }
            /* adding the temp result to the current result */
            result = result.plus(tmpResult);
        }

        result.trimLeftZeros();

        /* the other number's sign is already in the result. */
        if (!this.isPositive) {
            result.isPositive = !result.isPositive;
        }
        if (0 == result.compareAbsValue(new BigInt("0"))) {
            result.isPositive = true;
        }

        return result;
    }

    /**
     * divide one bigInt with another
     *
     * @param other the other bigint to divide.
     * @return the result
     */
    public BigInt divide(BigInt other) {
        BigInt result = new BigInt("0");
        BigInt tmpInt = new BigInt(this.toString());
        BigInt otherAbsValue = new BigInt(other.toString().replace("-", "+"));
        BigInt bigInt1 = new BigInt("+1");

        if (other.equals(new BigInt("0"))) {
            System.out.println("You can not divide by zero!");
            throw new ArithmeticException();
        }

        while (tmpInt.compareTo(otherAbsValue) >= 0) {
            result = result.plus(bigInt1);
            tmpInt = tmpInt.minus(otherAbsValue);
        }

        result.isPositive = (other.isPositive == this.isPositive);

        return result;
    }

    @Override
    public int compareTo(BigInt other) {
        int absoluteCompareValue;

        if (this.isPositive && !other.isPositive) {
            return 1;
        } else if (!this.isPositive && other.isPositive) {
            return -1;
        }
        absoluteCompareValue = this.compareAbsValue(other);

        /* if the numbers are negative, then the result should be flipped */
        return (this.isPositive) ? absoluteCompareValue : -1 * absoluteCompareValue;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        BigInt bigInt = (BigInt) o;
        return isPositive == bigInt.isPositive && numberList.equals(bigInt.numberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberList, isPositive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        char sign = this.isPositive ? '+' : '-';
//        sb.append(sign);

        if (!this.isPositive) {
            sb.append('-');
        }

        for (int i = 0; i < this.numberList.size(); i++) {
            sb.append(String.valueOf(this.numberList.get(i)));
        }
        return sb.toString();
    }
}
