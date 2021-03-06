

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMedian {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        private int travPos =0;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        public void insert(int val) {
            if( val < this.val) {
                insert(this.left, val, true);
            } else {
                insert(this.right, val, false);
            }
        }

        private void insert(final TreeNode treeNode, final int val, boolean leftNode) {
            if( treeNode != null) {
                treeNode.insert(val);
            } else {
                TreeNode node = new TreeNode(val);
                if(leftNode) {
                    this.left = node;
                } else {
                    this.right = node;
                }
            }
        }

        public TreeNode remove(int val) {
            if(val > this.val && this.right != null) {
                this.right = this.right.remove(val);
            } else if(val < this.val && this.left != null) {
                this.left = this.left.remove(val);
            } else if(this.val == val) {
                if(this.right == null && this.left == null) {
                    return null;
                } else if(this.right == null) {
                    this.val = this.left.findMax();
                    this.left = this.left.remove(this.val);
                } else {
                    this.val = this.right.findMin();
                    this.right = this.right.remove(this.val);
                }
            }
            return this;
        }

        private int findMin() {
            if(left == null) {
                return val;
            }
            return left.findMin();
        }

        private int findMax() {
            if(right == null) {
                return val;
            }
            return right.findMax();
        }

        public int get(int pos) {
            travPos = 0;
            return inOrderAt(this, pos);
        }

        private int inOrderAt(final TreeNode treeNode, final int pos) {
            if(treeNode == null ) {
                return Integer.MIN_VALUE;
            }
            int result = inOrderAt(treeNode.left, pos);
            if(result != Integer.MIN_VALUE) {
                return result;
            }
            travPos++;
            if( travPos == pos) {
                return treeNode.val;
            }
            return inOrderAt(treeNode.right, pos);
        }
    }

    public static Double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> medians = new ArrayList<>();
        TreeNode pq = new TreeNode(nums[0]);
        int kWinStart = 0;
        for(int i=1;i<k;i++) {
            pq.insert(nums[i]);
        }
        medians.add(getMedian(pq, k));
        for(int i=kWinStart+1;i<=nums.length - k;i++) {
            pq = pq.remove(nums[i-1]);
            pq.insert(nums[i+k-1]);
            medians.add(getMedian(pq, k));
        }
        Double[] mediansPrim = new Double[medians.size()];
        return medians.toArray(mediansPrim);
    }

    private static Double getMedian(final TreeNode pq, final int k) {
        int mid = k/2 + 1;
        double median = (double) pq.get(mid);
        if( k % 2 == 0) {
            median = ((median +pq.get(mid-1)) /2.0d);
        }
        return median;
    }

    public static void main(String[] args) {
        Double[] medians = medianSlidingWindow(new int[] {1,8,8,3,5,2,2,6,6,7}, 8);
        for(Double i : medians) {
            System.out.print(i + ",");
        }
        System.out.println("");
    }

}
