package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CocktailSorterTest {
    List<Comparable> mockList;
    Comparable mockComparable;
    JSONObject mockJSONObject;
    List<JSONObject> mockJSONList;

    @Test
    void testAscendingSimple(){
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(0)).thenReturn(-1);
        when(mockList.get(1)).thenReturn(100);
        when(mockList.get(2)).thenReturn(12);
        when(mockList.get(3)).thenReturn(176);

        CocktailSorter cocktailSorter = new CocktailSorter();
        cocktailSorter.sort(mockList, 0, true);
        verify(mockList, times(1)).size();
        verify(mockList, times(28)).get(any(Integer.class));

        // --------------------------------------

        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(0)).thenReturn(100);
        when(mockList.get(1)).thenReturn(100);
        when(mockList.get(2)).thenReturn(100);
        when(mockList.get(3)).thenReturn(100);

        cocktailSorter.sort(mockList, 0, true);
        verify(mockList, times(1)).size();
        verify(mockList, times(9)).get(any(Integer.class));
    }

    @Test
    void testDescendingSimple(){
        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(0)).thenReturn(-1);
        when(mockList.get(1)).thenReturn(100);
        when(mockList.get(2)).thenReturn(12);
        when(mockList.get(3)).thenReturn(176);

        CocktailSorter cocktailSorter = new CocktailSorter();
        cocktailSorter.sort(mockList, 0, false);
        verify(mockList, times(1)).size();
        verify(mockList, times(25)).get(any(Integer.class));

        // --------------------------------------

        mockList        = mock(List.class);
        mockComparable  = mock(Comparable.class);
        when(mockList.size()).thenReturn(4);
        when(mockList.get(0)).thenReturn(100);
        when(mockList.get(1)).thenReturn(100);
        when(mockList.get(2)).thenReturn(100);
        when(mockList.get(3)).thenReturn(100);

        cocktailSorter.sort(mockList, 0, false);
        verify(mockList, times(1)).size();
        verify(mockList, times(9)).get(any(Integer.class));
    }

    @Test
    void testAscendingJSON() throws JSONException {
        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(new JSONObject());

        CocktailSorter cocktailSorter = new CocktailSorter();
        assertThrows(JSONException.class, () ->
                cocktailSorter.sort(
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

        cocktailSorter.sort(
                mockJSONList,
                0,
                true,
                new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
        );

        verify(mockJSONList, times(1)).size();
        verify(mockJSONList, times(6)).get(any(Integer.class));
    }

    @Test
    void testDescendingJSON() throws JSONException {
        mockJSONList    = mock(List.class);
        mockJSONObject  = mock(JSONObject.class);

        when(mockJSONList.size()).thenReturn(4);
        when(mockJSONList.get(any(Integer.class))).thenReturn(new JSONObject());

        CocktailSorter cocktailSorter = new CocktailSorter();
        assertThrows(JSONException.class, () ->
                cocktailSorter.sort(
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

        cocktailSorter.sort(
                mockJSONList,
                0,
                false,
                new JSONComparator(new ArrayList<>(Collections.singletonList("id")))
        );

        verify(mockJSONList, times(1)).size();
        verify(mockJSONList, times(6)).get(any(Integer.class));
    }
}