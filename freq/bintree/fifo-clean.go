package main

import (
	"container/list"
	"fmt"
)

func main() {
	vals := []string{"001", "002", "003", "004"}
	fifo := list.New()

	for _, val := range vals {
		fifo.PushBack(val)
	}

	for val := fifo.Front(); val != nil; val = val.Next() {
		fmt.Println(val.Value)
	}
}