package server

import (
	"net/http"

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	log "github.com/sirupsen/logrus"
)

type Server interface {
	Start() error
}
type server struct {
	router  *mux.Router
	address string
}

func New(address string) Server {
	serv := server{
		router:  mux.NewRouter(),
		address: address,
	}

	serv.router.HandleFunc("/table/{id_table}", serv.RandomHandler).Methods("POST", "GET", "PUT", "DELETE")
	serv.router.HandleFunc("/taskGroup/{id_taskGroup}", serv.RandomHandler).Methods("POST", "GET", "PUT", "DELETE")
	serv.router.HandleFunc("/task/{id_task}", serv.RandomHandler).Methods("POST", "GET", "PUT", "DELETE")
	serv.router.HandleFunc("/tag/{id_tag}", serv.RandomHandler).Methods("POST", "GET", "PUT", "DELETE")

	return serv
}

func (s server) Start() error {
	log.WithFields(log.Fields{
		"ip": s.address,
	}).Info("Launching the server.")

	corsObj := handlers.AllowedOrigins([]string{"*"})

	return http.ListenAndServe(s.address, handlers.CORS(corsObj)(s.router))
}
