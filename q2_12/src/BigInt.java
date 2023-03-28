import java.util.ArrayList;
import java.util.Objects;

public class BigInt implements Comparable<BigInt>{
    private ArrayList<Integer> number_list;
    private boolean is_positive;
    public BigInt(String number) {
        boolean reached_non_zero_char = false;

        this.number_list = new ArrayList<Integer>();
        if ('-' == number.charAt(0)) {
            is_positive = false;
            number = number.substring(1);
        }
        else if ('+' == number.charAt(0)) {
            is_positive = true;
            number = number.substring(1);
        }
        else {
            is_positive = true;
        }

        for (String digit : number.split("(?!^)")) {
            if ("0".equals(digit) && !reached_non_zero_char) {
                continue;
            }
            reached_non_zero_char = true;
            try {
                this.number_list.add(Integer.parseInt(digit));

            }
            catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        if (0 == this.number_list.size()) {
            is_positive = true;
            this.number_list.add(0);
        }
    }
    public BigInt() {
        this.number_list = new ArrayList<Integer>();
        this.is_positive = true;
    }

    private BigInt inner_abs_add(BigInt other) {
        int my_digit = 0;
        int other_digit = 0;
        int current_value = 0;
        int carry = 0;
        int max_index = Math.max(other.number_list.size(), this.number_list.size());
        BigInt result = new BigInt();


        for (int i = 0; i < max_index ; i++) {
//            if (max_index - this.number_list.size() > i) {
            if (i >= this.number_list.size()) {
                my_digit = 0;
            }
            else {
                my_digit = this.number_list.get(this.number_list.size() - 1 - i);
            }
//            if (max_index - other.number_list.size() > i) {
            if (i >= other.number_list.size()) {
                other_digit = 0;
            }
            else {
                other_digit = other.number_list.get(other.number_list.size() - 1 - i);
            }
            current_value = my_digit + other_digit + carry;
            if (current_value > 9) {
                current_value = current_value - 10;
                carry = 1;

            }
            else {
                carry = 0;
            }

            result.number_list.add(0, current_value);
        }
        if (carry > 0) {
            result.number_list.add(0, 1);
        }
        result.is_positive = true;
        return result;
    }

    public BigInt plus(BigInt other) {
        BigInt result;
        BigInt tmp_int;

        if (this.is_positive && !other.is_positive) {
            tmp_int = new BigInt(other.toString().replace("-", "+"));
            return this.minus(tmp_int);
        } else if (!this.is_positive && other.is_positive) {
            tmp_int = new BigInt(this.toString().replace("-", "+"));
            return other.minus(tmp_int);
        } else if (!this.is_positive /* && !other.is_positive */) {
            result = inner_abs_add(other);
            result.is_positive = false;
            return result;
        }
        else {
            return inner_abs_add(other);
        }
    }

    private BigInt inner_minus(BigInt other)
    {
        int my_digit = 0;
        int other_digit = 0;
        int current_value = 0;
        int carry = 0;
        int max_index = Math.max(other.number_list.size(), this.number_list.size());
        int size_tmp = 0;
        BigInt result = new BigInt();

        for (int i = 0; i < max_index ; i++) {
//            if (max_index - this.number_list.size() > i) {
            if (i >= this.number_list.size()) {
                my_digit = 0;
            }
            else {
                my_digit = this.number_list.get(this.number_list.size() - 1 - i);
            }
//            if (max_index - other.number_list.size() > i) {
            if (i >= other.number_list.size()) {
                other_digit = 0;
            }
            else {
                other_digit = other.number_list.get(other.number_list.size() - 1 - i);
            }
            current_value = my_digit - other_digit - carry;
            if (current_value < 0) {
                current_value = current_value + 10;
                carry = 1;

            }
            else {
                carry = 0;
            }

            result.number_list.add(0, current_value);
        }
        result.is_positive = (carry == 0);

        /* trim left zeros */
        /* need this variable because the list's size can be reduced during the loop */
        size_tmp = result.number_list.size() - 1;
        for (int i = 0; i < size_tmp; i++) {
            if (0 != result.number_list.get(0)) {
                break;
            }
            result.number_list.remove(0);
        }

        return result;
    }

    public BigInt minus(BigInt other) {
        BigInt result;

        if (this.is_positive && !other.is_positive) {
            return this.inner_abs_add(other);
        }
        else if (!this.is_positive && other.is_positive) {
            result = other.inner_abs_add(this);
            result.is_positive = false;
            return result;
        } else if (!this.is_positive /* && !other.is_positive */ ) {
            result = (this.compareTo(other) >= 0) ? other.inner_minus(this) : this.inner_minus(other);
            result.is_positive = (this.compareTo(other) >= 0);
            return result;
        } else {
            /* both positive */
            result = (this.compareTo(other) < 0) ? other.inner_minus(this) : this.inner_minus(other);
            result.is_positive = (this.compareTo(other) >= 0);
            return result;
        }

    }

    private int compare_abs_value(BigInt other) {
        int my_digit;
        int other_digit;

        if (this.number_list.size() > other.number_list.size()) {
            return 1;
        }
        else if (this.number_list.size() < other.number_list.size()) {
            return -1;
        }

        for (int i = 0; i < this.number_list.size() ; i++) {

            my_digit = this.number_list.get(i);

            other_digit = other.number_list.get(i);
            if (my_digit == other_digit) {
                continue;
            }
            return (my_digit > other_digit) ? 1 : -1;

        }
        return 0;
    }

    public BigInt multiply(BigInt other) {
        int current_value = 0;
        BigInt result = new BigInt("0");
        BigInt tmp_result;

        /* TODO check zero ? */

        for (int i = 0; i < this.number_list.size(); i++) {
            current_value = this.number_list.get(this.number_list.size() - 1 - i);
            tmp_result = new BigInt("0");
            while (0 != current_value) {
                tmp_result = tmp_result.plus(other);
                current_value--;
            }
            for (int j = i; j > 0; j--) {
                tmp_result.number_list.add(0);
            }
            result = result.plus(tmp_result);
        }
        if (!this.is_positive) {
            result.is_positive = !result.is_positive;
        }
        /* TODO trim zeros */

        return result;
    }

    public BigInt divide(BigInt other)  {
        BigInt result = new BigInt();
        BigInt tmp_int = new BigInt(this.toString());
        BigInt other_abs = new BigInt(other.toString().replace("-", "+"));
        BigInt big_int_1 = new BigInt("+1");

        if (other.equals(new BigInt())) {
            throw new ArithmeticException();
        }

        while (tmp_int.compareTo(other_abs) > 0) {
            result = result.plus(big_int_1);
            tmp_int = tmp_int.minus(other_abs);
        }

        result.is_positive = (other.is_positive == this.is_positive);

        return result;
    }

    @Override
    public int compareTo(BigInt other) {
        int absolute_compare_value;

        if (this.is_positive && !other.is_positive) {
            return 1;
        }
        else if (!this.is_positive && other.is_positive) {
            return -1;
        }
        absolute_compare_value = this.compare_abs_value(other);

        /* if the numbers are negative, then the result should be flipped */
        return (this.is_positive) ? absolute_compare_value : -1 * absolute_compare_value;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        BigInt bigInt = (BigInt) o;
        return is_positive == bigInt.is_positive && number_list.equals(bigInt.number_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number_list, is_positive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char sign = this.is_positive ? '+' : '-';

        sb.append(sign);

        for (int i = 0; i < this.number_list.size(); i++) {
            sb.append(String.valueOf(this.number_list.get(i)));
        }
        return sb.toString();
    }
}
