package pl.put.poznan.sortingmadness.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.logic.JSONComparator;
import pl.put.poznan.sortingmadness.logic.MergeSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MergeSorterTest {
    MergeSorter mockMergeSorter = null;
    JSONObject mockData1 = null;
    JSONObject mockData2 = null;
    JSONObject mockData3 = null;
    JSONObject mockData4 = null;
    JSONObject mockData5 = null;
    List<JSONObject> mockListObjects = null;
    List<Comparable> mockList = null;

    @BeforeEach
    public void setup() throws JSONException {
        mockMergeSorter = mock(MergeSorter.class);
        mockData1 = mock(JSONObject.class);
        mockData1.put("name", "Szymon");
        mockData1.put("id", "1");
        mockData2 = mock(JSONObject.class);
        mockData2.put("name", "Aleksandra");
        mockData2.put("id", "2");
        mockData3 = mock(JSONObject.class);
        mockData3.put("name", "Mateusz");
        mockData3.put("id", "3");
        mockData4 = mock(JSONObject.class);
        mockData4.put("name", "Agnieszka");
        mockData4.put("id", "3");
        mockData5 = mock(JSONObject.class);
        mockData5.put("name", "Wiktor");
        mockData5.put("id", "3");
        mockListObjects = mock(List.class);
        mockListObjects.add(mockData1);
        mockListObjects.add(mockData2);
        mockListObjects.add(mockData3);
        mockListObjects.add(mockData4);
        mockListObjects.add(mockData5);
        mockList = mock(List.class);
        for (int x : new int[]{5, 2, 5, 3, 7, 5, 6, 7, 4, 2})
            mockList.add(x);
    }

    @Test
    public void testSortAscendingObjects() throws JSONException {
        mockMergeSorter.sort(mockListObjects,
                -1,
                true,
                new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        verify(mockMergeSorter, times(1)).sort(
                anyListOf(JSONObject.class),
                isA(Integer.class),
                isA(Boolean.class),
                isA(JSONComparator.class));
    }

    @Test
    public void testSortDescendingObjects() throws JSONException {
        mockMergeSorter.sort(mockListObjects,
                -1,
                false,
                new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        verify(mockMergeSorter,
                times(1)).sort(
                anyListOf(JSONObject.class),
                isA(Integer.class),
                isA(Boolean.class),
                isA(JSONComparator.class));
    }

    @Test
    public void testSortAscendingSimple() {
        mockMergeSorter.sort(mockList, -1, true);
        verify(mockMergeSorter, times(1)).sort(
                anyListOf(Comparable.class),
                isA(Integer.class),
                isA(Boolean.class));
    }

    @Test
    public void testSortDescendingSimple() {
        mockMergeSorter.sort(mockList, -1, false);
        verify(mockMergeSorter, times(1)).sort(
                anyListOf(Comparable.class),
                isA(Integer.class),
                isA(Boolean.class));
    }
}