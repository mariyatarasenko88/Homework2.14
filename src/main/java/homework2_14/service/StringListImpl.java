package homework2_14.service;

import homework2_14.exception.MyIndexOutOfBoundsException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] array;
    private int arraySize;

    public StringListImpl() {
        array = new String[3];
        arraySize = 0;
    }

    @Override
    public String add(String item) {
        validateItem(item);
        increaseArraySize();
        array[arraySize] = item;

        return array[arraySize++];
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);

        if (index < 0 || index > arraySize) {
            throw new MyIndexOutOfBoundsException("Введен некорректный индекс");
        }
        increaseArraySize();

        if (arraySize - index >= 0) {
            System.arraycopy(array, index, array, index + 1, arraySize - index);
        }

        array[index] = item;
        arraySize++;
        return array[index];
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullPointerException("Значение не должно быть равно null");
        }
    }

    private void increaseArraySize() {
        if (arraySize == (array.length - 1)) {
            int newSize = array.length * 2 + 1;
            array = Arrays.copyOf(array, newSize);
        }
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
        array[index] = item;
        return array[index];
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new MyIndexOutOfBoundsException("Элемент не найден");
        }

        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String element = array[index];

        if (arraySize - index >= 0) {
            System.arraycopy(array, index + 1, array, index, arraySize - index);
        }

        arraySize--;
        return element;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < arraySize; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = arraySize - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (arraySize != otherList.size()) {
            return false;
        }

        for (int i = 0; i < arraySize; i++) {
            if (!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    @Override
    public void clear() {
        arraySize = 0;
    }

    @Override
    public String[] toArray() {
        /*String[] result = new String[arraySize];
        System.arraycopy(array, 0, result, 0, arraySize);*/
        String[] result = Arrays.copyOf(array, arraySize);
        return result;
    }

    private void validateIndex(int index) {
        try {
            if (array[index] == null || index >= arraySize) {
                throw new MyIndexOutOfBoundsException("Такого элемента не существует");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MyIndexOutOfBoundsException("Превышен размер внутреннего массива");
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("(");
        for (int i = 0; i < arraySize; i++) {
            if (i != 0) {
                result.append(", ");
            }
            result.append(array[i]);
        }
        return result + ")";
    }
}
