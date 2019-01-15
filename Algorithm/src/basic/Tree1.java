package basic;

public class Tree1 {

	public static void add(Node parent, Node child){
		if(parent == null){
			parent = child;
		}
		
		if(parent.getData() > child.getData()){
			if(parent.getLeftChild() == null){
				parent.setLeftChild(child);
			}else{
				add(parent, child);
			}
		}else if(parent.getData() < child.getData()){
			if(parent.getRightChild() == null){
				parent.setRightChild(child);
			}else{
				add(parent, child);
			}
		}
		
	}
	
	public static void delete(){
		
	}
	
	public static void printLevel(Node node, int level){
		
	}
	
	public static void printTree(Node node, int depth){
		
	}
	
	public static void main(String[] args) {
		

	}

}

class Node{
	private int data;
	private Node leftChild;
	private Node rightChild;
	
	public Node(char data){
		this.data = data;
	}
	
	public void setData(char data){
		this.data = data;
	}
	
	public int getData(){
		return data;
	}
	
	public void setLeftChild(Node leftChild){
		this.leftChild = leftChild;
	}
	
	public Node getLeftChild(){
		return leftChild;
	}
	
	public void setRightChild(Node rightChild){
		this.rightChild = rightChild;
	}
	
	public Node getRightChild(){
		return rightChild;
	}
	
	
	
}