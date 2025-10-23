int combinations(int n, int k) {
    if (k == 0 || k == n) return 1;
    if (k > n) return 0;
    return combinations(n - 1, k - 1) + combinations(n - 1, k);
}

void main() {
    System.out.println(combinations(30 ,5));
}