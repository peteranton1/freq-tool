// Package evaluator evaluator/evaluator_test.go
package btree

import (
	"freq/btree"
	"testing"
)

func TestEvalIntegerExpression(t *testing.T) {
	tests := []struct {
		input    []string
		expected []string
	}{
		{
		[]string{"5","3","9"},
		[]string{"9","5","3"},
		},
	}

	for _, tt := range tests {
		tree := testEval(tt.input)

        Print(os.Stdout, tree.root, 0, "M")
	}
}

func testEval(input []string) *BTree {
    tree := &BTree{}
    for _, val := range input {
    	tree.insert(val)
    }
    return tree
}
