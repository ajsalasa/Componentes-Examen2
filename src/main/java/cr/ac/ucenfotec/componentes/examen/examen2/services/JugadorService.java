package cr.ac.ucenfotec.componentes.examen.examen2.services;

import cr.ac.ucenfotec.componentes.examen.examen2.domain.Jugador;
import cr.ac.ucenfotec.componentes.examen.examen2.repo.JugadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    @Autowired
    JugadorRepo jugadorRepo;
    public void insertarJugador(Jugador nuevo){
         jugadorRepo.save(nuevo);
    }

    public void activarDesactivarJugador(long id) {
        Optional<Jugador> jugadorOptional = jugadorRepo.findById(id);
        if (jugadorOptional.isPresent()) {
            Jugador jugador = jugadorOptional.get();
            jugador.setActivo(!jugador.getActivo());
            jugadorRepo.save(jugador);
        }
    }
    public List<Jugador> listar() {
        return jugadorRepo.findAll();
    }

    public Optional<Jugador> get(long id) {
        return  jugadorRepo.findById(id);
    }


}
