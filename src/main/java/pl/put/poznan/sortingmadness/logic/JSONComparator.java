package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.util.Comparator;
import java.util.List;

/**
 * JSONComparator used to compare JSONObjects on specified keys
 * @author Mateusz Kolano
 * @version 1.1
 */
public class JSONComparator implements Comparator<JSONObject> {
    /**
     * Stores keys by which the objects will be sorted
     */
    private List<String> keys;
    public JSONComparator(List<String> keys){
        this.keys = keys;
    }

    /**
     * Compares two given JSONObjects.
     * Compares two properties either when they are either Integer, Long, Double or String.
     * If property type is different it assumes objects are equal on that given key.
     *
     * @param o1 object to compare
     * @param o2 object to compare
     * @return value lower than zero if o1 lesser than o2, zero if o1 equals o2 and value greater than zero if o1 greater than o2
     */
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        int ret_val = 0;
        for(String key: keys){
            Object o1_param = o1.get(key);
            Object o2_param = o2.get(key);
            int comp_value = 0;
            if(o1_param instanceof Integer){
                Integer o1_param_val = (Integer) o1_param;
                Integer o2_param_val = (Integer) o2_param;
                comp_value = o1_param_val.compareTo(o2_param_val);
            }else if(o1_param instanceof Long){
                Long o1_param_val = (Long) o1_param;
                Long o2_param_val = (Long) o2_param;
                comp_value =  o1_param_val.compareTo(o2_param_val);
            }else if(o1_param instanceof Float){
                Float o1_param_val = (Float) o1_param;
                Float o2_param_val = (Float) o2_param;
                comp_value =  o1_param_val.compareTo(o2_param_val);
            }else if(o1_param instanceof Double){
                Double o1_param_val = (Double) o1_param;
                Double o2_param_val = (Double) o2_param;
                comp_value =  o1_param_val.compareTo(o2_param_val);
            }else if(o1_param instanceof String){
                String o1_param_val = (String) o1_param;
                String o2_param_val = (String) o2_param;
                comp_value =  o1_param_val.compareToIgnoreCase(o2_param_val);
            }
            if(comp_value!=0) {
                ret_val = comp_value;
                break;
            }
        }

        return ret_val;
    }


}
