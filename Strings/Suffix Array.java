/*
About: Suffix Array
Complexity: O(N logN^2)
Date: February 22nd, 2023
Verified: https://judge.yosupo.jp/submission/127391
*/

public static class SuffixArray {
    String s;
    SuffixArray (String s) { 
        this.s = s + '$';
        int n = this.s.length();
        int[] equivalence_class = new int[n];
        TreeSet<Character> hs = new TreeSet<>();
        TreeMap<Character, Integer> hm = new TreeMap<>();
        for (int i = 0; i < this.s.length(); i++) {
            hs.add(this.s.charAt(i));
        }
        int cntr = 0;
        for (Character c: hs) {
            hm.put(c, cntr++);
        }
        for (int i = 0; i < n; i++) {
            equivalence_class[i] = hm.get(this.s.charAt(i));
        }
        ArrayList<Integer> vec = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vec.add(i);
        }
        for (int i = 0; (1 << i) < 4 * n; i++) {
            final int final_i = i;
            final int[] cur_equivalence_class = equivalence_class.clone();
            Comparator<Integer> cmp = (x, y) -> {
                if (cur_equivalence_class[x] == cur_equivalence_class[y]) {
                    return Integer.compare(cur_equivalence_class[(x + (1 << final_i)) % n], cur_equivalence_class[(y + (1 << final_i)) % n]);
                }
                return Integer.compare(cur_equivalence_class[x], cur_equivalence_class[y]);
            };
            vec.sort(cmp);
            equivalence_class[vec.get(0)] = 0;
            for (int j = 1; j < vec.size(); j++) {
                equivalence_class[vec.get(j)] = equivalence_class[vec.get(j - 1)];
                if (cmp.compare(vec.get(j), vec.get(j - 1)) != 0) {
                    equivalence_class[vec.get(j)]++;
                }
            }
        }
        for (int j = 1; j < vec.size(); j++) {
            System.out.print(vec.get(j) + " ");
        }
        System.out.println();
    }
}
