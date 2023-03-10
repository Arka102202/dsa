import java.util.*;

public class EvaluateDivision {
    static HashSet<String> seen = new HashSet<>();
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] r = new double[queries.size()];
        HashMap<String, HashSet<String>> equationMap = new HashMap<>();
        HashMap<String, Double> eV = new HashMap<>(); //expression ==> value
        int i = 0;
        for(List<String> list:equations){
            String e1 = list.get(0), e2 = list.get(1);
            eV.put(e1+" "+e2, values[i]);
            eV.put(e2+" "+e1, 1/values[i]);
            if(!equationMap.containsKey(e1)) equationMap.put(e1,new HashSet<>());
            HashSet<String> temp1 = equationMap.get(e1);
            temp1.add(e2);
            if(!equationMap.containsKey(e2)) equationMap.put(e2, new HashSet<>());
            HashSet<String> temp2 = equationMap.get(e2);
            temp2.add(e1);
            i++;
        }
        i = 0;
        for(List<String> list:queries){
            String e1 = list.get(0), e2 = list.get(1);
            if(e1.equals(e2) && equationMap.containsKey(e1)) r[i] = 1;
            else if(eV.containsKey(e1+" "+e2)) r[i] = eV.get(e1+" "+e2);
            else {
                double val = calculateCrossConnection(equationMap, eV, e1, e2);
                r[i] = val >= 0 ? val:-1;
                seen.clear();
            }
            i++;
        }

        return r;
    }
    static double calculateCrossConnection(HashMap<String, HashSet<String>> equationMap,
                                           HashMap<String, Double> eV, String e1, String e2){
        if(equationMap.containsKey(e1)){
            seen.add(e1);
            HashSet<String> temp1 = equationMap.get(e1);
            for(String s:temp1){
                if(!seen.contains(s)){
                    if(eV.containsKey(s+" "+e2)) return eV.get(e1+" "+s) * eV.get(s+" "+e2);
                    else {
                        double val =eV.get(e1+" "+s)*calculateCrossConnection(equationMap, eV, s, e2);
                        if(val >= 0) return val;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                calcEquation(List.of(List.of("a", "b"), List.of("ab", "c"), List.of("a", "bc")),
                        new double[]{2.0, 3.0, 4.0},
                        List.of(List.of("a", "c"), List.of("ab", "c"), List.of("b", "a"), List.of("x", "x")))));

        System.out.println(Arrays.toString(
                calcEquation(List.of(List.of("x1", "x2"), List.of("x2", "x3"), List.of("x3", "x4"), List.of("x4", "x5")),
                        new double[]{3.0, 4.0, 5.0, 6.0},
                        List.of(List.of("x1", "x5"), List.of("x5", "x2"), List.of("x2", "x4"), List.of("a", "a"), List.of("x", "x")))));

        System.out.println(Arrays.toString(
                calcEquation(List.of(List.of("a", "b"), List.of("b", "c"), List.of("c", "d"), List.of("d", "e")),
                        new double[]{2.0, 3.0, 4.0, 5.0},
                List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x")))));
    }
}
