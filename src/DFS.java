public class DFS {

    private static final int NUMVARS = 8;


    int[] statevector = { 0,0,0,0,0,0,0,0 };


    int[] variableOrder = {0,0,0,0,0,0,0,0 };


    int[] testVector = { 1,0,5,0,0,6,0,0 };


    int numFailures = 0;

    // stavector[0] = a
    // stavector[1] = b
    // stavector[2] = c
    // stavector[3] = d
    // stavector[4] = e
    // stavector[5] = f
    // stavector[6] = g
    // stavector[7] = h





    public void checklevel(int d, int[] statevector) {

        if (d == NUMVARS) {
            printSolution(statevector);
            return;
        }

        for (int i = 1; i <= 4; i ++) {
            statevector[d] = i;
            if (! consistent(statevector)) {
                numFailures++;
                printFail(statevector);

            } else {
                checklevel(d+1, statevector);
            }
            statevector[d] = 0;
        }

    }

    public void printFail(int[] statevector) {
        int ch1 = 'a';
        for (int variable : statevector) {

            if (variable != 0) {
                System.out.print((char)ch1 + " = " + variable + ", ");
            }
            ch1++;

        }
        System.out.println("FAIL " + "Number of failures = " + numFailures);
    }

    private boolean consistent(int[] statevector) {   // returns true if constraints are satisfied else false
        int a = statevector[0];
        int b = statevector[1];
        int c = statevector[2];
        int d = statevector[3];
        int e = statevector[4];
        int f = statevector[5];
        int g = statevector[6];
        int h = statevector[7];

        // A >= G
        if (isVoid(a,g)) {
            if (! (a >= g)){
                return false;
            }
        }

        // A < H
        if (isVoid(a,h)) {
            if (!(a < h)) {
                return false;
            }
        }

        // |F –B| = 1

        if (isVoid(f,b)) {
            if (! (Math.abs(f - b) == 1) ) {
                return false;
            }

        }

        // G < H

        if (isVoid(g,h)) {
            if (! (g < h)) {
                return false;
            }
        }

        //G –C| = 1

        if (isVoid(g,c)){
            if (! (Math.abs(g-c) == 1)){
                return false;
            }
        }

        // H –C| is even

        if (isVoid(h,c)) {
            if (! (Math.abs(h-c) % 2 == 0)) {
                return false;
            }
        }

        //H != D

        if(isVoid(h,d)) {
            if (! (h != d)){
                return false;
            }
        }

        //D ≥ G

        if (isVoid(d,g)){
            if (! (d >= g)) {
                return false;
            }
        }

        // D != C

        if ( isVoid(d,c)) {
            if (! (d != c)) {
                return false;
            }
        }

        // E != C

        if ( isVoid(e,c)) {
            if (! (e != c)) {
                return false;
            }
        }

        //E < D –1

        if ( isVoid(e,d)){
            if (! (e < (d -1))) {
                return false;
            }
        }

        // E != H –2
        if ( isVoid(e,h)){
            if (! (e != (h -2))) {
                return false;
            }
        }

        // G != F

        if ( isVoid(g,f)){
            if (! (g != f)) {
                return false;
            }
        }

        // H != F

        if ( isVoid(h,f)){
            if (! (h != f)) {
                return false;
            }
        }

        //C != F

        if ( isVoid(c,f)){
            if (! (c != f)) {
                return false;
            }
        }

        //D != F

        if ( isVoid(d,f)){
            if (! (d != f)) {
                return false;
            }
        }

        // |E –F| is odd

        if (isVoid(e,f)) {
            if (! (Math.abs(e-f) % 2 == 1)) {
                return false;
            }
        }

        return true;
    }

    boolean isVoid (int a, int b) { // returns true if variable assignments are 0
        return (a != 0 && b != 0);
    }

    public void printSolution(int[] statevector){
        int ch1 = 'a';

        for (int variable : statevector) {
            System.out.print((char)ch1 + " = " + variable + ", ");
            ch1 ++;
        }
        System.out.print("SOLUTION");
        System.out.println();

    }


}