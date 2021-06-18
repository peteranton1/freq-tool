package btree

import (
    "fmt"
    "os"
    "strings"
    "io"
)

type BTreeValue struct {
    value string
    count int64
}

type BTreeNode struct {
    data *BTreeValue
    left  *BTreeNode
    right *BTreeNode
}

type BTree struct {
    root *BTreeNode
}

func newNode(value string) *BTreeNode {
    return &BTreeNode{
               data: &BTreeValue {
                   value: value,
                   count: 1,
               },
               left: nil,
               right: nil,
           }
}

func (t *BTree) insert(value string) *BTree {
    if t.root == nil {
        t.root = newNode(value)
    } else {
        t.root.insert(value)
    }
    return t
}

func (n *BTreeNode) insert(value string) {
    if n == nil {
        return
    } else if strings.Compare(value, n.data.value) < 0 {
        // Less than
        if n.left == nil {
            n.left = newNode(value)
        } else {
            n.left.insert(value)
        }

    } else if strings.Compare(value, n.data.value) > 0 {
        // Greater than
        if n.right == nil {
            n.right = newNode(value)
        } else {
            n.right.insert(value)
        }

    } else {
        // Equal
        n.data.count = n.data.count + 1
    }
}

func print(w io.Writer, node *BTreeNode, ns int, ch string) {
    if node == nil {
        return
    }

    for i := 0; i < ns; i++ {
        fmt.Fprint(w, " ")
    }
    fmt.Fprintf(w, "%v:%v\n", ch, node.data)
    print(w, node.left, ns+2, "L")
    print(w, node.right, ns+2, "R")
}

func Show() {
    tree := &BTree{}
    tree.insert("b100").
        insert("a020").
		insert("a050").
		insert("a015").
		insert("a060").
        insert("b050").
		insert("b060").
		insert("b055").
		insert("b055").
		insert("b055").
        insert("b085").
        insert("b085").
		insert("b015").
		insert("b005").
        insert("a010")
    print(os.Stdout, tree.root, 0, "M")
}