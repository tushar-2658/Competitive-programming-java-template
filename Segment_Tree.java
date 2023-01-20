// segment tree with lazy propagation
// updates a range with a number
// returns the minimum in range in O(logN) time


static class Segtree {
    private int n, tree[], lazy[];
    private int inf = 1000000000 + 7;
    public Segtree(int N) {
      n = N;
      tree = new int[4 * N + 1];
      lazy = new int[4 * N + 1];
    }
    public void push(int n, int b, int e) {
      if(lazy[n] == inf)return;
      tree[n] = Math.min(tree[n], lazy[n]);
      if(b != e) {
        lazy[2 * n] = Math.min(lazy[2 * n], lazy[n]);
        lazy[2 * n + 1] = Math.min(lazy[2 * n + 1], lazy[n]);
      }
      lazy[n] = inf;
    }
    public int combine(int a, int b) {
      return Math.min(a, b);
    }
    public void pull(int n) {
      tree[n] = Math.min(tree[2 * n], tree[2 * n + 1]);
    }
    public void build(int[] A, int n, int b, int e) {
      lazy[n] = inf;
      if(b == e) {
        tree[n] = A[b];
        return;
      }
      int mid = (b + e) >> 1;
      build(A, 2 * n, b, mid);
      build(A, 2 * n + 1, mid + 1, e);
      tree[n] = Math.min(tree[2 * n], tree[2 * n + 1]);
    }
    public void upd(int n, int b, int e, int i, int j, int v) {
      push(n, b, e);
      if(j < b || e < i)return;
      if(i <= b && e <= j) {
        lazy[n] = v;
        push(n, b, e);
        return;
      }
      int mid = (b + e) >> 1;
      upd(2 * n, b, mid, i, j, v);
      upd(2 * n + 1, mid + 1, e, i, j, v);
      pull(n);
    }
    public int qry(int n, int b, int e, int i, int j) {
      push(n, b, e);
      if(i > e || b > j)return inf;
      if(i <= b && e <= j)return tree[n];
      int mid = (b + e) >> 1;
      return combine(qry(2 * n, b, mid, i, j), qry(2 * n + 1, mid + 1, e, i, j));
    }
  }
