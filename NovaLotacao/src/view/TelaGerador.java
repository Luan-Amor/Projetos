package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;



public class TelaGerador extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String caminho;
	private JFileChooser diretorio = new JFileChooser();
	private JFrame janela = new JFrame("Script de lotação - TRF");
	private JButton btnArmazenar = new JButton("Seleciona");
	private JButton btnGerar = new JButton("Gerar");
	private JLabel labelModelo = new JLabel("Lotação Modelo:");
	private JLabel cabecalho = new JLabel("Gerador de Script");
	private JLabel labelLotacao = new JLabel("Nova lotação:");
	private JTextField txtModelo = new JTextField("406040");
	private JFormattedTextField txtLotacao = new JFormattedTextField(new MaskFormatter("######")); //Quantidade de dígitos permitidos no campo lotação
	

	public TelaGerador() throws ParseException {
		super();
		criarTela();
	}
	
	public void criarTela(){
		botaoArmazenar();
		cabecalho();
		framePrincipal();
		lblModelo();
		lblLotacao();
		txtModelo();
		txtLotacao();
		btnGerarScript();
	}
	

	private void botaoArmazenar() {
		btnArmazenar.setBounds(new Rectangle(new Point(400, 300), new Dimension(120, 25)));
		btnArmazenar.setLocation(170, 180);
		btnArmazenar.addActionListener(new botaoArmazenar());
		janela.add(btnArmazenar);
		
	}

	private void cabecalho() {
		Font font = new Font("SansSerif", Font.BOLD, 20);
		cabecalho.setBounds(new Rectangle(new Point(120, 140), new Dimension(300, 25)));
		cabecalho.setFont(font);
		cabecalho.setLocation(80, 30);
		janela.add(cabecalho);
	}

	

	private void framePrincipal() {
		janela.setSize(350, 250);
		janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
		
		
	}

	
	private void btnGerarScript() {
		
		btnGerar.setBounds(new Rectangle(new Point(400, 300), new Dimension(120, 25)));
		btnGerar.setLocation(40, 180);
		btnGerar.setVisible(false);
		btnGerar.addActionListener(new botaoGerarScript());
		janela.add(btnGerar);
		
		
	}

	private void txtLotacao() {
		txtLotacao.setSize(80, 20);
		txtLotacao.setLocation(210, 135);
		janela.add(txtLotacao);
		
	} 

	private void txtModelo() {
		txtModelo.setSize(80, 20);
		txtModelo.setLocation(210, 95);
		janela.add(txtModelo);
		
	}

	private void lblLotacao() {
		Font font = new Font("SansSerif", Font.BOLD, 15);
		labelLotacao.setBounds(new Rectangle(new Point(120, 140), new Dimension(300, 25)));
		labelLotacao.setFont(font);
		labelLotacao.setLocation(40, 132);
        janela.add(labelLotacao);

		
	}

	public void lblModelo(){
		Font font = new Font("SansSerif", Font.BOLD, 15);
		labelModelo.setBounds(new Rectangle(new Point(120, 140), new Dimension(300, 25)));
		labelModelo.setFont(font);
		labelModelo.setLocation(40, 92);
		janela.add(labelModelo);
	}
	
    public class botaoGerarScript implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	GeraScript g = new GeraScript();
        	int mod = Integer.parseInt(txtModelo.getText());
        	int lot = Integer.parseInt(txtLotacao.getText());
    		try {
				g.gerarScript(lot, mod, caminho);
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
}

    public class botaoArmazenar implements ActionListener {
    	 @Override
         public void actionPerformed(ActionEvent e) {
    		 diretorio.setCurrentDirectory(new File("D://"));
    	     diretorio.setDialogTitle("Escolha onde será armazenado");
    	     diretorio.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);
    	     diretorio.showSaveDialog(null);
    	     btnGerar.setVisible(true);
    	     caminho = diretorio.getSelectedFile().getAbsolutePath();
    	     
    	 }
    }
	
}
