// sparse table java implementation
// build takes O(NlogN) time
// qry returns maximum in a range L to R in O(1) time

static class Sparse_Table {
    private int t[][], n;
    public Sparse_Table(int N) {
      n = N;
      t = new int[N + 1][18];
    }
    public void build(int[] b, int N) {
      for(int i = 1; i <= N; ++i) {
        t[i][0] = b[i];
      }
      for(int j = 1; j < 18; ++j) {
        for(int i = 1; i + (1 << j) - 1 <= N; ++i) {
          t[i][j] = Math.max(t[i][j - 1], t[i + (1 << (j - 1))][j - 1]);
        }
      }
    }
    public int qry(int l, int r) {
      int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
      return Math.max(t[l][k], t[r - (1 << k) + 1][k]);
    }
  }
