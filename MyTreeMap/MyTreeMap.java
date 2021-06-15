package MyTreeMap;

public class MyTreeMap {
    private TreeNode root;

    public Integer put(String key,Integer val){
        if(root == null){
            root = new TreeNode(key,val);
            return null;
        }

        TreeNode parent = null;
        TreeNode cur = root;
        while(cur != null){
            int cmp = key.compareTo(cur.key);
            if(cmp == 0){
                Integer oldValue = cur.val;
                 cur.val = val;
                return  oldValue;
            }else if(cmp < 0){
                parent = cur;
                cur = cur.right;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        return null;
    }

    public Integer get(String key){
        TreeNode cur = root;
        while(cur != null){
            int cmp = key.compareTo(cur.key);
            if(cmp == 0){
                return cur.val;
            }else if (cmp < 0){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return null;
    }

    public Integer getOrDefault(String key,Integer defaultValue){
        Integer v = get(key);
        if(v != null){
            return v;
        }

        return defaultValue;
    }

}

