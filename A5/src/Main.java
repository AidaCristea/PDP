import Algorithms.ParallelKaratsuba;
import Algorithms.ParallelRegular;
import Algorithms.SequentialKaratsuba;
import Algorithms.SequentialRegular;
import model.Algorithm;
import model.Method;
import model.Polynomial;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    private static Method method;// = Method.PARALLELIZED;
    private static Algorithm algorithm;// = Algorithm.KARATSUBA;


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Polynomial p1 = new Polynomial(100);
        System.out.println("p1 = " + p1);
        Polynomial p2 = new Polynomial(100);
        System.out.println("p2 = " + p2);
        //Main.start(p1, p2);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the method (SEQUENTIAL OR PARALLELIZED): ");
            String inputMethod = scanner.nextLine().toUpperCase();

            System.out.println("Enter the algorithm (REGULAR OR KARATSUBA): ");
            String inputAlgorithm = scanner.nextLine().toUpperCase();
            Main.start(p1, p2, inputMethod, inputAlgorithm);

        }


        /*System.out.println(po.add(p1, p2));
        System.out.println(po.subtract(p1, p2));
        System.out.println(po.addZeros(p1, 2));

        System.out.println();
        SequentialRegular sr = new SequentialRegular();
        System.out.println(sr.multiply(p1, p2));*/

        /*List<Integer> p1Coeffs = new ArrayList<>();
        p1Coeffs.add(4);
        p1Coeffs.add(3);
        p1Coeffs.add(2);

        List<Integer> p2Coeffs = new ArrayList<>();
        p2Coeffs.add(1);
        p2Coeffs.add(2);
        p2Coeffs.add(5);


        Polynomial p1 = new Polynomial(p1Coeffs);
        System.out.println(p1);

        Polynomial p2 = new Polynomial(p2Coeffs);
        System.out.println(p2);

        *//*SequentialKaratsuba sk = new SequentialKaratsuba();
        System.out.println(sk.multiply(p1, p2));*//*


         *//* ParallelRegular pr = new ParallelRegular();
        System.out.println(pr.multiply(p1, p2));*//*

        ParallelKaratsuba pk = new ParallelKaratsuba();
        System.out.println(pk.multiply(p1, p2,1 ));
*/
    }


    private static void start(Polynomial p1, Polynomial p2, String inputMethod, String inputAlgorithm) throws InterruptedException, ExecutionException {
        Polynomial res;
        method = Method.valueOf(inputMethod);
        algorithm = Algorithm.valueOf(inputAlgorithm);
        switch (method) {
            case SEQUENTIAL:
                if (algorithm.equals(Algorithm.REGULAR))
                    res = SequentialRegular.multiply(p1, p2);
                else res = SequentialKaratsuba.multiply(p1, p2);
                break;
            case PARALLELIZED:
                if (algorithm.equals(Algorithm.REGULAR))
                    res = ParallelRegular.multiply(p1, p2);
                else res = ParallelKaratsuba.multiply(p1, p2, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);

        }
        System.out.println("result = " + res);
    }


}


