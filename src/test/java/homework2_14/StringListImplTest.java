package homework2_14;

import homework2_14.exception.MyIndexOutOfBoundsException;
import homework2_14.service.StringList;
import homework2_14.service.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static homework2_14.StringListImplConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringListImplTest {
    private final StringList stringList = new StringListImpl();

    @BeforeEach
    public void addElements() {
        stringList.add(VALUE_1);
        stringList.add(VALUE_2);
        stringList.add(VALUE_3);
        stringList.add(VALUE_4);
    }

    @Test
    public void addValue() {
        assertThat(stringList.toString())
                .isEqualTo("(" + VALUE_1 + ", " + VALUE_2 + ", " + VALUE_3 + ", " + VALUE_4 + ")");
    }

    @Test
    public void addValueByIndexPositive() {
        stringList.add(2, VALUE_5);
        stringList.add(2, VALUE_1);
        stringList.add(2, VALUE_3);
        stringList.add(2, VALUE_5);

        assertThat(stringList.toString())
                .isEqualTo("(" + VALUE_1 + ", " + VALUE_2 + ", " + VALUE_5 + ", " + VALUE_3 + ", "
                        + VALUE_1 + ", " + VALUE_5 + ", " + VALUE_3 + ", " + VALUE_4 + ")");
    }

    @Test
    public void addValueByIndexNegative() {
        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> stringList.add(8, VALUE_5))
                .withMessage(ERROR_MESSAGE_NOT_CORRECT_INDEX);
    }

    @Test
    public void addValueNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> stringList.add(null))
                .withMessage("Значение не должно быть равно null");

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> stringList.add(1, null))
                .withMessage("Значение не должно быть равно null");
    }

    @Test
    public void setValuePositive() {
        stringList.set(3, VALUE_1);

        assertThat(stringList.toArray())
                .isEqualTo(new String[] {VALUE_1, VALUE_2, VALUE_3, VALUE_1});
    }

    @Test
    public void setValueNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> stringList.set(1, null))
                .withMessage("Значение не должно быть равно null");

        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> stringList.set(5, VALUE_1))
                .withMessage("Такого элемента не существует");

        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> stringList.set(300, VALUE_1))
                .withMessage("Превышен размер внутреннего массива");
    }

    @Test
    public void removeValueByIndex() {
        stringList.remove(1);
        assertThat(stringList.toString())
                .isEqualTo("(" + VALUE_1 + ", " + VALUE_3 + ", " + VALUE_4 + ")");
    }

    @Test
    public void removeValueByItemPositive() {
        stringList.remove(VALUE_3);
        assertThat(stringList.toString())
                .isEqualTo("(" + VALUE_1 + ", " + VALUE_2 + ", " + VALUE_4 + ")");
    }

    @Test
    public void removeValueByItemNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> stringList.remove(null))
                .withMessage("Значение не должно быть равно null");

        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> stringList.remove(VALUE_NON_ELEMENT));
    }

    @Test
    public void containsValueInArray() {
        assertThat(stringList.contains(VALUE_3))
                .isTrue();

        assertThat(stringList.contains(VALUE_NON_ELEMENT))
                .isFalse();
    }

    @Test
    public void lastIndexOf() {
        assertThat(stringList.lastIndexOf(VALUE_3))
                .isEqualTo(2);

        assertThat(stringList.lastIndexOf(VALUE_NON_ELEMENT))
                .isEqualTo(-1);
    }

    @Test
    public void getValue() {
        assertThat(stringList.get(1))
                .isEqualTo(VALUE_2);
    }

    @Test
    public void equalsArrays() {
        StringList stringListSecond = new StringListImpl();
        stringListSecond.add(VALUE_1);
        stringListSecond.add(VALUE_2);
        stringListSecond.add(VALUE_3);

        assertThat(stringList.equals(stringListSecond))
                .isFalse();

        stringListSecond.add(VALUE_4);

        assertThat(stringList.equals(stringListSecond))
                .isTrue();

        stringListSecond.set(2, VALUE_1);
        assertThat(stringList.equals(stringListSecond))
                .isFalse();
    }

    @Test
    public void sizeArray() {
        assertThat(stringList.size())
                .isEqualTo(4);
    }

    @Test
    public void isEmptyAndClearArray() {
        assertThat(stringList.isEmpty())
                .isFalse();
        stringList.clear();
        assertThat(stringList.isEmpty())
                .isTrue();
    }

}
