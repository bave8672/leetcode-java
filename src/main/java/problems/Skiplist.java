package problems;

import java.util.HashMap;

public class Skiplist {
    protected Node head = null;

    public boolean search(int target) {
        if (head == null) {
            return false;
        }
        return search(head, target, head.child.size());
    }

    public void add(int num) {
        if (head ==  null) {
            head = new Node(num);
            return;
        }
        if (num < head.value) {
            Node newHead = new Node(num);
            newHead.child = head.child;
            head.child = new HashMap<>();
            while (head.child.size() == 0 || Math.random() > 0.5) {
                Integer size = head.child.size();
                head.child.put(size, newHead.child.get(size));
                newHead.child.put(size, head);
            }
            head = newHead;
            return;
        }
        HashMap<Integer, Node> prevNodes = new HashMap<>();
        add(head, num, head.child.size(),  prevNodes);
    }

    public boolean erase(int num) {
        if (head == null) {
            return false;
        } if (head.value.equals(num)) {
            if (head.count > 1) {
                head.count--;
            } else {
                Node newHead = head.child.get(0);
                if (newHead != null) {
                    Integer i = 0;
                    while (head.child.get(i) != null && head.child.get(i).equals(newHead)) {
                        head.child.put(i, newHead.child.get(i));
                        i++;
                    }
                    newHead.child = head.child;
                }
                head = newHead;
            }
            return true;
        }
        HashMap<Integer, Node> prevNodes = new HashMap<>();
        return erase(head, num, head.child.size(), prevNodes);
    }

    private boolean erase(Node node, Integer value, Integer list, HashMap<Integer, Node> prevNodes) {
        if (node == null) {
            return false;
        }
        Node child = node.child.get(list);
        if (child == null && list == 0) {
            return false;
        }
        prevNodes.put(list, node);
        if (child != null && child.value.equals(value)) {
            if (child.count > 1) {
                child.count--;
                return true;
            } else if (list == 0) {
                for (Integer i = 0; i < Math.max(prevNodes.size(), child.child.size()); i++) {
                    Node prevNode = prevNodes.get(i);
                    if (prevNode == null) {
                        prevNode = head;
                    }
                    prevNode.child.put(i, child.child.get(i));
                }
                return true;
            }
        }
        if (list > 0 && node.value < value && ((child == null) || (child != null && child.value >= value))) {
            return erase(node, value, list - 1, prevNodes);
        }
        return erase(child, value, list, prevNodes);
    }

    private void add(Node node, Integer value, Integer list, HashMap<Integer, Node> prevNodes) {
        if (node == null) {
            return;
        }
        if (node.value.equals(value)) {
            node.count++;
            return;
        }
        Node child = node.child.get(list);
        if (node.value < value && ((child == null) || (child != null && child.value > value))) {
            prevNodes.put(list, node);
            if (list == 0) {
                Node newNode = new Node(value);
                while (list == 0 || Math.random() > 0.5) {
                    Node prevNode = prevNodes.get(list);
                    if (prevNode == null) {
                        prevNode = head;
                    }
                    newNode.child.put(list, prevNode.child.get(list));
                    prevNode.child.put(list, newNode);
                    list++;
                }
                return;
            }
            add(node, value, list - 1, prevNodes);
            return;
        }
        add(child, value, list, prevNodes);
    }

    private boolean search(Node node, Integer value, Integer list) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        Node child = node.child.get(list);
        if (list > 0 && node.value < value && ((child == null) || (child != null && child.value >= value))) {
            return search(node, value, list - 1);
        }
        return search(child, value, list);
    }

    protected class Node {
        public Integer count = 1;
        public Integer value;
        public HashMap<Integer, Node> child = new HashMap();

        public Node(Integer value) {
            this.value = value;
        }
    }
}
