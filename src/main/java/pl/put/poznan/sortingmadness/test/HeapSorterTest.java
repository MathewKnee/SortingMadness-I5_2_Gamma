package pl.put.poznan.sortingmadness.test;

import org.junit.jupiter.api.Test;

import org.json.JSONException;
import org.json.JSONObject;
import pl.put.poznan.sortingmadness.logic.HeapSorter;
import pl.put.poznan.sortingmadness.logic.JSONComparator;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HeapSorterTest {
    List<Comparable> mockList;
    Comparable mockComparable;
    JSONObject mockJSONObject;
    List<JSONObject> mockJSONList;

    @Test
    void testAscendingSimple(){
        // max iter
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(any(Integer.class))).thenReturn(1);

        HeapSorter heapSorter = new HeapSorter();
        heapSorter.sort(mockList, 0, true);
        verify(mockList, times(9)).size();
        verify(mockList, times(16)).get(any(Integer.class));

        // 1 iter
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(any(Integer.class))).thenReturn(1);

        heapSorter.sort(mockList, 1, true);
        verify(mockList, times(6)).size();
        verify(mockList, times(11)).get(any(Integer.class));
    }

    @Test
    void testDescendingSimple(){
        // max iter
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(any(Integer.class))).thenReturn(1);

        HeapSorter heapSorter = new HeapSorter();
        heapSorter.sort(mockList, 0, false);
        verify(mockList, times(9)).size();
        verify(mockList, times(16)).get(any(Integer.class));

        // 1 iter
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(any(Integer.class))).thenReturn(1);

        heapSorter.sort(mockList, 1, false);
        verify(mockList, times(6)).size();
        verify(mockList, times(11)).get(any(Integer.class));
    }

    @Test
    void testAscendingJSON() throws JSONException {
        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(new JSONObject());

        HeapSorter heapSorter = new HeapSorter();
        assertThrows(JSONException.class, () ->
                heapSorter.sort(
                        mockJSONList,
                        0,
                        true,
                        new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
                ));

        // -----------------------------------

        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 3);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(jsonObject);

        heapSorter.sort(
                mockJSONList,
                0,
                true,
                new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
        );

        verify(mockJSONList, times(9)).size();
        verify(mockJSONList, times(16)).get(any(Integer.class));
    }

    @Test
    void testDescendingJSON() throws JSONException {
        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(new JSONObject());

        HeapSorter heapSorter = new HeapSorter();
        assertThrows(JSONException.class, () ->
                heapSorter.sort(
                        mockJSONList,
                        0,
                        false,
                        new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
                ));

        // -----------------------------------

        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 3);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(jsonObject);

        heapSorter.sort(
                mockJSONList,
                0,
                false,
                new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
        );

        verify(mockJSONList, times(9)).size();
        verify(mockJSONList, times(16)).get(any(Integer.class));
    }
}