package bancario.tsi;

import java.util.Scanner;

import persistencia.PersistenciaArquivo;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PersistenciaArquivo pa = new PersistenciaArquivo();
		Scanner sc = new Scanner(System.in);

		boolean sair = true;
		int opcao = 0;

		while (sair) {
			System.out.println("Escolha uma opção:\n1) Cadastrar um cliente;\n2) Opcoes para conta;\n3) Remover Cliente;\n4) Listar Clientes;\n5) Sair");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				String nome = "";
				String cpf = "";
				System.out.println("Insira o nome do cliente: ");
				nome = sc.next();
				System.out.println("Insira o CPF do cliente: ");
				cpf = sc.next();
				Cliente cli = new Cliente(cpf, nome);
				pa.cadastrarCliente(cli);
				break;
			
			case 2:
				System.out.println("Insira o CPF do cliente: ");
				String cpfConsulta = "";
				int segundaOpcao = 0;
				boolean segundoSair = true;
				cpfConsulta = sc.next();
				Cliente cliConsulta = pa.buscarClienteCPF(cpfConsulta);
				if (cliConsulta != null) {
					while (segundoSair) {
						System.out.println(
								"\n\n\n\nEscolha as opções para CONTA: \n1) Cadastrar uma conta;\n2) Remover uma conta;\n3) Listar contas;\n4) Realizar Deposito;\n5) Realizar saque;\n7) Consultar Saldo;\n6) Sair;");
						segundaOpcao = sc.nextInt();
						switch (segundaOpcao) {
						case 1:
							String numeroConta = "";
							System.out.println("Insira o número da conta");
							numeroConta = sc.next();
							Conta c1 = new Conta(numeroConta);
							cliConsulta.adicionarConta(c1);
							pa.atualizarCliente(cliConsulta);
							break;
						case 2:
							System.out.println("Insira o número da conta que deseja remover");
							numeroConta = sc.next();
							Conta c2= new Conta(numeroConta);
							cliConsulta.removerConta(c2);
							pa.atualizarCliente(cliConsulta);
							break;
						case 3:
							System.out.print(cliConsulta.getContas());
							break ;
						case 4:
							System.out.println("Insira o numero da sua conta:");
							numeroConta =sc.next();
							Conta c3 = new Conta(numeroConta);
							System.out.println("Insira o valor do seu depósito:");
							float valor = sc.nextFloat();
							if(cliConsulta.buscarConta(c3)!=null) {
								c3 = cliConsulta.buscarConta(c3);
								c3.realizarDeposito(valor);
								cliConsulta.atualizarConta(c3);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 5:
							System.out.println("Insira o número da sua conta:");
							numeroConta =sc.next();
							Conta c4 = new Conta(numeroConta);
							System.out.println("Insira o valor do seu saque:");
							float quantia = sc.nextFloat();
							if(cliConsulta.buscarConta(c4)!=null) {
								c4 = cliConsulta.buscarConta(c4);
								c4.realizarSaque(quantia);
								cliConsulta.atualizarConta(c4);
								pa.atualizarCliente(cliConsulta);
							}
							break;
						case 6:
							System.out.println("Insira o número da sua conta");
							numeroConta = sc.next();
							Conta c5 = new Conta(numeroConta);
							if(cliConsulta.buscarConta(c5) != null) {
								c5 = cliConsulta.buscarConta(c5);
								System.out.println(c5.saldo()); 
							}
							break;
						case 7:
							segundoSair = false;
							System.out.println("\n\n\n");
							break;
						default:
							sair = false;
							break;
						}
					}
				} else
					System.err.println("Cliente não encontrado! Tente novamente.");
				break;
			case 3:
				System.out.println("Insira o CPF do cliente: ");
				cpfConsulta = sc.next();
				Cliente cliConsulta2 = pa.buscarClienteCPF(cpfConsulta);
				if(cliConsulta2 != null) {
					pa.RemoverCliente(cliConsulta2);
				}
			break;
			case 4:
				pa.buscarCliente();
				break;
			case 5:
				System.out.println("Você saiu.");
				sair = false;
				break;
			default:
				
				break;
			}
			
		}
	}
}