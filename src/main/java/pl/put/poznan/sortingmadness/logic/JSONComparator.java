package pl.put.poznan.sortingmadness.logic;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Comparator;

public class JSONComparator implements Comparator<JSONObject> {
    private String deciding_param;
    public JSONComparator(String deciding_param){
        this.deciding_param = deciding_param;
    }


    @Override
    public int compare(JSONObject o1, JSONObject o2) {

        Object o1_param = o1.get(deciding_param);
        Object o2_param = o2.get(deciding_param);
        if(o1_param instanceof Integer){
            Integer o1_param_val = (Integer) o1_param;
            Integer o2_param_val = (Integer) o2_param;
            return o1_param_val.compareTo(o2_param_val);
        }else if(o1_param instanceof Long){
            Long o1_param_val = (Long) o1_param;
            Long o2_param_val = (Long) o2_param;
            return o1_param_val.compareTo(o2_param_val);
        }else if(o1_param instanceof Float){
            Float o1_param_val = (Float) o1_param;
            Float o2_param_val = (Float) o2_param;
            return o1_param_val.compareTo(o2_param_val);
        }else if(o1_param instanceof Double){
            Double o1_param_val = (Double) o1_param;
            Double o2_param_val = (Double) o2_param;
            return o1_param_val.compareTo(o2_param_val);
        }else if(o1_param instanceof String){
            String o1_param_val = (String) o1_param;
            String o2_param_val = (String) o2_param;
            return o1_param_val.compareTo(o2_param_val);
        }else{
            return 0;
        }
    }


}
