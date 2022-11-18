package user

import (
	"main/internal/model/table"
)

type User struct {
	ID       int
	name     string
	email    string
	password string
	tables   []table.Table
}
