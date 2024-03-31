package org.example;

import org.example.Exception.ElementNotFountException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.example.Exception.StorageIsFullException;
import org.example.Service.IntegerListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {

    private IntegerListImpl list;

    @BeforeEach
    void setUp() {
        list = new IntegerListImpl();
    }

    @Test
    void testAdd() {
        list.add(1);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void testAddWithIndex() {
        list.add(1);
        list.add(0, 2);
        assertEquals(2, list.get(0));
        assertEquals(1, list.get(1));
    }

    @Test
    void testSet() {
        list.add(1);
        list.set(0, 2);
        assertEquals(2, list.get(0));
    }

    @Test
    void testRemoveByItem() {
        list.add(Integer.valueOf(1));
        list.remove(Integer.valueOf(1));
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    void testIndexOf() {
        list.add(1);
        assertEquals(0, list.indexOf(1));
        assertEquals(-1, list.indexOf(2));
    }

    @Test
    void testLastIndexOf() {
        list.add(1);
        list.add(2);
        list.add(1);
        assertEquals(2, list.lastIndexOf(1));
        assertEquals(1, list.lastIndexOf(2));
    }

    @Test
    void testGet() {
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    void testSize() {
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        list.add(1);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testToArray() {
        list.add(1);
        list.add(2);
        assertArrayEquals(new Integer[]{1, 2}, list.toArray());
    }

    @Test
    void testAddNullItemThrows() {
        assertThrows(NullItemException.class, () -> list.add(null));
    }

    @Test
    void testAddWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.add(1, 1));
    }

    @Test
    void testSetWithInvalidIndexThrows() {
        assertThrows(InvalidIndexException.class, () -> list.set(0, 1));
    }

    @Test
    void testRemoveNonExistentItemThrows() {
        IntegerListImpl list = new IntegerListImpl();
        list.add(0);
        assertThrows(ElementNotFountException.class, () -> list.remove(Integer.valueOf(1)));
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
