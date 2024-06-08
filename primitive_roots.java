import java.util.*;

public class Main{

    static boolean isPrimitiveRoot(int a, int n) {
        Set<Integer> residues = new HashSet<>();
        int current = 1 % n;

        for (int i = 1; i < n; i++) {
            if (residues.contains(current))
                return false;
            residues.add(current);
            current = (current * a) % n;
        }

        return residues.size() == n - 1;
    }

    static List<Integer> findPrimitiveRoots(int n) {
        List<Integer> roots = new ArrayList<>();

        for (int a = 2; a < n; a++) {
            if (isPrimitiveRoot(a, n)) {
                roots.add(a);
            }
        }

        return roots;
    }

    public static void main(String[] args) {
        int n = 11; // Change this to the desired modulo value
        List<Integer> primitiveRoots = findPrimitiveRoots(n);

        if (primitiveRoots.isEmpty()) {
            System.out.println("No primitive roots found modulo " + n);
        } else {
            System.out.println("Primitive roots modulo " + n + ": " + primitiveRoots);
        }
    }
}
