package cr.ac.ucenfotec.componentes.examen.examen2.controllers;

import cr.ac.ucenfotec.componentes.examen.examen2.domain.Jugador;
import cr.ac.ucenfotec.componentes.examen.examen2.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class Principal {
    @Autowired
    JugadorService jugadorService;

    @RequestMapping("/")
    public String homePage(Model model){
        return "index";
    }
    @RequestMapping("/guardar")
    public String guardarPage(Model model){
        model.addAttribute(new Jugador());
        return "guardar";
    }
    @RequestMapping("/listar")
    public String listarPage(Model model){
        model.addAttribute("jugadores", jugadorService.listar());
        return "listar";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarAction(Jugador jugador, BindingResult result, Model model){
        jugadorService.insertarJugador(jugador);
        return "index";
    }

    @RequestMapping(value = "/jugador/desactivar/{id}")
    public String desactivarAction(Model model, @PathVariable long id){
        jugadorService.activarDesactivarJugador(id);
        return "index";
    }

    @RequestMapping(value = "/jugador/activar/{id}")
    public String activarAction(Model model, @PathVariable long id){
        jugadorService.activarDesactivarJugador(id);
        return "index";
    }

    @RequestMapping("/jugador/{id}")
    public String verPage(Model model, @PathVariable long id) {
        Optional<Jugador> jugador = jugadorService.get(id);
        if (jugador.isPresent()) {
            model.addAttribute("jugador", jugador.get());
            return "jugador";
        }
        return "notFound";
    }
}
