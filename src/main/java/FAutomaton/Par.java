package FAutomaton;

class Par<A, B> implements Comparable<Par> {
    A a;
    B b;

    Par(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Par outroPar) {
        // Se o par passado veio depois lexicograficamente
            // -1
        // Se ele veio antes
            //  1
        // Se forem iguais
            //  0

        int x = this.a.toString().compareTo(outroPar.a.toString());
        int y = this.b.toString().compareTo(outroPar.b.toString());

        if (x == 0) {    // A1 == A2
            if (y == 0)      // B1 == B2
                return 0;       // São iguais
            else if (y < 0)  // B1 < B2
                return -1;
            else            // B1 > B2
                return 1;
        }
        else if (x < 0) // A1 < A2
            return -1;
        else            // A1 > A2
            return 1;
    }
}
