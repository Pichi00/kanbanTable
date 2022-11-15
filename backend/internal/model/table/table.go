package table

type Table struct {
	ID            int
	name          string
	taskList      []TaskGroup
	avaliableTags []Tag
}
