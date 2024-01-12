package model;

public class Task implements Runnable {
    //[start,end] is the range of indices for which the task is responsible in the result coeffs list
    private final int start;
    private final int end;
    private final Polynomial p1;
    private final Polynomial p2;
    private final Polynomial res;

    public Task(int start, int end, Polynomial p1, Polynomial p2, Polynomial res) {
        this.start = start;
        this.end = end;
        this.p1 = p1;
        this.p2 = p2;
        this.res = res;
    }

    @Override
    public void run() {
        /*System.out.println("in task run");

        System.out.println("start = " + start);
        System.out.println("end = " + end);*/

        for (int i = start; i < end; i++) {
            if (i > res.getCoefficients().size())
                return;

            //find all the pairs that we add to obtain the value of the result coeff
            for (int j = 0; j <= i; j++) {
                if (j < p1.getCoefficients().size() && (i - j) < p2.getCoefficients().size()) {
                    //System.out.println("i= " + i + " j=" + j);
                    int val = p1.getCoefficients().get(j) * p2.getCoefficients().get(i - j);
                    //System.out.println("val=" + val);
                    res.getCoefficients().set(i, res.getCoefficients().get(i) + val);
                }
            }
        }
    }

}
