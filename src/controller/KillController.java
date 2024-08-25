package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void listaProcessos() {
		String osName = os().toLowerCase();
		if (osName.contains("windows")) {
			readProcess("TASKLIST /FO TABLE");
		} else if (osName.contains("linux")) {
			readProcess("ps -ef");
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void mataPid(String pidStr) {
		String osName = os().toLowerCase();
		StringBuffer buffer = new StringBuffer();
		int pid = 0;
		if (osName.contains("windows")) {
			String cmdPid = "TASKKILL /PID";
			try {
				pid = Integer.parseInt(pidStr);
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(pid);
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
			callProcess(buffer.toString());
		} else if (osName.contains("linux")) {
			String cmdPid = "kill -9";
			try {
				pid = Integer.parseInt(pidStr);
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(pid);
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
			callProcess(buffer.toString());
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void mataNome(String nome) {
		String osName = os().toLowerCase();
		StringBuffer buffer = new StringBuffer();
		if (osName.contains("windows")) {
			String cmdNome = "TASKKILL /IM";
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(nome);
			callProcess(buffer.toString());
		} else if (osName.contains("linux")) {
			String cmdNome = "pkill -f";
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(nome);
			callProcess(buffer.toString());
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	public void callProcess(String proc) {
		try {
			Runtime.getRuntime().exec(proc.split(" "));
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(proc);
				try {
					Runtime.getRuntime().exec(buffer.toString().split(" "));					
				}catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			} else {
				System.err.println(msg);
			}
		}
	}
	
	public void readProcess(String proc) {
		try {
			Process p = Runtime.getRuntime().exec(proc.split(" "));
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
