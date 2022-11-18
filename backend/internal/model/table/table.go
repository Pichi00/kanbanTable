package table

type Table struct {
	ID            int
	name          string
	TaskGroup     []TaskGroup
	avaliableTags []Tag
}
