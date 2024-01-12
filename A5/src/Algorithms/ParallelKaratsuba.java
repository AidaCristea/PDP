package Algorithms;

import model.Polynomial;
import model.PolynomialOperations;

import java.util.concurrent.*;

public class ParallelKaratsuba {
    private static final int nrThreads = 4;
    private static final int maxDepth = 4;

    public static Polynomial multiply(Polynomial p1, Polynomial p2, int currentDepth) throws ExecutionException, InterruptedException {
        System.out.println("In Parallel Karatsuba");

        if (currentDepth > maxDepth)
            return SequentialKaratsuba.multiply(p1, p2);

        if (p1.getDegree() < 2 || p2.getDegree() < 2)
            return SequentialKaratsuba.multiply(p1, p2);

        int len = Math.max(p1.getDegree(), p2.getDegree()) / 2;

        Polynomial firstHalfP1 = new Polynomial(p1.getCoefficients().subList(0, len));
        //System.out.println("fP1 = "+firstHalfP1);
        Polynomial secondHalfP1 = new Polynomial(p1.getCoefficients().subList(len, p1.getCoefficients().size()));
        //System.out.println("sP1 = "+secondHalfP1);
        Polynomial firstHalfP2 = new Polynomial(p2.getCoefficients().subList(0, len));
        //System.out.println("fP2 = " +firstHalfP2);
        Polynomial secondHalfP2 = new Polynomial(p2.getCoefficients().subList(len, p2.getCoefficients().size()));
        //System.out.println("sP2 = " +secondHalfP2);

        ExecutorService executorService = Executors.newFixedThreadPool(nrThreads);

        //z0 = x0*y0
        Future<Polynomial> f1 = executorService.submit(() -> multiply(firstHalfP1, firstHalfP2, currentDepth + 1));

        //partial z1 = (x1+x0)*(y1+y0)
        Future<Polynomial> f2 = executorService.submit(() -> multiply(PolynomialOperations.add(firstHalfP1, secondHalfP1), PolynomialOperations.add(firstHalfP2, secondHalfP2), currentDepth + 1));

        //z2 = x1*y1
        Future<Polynomial> f3 = executorService.submit(() -> multiply(secondHalfP1, secondHalfP2, currentDepth + 1));

        executorService.shutdown();

        Polynomial z1 = f1.get();
        Polynomial z2 = f2.get();
        Polynomial z3 = f3.get();

        executorService.awaitTermination(6, TimeUnit.SECONDS);

        //compute final res

        //final z1 = (x1+x0)*(y1+y0) - z2 - z0
        //final z1 = partial z1 - z2 - z0
        Polynomial r1 = PolynomialOperations.addZeros(z3, 2 * len);
        Polynomial r2 = PolynomialOperations.addZeros(PolynomialOperations.subtract(PolynomialOperations.subtract(z2, z3), z1), len);
        return PolynomialOperations.add(PolynomialOperations.add(r1, r2), z1);

    }
}
