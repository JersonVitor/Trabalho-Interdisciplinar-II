package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.CarroDAO;
import model.Carro;

public class Aplicacao {
	public static Scanner sc =new Scanner(System.in);
	public static int opcao(){
		int opcao;
		boolean erro;
		do {
			System.out.println("Digite sua opcao:");
			System.out.println("1) Inserir");
			System.out.println("2) Listar");
			System.out.println("3)Atualizar");
			System.out.println("4)Excluir");
			System.out.println("5)sair");
			opcao = sc.nextInt();
			erro=opcao<1&&opcao>5;
			if(erro)System.out.println("Opcao invalida, tente outra opcao!!");
		}while(erro);
		return opcao;
	}
	public static void main(String[] args) throws Exception {
		
		
		int opc;
		do {
			opc=opcao();
			switch(opc) {
			case 1: inserir();
			break;
			case 2: listar();
			break;
			case 3: atualizar();
			break;
			case 4: excluir();
			break;
			case 5: System.out.println("Até mais!!");
			break;
			}
		}while(opc!=5);
	}
	public static void inserir() {
		CarroDAO carroDAO = new CarroDAO();
		System.out.println("\n\n==== Inserir carro === ");
		System.out.print("Digite o codigo do carro: ");
		int codigo = sc.nextInt();
		System.out.print("Digite o nome do carro: ");
		String nome = sc.next();
		System.out.print("Digite a cor do carro: ");
		String cor= sc.next();
		System.out.print("Digite o preco do carro: ");
		float preco=sc.nextFloat();
		System.out.print("Digite ");
		Carro carro = new Carro(codigo, nome, cor,preco);
		
		if(carroDAO.insert(carro) == true) {
			
				System.out.println("Inserção com sucesso -> " + carro.toString());
			
		}
	}
		
		public static void listar() {
		CarroDAO carroDAO = new CarroDAO();
		System.out.println("\n\n==== Mostrar Carros ordenados por código === ");
		List<Carro> carros = carroDAO.getOrderByCodigo();
		for (Carro c: carros) {
			System.out.println(c.toString());
		}
	}
		
		public static void excluir() {
		CarroDAO carroDAO = new CarroDAO();
		System.out.println("\n\n==== Excluir Carro === ");
		listar();
		System.out.print("Digite o codigo que deseja apagar:");
		int codigo = sc.nextInt();
		carroDAO.delete(codigo);
		
	}
		public static void atualizar() {
			CarroDAO carroDAO = new CarroDAO();

			System.out.println("\n\n==== Atualizar Carro === ");
			listar();
			System.out.print("Digite o codigo do carro que deseja atualizar: ");
			int codigo=sc.nextInt();
			System.out.print("Digite o nome do carro : ");
			String nome=sc.next();
			System.out.print("Digite o cor do carro : ");
			String cor =sc.next();
			System.out.print("Digite o preco do carro : ");
			float preco =sc.nextFloat();
			Carro carro = new Carro(codigo,nome,cor,preco);
			carroDAO.update(carro);
		}
}
	
	
