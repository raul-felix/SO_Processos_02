package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {
	
	public static void main(String[] args) {
		
		KillController killController = new KillController();
		
		String menu = "MENU: \n"
					+ "1 - Lista processos\n"
					+ "2 - Mata processo por PID\n"
					+ "3 - Mata processo por nome\n"
					+ "9 - FIM";
	
		String opc = "";
		
		do {
			opc = JOptionPane.showInputDialog(menu);
			
			switch (opc) {
				case "1":
					killController.listaProcessos();
					break;
				case "2":
					String pidStr = JOptionPane.showInputDialog("Digite o PID do processo:");
					killController.mataPid(pidStr);
					break;
				case "3":
					String nome = JOptionPane.showInputDialog("Digite o nome do processo:");
					killController.mataNome(nome);
					break;
				case "9":
					System.out.println("FIM DO PROGRAMA");
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA");
			}
		}
		while (!opc.equals("9"));
	}
}
