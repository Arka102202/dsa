import java.util.LinkedList;

public class SolutionRegxMatch {

    public static int sCount, pCount, k;
    public static char aADS_WithoutStar, aADS_WithoutStar1;
    public static LinkedList<Character> aADS_WithStar = new LinkedList<>();
    public static boolean flag = false, flagDotStar2 = false;
    public static void main(String[] args) {
        String s = "ccc";
        String p = ".*a*b*ccc";
        System.out.println(isMatch(s, p));
    }
    /**
     * starting of every recursion the recursive function will have 3 options:
     * 1.  search for ".*"
     * 2.  search for "(someAlpha)*"
     * 3.  search for "someAlpha" or a "."
     * 4.  search for ".*." or ".*........."
     * <p>
     * if it finds ""option 1"" then
     * 1.   the function will go on and look for the alpha,""""" which doesn't have any * following it""""", after ".*"
     *      and count how many times that have been repeated and save that in "pCount" and the alpha
     *      as "alphaAfterDotStar" or "aADS_WithoutStar".
     * 2.   the function will go on and look for the alphas,""""" which have * following it""""", after ".*"
     *      and store them in an array --> aASD_WithStar and when it doesn't find any more of this kind of alphabet,
     *      then it repeats the previous step.
     *
     * for 1--> then the function will have two options:
     *
     * A: either it will find a match with the "aADS_WithoutStar"
     * B: or not
     * <p>
     * A--> if it finds a match then:
     *          it will count how many times that have been repeated and save that as "sCount" and then
     *          it will check:
     *              if (sCount >= pCount):
     *                  a:  if "yes"; it will add the "aADS_WithoutStar" to "newS" sCount times.
     *                      and increase i by 3 and j by "sCount"; --> """"call the main function.""""
     * <p>
     * B--> if it does not find a match then:
     *      it will add the next character from the string to newS
     *      and increase i by 0 and j by 1; --> """"call the main function.""""
     *
     * for 2--> then the function will have two options:
     *
     * A: it tries to find a match from the array --> aASD_WithStar and if it finds a match:
     *      then it increases i by 3 and j by "sCount"; --> """"call the main function.""""
     *
     * B: if it finds a match with the "aADS_WithoutStar" then it repeats the steps for --> 1.
     *
     *
     *
     * if it finds ""option 2"" then
     * 1st, it will store the character in "aADS_WithoutStar".
     *      then go on and look for that same character,""""" which doesn't have any * following it""""", after "*"
     *      and count how many times that have been repeated and save that in "pCount".
     *
     * then it will search for the same character in String,
     * if found then count the number of times it has been repeated and then put the count in "sCount".
     * then it checks if (sCount >= pCount):
     *      if "yes" --> it increases i by (3+pCount) and j by "sCount"; --> """"call the main function.""""
     */
    public static boolean isMatch(String s, String p) {

        isMatch(s, p, 0, 0);
        return flag;
    }
    public static void isMatch(String s, String p, int i, int j) {
        if (j < s.length()) {
            //option 1:
            if ((i + 1) < p.length() && p.charAt(i) == '.' && p.charAt(i + 1) == '*') {

                // for --> ".*aaaaaa"
                if (i < p.length() - 3 && p.charAt(i + 3) != '*') {
                    aADS_WithoutStar = p.charAt(i + 2);
                    pCount++;
                    while (i < (p.length() - 3 - pCount) && aADS_WithoutStar == p.charAt(i + 2 + pCount) && p.charAt(i + 3 + pCount) != '*') {
                        pCount++;
                    }
                }
                // for --> ".*a*c*a"
                else if (i < p.length() - 3 && p.charAt(i + 3) == '*') {
                    flagDotStar2 = true;



                    k=0;
                    aADS_WithStar.add(p.charAt(i + 2));
                    int l = i + 2 + 2;
                    while (l < p.length() - 1 && p.charAt(l + 1) == '*') {
                        aADS_WithStar.add(p.charAt(l++));
                    }
                    if (l < p.length()) {
                        aADS_WithoutStar = p.charAt(++l);
                        pCount++;
                        while (l < p.length()-(1 + pCount) && aADS_WithoutStar == p.charAt(l + pCount) && p.charAt(l + 1 + pCount) != '*') {
                            pCount++;
                        }
                        k = 0;
                        while (k < aADS_WithStar.size() ) {
                            if (s.charAt(j) == aADS_WithStar.get(k++)){
                                flag = true;
                                aADS_WithStar.clear();
                                isMatch(s, p, i + 2, j);
                            }
                        }
                    }



                }
                //for --> ".*aaaaaa"
                else if (i < p.length() - 2) {
                    aADS_WithoutStar = p.charAt(i + 2);
                    pCount++;
                    while (i < (p.length() - 3 - pCount) && aADS_WithoutStar == p.charAt(i + 2 + pCount) && p.charAt(i + 3 + pCount) != '*') {
                        pCount++;
                    }
                }
                //for --> ".*"
                else{
                    aADS_WithoutStar = p.charAt(i);
                    pCount =0;
                }

                if (s.charAt(j) == aADS_WithoutStar || aADS_WithoutStar == '.') {
                    sCount++;
                    while (j < (s.length() - sCount) && (aADS_WithoutStar == s.charAt(i + sCount) || aADS_WithoutStar == '.')) {
                        sCount++;
                    }
                    if (sCount >= pCount) {
                        i += 2+pCount;
                        j += sCount;
                        sCount = pCount = 0;
                        flag = true;
                        isMatch(s, p, i, j);
                    } else {
                        flag = false;
                    }
                }
                else {
                    if(!flagDotStar2){
                        pCount = 0;
                        flag = true;
                        isMatch(s, p, i, j + 1);
                    }else{
                        pCount = 0;
                        flag = true;
                        if(s.charAt(j) != aADS_WithoutStar){
                            isMatch(s, p, i, j + 1);
                        }else if(j < s.length()-1 && s.charAt(j) == aADS_WithoutStar && s.charAt(j+1) == aADS_WithoutStar){
                            isMatch(s, p, i, j + 1);
                            flagDotStar2 = false;
                        }else{
                            isMatch(s, p, i, j);
                            flagDotStar2 = false;
                        }
                    }
                }
            }


            //option 2:
            else if ((i + 1) < p.length() && p.charAt(i) != '.' && p.charAt(i + 1) == '*'){
                aADS_WithoutStar = p.charAt(i);
                int o = i+2;
                pCount = 0;
                if(o < p.length()-1 && aADS_WithoutStar == p.charAt(o) && p.charAt(o + 1) != '*'){
                    pCount++;
                    o++;
                }




                k=0;
                aADS_WithStar.add(p.charAt(i + 2));
                int l = i + 2 + 2;
                while (l < p.length() - 1 && p.charAt(l + 1) == '*') {
                    aADS_WithStar.add(p.charAt(l++));
                }
                if (l < p.length()) {
                    aADS_WithoutStar1 = p.charAt(l);
                    pCount++;
                    while (l < p.length()-(1 + pCount) && aADS_WithoutStar == p.charAt(l + pCount) && p.charAt(l + 1 + pCount) != '*') {
                        pCount++;
                    }
                    k = 0;
                    while (k < aADS_WithStar.size()) {
                        if (s.charAt(j) == aADS_WithStar.get(k++)){
                            flag = true;
                            isMatch(s, p, i + 2, j);
                        }
                    }
                }



                if(s.charAt(j) == aADS_WithoutStar){
                    sCount++;
                    while (j < (s.length() - sCount) && aADS_WithoutStar == s.charAt(j + sCount)) {
                        sCount++;
                    }
                    if(sCount == pCount && aADS_WithStar.size() > 0){
                        isMatch(s, p, i+2, j);
                        aADS_WithStar.clear();
                    }
                    else if (sCount >= pCount) {
                        i += 2+pCount;
                        j += sCount;
                        sCount = pCount = 0;
                        flag = true;
                        if(j < s.length()-1 && s.charAt(j) == aADS_WithoutStar && s.charAt(j+1) == aADS_WithoutStar){
                            isMatch(s, p, i, j);
                        }else{
                            isMatch(s, p, i-1, j-1);
                        }
                        flagDotStar2 = false;
                    } else {
                        flag = false;
                    }
                }else {
                    pCount = 0;
                    flag = true;
                    isMatch(s, p, i+2, j);
                }

            }
            //option 3:
            else if(i < p.length() && (p.charAt(i) == s.charAt(j) || p.charAt(i) == '.')){
                flag = true;
                isMatch(s,p,i+1,j+1);
            }
            else{
                flag = false;
            }
        }
        // when there are no-more alphabets in string to match with pattern
        else{
            while(i < p.length()-1 && p.charAt(i+1)=='*'){
                i+=2;
            }
            flag = i == p.length();
        }


    }




}