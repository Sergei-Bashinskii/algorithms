package org.example;

import org.example.Exception.ElementNotFountException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.example.Exception.StorageIsFullException;
import org.example.Service.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {

    private StringListImpl list;

    @BeforeEach
    void setUp() {
        list = new StringListImpl();
    }

    @Test
    void testAdd() {
        list.add("Первый компонент.");
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals("Первый компонент.", list.get(0));
    }

    @Test
    void testAddWithIndex() {
        list.add("Первый компонент.");
        list.add(0, "Второй компонент.");
        assertEquals("Второй компонент.", list.get(0));
        assertEquals("Первый компонент.", list.get(1));
    }

    @Test
    void testSet() {
        list.add("Первый компонент.");
        list.set(0, "Второй компонент.");
        assertEquals("Второй компонент.", list.get(0));
    }

    @Test
    void testRemoveByItem() {
        list.add("Первый компонент.");
        list.remove("Первый компонент.");
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveByIndex() {
        list.add("Первый компонент.");
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void testContains() {
        list.add("Первый компонент.");
        assertTrue(list.contains("Первый компонент."));
        assertFalse(list.contains("Второй компонент."));
    }

    @Test
    void testIndexOf() {
        list.add("Первый компонент.");
        assertEquals(0, list.indexOf("Первый компонент."));
        assertEquals(-1, list.indexOf("Второй компонент."));
    }

    @Test
    void testLastIndexOf() {
        list.add("Первый компонент.");
        list.add("Второй компонент.");
        list.add("Первый компонент.");
        assertEquals(2, list.lastIndexOf("Первый компонент."));
        assertEquals(1, list.lastIndexOf("Второй компонент."));
    }

    @Test
    void testGet() {
        list.add("Первый компонент.");
        assertEquals("Первый компонент.", list.get(0));
    }

    @Test
    void testSize() {
        list.add("Первый компонент.");
        assertEquals(1, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("Первый компонент.");
        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        list.add("Первый компонент.");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testToArray() {
        list.add("Первый компонент.");
        list.add("Второй компонент.");
        assertArrayEquals(new String[]{"Первый компонент.", "Второй компонент."}, list.toArray());
    }

    @Test
    void testAddWhenFullThrows() {
        list = new StringListImpl(1);
        list.add("Первый компонент.");
        assertThrows(StorageIsFullException.class, () -> list.add("Второй компонент."));
    }

    @Test
    void testAddNullItemThrows() {
        assertThrows(NullItemException.class, () -> list.add(null));
    }

    @Test
    void testAddWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.add(1, "Первый компонент."));
    }

    @Test
    void testSetWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.set(0, "Первый компонент."));
    }

    @Test
    void testRemoveNonExistentItemThrows() {
        assertThrows(ElementNotFountException.class, () -> list.remove("Первый компонент."));
    }

    @Test
    void testRemoveWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.remove(0));
    }

    @Test
    void testGetWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.get(0));
    }
}
