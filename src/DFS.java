public class DFS {

    private static final int NUMVARS = 8;


    int[] stateVector = {0, 0, 0, 0, 0, 0, 0, 0};

    // stavector[0] = a
    // stavector[1] = b
    // stavector[2] = c
    // stavector[3] = d
    // stavector[4] = e
    // stavector[5] = f
    // stavector[6] = g
    // stavector[7] = h

    // used to reorder variables eg. reorder [2,5,6] means input [C,F,G ...] according to the index values above.
    // set to 0,1,2,3,4,5,6,7 for ordering ABCDEFGH
    int[] reOrder = {0, 1, 2, 3, 4, 5, 6, 7};


    // the number of constraints each variable is involved in. used to compute degree heuristic. eg. A is involved in 2 constraints, B is involved in 1, C is involved in 5 ...
    //                      a b c d e f g h
    int[] numConstraints = {2,1,5,5,4,6,5,6};


    int numFailures = 0;


    public void checklevel(int d, int[] stateVector) {

        if (d == NUMVARS) {
            printSolution(stateVector);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            stateVector[reOrder[d]] = i;
            if (!consistent(stateVector)) {
                numFailures++;
                printFail(stateVector);

            } else {
                checklevel(d + 1, stateVector);
            }
            stateVector[reOrder[d]] = 0;
        }

    }

    public void printFail(int[] stateVector) {
        int ch1 = 'a';
        for (int variable : stateVector) {

            if (variable != 0) {
                System.out.print((char) ch1 + " = " + variable + ", ");
            }
            ch1++;

        }
        System.out.println("FAIL " + "Number of failures = " + numFailures);
    }

    private boolean consistent(int[] stateVector) {   // returns true if constraints are satisfied else false
        int a = stateVector[0];
        int b = stateVector[1];
        int c = stateVector[2];
        int d = stateVector[3];
        int e = stateVector[4];
        int f = stateVector[5];
        int g = stateVector[6];
        int h = stateVector[7];

        // A >= G
        if (isVoid(a, g)) {
            if (!(a >= g)) {
                return false;
            }
        }

        // A < H
        if (isVoid(a, h)) {
            if (!(a < h)) {
                return false;
            }
        }

        // |F –B| = 1

        if (isVoid(f, b)) {
            if (!(Math.abs(f - b) == 1)) {
                return false;
            }

        }

        // G < H

        if (isVoid(g, h)) {
            if (!(g < h)) {
                return false;
            }
        }

        //G –C| = 1

        if (isVoid(g, c)) {
            if (!(Math.abs(g - c) == 1)) {
                return false;
            }
        }

        // H –C| is even

        if (isVoid(h, c)) {
            if (!(Math.abs(h - c) % 2 == 0)) {
                return false;
            }
        }

        //H != D

        if (isVoid(h, d)) {
            if (!(h != d)) {
                return false;
            }
        }

        //D ≥ G

        if (isVoid(d, g)) {
            if (!(d >= g)) {
                return false;
            }
        }

        // D != C

        if (isVoid(d, c)) {
            if (!(d != c)) {
                return false;
            }
        }

        // E != C

        if (isVoid(e, c)) {
            if (!(e != c)) {
                return false;
            }
        }

        //E < D –1

        if (isVoid(e, d)) {
            if (!(e < (d - 1))) {
                return false;
            }
        }

        // E != H –2
        if (isVoid(e, h)) {
            if (!(e != (h - 2))) {
                return false;
            }
        }

        // G != F

        if (isVoid(g, f)) {
            if (!(g != f)) {
                return false;
            }
        }

        // H != F

        if (isVoid(h, f)) {
            if (!(h != f)) {
                return false;
            }
        }

        //C != F

        if (isVoid(c, f)) {
            if (!(c != f)) {
                return false;
            }
        }

        //D != F

        if (isVoid(d, f)) {
            if (!(d != f)) {
                return false;
            }
        }

        // |E –F| is odd

        if (isVoid(e, f)) {
            if (!(Math.abs(e - f) % 2 == 1)) {
                return false;
            }
        }

        return true;
    }

    boolean isVoid(int a, int b) { // returns true if variable assignments are 0
        return (a != 0 && b != 0);
    }

    public void printSolution(int[] statevector) {
        int ch1 = 'a';

        for (int variable : statevector) {
            System.out.print((char) ch1 + " = " + variable + ", ");
            ch1++;
        }
        System.out.print("SOLUTION");
        System.out.println();

    }

    //Fills up the reOrder array so that the variable involved with the most constraints is assigned a value first. in the case of ties, the reOrder array is filled in order
    public void degreeHeuristic () {
        for (int i = 0; i < NUMVARS; i ++) {
           int j = maxElementIndex(numConstraints);
           reOrder[i] = j;
           numConstraints[j] = -1; // note we are destroying the num constraints array but that's ok, do not reuse.

        }
    }


    // returns index of the largest element in vector
    public int maxElementIndex(int[] vector) {
        int j = 0;
        int b = vector[0];  // b= biggest so far

        for (int i = 1; i < vector.length; i ++) {
            if (vector[i] > b) {
                b = vector[i];
                j = i;
            }

        }
        return j;

    }


}