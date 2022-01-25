package pl.put.poznan.sortingmadness;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import pl.put.poznan.sortingmadness.logic.JSONComparator;
import pl.put.poznan.sortingmadness.logic.QuickSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class QuickSorterTest {
    private List<JSONObject> converter(JSONArray list) throws JSONException {
        List<JSONObject> jsonObjectList = Mockito.mock(ArrayList.class);
        for (int i = list.length() - 1; i >= 0; i--) {
            jsonObjectList.add(list.getJSONObject(i));
        }
        return jsonObjectList;
    }


    @Test
    public void testSortAscendingObj() throws JSONException {
        QuickSorter mockQuickSorter = Mockito.mock(QuickSorter.class);
        JSONArray arr = Mockito.mock(JSONArray.class);
        JSONObject data1 = Mockito.mock(JSONObject.class);
        data1.put("name","Ola");
        data1.put("id","1");
        JSONObject data2 = Mockito.mock(JSONObject.class);
        data2.put("name","Ala");
        data2.put("id","2");
        JSONObject data3 = Mockito.mock(JSONObject.class);
        data3.put("name","Ela");
        data3.put("id","3");
        arr.put(data1);
        arr.put(data2);
        arr.put(data3);
        List<JSONObject> mockListObj = converter(arr);
        mockQuickSorter.sort(mockListObj, 100, true,new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        Mockito.verify(mockQuickSorter, Mockito.times(1)).sort(ArgumentMatchers.anyList(), ArgumentMatchers.isA(Integer.class), ArgumentMatchers.isA(Boolean.class), ArgumentMatchers.isA(JSONComparator.class));
    }
    @Test
    public void testSortDescendingObj() throws JSONException {
        QuickSorter mockQuickSorter = Mockito.mock(QuickSorter.class);
        JSONArray arr = Mockito.mock(JSONArray.class);
        JSONObject data1 = Mockito.mock(JSONObject.class);
        data1.put("name","Ola");
        data1.put("id","1");
        JSONObject data2 = Mockito.mock(JSONObject.class);
        data2.put("name","Ala");
        data2.put("id","2");
        JSONObject data3 = Mockito.mock(JSONObject.class);
        data3.put("name","Ela");
        data3.put("id","3");
        arr.put(data1);
        arr.put(data2);
        arr.put(data3);
        List<JSONObject> mockListObj = converter(arr);
        mockQuickSorter.sort(mockListObj, 100, false,new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        Mockito.verify(mockQuickSorter, Mockito.times(1)).sort(ArgumentMatchers.anyList(), ArgumentMatchers.isA(Integer.class), ArgumentMatchers.isA(Boolean.class), ArgumentMatchers.isA(JSONComparator.class));

    }
    @Test
    public void testSortAscending(){
        QuickSorter mockQuickSorter = Mockito.mock(QuickSorter.class);
        List<Comparable> mockList = Mockito.mock(ArrayList.class);
        when(mockList.size()).thenReturn(3);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(1)).thenReturn(2);
        when(mockList.get(2)).thenReturn(3);
        mockQuickSorter.sort(mockList,100,true);
        Mockito.verify(mockQuickSorter, Mockito.times(1)).sort(ArgumentMatchers.anyListOf(Comparable.class), ArgumentMatchers.isA(Integer.class), ArgumentMatchers.isA(Boolean.class));
    }
    @Test
    public void testSortDescending(){
        QuickSorter mockQuickSorter = Mockito.mock(QuickSorter.class);
        List<Comparable> mockList = Mockito.mock(ArrayList.class);
        when(mockList.size()).thenReturn(3);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(1)).thenReturn(2);
        when(mockList.get(2)).thenReturn(3);
        mockQuickSorter.sort(mockList,100,false);
        Mockito.verify(mockQuickSorter, Mockito.times(1)).sort(ArgumentMatchers.anyListOf(Comparable.class), ArgumentMatchers.isA(Integer.class), ArgumentMatchers.isA(Boolean.class));
    }
}