package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;

import dao.CarroDAO;

import model.Carro;
import spark.Request;
import spark.Response;


public class CarroService {

	private CarroDAO carroDAO = new CarroDAO();
	private String form;

	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CODIGO = 1;
	private final int FORM_ORDERBY_DESCRICAO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public CarroService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Carro(), FORM_ORDERBY_CODIGO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Carro(), orderBy);
	}

	
	public void makeForm(int tipo, Carro carro, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umCarro = "";
		if(tipo != FORM_INSERT) {
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/produto/list/1\">Novo Carro</a></b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/produto/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Carro";
				nome = "";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + carro.getCodigo();
				name = "Atualizar Carro = "+carro.getNome();
				nome = carro.getNome();
				buttonLabel = "Atualizar";
			}
			umCarro += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ nome+"\"></td>";
			umCarro += "\t\t\t<td>&nbsp;Descrição: <input class=\"input--register\" type=\"text\" name=\"descricao\" value=\""+ carro.getDescricao()+"\"></td>";		
			umCarro += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ carro.getPreco() +"\"></td>";
			umCarro += "\t\t\t<td>Cor: <input class=\"input--register\" type=\"text\" name=\"cor\" value=\""+ carro.getCor() +"\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Carro (Codigo " + carro.getCodigo() + ")</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Nome: "+ carro.getNome() +"</td>";
			umCarro += "\t\t\t<td>&nbsp;Descrição: "+ carro.getDescricao() +"</td>";
			umCarro += "\t\t\t<td>Preço: "+ carro.getPreco() +"</td>";
			umCarro += "\t\t\t<td>Cor: "+ carro.getCor() +"</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Carro>", umCarro);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Carros</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_CODIGO + "\"><b>Codigo</b></a></td>\n" +
        		"\t<td><p>Nome</p></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_DESCRICAO + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><p>Cor</p></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Carro> carros;
		if (orderBy == FORM_ORDERBY_CODIGO) {                 	carros = carroDAO.getOrderByCodigo();
		} else if (orderBy == FORM_ORDERBY_DESCRICAO) {		carros = carroDAO.getOrderByDescricao();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			carros = carroDAO.getOrderByPreco();
		} else {											carros = carroDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Carro c : carros) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + c.getCodigo() + "</td>\n" +
            		  "\t<td>" + c.getNome() + "</td>\n" +
            		  "\t<td>" + c.getDescricao() + "</td>\n" +
            		  "\t<td>" + c.getCor() + "</td>\n" +
            		  "\t<td>" + c.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + c.getCodigo() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/update/" + c.getCodigo() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + c.getCodigo() +"', '" + c.getNome() +  "', '" + c.getDescricao() + "', '" + c.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CARRO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		int codigo;
		try {
			codigo=carroDAO.getRecentCarro()+1;
		} catch (Exception e) {
		codigo=1;
		}
		String nome = request.queryParams("nome");
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		String cor = request.queryParams("cor");
		
		String resp = "";
		
		Carro carro = new Carro(codigo, nome, cor, descricao, preco);
		
		if(carroDAO.insert(carro) == true) {
            resp = "Carro inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Carro não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Carro carro = (Carro) carroDAO.get(codigo);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, carro, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Carro não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Carro carro = (Carro) carroDAO.get(codigo);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, carro, FORM_ORDERBY_DESCRICAO);
        } else {
            response.status(404); // 404 Not found
            String resp = "carro não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":codigo"));
		Carro carro = carroDAO.get(codigo);
        String resp = "";       

        if (carro != null) {
        	carro.setNome(request.queryParams("nome"));
        	carro.setCor(request.queryParams("cor"));
        	carro.setDescricao(request.queryParams("descricao"));
        	carro.setPreco(Float.parseFloat(request.queryParams("preco")));
        	
        	carroDAO.update(carro);
        	response.status(200); // success
            resp = "Carro atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":codigo"));
       Carro carro = carroDAO.get(codigo);
        String resp = "";       
        if (carro != null) {
            carroDAO.delete(codigo);
            response.status(200); // success
            resp = "Carro (" + carro.getNome() + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}