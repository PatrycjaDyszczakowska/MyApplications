package com.praca_inzynierska_server.app;
import static spark.Spark.*;
import static com.praca_inzynierska_server.app.JsonUtil.*;

/**
 *Klasa odpowiadjąca za wyświetlanie informacji we wzorcu REST
 */
public class StartServer {
	/**
	 * @param serverTree zmienna odpowiadajaca za wywoływanie metod z klasy ServerTree
	 */
	public StartServer(final ServerTree serverTree) {
		/**
		 * Metoda odpowiadająca za wyświtlanie przez uzytkownika tekstu powitalnego
		 * 
		 */
		get("/server/", (request, response) -> "Witam w serwerze Aplikacji mobilnej do przeglądania i oceny kursów");
		/**
		 * Metoda odpowiadająca za wyświtlanie tablicy departamentów do klienta
		 */
		get("/server/departaments", (request, response) -> serverTree.getAllDep(), json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego wydziału przez klienta
		 */
		post("/server/departaments", (request, response) -> serverTree.createDep(
			Integer.parseInt(request.queryParams("id")),
			request.queryParams("name")
			), json());
		/**
		 * Metoda odpowiadająca za wyświtlanie tablicy kierunków do klienta
		 */
		get("/server/departaments/:specialization", (request, response) -> {
			Integer id = Integer.parseInt(request.params(":specialization"));
			Departaments dep = serverTree.getDep(id);
			return dep.getAllSpec();
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego kierunku przez klienta
		 */
		put("/server/departaments/:specialization",(request, response) -> serverTree.updateDep(
			Integer.parseInt(request.params(":specialization")),
			Integer.parseInt(request.queryParams("id")),
			request.queryParams("name")
		),json());
		/**
		 * Metoda odpowiadająca za wyświtlanie tablicy stopni studiowania do klienta
		 */
		get("/server/departaments/:specialization/:degree", (request, response) -> {
			Integer id = Integer.parseInt(request.params(":specialization"));
			Integer idSpec = Integer.parseInt(request.params(":degree"));
			Departaments dep = serverTree.getDep(id);
			Specializations spec = dep.getSpec(idSpec);
			return spec.getAllDeg();
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego stopnia studiowania przez klienta
		 */
		put("/server/departaments/:specialization/:degree",(request, response) -> serverTree.updateSpec(
			Integer.parseInt(request.params(":specialization")),
			Integer.parseInt(request.params(":degree")),
			Integer.parseInt(request.queryParams("id")),
			request.queryParams("name")
		),json());
		/** 
		 * Metoda odpowiadająca za wyświtlanie tablicy kursów do klienta
		 */
		get("/server/departaments/:specialization/:degree/:course", (request, response) -> {
			Integer id = Integer.parseInt(request.params(":specialization"));
			Integer idSpec = Integer.parseInt(request.params(":degree"));
			Integer idDeg = Integer.parseInt(request.params(":course"));
			Departaments dep = serverTree.getDep(id);
			Specializations spec = dep.getSpec(idSpec);
			Degree deg = spec.getDeg(idDeg);
			return deg.getAllCourse();
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego kursu przez klienta
		 */
		put("/server/departaments/:specialization/:degree/:course",(request, response) -> serverTree.updateDeg(
			Integer.parseInt(request.params(":specialization")),
			Integer.parseInt(request.params(":degree")),
			Integer.parseInt(request.params(":course")),
			Integer.parseInt(request.queryParams("id")),
			request.queryParams("name")
		),json());
		/**
		 * Metoda odpowiadająca za wyświtlanie tablicy semestrów do klienta
		 */
		get("/server/departaments/:specialization/:degree/:course/:semester", (request, response) -> {
			Integer id = Integer.parseInt(request.params(":specialization"));
			Integer idSpec = Integer.parseInt(request.params(":degree"));
			Integer idDeg = Integer.parseInt(request.params(":course"));
			Integer idCourse = Integer.parseInt(request.params(":semester"));
			Departaments dep = serverTree.getDep(id);
			Specializations spec = dep.getSpec(idSpec);
			Degree deg = spec.getDeg(idDeg);
			Course course = deg.getCourse(idCourse);
			return course.getAllSemester();
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego semestru przez klienta
		 */
		put("/server/departaments/:specialization/:degree/:course/:semester",(request, response) -> serverTree.updateCourse(
			Integer.parseInt(request.params(":specialization")),
			Integer.parseInt(request.params(":degree")),
			Integer.parseInt(request.params(":course")),
			Integer.parseInt(request.params(":semester")),
			Integer.parseInt(request.queryParams("id")),
			request.queryParams("name")
		),json());
		/**
		 * Metoda odpowiadająca za wyświtlanie tablicy opinii do klienta
		 */
		get("/server/departaments/:specialization/:degree/:course/:semester/:listofreviews", (request, response) -> {
			Integer id = Integer.parseInt(request.params(":specialization"));
			Integer idSpec = Integer.parseInt(request.params(":degree"));
			Integer idDeg = Integer.parseInt(request.params(":course"));
			Integer idCourse = Integer.parseInt(request.params(":semester"));
			Integer idSem = Integer.parseInt(request.params(":listofreviews"));
			Departaments dep = serverTree.getDep(id);
			Specializations spec = dep.getSpec(idSpec);
			Degree deg = spec.getDeg(idDeg);
			Course course = deg.getCourse(idCourse);
			Semester sem = course.getSemester(idSem);
			return sem.getAllListOfRev();
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowej opinii przez klienta
		 */
		put("/server/departaments/:specialization/:degree/:course/:semester/:listofreviews",(request, response) -> serverTree.updateSemester(
			Integer.parseInt(request.params(":specialization")),
			Integer.parseInt(request.params(":degree")),
			Integer.parseInt(request.params(":course")),
			Integer.parseInt(request.params(":semester")),
			Integer.parseInt(request.params(":listofreviews")),
			Integer.parseInt(request.queryParams("id")),
			Integer.parseInt(request.queryParams("rate")),
			request.queryParams("nameUser"),
			request.queryParams("name"),
			request.queryParams("review")
		),json());
		/**
		 * Metoda odpowiadająca za wyświtlanie dane uzytkownika do klienta
		 */
		get("/server/user/:id",(request, response) -> {
			String id = request.params(":id");
			User user = serverTree.getUser(id);
			return user;
		},json());
		/**
		 * Metoda odpowiadająca za dodawanie nowego użytkownika przez klienta
		 */
		post("/server/user",(request, response)->serverTree.createUser(
				request.queryParams("name"),
				request.queryParams("password")
		),json());
		/**
		 * Metoda aktualizująca dane o użytkowniku przez klienta
		 */
		put("/server/user/:id",(request, response)->{
			String id = request.params(":id");
			Boolean acc = Boolean.parseBoolean(request.queryParams("acc"));
			User user = serverTree.getUser(id);
			user.setAcc(acc);
			return user;
		},json());
		/**
		 * Metoda wyświtlająca typ aplikacji w logach 
		 */
		after((req, res) -> {
			res.type("application/json");
		});
		/**
		 * Metoda wyrzucająca status 400 gdy klient wpisze złą metode
		 */
		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
		});
	}

}
