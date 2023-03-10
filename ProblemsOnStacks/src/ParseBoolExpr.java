public class ParseBoolExpr {
    static int index;
    public static boolean parseBoolExpr(String expression) {
        index = 0;
        return temp(expression);
    }
    public static boolean temp(String expression) {
        boolean bool = false;
        if(expression.length() == 1){
            if(expression.equals("f")) return false;
            else return expression.equals("t");
        }
        for(int i = index; i<expression.length(); i++){
            if(expression.charAt(i) == '|'){
                i+= 2;
                bool = false;
                for(; i<expression.length() && expression.charAt(i) != ')'; i++){
                    if(expression.charAt(i) == 't') bool = true;
                    else if (expression.charAt(i) == '!' || expression.charAt(i) == '|' || expression.charAt(i) == '&'){
                        index = i;
                        bool |= temp(expression);
                        i = index;
                    }
                }
            }
            else if(expression.charAt(i) == '&'){
                i+= 2;
                bool = true;
                for(; i<expression.length() && expression.charAt(i) != ')'; i++){
                    if(expression.charAt(i) == 'f') bool = false;
                    else if (expression.charAt(i) == '!' || expression.charAt(i) == '|' || expression.charAt(i) == '&'){
                        index = i;
                        bool &= temp(expression);
                        i = index;
                    }
                }
            }
            else if(expression.charAt(i) == '!'){
                i+= 2;
                bool = true;
                for(; i<expression.length() && expression.charAt(i) != ')'; i++){
                    bool = (expression.charAt(i) == 'f');
                    if (expression.charAt(i) == '!' || expression.charAt(i) == '|' || expression.charAt(i) == '&'){
                        index = i;
                        bool = !(temp(expression));
                        i = index;
                    }
                }
            }
            if(i<expression.length() && expression.charAt(i) == ')') {
                index = i;
                break;
            }

        }


        return bool;
    }

    public static void main(String[] args) {
        System.out.println(parseBoolExpr("!(&(!(&(f)),&(t),|(f,f,t)))"));
        System.out.println(parseBoolExpr("!(&(!(t),&(f),|(f)))"));
        System.out.println(parseBoolExpr("t"));
        System.out.println(parseBoolExpr("f"));
        System.out.println(parseBoolExpr("!(f)"));
        System.out.println(parseBoolExpr("|(f,t)"));
        System.out.println(parseBoolExpr("&(t,f)"));
        System.out.println(parseBoolExpr("|(f,&(t,t))"));
        System.out.println(parseBoolExpr("!(&(t,t),&(t,t),&(t,|(t,t)))"));
        System.out.println(parseBoolExpr("t"));
        System.out.println(parseBoolExpr("t"));
    }
}
