package io.card.payment;

/* DetectionInfo.java
 * See the file "LICENSE.md" for the full license governing this code.
 */

/**
 * This class implements a data structure used to pass card detection details back and forth between
 * java and native code/
 */

class DetectionInfo {
    public boolean complete;
    public boolean topEdge;
    public boolean bottomEdge;
    public boolean leftEdge;
    public boolean rightEdge;
    public float focusScore;
    public int[] prediction;
    public int expiry_month;
    public int expiry_year;
    public nfa aa;

    public DetectionInfo() {
        complete = false;
        
        prediction = new int[16];
        prediction[0] = -1;
        prediction[15] = -1;

        aa = new nfa();
    }

    ;

    boolean sameEdgesAs(DetectionInfo other) {
        return other.topEdge == this.topEdge && other.bottomEdge == this.bottomEdge
                && other.leftEdge == this.leftEdge && other.rightEdge == this.rightEdge;
    }

    boolean detected() {
        return (topEdge && bottomEdge && rightEdge && leftEdge);
    }

    boolean predicted() {
        return complete;
    }

    nfa bca() {
        String numberStr = new String();
        for (int i = 0; i < 16 && 0 <= prediction[i] && prediction[i] < 10; i++) {
            numberStr += String.valueOf(prediction[i]);
        }
        aa.ag = numberStr;

        // set these regardless. They'll just be zeroes if not found.
        aa.bg = expiry_month;
        aa.cc = expiry_year;
        
        return aa;
    }

    int numVisibleEdges() {
        return (topEdge ? 1 : 0) + (bottomEdge ? 1 : 0) + (leftEdge ? 1 : 0) + (rightEdge ? 1 : 0);
    }
}
