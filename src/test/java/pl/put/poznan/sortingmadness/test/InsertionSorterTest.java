package pl.put.poznan.sortingmadness.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.logic.InsertionSorter;
import pl.put.poznan.sortingmadness.logic.JSONComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


class InsertionSorterTest {
    private List<JSONObject> converter(JSONArray list) throws JSONException {
        List<JSONObject> jsonObjectList = mock(ArrayList.class);
        for (int i = list.length() - 1; i >= 0; i--) {
            jsonObjectList.add(list.getJSONObject(i));
        }
        return jsonObjectList;
    }


    @Test
    public void testSortAscendingObj() throws JSONException {
        InsertionSorter mockInsertionSorter = mock(InsertionSorter.class);
        JSONArray arr = mock(JSONArray.class);
        JSONObject data1 = mock(JSONObject.class);
        data1.put("name","Ola");
        data1.put("id","1");
        JSONObject data2 = mock(JSONObject.class);
        data2.put("name","Ala");
        data2.put("id","2");
        JSONObject data3 = mock(JSONObject.class);
        data3.put("name","Ela");
        data3.put("id","3");
        arr.put(data1);
        arr.put(data2);
        arr.put(data3);
        List<JSONObject> mockListObj = converter(arr);
        mockInsertionSorter.sort(mockListObj, 100, true,new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        verify(mockInsertionSorter, times(1)).sort(anyList(),isA(Integer.class),isA(Boolean.class),isA(JSONComparator.class));
    }
    @Test
    public void testSortDescendingObj() throws JSONException {
        InsertionSorter mockInsertionSorter = mock(InsertionSorter.class);
        JSONArray arr = mock(JSONArray.class);
        JSONObject data1 = mock(JSONObject.class);
        data1.put("name","Ola");
        data1.put("id","1");
        JSONObject data2 = mock(JSONObject.class);
        data2.put("name","Ala");
        data2.put("id","2");
        JSONObject data3 = mock(JSONObject.class);
        data3.put("name","Ela");
        data3.put("id","3");
        arr.put(data1);
        arr.put(data2);
        arr.put(data3);
        List<JSONObject> mockListObj = converter(arr);
        mockInsertionSorter.sort(mockListObj, 100, false,new JSONComparator(new ArrayList<>(Collections.singleton("id"))));
        verify(mockInsertionSorter, times(1)).sort(anyList(),isA(Integer.class),isA(Boolean.class),isA(JSONComparator.class));

    }
    @Test
    public void testSortAscending(){
        InsertionSorter mockInsertionSorter = mock(InsertionSorter.class);
        List<Comparable> mockList = mock(ArrayList.class);
        mockList.add(1);
        mockList.add(2);
        mockList.add(3);
        mockInsertionSorter.sort(mockList,100,true);
        verify(mockInsertionSorter, times(1)).sort(anyListOf(Comparable.class),isA(Integer.class),isA(Boolean.class));
    }
    @Test
    public void testSortDescending(){
        InsertionSorter mockInsertionSorter = mock(InsertionSorter.class);
        List<Comparable> mockList = mock(ArrayList.class);
        mockList.add(1);
        mockList.add(2);
        mockList.add(3);
        mockInsertionSorter.sort(mockList,100,false);
        verify(mockInsertionSorter, times(1)).sort(anyListOf(Comparable.class),isA(Integer.class),isA(Boolean.class));
    }
}