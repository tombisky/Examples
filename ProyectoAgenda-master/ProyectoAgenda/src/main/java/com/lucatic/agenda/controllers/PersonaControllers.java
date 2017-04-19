package com.lucatic.agenda.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lucatic.agenda.beans.Categoria;
import com.lucatic.agenda.beans.Direccion;
import com.lucatic.agenda.beans.Persona;
import com.lucatic.agenda.beans.Telefono;
import com.lucatic.agenda.dao.PersonaDAOImpl;
import com.lucatic.agenda.servicios.CategoriaService;
import com.lucatic.agenda.servicios.DepartamentoService;
import com.lucatic.agenda.servicios.DireccionService;
import com.lucatic.agenda.servicios.EmpleadoService;
import com.lucatic.agenda.servicios.PersonaService;
import com.lucatic.agenda.servicios.Service;
import com.lucatic.agenda.servicios.TelefonoService;




@Controller
public class PersonaControllers {
	
	@Autowired
	private PersonaService servicePersona;
	private CategoriaService serviceCategoria;
	private TelefonoService serviceTelefono;
	private EmpleadoService serviceEmpleado;
	private DepartamentoService serviceDepartamento;
	private DireccionService serviceDireccion;
	
	
	
	
	@RequestMapping("/")
	public ModelAndView Index() throws Exception {
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	

	@RequestMapping("/listado")
	public ModelAndView handleRequest() throws Exception {
		
		List<Persona> personas =  new ArrayList<Persona>();
		personas = servicePersona.list();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listado", personas);
		return model;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newPersona() {
		ModelAndView model = new ModelAndView("listado");
		model.addObject("persona", new Persona());
		return model;		
	}
	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editContacto(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("modificar");
		model.addObject("persona", new Persona());
		model.addObject("direccion", new Direccion());
		model.addObject("telefono", new Telefono());
		return model;		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int personaId = Integer.parseInt(request.getParameter("id"));
		servicePersona.delete(personaId);
		return new ModelAndView("redirect:/");		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute Persona persona,@ModelAttribute Telefono telefono,@ModelAttribute Direccion direccion) {
		persona.getTelefonoses().add(telefono);
		persona.getDireccioneses().add(direccion);
		servicePersona.saveOrUpdate(persona);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(@ModelAttribute String nombre) {
		Persona findPersona=servicePersona.getNombre(nombre).get(0);
		ModelAndView model = new ModelAndView("redirect:/buscador");
		model.addObject("detalle_contacto", findPersona);
		return model;
	}

	@RequestMapping(value = "/detalle", method = RequestMethod.GET)
	public ModelAndView DetalleContacto(HttpServletRequest request) {
		int personaId = Integer.parseInt(request.getParameter("idpersonas"));
		List<Persona> listPersona = servicePersona.list();
		Persona persona=listPersona.get(personaId-1);
		ModelAndView model = new ModelAndView("detalle_contacto");
		Set <Direccion> d = persona.getDireccioneses();
		Set <Telefono> t = persona.getTelefonoses();
		model.addObject("persona", persona);
		model.addObject("direccion", d);
		model.addObject("telefono", t);
		
		return model;
	}
	
}

