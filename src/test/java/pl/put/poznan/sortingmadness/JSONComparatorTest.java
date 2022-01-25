package pl.put.poznan.sortingmadness;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sortingmadness.logic.JSONComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
public class JSONComparatorTest {
    @Test
    public void testCompare() throws JSONException {
        JSONObject object1 = Mockito.mock(JSONObject.class);
        JSONObject object2 = Mockito.mock(JSONObject.class);
        when(object1.get("name")).thenReturn("Ala");
        when(object2.get("name")).thenReturn("Ola");
        List<String> keys = new ArrayList<>();
        keys.add("name");
        JSONComparator comparator = new JSONComparator(keys);
        assertTrue(comparator.compare(object1,object2)<0);
        assertTrue(comparator.compare(object2,object1)>0);
    }

}
