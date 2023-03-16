package app;

import static spark.Spark.*;

import service.CarroService;



public class Aplicacao {
	
	private static CarroService carroService = new CarroService();
	
    public static void main(String[] args) {
        port(4567);
        
        staticFiles.location("/public");
        
        post("/produto/insert", (request, response) -> carroService.insert(request, response));

        get("/produto/:codigo", (request, response) -> carroService.get(request, response));
        
        get("/produto/list/:orderby", (request, response) -> carroService.getAll(request, response));

        get("/produto/update/:codigo", (request, response) -> carroService.getToUpdate(request, response));
        
        post("/produto/update/:codigo", (request, response) -> carroService.update(request, response));
           
        get("/produto/delete/:codigo", (request, response) -> carroService.delete(request, response));

             
    }
}