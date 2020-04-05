public class Actions {
    private BinSearchTree myTree;
    private Graphics graphics;

    public Actions(Graphics g, BinSearchTree t){
        this.graphics = g;
        this.myTree = t;
    }

    public void insert(String id, String fac, String major, String yr){
        @Override
        public void actionPerformed(){
            myTree.insert(id, fac, major, yr);
        }
    }
}